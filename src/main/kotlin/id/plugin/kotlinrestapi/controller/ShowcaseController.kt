package id.plugin.kotlinrestapi.controller

import id.plugin.kotlinrestapi.helper.BaseResponse
import id.plugin.kotlinrestapi.model.CreateShowcaseRequest
import id.plugin.kotlinrestapi.model.ListShowcaseRequest
import id.plugin.kotlinrestapi.model.ShowcaseResponse
import id.plugin.kotlinrestapi.model.UpdateShowcaseRequest
import id.plugin.kotlinrestapi.service.ShowcaseService
import org.springframework.data.jpa.repository.Modifying
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class ShowcaseController(val showcaseService: ShowcaseService) {
    @PostMapping(
        value = ["/api/showcase"],
    )
    fun createShowcase(@ModelAttribute createShowcaseRequest: CreateShowcaseRequest): BaseResponse<ShowcaseResponse> {
        val showcaseResponse = showcaseService.create(createShowcaseRequest)
        return BaseResponse(
            code = 200,
            message = "Data berhasil di buat",
            data = showcaseResponse
        )
    }

    @GetMapping(
        value = ["api/showcase/{idShowcase}"],
        produces = ["application/json"]
    )
    fun getShowcase(@PathVariable("idShowcase") id : Int) : BaseResponse<ShowcaseResponse>{
        val showcaseResponse = showcaseService.get(id)
        return BaseResponse(
            code = 200,
            message = "Data berhasil ditampilkan",
            data = showcaseResponse
        )
    }

    @GetMapping(
        value = ["api/showcase"],
        produces = ["application/json"]
    )
    fun listShowcase(@RequestParam(value = "page", defaultValue = "0") page : Int,
                     @RequestParam(value = "size", defaultValue = "2") size : Int)
            : BaseResponse<List<ShowcaseResponse>> {
        val request = ListShowcaseRequest(page = page, size = size)
        val showcaseResponse = showcaseService.list(request)
        return BaseResponse(
            code = 200,
            message = "List Data Berhasil di ambil",
            data = showcaseResponse
        )
    }
    @DeleteMapping(
        value = ["api/showcase/{idShowcase}"],
        produces = ["application/json"]
    )

    fun deleteShowcase(@PathVariable("idShowcase") id: Int): BaseResponse<ShowcaseResponse> {
        val showcaseResponse = showcaseService.delete(id)
        return BaseResponse(
            code = 200,
            message = "Data berhasil dihapus",
            data = showcaseResponse
        )
    }


    @PutMapping(
        value = ["api/showcase/{idShowcase}"],
        produces = ["application/json"]
    )
    fun updateShowcase(@PathVariable("idShowcase") id: Int,
                       @ModelAttribute updateShowcaseRequest: UpdateShowcaseRequest): BaseResponse<ShowcaseResponse>{
        val showcaseResponse = showcaseService.update(id, updateShowcaseRequest)
        return BaseResponse(
            code = 200,
            message = "Data berhasil diupdate",
            data = showcaseResponse
        )
    }
}