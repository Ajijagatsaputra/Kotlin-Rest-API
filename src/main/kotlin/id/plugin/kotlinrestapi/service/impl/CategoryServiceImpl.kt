package id.plugin.kotlinrestapi.service.impl

import id.plugin.kotlinrestapi.entity.Category
import id.plugin.kotlinrestapi.entity.Showcase
import id.plugin.kotlinrestapi.model.CategoryResponse
import id.plugin.kotlinrestapi.model.CreateCategoryRequest
import id.plugin.kotlinrestapi.model.ListCategoryRequest
import id.plugin.kotlinrestapi.model.UpdateCategoryRequest
import id.plugin.kotlinrestapi.repository.CategoryRepository
import id.plugin.kotlinrestapi.service.CategoryService
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class CategoryServiceImpl (val categoryRepository: CategoryRepository) : CategoryService{
    override fun create(createCategoryRequest: CreateCategoryRequest): CategoryResponse {
        val category = Category(
            categoryName = createCategoryRequest.category_name!!
        )
        categoryRepository.save(category)
        return convertCategoryToResponse(category)
    }

    override fun get(id: Int): CategoryResponse {
        val category= categoryRepository.findByIdOrNull(id)
        if (category === null){
            throw Exception()
        }else{
            return convertCategoryToResponse(category)
        }
    }

    override fun delete(id: Int): CategoryResponse {
        val category= categoryRepository.findByIdOrNull(id)
        if (category === null){
            throw Exception()
        }else{
            categoryRepository.delete(category)
            return convertCategoryToResponse(category)
        }
    }

    override fun update(id: Int, updateCategoryRequest: UpdateCategoryRequest): CategoryResponse {
        val category= categoryRepository.findByIdOrNull(id)
        if (category === null){
            throw Exception()
        }else{
            categoryRepository.save(Category(
                idCategory = category.idCategory,
                categoryName = updateCategoryRequest.category_name.toString()
            )
            )
            return convertCategoryToResponse(category)
        }
    }

    override fun list(listCategoryRequest: ListCategoryRequest): List<CategoryResponse> {
        val page  =  categoryRepository.findAll(
            PageRequest.of(
                listCategoryRequest.page,
                listCategoryRequest.size
            )
        )
        val showcase : List<Category> = page.get().collect(Collectors.toList())
        return showcase.map { convertCategoryToResponse(it) }
    }
}

private fun convertCategoryToResponse(category: Category) : CategoryResponse{
    return CategoryResponse(
        id = category.idCategory,
        category_name = category.categoryName
    )
}