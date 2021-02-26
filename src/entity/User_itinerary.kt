package fr.find.entity

import org.jetbrains.exposed.sql.Table

object User_itinerary: Table() {
    val user_id  = reference("user_id", User_findme.id)
    val place_id  = reference("place_id", Place.id)
    override val primaryKey = PrimaryKey(user_id, place_id, name = "PK_User_itinerary")
}