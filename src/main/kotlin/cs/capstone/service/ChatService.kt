package cs.capstone.service

import cs.capstone.dto.request.ChatRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class ChatService(
    private val recipeService: RecipeService,
    private val chatbot: ChatBot,
) {
    fun chat(userId: String, request: ChatRequest): ChatBot.RecipeDraft {
        // TODO: 실제 데이터 반환하도록 변경
        val draft = chatbot.generateRecipe(request.question)

        return draft
    }

}