package fr.find.repository

import fr.find.dataclass.UserData
import fr.find.dataclass.UserWithIdData
import fr.find.entity.User_findme
import io.ktor.http.*
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction


class UserFindmeRepository {

    fun getAll(): ArrayList<UserData>{
        val users : ArrayList<UserData> = arrayListOf()
        transaction {
            User_findme.selectAll().map {
                users.add(
                    UserData(
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
                        pseudo = it[User_findme.pseudo]
                    )
                )
            }
        }
        return users
    }

    fun getUserById(id: Int): ArrayList<UserWithIdData>{
        val user : ArrayList<UserWithIdData> = arrayListOf()
        transaction {
            User_findme.select { User_findme.id eq id }.map {
                user.add(
                    UserWithIdData(
                        id = it[User_findme.id],
                        picture = it[User_findme.picture],
                        pseudo = it[User_findme.pseudo]
                    )
                )
            }
        }
        return user
    }
}