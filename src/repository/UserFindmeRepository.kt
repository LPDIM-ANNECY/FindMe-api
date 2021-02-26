package fr.find.repository

import fr.find.dataclass.UserData
import fr.find.entity.User_findme
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
}