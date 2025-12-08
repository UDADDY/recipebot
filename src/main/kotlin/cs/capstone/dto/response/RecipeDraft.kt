package cs.capstone.dto.response

import com.fasterxml.jackson.annotation.JsonProperty

data class RecipeDraft(
    val name: String,
    val content: String,
    val recipe: List<String>,
)


data class RecipeChatbotResponse(
    val answer: String,
    val recipe: RecipeInfo?,   // recipe 는 객체 or null
)

data class RecipeInfo(
    val index: Int?,
    val title: String?,
    val url: String?,
    val chef: String?,
    val views: String?,
    val servings: String?,      // "2.0" 처럼 문자열로 옴
    @JsonProperty("cook_time")  // snake_case 매핑
    val cookTime: String?,
    val difficulty: String?,
)