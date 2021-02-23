package fr.management.dataclass

import kotlinx.serialization.Serializable

@Serializable
data class User_itinerary(val user_id: Int,
                          val place_id: Int)
