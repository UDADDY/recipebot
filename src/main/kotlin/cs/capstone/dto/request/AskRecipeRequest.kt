package cs.capstone.dto.request

data class AskRecipeRequest(
    val query: String,
    val isVegan: Boolean,
    val allergies: List<String>,
)