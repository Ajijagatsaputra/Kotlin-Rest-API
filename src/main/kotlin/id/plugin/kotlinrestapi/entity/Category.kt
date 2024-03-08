package id.plugin.kotlinrestapi.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "category")
data class Category(
    @Id
    @GeneratedValue
    val idCategory : Int =0,
    @Column(name = "category_name")
    val categoryName : String
)
