package fr.find.repository

import fr.find.dataclass.PlaceData
import fr.find.dataclass.UserItineraryData
import fr.find.dataclass.UserWithIdData
import fr.find.entity.Place
import fr.find.entity.User_itinerary
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

class UserItineraryRepository {
    fun getUserItineraryById(userId: Int, placeId: Int): ArrayList<UserItineraryData> {
        val userItinerary : ArrayList<UserItineraryData> = arrayListOf()
        transaction {
            User_itinerary.select { User_itinerary.user_id eq userId and (User_itinerary.place_id eq placeId) }.map {
                userItinerary.add(
                    UserItineraryData(
                        user_id = it[User_itinerary.user_id],
                        place_id = it[User_itinerary.place_id]
                    )
                )
            }
        }
        return userItinerary
    }

    fun addUserItinerary(userId: Int, placeId: Int): ArrayList<UserItineraryData> {
        transaction {
            User_itinerary.insert {
                it[user_id] = userId
                it[place_id] = placeId
            }
        }
        return getUserItineraryById(userId, placeId)
    }
}