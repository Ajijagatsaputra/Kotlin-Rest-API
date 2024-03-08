package id.plugin.kotlinrestapi

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController {
    @GetMapping("/greeting")
    fun sayHello() = "HelloWord"

    data class DataDiri(
        val nama : String,
        val alamat : String,
        val umur : Int
    )

    data class BaseResponse(
        val message : String,
        val status : Int,
        val dataDiri: DataDiri
    )

    @GetMapping("/data")
    fun statusData():BaseResponse{
        val dataDiri = DataDiri("Aji Jagat","Desa Pagedangan",20)
        return BaseResponse(
            message = "Data Sukses ditambahkan",
            status = 200,
            dataDiri
        )
    }
}