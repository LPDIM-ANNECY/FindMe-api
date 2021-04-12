package fr.find.dataclass

data class PlaceUserIdData(
    val id: Int?,
    val name: String?,
    val latitude: Float?,
    val longitude: Float?,
    val difficulty: Int?,
    val radius_type: Int,
    val active: Boolean?,
    val description: String?,
    val visited: Boolean?)
