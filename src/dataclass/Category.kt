package fr.management.dataclass

import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class Category(val id: Int,
                    val name: String)
