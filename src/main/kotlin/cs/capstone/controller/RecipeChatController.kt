package cs.capstone.controller

import cs.capstone.dto.request.AskRecipeRequest
import cs.capstone.dto.response.RecipeDraft
import cs.capstone.service.ChatService
import cs.capstone.service.RecipeService
import cs.capstone.util.currentUserIdOrNull
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/chats")
class RecipeChatController(
    private val chatService: ChatService,
    private val recipeService: RecipeService,
) {

    @PostMapping
    fun chat(
        request: HttpServletRequest,
        @RequestBody question: AskRecipeRequest
    ): ResponseEntity<RecipeDraft> {
        val memberId = request.currentUserIdOrNull()!!

        val draft = chatService.chat(memberId, question)
        recipeService.saveRecipeDraft(draft, memberId)

        return ResponseEntity.ok(draft)
    }
}