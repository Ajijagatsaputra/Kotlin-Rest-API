package id.plugin.kotlinrestapi.repository

import id.plugin.kotlinrestapi.entity.Category
import org.springframework.data.jpa.repository.JpaRepository

interface CategoryRepository : JpaRepository<Category, Int> {
    fun findCategoryByIdCategory(id : Int) : Category
}