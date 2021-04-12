package fr.find.repository

import fr.find.dataclass.CategoryData
import fr.find.entity.Category
import fr.find.entity.Place
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class CategoryRepository {

    fun getAll(): ArrayList<CategoryData>{
        val categories : ArrayList<CategoryData> = arrayListOf()
        transaction {
            Category.selectAll().map {
                categories.add(
                    CategoryData(
                        id = it[Category.id],
                        name = it[Category.name]
                    )
                )
            }
        }
        return categories
    }
}