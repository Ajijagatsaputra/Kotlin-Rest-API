package id.plugin.kotlinrestapi.model

import java.util.*

data class ShowcaseResponse(
    val id : Int,
    val title : String,
    val image : String,
    val description : String,
    val createdAt : Date,
    val updatedAt : Date
)
