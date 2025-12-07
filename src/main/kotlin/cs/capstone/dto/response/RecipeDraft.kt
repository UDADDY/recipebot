package cs.capstone.dto.response

data class RecipeDraft(
    val name: String,
    val content: String,
    val recipe: List<String>,
)