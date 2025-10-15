package cs.capstone.controller

import cs.capstone.dto.response.RecipeResponse
import cs.capstone.entity.Recipe
import cs.capstone.service.RecipeService
import cs.capstone.util.currentUserIdOrNull
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/recipes")
class RecipeController(
    private val recipeService: RecipeService,
) {
    @GetMapping
    fun getRecipeHistory(request: HttpServletRequest,): ResponseEntity<List<RecipeResponse>> {
        val userId = request.currentUserIdOrNull()!!
        val history: List<Recipe> = recipeService.getHistory(userId)
        val responses = history.map { RecipeResponse.fromEntity(it) }

        return ResponseEntity.ok(responses)
    }

    @GetMapping("/{recipeId}")
    fun getRecipe(@PathVariable recipeId: Long): ResponseEntity<RecipeResponse> {
        val response = recipeService.getRecieptById(recipeId)

        return ResponseEntity.ok(RecipeResponse.fromEntity(response))
    }

    @DeleteMapping("/{recipeId}")
    fun delete(@PathVariable recipeId: Long): ResponseEntity<Unit> {
        recipeService.delete(recipeId)

        return ResponseEntity.noContent().build()
    }

}