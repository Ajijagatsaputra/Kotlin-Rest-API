package id.plugin.kotlinrestapi.controller

import id.plugin.kotlinrestapi.helper.BaseResponse
import id.plugin.kotlinrestapi.model.CategoryResponse
import id.plugin.kotlinrestapi.model.CreateCategoryRequest
import id.plugin.kotlinrestapi.model.ListCategoryRequest
import id.plugin.kotlinrestapi.model.UpdateCategoryRequest
import id.plugin.kotlinrestapi.service.CategoryService
import org.hibernate.query.Page
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class CategoryController (val categoryservice: CategoryService) {

    @PostMapping(
        value = ["api/category"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun createCategory(@RequestBody createCategoryRequest: CreateCategoryRequest) : BaseResponse<CategoryResponse>{
        val categoryResponse = categoryservice.create(createCategoryRequest)
        return BaseResponse(
            code = 200,
            message = "Data category berhasil ditambahkan",
            data = categoryResponse
        )
    }

    @GetMapping(
        value = ["api/category/{idCategory}"],
        produces = ["application/json"]
    )
    fun getCategory(@PathVariable("idCategory") id : Int) : BaseResponse<CategoryResponse>{
        val categoryResponse = categoryservice.get(id)
        return BaseResponse(
            code = 200,
            message = "Data category berhasil ditampilkan",
            data = categoryResponse
        )
    }

    @DeleteMapping(
        value = ["api/category/{idCategory}"],
        produces = ["application/json"]
    )
    fun deleteCategory(@PathVariable("idCategory") id: Int) : BaseResponse<CategoryResponse>{
        val categoryResponse = categoryservice.delete(id)
        return BaseResponse(
            code = 200,
            message = "Data category berhasil dihapus",
            data = categoryResponse
        )
    }

    @PutMapping(
        value = ["api/category/{idCategory}"],
        produces = ["application/json"]
    )
    fun putCategory(@PathVariable("idCategory") id: Int,
                    @RequestBody updateCategoryRequest: UpdateCategoryRequest)
    : BaseResponse<CategoryResponse>{
        val categoryResponse = categoryservice.update(id, updateCategoryRequest)
        return BaseResponse(
            code = 200,
            message = "Data category berhasil diupdate",
            data = categoryResponse
        )
    }

    @GetMapping(
        value = ["api/category"],
        produces = ["application/json"]
    )
    fun listCategory(@RequestParam(value = "page", defaultValue = "0") page: Int,
                     @RequestParam(value = "size", defaultValue = "2")size : Int) : BaseResponse<List<CategoryResponse>>{
        val request = ListCategoryRequest(page = page, size = size)
        val categoryResponse = categoryservice.list(request)
        return BaseResponse(
            code = 200,
            message = "Data berhasil diambil",
            data = categoryResponse
        )
    }
}