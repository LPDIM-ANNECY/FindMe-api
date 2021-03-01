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
        get("users/{id}"){
            val id = call.parameters["id"]

            if (id != null)
                call.respond(place_repository.getAllPlaceAndUserVisited(id.toInt()))
            else
                call.respondText("id is null")
        }
    }
}