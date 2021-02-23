package fr.find.dataclass

import java.time.LocalDate

data class Itinerary(val id: Int,
                     val name: String,
                     val duration: LocalDate,
                     val length: Int,
                     val active: Boolean,
                     val category_id: Int)
