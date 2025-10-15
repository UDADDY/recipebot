package cs.capstone.controller

import cs.capstone.dto.request.ChatRequest
import cs.capstone.service.ChatBot
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
@RequestMapping("/chats")
class ChatController(
    private val chatService: ChatService,
    private val recipeService: RecipeService,
) {

    /**
     * 레시피 생성도 동일함
     */
    @PostMapping
    fun chat(
        request: HttpServletRequest,
        @RequestBody question: ChatRequest
    ): ResponseEntity<ChatBot.RecipeDraft> {
        val memberId = request.currentUserIdOrNull()!!

        val draft = chatService.chat(memberId, question)
        recipeService.saveRecipeDraft(draft, memberId)

        return ResponseEntity.ok(draft)
    }
}