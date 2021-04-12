package fr.find.entity

import org.jetbrains.exposed.sql.Table

object Place: Table() {
    val id = integer("id").autoIncrement().uniqueIndex()
    val name = varchar("name", 30)
    val latitude = float("latitude")
    val longitude = float("longitude")
    val difficulty  = integer("difficulty")
    val radius_type = integer("radius_type")
    val active = bool("active")
    val description = text("description")
    val category_id = reference("category_id", Category.id)
    override val primaryKey = PrimaryKey(id, name = "PK_Place_Id")
}