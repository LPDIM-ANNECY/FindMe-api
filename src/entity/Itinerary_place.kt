package fr.find.entity

import org.jetbrains.exposed.sql.Table

object Itinerary_place: Table() {
    val itinerary_id = reference("itinerary_id", Itinerary.id)
    val place_id = reference("place_id", Place.id)
    override val primaryKey = PrimaryKey(itinerary_id, place_id, name = "PK_Itinerary_place")
}