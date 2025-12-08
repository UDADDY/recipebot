package cs.capstone.dto.response

data class RecipeResponse(
    val recipeId: Long,
    val query: String,
    val recipe: String,
)
