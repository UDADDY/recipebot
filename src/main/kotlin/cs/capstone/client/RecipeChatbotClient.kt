package cs.capstone.client

import cs.capstone.dto.request.AskRecipeRequest
import cs.capstone.dto.response.RecipeChatbotResponse
import cs.capstone.dto.response.RecipeDraft
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@FeignClient(
    name = "recipeChatbot",
    url = "\${chatbot.base-url}" // 챗봇 URL
)
interface RecipeChatbotClient {

    @PostMapping("/api/recipes/ask")
    fun askRecipe(@RequestBody request: AskRecipeRequest): RecipeChatbotResponse
}