package id.plugin.kotlinrestapi.config

import com.cloudinary.Cloudinary
import com.cloudinary.utils.ObjectUtils
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CloudinaryConfig {
    @Bean
    fun cloudinaryAccount() : Cloudinary{
        return Cloudinary(ObjectUtils.asMap(
            "cloud_name", "dhp5l6iev",
            "api_key", "776197122579388",
            "api_secret", "Y605TRyn1aVvpe4VSGZ4wN6sbfU"
        ))
    }
}