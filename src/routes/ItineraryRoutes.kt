package fr.find.routes

import fr.find.repository.ItineraryRepository
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*

val itinerary_repository = ItineraryRepository()

fun Application.registerItineraryRoutes() {
    routing {
        ItinerariesRoute()
    }
}

fun Route.ItinerariesRoute() {
    route("/itineraries") {
        get("/") {
            call.respond(itinerary_repository.getAll())
        }

        get("/{id}"){
            val id: Int? = call.parameters["id"]?.toInt()

            if (id != null)
                call.respond(itinerary_repository.getItineraryPlaces(id.toInt()))
            else
                call.respondText("id is null")
        }
    }
}