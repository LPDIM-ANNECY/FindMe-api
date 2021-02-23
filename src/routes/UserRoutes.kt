package fr.management.routes

import fr.management.dataclass.User
import io.ktor.application.Application
import io.ktor.routing.*

import io.ktor.http.HttpStatusCode
import io.ktor.application.call
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.*


fun Application.registerOrderRoutes() {
    routing {
        listUsersRoute()
        getUserRoute()
        getUserRoutePlaces()
        postUserRoute()
    }
}


fun Route.listUsersRoute() {
    get("/users") {
        call.respond("SELECT * FROM User_findme")
    }
}
fun Route.getUserRoute() {
    get("/user/{id}") {
        val id = call.parameters["id"] ?: return@get call.respondText("Bad Request", status = HttpStatusCode.BadRequest)
        val user =  "SELECT * FROM User_findme WHERE id =$id" ?: return@get call.respondText(
            "Not Found",
            status = HttpStatusCode.NotFound
        )
        call.respond(user)
    }
}

fun Route.getUserRoutePlaces() {
    get("/user/{id}/places") {
           val id = call.parameters["id"] ?: return@get call.respondText("Bad Request", status = HttpStatusCode.BadRequest)
           val allPlaces = "SELECT * FROM Place p JOIN User_itinerary u ON u.place_id = p.id  WHERE u.user_id =$id"
               ?: return@get call.respondText(
               "Not Found",
               status = HttpStatusCode.NotFound
           )
           call.respond(allPlaces)
    }
}

fun Route.postUserRoute() {
    post("/user/add/{id}")  {
     /* val user = call.receive<User>()
        dbData.add(user)
        call.respondText("User stored correctly", status = HttpStatusCode.Accepted)*/
    }
}
