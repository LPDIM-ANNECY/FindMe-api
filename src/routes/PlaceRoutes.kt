package fr.find.routes

import fr.find.repository.PlaceRepository
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*

val place_repository = PlaceRepository()

fun Application.registerPlaceRoutes() {
    routing {
        PlacesRoute()
    }
}

fun Route.PlacesRoute(){
    route("/places"){
        get("/"){
            call.respond(place_repository.getAll())
        }
        get("/{id}"){
            val id: Int? = call.parameters["id"]?.toInt()

            if (id != null)
                call.respond(place_repository.getPlaceById(id))
            else
                call.respondText("id is null")
        }
        get("users/{id}"){
            val id: Int? = call.parameters["id"]?.toInt()

            if (id != null)
                call.respond(place_repository.getAllPlaceAndUserVisited(id))
            else
                call.respondText("id is null")
        }
        get("/category/{name}"){
            val name: String? = call.parameters["name"]

            println(name)

            if (name != null)
                call.respond(place_repository.getPlaceWithCategory(name.toString()))
            else
                call.respondText("id is null")
        }
    }
}