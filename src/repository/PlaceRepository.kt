package fr.find.repository




import fr.find.dataclass.PlaceData
import fr.find.dataclass.PlaceUserIdData
import fr.find.entity.Place
import fr.find.entity.User_findme
import fr.find.entity.User_itinerary
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.isNotNull
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.transaction
import java.sql.ResultSet

class PlaceRepository {

    fun getAll(): ArrayList<PlaceData>{
        val places : ArrayList<PlaceData> = arrayListOf()
        transaction {
            Place.selectAll().map {
                places.add(
                    PlaceData(
                        id = it[Place.id],
                        name = it[Place.name],
                        latitude = it[Place.latitude],
                        longitude = it[Place.longitude],
                        difficulty = it[Place.difficulty],
                        radius_type = it[Place.radius_type],
                        active = it[Place.active]
                    )
                )
            }
        }
        return places
    }

    fun getAllPlaceAndUserVisited(id: Int): ArrayList<PlaceUserIdData> {
        val places : ArrayList<PlaceUserIdData> = arrayListOf()
        transaction {
            val selectJoin = (User_itinerary innerJoin User_findme).slice(User_itinerary.place_id, User_findme.id).select { User_findme.id eq id }.alias("patterns")
            Place.join(
                selectJoin,
                JoinType.LEFT,
                additionalConstraint = { Place.id eq selectJoin[User_itinerary.place_id] })
                .slice(Place.id, Place.name, Place.latitude, Place.longitude, Place.difficulty, Place.radius_type, Place.active, SqlExpressionBuilder.case()
                    .When(selectJoin[User_findme.id].isNotNull(), Op.TRUE).Else (Op.FALSE).alias("visited"))
                .select { Place.active eq true }
                .map{
                places.add(
                    PlaceUserIdData(
                        id = it[Place.id],
                        name = it[Place.name],
                        latitude = it[Place.latitude],
                        longitude = it[Place.longitude],
                        difficulty = it[Place.difficulty],
                        radius_type = it[Place.radius_type],
                        active = it[Place.active],
                        visited = it[SqlExpressionBuilder.case()
                            .When(selectJoin[User_findme.id].isNotNull(), Op.TRUE).Else (Op.FALSE).alias("visited")]
                    )
                )
            }
        }
        return places
    }
}