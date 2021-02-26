package fr.find.dataclass

import java.time.LocalDate

data class ItineraryData(val id: Int,
                         val name: String,
                         val duration: LocalDate,
                         val length: Int,
                         val active: Boolean,
                         val category_id: Int)
