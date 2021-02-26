package fr.find.entity

import org.jetbrains.exposed.sql.Table

object Place: Table() {
    val id = integer("id").autoIncrement().uniqueIndex()
    val name = varchar("name", 30)
    val latitude = float("latitude")
    val longitude = float("longitude")
    val difficulty  = integer("difficulty")
    val radius_type = varchar("radius_type", 10)
    val active = bool("active")
    override val primaryKey = PrimaryKey(id, name = "PK_Place_Id")
}