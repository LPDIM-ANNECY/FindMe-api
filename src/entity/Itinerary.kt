package fr.find.entity

import fr.find.entity.Category.autoIncrement
import fr.find.entity.Category.uniqueIndex
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.`java-time`.datetime

object Itinerary: Table() {
    val id = integer("id").autoIncrement().uniqueIndex()
    val name = varchar("name", 255)
    val duration = float("duration")
    val length = float("length")
    val active = bool("active")
    override val primaryKey = PrimaryKey(Itinerary.id, name = "PK_Itinerary_Id")
}