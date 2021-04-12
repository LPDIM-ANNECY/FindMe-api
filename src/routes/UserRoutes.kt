package fr.find.routes

import fr.find.repository.PlaceRepository
import fr.find.repository.UserFindmeRepository
import io.ktor.application.Application
import io.ktor.routing.*

import io.ktor.http.HttpStatusCode
import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.response.respondText


val user_repository = UserFindmeRepository()

fun Application.registerUserRoutes() {
    routing {
        getUserRoute()
    }
}

fun Route.getUserRoute() {
    route("/users"){
        get("/"){
            call.respond(user_repository.getAll())
        }
        get("users/{id}"){
            val id = call.parameters["id"]

            if (id != null) {
                val response = user_repository.getUserById(id.toInt())
                call.respond(if (response.isNullOrEmpty()) HttpStatusCode.NotFound else response)
            }
            else
                call.respondText("id is null")
        }
    }
}
