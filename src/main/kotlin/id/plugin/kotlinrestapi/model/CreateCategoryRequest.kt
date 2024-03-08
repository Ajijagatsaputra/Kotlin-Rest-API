package id.plugin.kotlinrestapi.model

import jakarta.validation.constraints.NotBlank
import org.springframework.web.multipart.MultipartFile

data class CreateCategoryRequest(
    @field: NotBlank
    val category_name :String?,
)
