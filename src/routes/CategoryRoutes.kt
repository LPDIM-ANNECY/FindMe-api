package fr.find.routes

import fr.find.repository.CategoryRepository
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*

val category_repository = CategoryRepository()

fun Application.registerCategoriesRoutes() {
    routing {
        CategoriesRoute()
    }
}

fun Route.CategoriesRoute(){
    route("/categories"){
        get("/"){
            call.respond(category_repository.getAll())
        }
    }
}