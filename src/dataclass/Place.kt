package fr.find.dataclass

import kotlinx.serialization.Serializable

@Serializable
data class Place(val id: Int,
                val name: String,
                val latitude: Float,
                val longitude: Float,
                val difficulty: Int,
                val radius_type: String)
