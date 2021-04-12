package fr.find.dataclass

import io.ktor.http.*
import java.awt.peer.TextAreaPeer

data class PlaceData(val id: Int?,
                     val name: String?,
                     val latitude: Float?,
                     val longitude: Float?,
                     val difficulty: Int?,
                     val radius_type: Int?,
                     val active: Boolean?,
                     val description: String?)
