package cs.capstone.service

import cs.capstone.dto.response.RecipeChatbotResponse
import cs.capstone.dto.response.RecipeDraft
import cs.capstone.entity.Recipe
import cs.capstone.repository.RecipeRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class RecipeService(
    private val recipeRepository: RecipeRepository,
    private val memberService: MemberService,
) {

    fun getHistory(userId: String): List<Recipe> {
        return recipeRepository.findByMember_Id(userId)
    }

    fun getRecieptById(recipeId: Long): Recipe {
        val findById = recipeRepository.findById(recipeId)
        if(findById.isEmpty)
            throw IllegalArgumentException("없는 레시피 아이디입니다.")

        return findById.get()
    }

    @Transactional
    fun delete(recieptId: Long) {
        recipeRepository.deleteById(recieptId)
    }

    @Transactional
    fun saveRecipeDraft(draft: RecipeChatbotResponse, memberId: String) {
        val name = draft.recipe!!.title!!
        val content = draft.answer
        val member = memberService.getByMemberId(memberId)

        val recipe = Recipe(
            name = name,
            content = content,
            member = member
        )
        recipeRepository.save(recipe)
    }
}
