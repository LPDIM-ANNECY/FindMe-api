package fr.find.repository




import fr.find.dataclass.PlaceData
import fr.find.dataclass.PlaceUserIdData
import fr.find.dataclass.PlaceWithCategory
import fr.find.entity.Category
import fr.find.entity.Place
import fr.find.entity.User_findme
import fr.find.entity.User_itinerary
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.SqlExpressionBuilder.isNotNull
import org.jetbrains.exposed.sql.transactions.transaction

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
                        active = it[Place.active],
                        description = it[Place.description],
                        picture = it[Place.picture]
                    )
                )
            }
        }
        return places
    }

    fun getPlaceById(id: Int): ArrayList<PlaceData> {
        val place : ArrayList<PlaceData> = arrayListOf()
        transaction {
            Place.select { Place.id eq id }.map {
                place.add(
                    PlaceData(
                        id = it[Place.id],
                        name = it[Place.name],
                        latitude = it[Place.latitude],
                        longitude = it[Place.longitude],
                        difficulty = it[Place.difficulty],
                        radius_type = it[Place.radius_type],
                        active = it[Place.active],
                        description = it[Place.description],
                        picture = it[Place.picture]
                    )
                )
            }
        }
        return place
    }


    fun getAllPlaceAndUserVisited(id: Int): ArrayList<PlaceUserIdData> {
        val places : ArrayList<PlaceUserIdData> = arrayListOf()
        transaction {
            val selectJoin = (User_itinerary innerJoin User_findme).slice(User_itinerary.place_id, User_findme.id).select { User_findme.id eq id }.alias("patterns")
            Place.join(
                selectJoin,
                JoinType.LEFT,
                additionalConstraint = { Place.id eq selectJoin[User_itinerary.place_id] })
                .slice(Place.id, Place.name, Place.latitude, Place.longitude, Place.difficulty, Place.radius_type, Place.active, Place.description, Place.category_id, SqlExpressionBuilder.case()
                    .When(selectJoin[User_findme.id].isNotNull(), Op.TRUE).Else (Op.FALSE).alias("visited"))
                .selectAll()
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
                        description = it[Place.description],
                        picture = it[Place.picture],
                        category_id = it[Place.category_id],
                        visited = it[SqlExpressionBuilder.case()
                            .When(selectJoin[User_findme.id].isNotNull(), Op.TRUE).Else (Op.FALSE).alias("visited")]
                    )
                )
            }
        }
        return places
    }

    fun getPlaceWithCategory(name: String): ArrayList<PlaceWithCategory>{
        val places : ArrayList<PlaceWithCategory> = arrayListOf()
        transaction {
          (Place innerJoin Category)
              .select { Category.name eq name }
              .map {
                  places.add(
                      PlaceWithCategory(
                          id = it[Place.id],
                          name = it[Place.name],
                          latitude = it[Place.latitude],
                          longitude = it[Place.longitude],
                          difficulty = it[Place.difficulty],
                          radius_type = it[Place.radius_type],
                          active = it[Place.active],
                          description = it[Place.description],
                          picture = it[Place.picture],
                          category_id = it[Place.category_id],
                          category_name = it[Category.name]
                      )
                  )
              }
        }
        return places
    }
}