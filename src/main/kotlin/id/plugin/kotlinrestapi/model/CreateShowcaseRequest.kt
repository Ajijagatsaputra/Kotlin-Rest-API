package id.plugin.kotlinrestapi.model

import jakarta.validation.constraints.NotBlank
import org.springframework.web.multipart.MultipartFile

data class CreateShowcaseRequest(
    @field: NotBlank
    val title : String?,
    @field: NotBlank
    val image : MultipartFile,
    @field: NotBlank
    val description : String?,
    @field: NotBlank
    val idCategory : Int,
)
