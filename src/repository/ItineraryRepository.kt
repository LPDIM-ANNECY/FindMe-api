package fr.find.repository

import fr.find.dataclass.ItineraryData
import fr.find.dataclass.PlaceData
import fr.find.entity.Itinerary
import fr.find.entity.Itinerary_place
import fr.find.entity.Place
import org.jetbrains.exposed.sql.JoinType
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class ItineraryRepository {

    fun getAll(): ArrayList<ItineraryData>{
        val itineraries : ArrayList<ItineraryData> = arrayListOf()
        transaction {
            Itinerary.selectAll().map {
                itineraries.add(
                    ItineraryData(
                        id = it[Itinerary.id],
                        name = it[Itinerary.name],
                        duration = it[Itinerary.duration],
                        length = it[Itinerary.length],
                        active = it[Itinerary.active]
                    )
                )
            }
        }
        return itineraries
    }

    fun getItineraryPlaces(id: Int): ArrayList<PlaceData>{
        val places : ArrayList<PlaceData> = arrayListOf()
        transaction {
            Itinerary
                .join(Itinerary_place, JoinType.INNER, additionalConstraint = {Itinerary.id eq Itinerary_place.itinerary_id})
                .join(Place, JoinType.INNER, additionalConstraint = {Place.id eq Itinerary_place.place_id})
                .selectAll().map {
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
}