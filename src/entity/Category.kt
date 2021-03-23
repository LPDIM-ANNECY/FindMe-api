package fr.find.entity

import org.jetbrains.exposed.sql.Table

object Category: Table() {
    val id = integer("id").autoIncrement().uniqueIndex()
    val name = varchar("name", 255)
    override val primaryKey = PrimaryKey(id, name = "PK_Category_Id")
}