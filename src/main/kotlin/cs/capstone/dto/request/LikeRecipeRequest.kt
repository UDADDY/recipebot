package cs.capstone.dto.request

data class LikeRecipeRequest(
    val index: Int,
)

data class LikeRecipeResponse(
    val index: Long,
    val likes: Int,
)