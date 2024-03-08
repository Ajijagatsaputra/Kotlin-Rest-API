package id.plugin.kotlinrestapi.service.impl

import com.cloudinary.utils.ObjectUtils
import id.plugin.kotlinrestapi.config.CloudinaryConfig
import id.plugin.kotlinrestapi.entity.Showcase
import id.plugin.kotlinrestapi.model.CreateShowcaseRequest
import id.plugin.kotlinrestapi.model.ListShowcaseRequest
import id.plugin.kotlinrestapi.model.ShowcaseResponse
import id.plugin.kotlinrestapi.model.UpdateShowcaseRequest
import id.plugin.kotlinrestapi.repository.CategoryRepository
import id.plugin.kotlinrestapi.repository.ShowcaseRepository
import id.plugin.kotlinrestapi.service.ShowcaseService
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.Date
import java.util.stream.Collectors
import kotlin.math.log

@Service
class ShowcaseServiceImpl (val showcaseRepository: ShowcaseRepository, val categoryRepository: CategoryRepository)
    : ShowcaseService {
    override fun create(createShowcaseRequest: CreateShowcaseRequest): ShowcaseResponse {
        val kategori = categoryRepository.findCategoryByIdCategory(createShowcaseRequest.idCategory)
        val cloudinary = CloudinaryConfig()
        val uploudResponse = cloudinary.cloudinaryAccount().uploader()
            .upload(createShowcaseRequest.image.bytes, ObjectUtils.asMap())

        val showcase = Showcase(
            title = createShowcaseRequest.title!!,
            image = uploudResponse.get("url").toString(),
            description = createShowcaseRequest.description!!,
            createdAt = Date(),
            updatedAt = Date()
        )
        showcase.category = kategori
        showcaseRepository.save(showcase)
        return convertShowcaseToShowcaseResponse(showcase)
    }

    override fun get(id: Int): ShowcaseResponse {
        val showcase = showcaseRepository.findByIdOrNull(id)
        if (showcase === null) {
            throw Exception()
        } else {
            return convertShowcaseToShowcaseResponse(showcase)
        }
    }

    override fun list(listShowcaseRequest: ListShowcaseRequest): List<ShowcaseResponse> {
        val page  =  showcaseRepository.findAll(
            PageRequest.of(
                listShowcaseRequest.page,
                listShowcaseRequest.size
            )
        )
        val showcase : List<Showcase> = page.get().collect(Collectors.toList())
        return showcase.map { convertShowcaseToShowcaseResponse(it) }
    }

    override fun delete(id: Int): ShowcaseResponse {
        val showcase = showcaseRepository.findByIdOrNull(id)
        if (showcase === null){
            throw Exception()
        }else{
            showcaseRepository.deleteById(id)
            return convertShowcaseToShowcaseResponse(showcase)
        }
    }

    override fun update(id: Int, updateShowcaseRequest: UpdateShowcaseRequest): ShowcaseResponse {
        val showcase = showcaseRepository.findByIdOrNull(id)
        if (showcase === null){
            throw Exception()
        }else{
            val kategori = categoryRepository.findCategoryByIdCategory(updateShowcaseRequest.idCategory)
            val cloudinary = CloudinaryConfig()
            val uploudResponse = cloudinary.cloudinaryAccount().uploader()
                .upload(updateShowcaseRequest.image.bytes, ObjectUtils.asMap())
            val showcaseUpdated = Showcase(
                title = updateShowcaseRequest.title!!,
                image = uploudResponse.get("url").toString(),
                description = updateShowcaseRequest.description!!,
                createdAt = Date(),
                updatedAt = Date()
            )
            showcaseUpdated.category = kategori
            showcaseRepository.save(showcaseUpdated)
            return convertShowcaseToShowcaseResponse(showcaseUpdated)
        }
    }

    private fun convertShowcaseToShowcaseResponse
                (showcase: Showcase): ShowcaseResponse {
        return ShowcaseResponse(
            id = showcase.id,
            title = showcase.title,
            image = showcase.image,
            description = showcase.description,
            createdAt = showcase.createdAt,
            updatedAt = showcase.updatedAt
        )
    }
}