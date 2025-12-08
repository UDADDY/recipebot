package cs.capstone.service

import cs.capstone.client.RecipeChatbotClient
import cs.capstone.dto.request.AskRecipeRequest
import cs.capstone.dto.request.GenerateRecipeRequest
import cs.capstone.dto.request.LikeRecipeRequest
import cs.capstone.dto.response.RecipeChatbotResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class ChatBot(
    private val recipeChatbotClient: RecipeChatbotClient,
    private val recipeService: RecipeService,
) {
    fun generateRecipe(question: AskRecipeRequest): RecipeChatbotResponse {
        val parts = mutableListOf<String>()

        // 메인 질문 먼저
        parts.add(question.query)

        // 비건 조건
        if (question.isVegan) {
            parts.add("비건 레시피")
        }

        // 알레르기 조건
        if (question.allergies.isNotEmpty()) {
            val allergyText = question.allergies.joinToString(", ")
            parts.add("알레르기: $allergyText")
        }

        val query = parts.joinToString(", ")

        return recipeChatbotClient.askRecipe(GenerateRecipeRequest(query = query))

    }

    fun like(recipeId: Long) {
        val recipe = recipeService.getRecieptById(recipeId)
        val externalRecipeIndex = recipe.externalRecipeIndex ?: throw IllegalArgumentException("외부 레시피 index가 없는 레시피입니다.")

        recipeChatbotClient.likeRecipe(LikeRecipeRequest(externalRecipeIndex))

//        recipeChatbotClient.like(recipeId)
    }

}
