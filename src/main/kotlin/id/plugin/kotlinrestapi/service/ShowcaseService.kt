package id.plugin.kotlinrestapi.service

import id.plugin.kotlinrestapi.model.CreateShowcaseRequest
import id.plugin.kotlinrestapi.model.ListShowcaseRequest
import id.plugin.kotlinrestapi.model.ShowcaseResponse
import id.plugin.kotlinrestapi.model.UpdateShowcaseRequest

interface ShowcaseService {
    fun create(createShowcaseRequest: CreateShowcaseRequest) : ShowcaseResponse
    fun get(id : Int) : ShowcaseResponse
    fun delete(id: Int) : ShowcaseResponse
    fun update(id: Int, updateShowcaseRequest: UpdateShowcaseRequest) : ShowcaseResponse
    fun list(listShowcaseRequest: ListShowcaseRequest) : List<ShowcaseResponse>
}