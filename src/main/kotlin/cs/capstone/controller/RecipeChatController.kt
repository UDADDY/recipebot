package cs.capstone.controller

import cs.capstone.dto.request.AskRecipeRequest
import cs.capstone.dto.request.FeedbackRequest
import cs.capstone.dto.response.RecipeChatbotResponse
import cs.capstone.dto.response.RecipeDraft
import cs.capstone.dto.response.RecipeResponse
import cs.capstone.entity.Recipe
import cs.capstone.service.ChatBot
import cs.capstone.service.RecipeService
import cs.capstone.util.currentUserIdOrNull
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/chats")
class RecipeChatController(
    private val chatBot: ChatBot,
    private val recipeService: RecipeService,
) {

    @PostMapping
    fun chat(
        request: HttpServletRequest,
        @RequestBody question: AskRecipeRequest
    ): ResponseEntity<RecipeResponse> {
        val memberId = request.currentUserIdOrNull()!!

        val draft = chatBot.generateRecipe(question)
        val recipe = recipeService.saveRecipeDraft(draft, memberId)

        val response = RecipeResponse(recipeId = recipe.id!!, query = recipe.name, recipe = recipe.content)

        return ResponseEntity.ok(response)
    }



    @PostMapping("/feedback")
    fun likeRecipe(@RequestBody feedbackRequest: FeedbackRequest): ResponseEntity<Unit> {
        chatBot.like(feedbackRequest.recipeId)

        return ResponseEntity.noContent().build()
    }


    @GetMapping("/history")
    fun getRecipeHistory(request: HttpServletRequest,): ResponseEntity<List<RecipeResponse>> {
        val userId = request.currentUserIdOrNull()!!
        val history: List<Recipe> = recipeService.getHistory(userId)
        val responses = history.map { RecipeResponse(it.id!!, it.name, it.content) }

        return ResponseEntity.ok(responses)
    }

    @DeleteMapping("/{recipeId}")
    fun delete(@PathVariable recipeId: Long): ResponseEntity<Unit> {
        recipeService.delete(recipeId)

        return ResponseEntity.noContent().build()
    }
}