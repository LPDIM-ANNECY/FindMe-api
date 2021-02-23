package fr.find.dataclass

import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class Itinerary(val id: Int,
                     val name: String,
                     val duration: Date,
                     val length: Int,
                     val active: Boolean,
                     val category_id: Int)
