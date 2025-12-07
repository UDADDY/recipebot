package cs.capstone.service

import cs.capstone.client.RecipeChatbotClient
import cs.capstone.dto.request.AskRecipeRequest
import cs.capstone.dto.response.RecipeChatbotResponse
import cs.capstone.dto.response.RecipeDraft
import org.springframework.stereotype.Service

@Service
class ChatBot(
    private val recipeChatbotClient: RecipeChatbotClient,
) {
    fun generateRecipe(question: String): RecipeChatbotResponse {
        return recipeChatbotClient.askRecipe(AskRecipeRequest(question = question))

    }
}
