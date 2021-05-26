package fr.find.dataclass

data class PlaceWithCategory(
    val id: Int?,
    val name: String?,
    val latitude: Float?,
    val longitude: Float?,
    val difficulty: Int?,
    val radius_type: Int,
    val active: Boolean?,
    val description: String?,
    val picture: String,
    val category_id: Int?,
    val category_name: String?)
