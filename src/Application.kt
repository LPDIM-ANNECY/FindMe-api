package fr.find


import fr.find.entity.Category
import fr.find.entity.Place
import fr.find.entity.User_findme
import fr.find.entity.User_itinerary
import fr.find.repository.UserFindmeRepository
import fr.find.routes.registerPlaceRoutes
import fr.find.routes.registerUserRoutes
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.jetbrains.exposed.sql.*
import io.ktor.features.ContentNegotiation
import io.ktor.jackson.*
import org.jetbrains.exposed.sql.transactions.transaction

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@kotlin.jvm.JvmOverloads
fun Application.myapp(testing: Boolean = false) {

    Database.connect("jdbc:postgresql://127.0.0.1:5432/postgres", driver = "org.postgresql.Driver", user ="postgres", password = "442442")
    val user_findme_repository = UserFindmeRepository()

    transaction {
        SchemaUtils.create(User_findme)
        SchemaUtils.create(Place)
        SchemaUtils.create(User_itinerary)
        SchemaUtils.create(Category)


        /*User_findme.insert {
            it[User_findme.email] = "test@gmail.com"
            it[User_findme.password] = "test"
            it[User_findme.locale] = "France"
            it[User_findme.enable] = true
            it[User_findme.level] = "2"
            it[User_findme.total_distance] = 26.6f
            it[User_findme.picture] = "test picture"
            it[User_findme.pseudo] = "bat"
        }

        Category.insert {
            it[Category.name] = "RELIGIEUX"
        }

        Place.insert {
            it[Place.name] = "Tour eiffel2"
            it[Place.latitude] = 48.455f
            it[Place.longitude] = 848.87f
            it[Place.difficulty] = 2
            it[Place.radius_type] = "50"
            it[Place.category_id] = 1
        }

        User_itinerary.insert {
            it[User_itinerary.user_id] = 1
            it[User_itinerary.place_id] = 1
        }*/

    }

    install(Routing){
        registerPlaceRoutes()
        registerUserRoutes()
        route("/users"){
            get("/"){
                call.respond(user_findme_repository.getAll())
            }
        }
    }

    install(ContentNegotiation){
        jackson{

        }
    }
}


