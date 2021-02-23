package fr.find


import fr.find.dataclass.User
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.Table
import sun.font.TrueTypeFont
import sun.util.resources.LocaleData
import java.time.LocalDate
import java.time.Month
import java.util.*
import io.ktor.features.ContentNegotiation
import io.ktor.jackson.*
import org.jetbrains.exposed.sql.`java-time`.CurrentDateTime
import org.jetbrains.exposed.sql.`java-time`.date
import org.jetbrains.exposed.sql.`java-time`.datetime
import org.jetbrains.exposed.sql.transactions.transaction

object User_findme : Table(){
    fun toUser(it: ResultRow): User =
        User(
            id = it[User_findme.id],
            email = it[User_findme.email],
            password = it[User_findme.password],
            created_at = it[User_findme.created_at],
            updated_at = it[User_findme.updated_at],
            locale = it[User_findme.locale],
            enable = it[User_findme.enable],
            level = it[User_findme.level],
            total_distance = it[User_findme.total_distance],
            picture = it[User_findme.picture],
            pseudo = it[User_findme.pseudo],
        )

    val id: Column<Int> = integer("id").autoIncrement()
    val email: Column<String> = varchar("email", 50)
    val password: Column<String> = varchar("password", 255)
    val created_at: Column<LocalDate> = date("created_at").default(LocalDate.now())
    val updated_at: Column<LocalDate?> = date("updated_at").nullable().default(null)
    val locale: Column<String> = varchar("locale", 50)
    val enable: Column<Boolean> = bool("enable")
    val level: Column<String> = varchar("level", 50)
    val total_distance: Column<Float> = float("total_distance")
    val picture: Column<String> = text("picture")
    val pseudo: Column<String> = varchar("pseudo", 15)
}

@kotlin.jvm.JvmOverloads
fun Application.myapp(testing: Boolean = false) {

    Database.connect("jdbc:postgresql://127.0.0.1:5432/postgres", driver = "org.postgresql.Driver", user ="postgres", password = "442442")

    transaction {
        SchemaUtils.create(User_findme)

        User_findme.insert {
            it[User_findme.email] = "test@gmail.com"
            it[User_findme.password] = "test"
            it[User_findme.locale] = "France"
            it[User_findme.enable] = true
            it[User_findme.level] = "2"
            it[User_findme.total_distance] = 26.6f
            it[User_findme.picture] = "test picture"
            it[User_findme.pseudo] = "bat"
        }

    }

    install(Routing){
        route("/users"){
            get("/"){
                val users = transaction {
                    User_findme.selectAll().map{ User_findme.toUser(it) }
                }
                call.respond(users)
            }
        }
    }

    routing{
        get("/user"){
            call.respond(User(1,
                "test@gmail.com",
                "test",
                LocalDate.of(2020, Month.DECEMBER, 12),
                LocalDate.of(2020, Month.DECEMBER, 12),
                "France",
                true,
                "2",
                26.6f,
                "test picture",
                "bat"
            ))
        }

        get("/"){
            call.respondText("Yo")
        }
    }

    install(ContentNegotiation){
        jackson{

        }
    }
}

fun main(args: Array<String>) {
    embeddedServer(
        Netty,
        watchPaths = listOf("fr.find"),
        module = Application::myapp,
        port = 8081
    ).start(wait = true)
}

