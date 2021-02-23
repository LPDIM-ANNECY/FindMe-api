package fr.management.dataclass

import kotlinx.serialization.Serializable

@Serializable
data class Card(val id: Int,
                val name: String,
                val description: String)
