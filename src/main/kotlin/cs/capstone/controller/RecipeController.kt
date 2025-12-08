package cs.capstone.controller

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
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/recipes")
class RecipeController(
    private val recipeService: RecipeService,
    private val chatBot: ChatBot,
) {

    @GetMapping("/{recipeId}")
    fun getRecipe(@PathVariable recipeId: Long): ResponseEntity<RecipeResponse> {
        val response = recipeService.getRecieptById(recipeId)

        return ResponseEntity.ok(RecipeResponse(response.id!!, response.name, response.content))
    }


}