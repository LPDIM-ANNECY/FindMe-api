package fr.find.dataclass

import java.time.LocalDate

data class UserData(val id: Int,
                    val email: String,
                    val password: String,
                    val created_at: LocalDate,
                    val updated_at: LocalDate?,
                    val locale: String,
                    val enable: Boolean,
                    val level: String,
                    val total_distance: Float,
                    val picture: String,
                    val pseudo: String)
