package cs.capstone.dto.response

import cs.capstone.entity.Recipe
import cs.capstone.entity.RecipeStep

data class RecipeResponse(
    val id: Long,
    val name: String,
    val steps: List<RecipeStep>,
) {
    companion object {
        fun fromEntity(recipe: Recipe): RecipeResponse {
            return RecipeResponse(
                id = recipe.id!!,
                name = recipe.name,
                steps = recipe.steps
            )
        }
    }
}