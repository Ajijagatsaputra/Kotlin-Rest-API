package id.plugin.kotlinrestapi.service

import id.plugin.kotlinrestapi.model.CategoryResponse
import id.plugin.kotlinrestapi.model.CreateCategoryRequest
import id.plugin.kotlinrestapi.model.ListCategoryRequest
import id.plugin.kotlinrestapi.model.UpdateCategoryRequest

interface CategoryService {
    fun create(createCategoryRequest: CreateCategoryRequest) : CategoryResponse
    fun get(id: Int) : CategoryResponse
    fun delete(id : Int) : CategoryResponse
    fun update(id: Int, updateCategoryRequest: UpdateCategoryRequest) : CategoryResponse
    fun list(listCategoryRequest: ListCategoryRequest) : List<CategoryResponse>
}