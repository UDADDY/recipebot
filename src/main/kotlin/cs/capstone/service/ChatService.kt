package cs.capstone.service

import cs.capstone.dto.request.AskRecipeRequest
import cs.capstone.dto.response.RecipeChatbotResponse
import cs.capstone.dto.response.RecipeDraft
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class ChatService(
    private val recipeService: RecipeService,
    private val chatbot: ChatBot,
) {
    fun chat(userId: String, request: AskRecipeRequest): RecipeChatbotResponse {
        val draft = chatbot.generateRecipe(request.question)

        return draft
    }

}