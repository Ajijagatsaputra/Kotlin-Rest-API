package id.plugin.kotlinrestapi.model

import jakarta.validation.constraints.NotBlank

data class UpdateCategoryRequest(
    @field: NotBlank
    val category_name: String?
)
