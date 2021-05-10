package fr.find.routes

import fr.find.repository.UserFindmeRepository
import fr.find.repository.UserItineraryRepository
import io.ktor.application.Application
import io.ktor.routing.*

import io.ktor.http.HttpStatusCode
import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.respond
import io.ktor.response.respondText
import java.lang.NumberFormatException


val userItinerayRepository = UserItineraryRepository()

fun Application.registerUserItineraryRoutes() {
    routing {
        getUserItineraryRoute()
    }
}

fun Route.getUserItineraryRoute() {
    route("/userItinerary"){
        post("/visited"){
            val parameters = call.receiveParameters()

            val userId = try {
                 parameters["userId"]?.toInt()
            } catch (e: NumberFormatException){
                null
            }

            val placeId = try {
                parameters["placeId"]?.toInt()
            } catch (e: NumberFormatException){
                null
            }

            if (userId != null && placeId != null) {
                val userItinerary = userItinerayRepository.getUserItineraryById(userId, placeId)
                if (!userItinerary.isEmpty()){
                    call.respondText("user itinerary already exists", status = HttpStatusCode.Conflict)
                } else {
                    call.respond(status = HttpStatusCode.Created, userItinerayRepository.addUserItinerary(userId, placeId))
                }
            }


        }
    }
}
