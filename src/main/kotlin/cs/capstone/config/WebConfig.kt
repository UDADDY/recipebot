package cs.capstone.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig: WebMvcConfigurer {

    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .allowedOriginPatterns("*")  // ✅ credentials=true와 함께 사용 가능
            .allowedHeaders("*")
            .allowedMethods("*")
            .allowCredentials(true)
    }
}