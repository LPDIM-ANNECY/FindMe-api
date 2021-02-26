package fr.find.entity

import fr.find.entity.Place.uniqueIndex
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.`java-time`.date
import java.time.LocalDate

object User_findme: Table() {
    val id = integer("id").autoIncrement().uniqueIndex()
    val email = varchar("email", 50)
    val password = varchar("password", 255)
    val created_at = date("created_at").default(LocalDate.now())
    val updated_at  = date("updated_at").nullable().default(null)
    val locale = varchar("locale", 50)
    val enable = bool("enable")
    val level = varchar("level", 50)
    val total_distance = float("total_distance")
    val picture = text("picture")
    val pseudo = varchar("pseudo", 15)
    override val primaryKey = PrimaryKey(id, name = "PK_User_findme_Id")
}