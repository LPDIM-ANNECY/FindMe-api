package fr.management.dataclass

import kotlinx.serialization.Serializable
import java.util.*


@Serializable
data class User(val id: Int,
                val email: String,
                val password: String,
                val created_at: Date,
                val updated_at: Date,
                val local: String,
                val enable: Boolean,
                val level: String,
                val total_distance: Float,
                val picture: String,
                val pseudo: String)
