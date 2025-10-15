package cs.capstone.service

import org.springframework.stereotype.Service

@Service
class ChatBot {
    fun generateRecipe(question: String): RecipeDraft {
        // TODO: 나중에 실제 LLM 호출/프롬프트 로직으로 교체
        return RecipeDraft(
            name = question,
            content = "돼지고기에 간장, 설탕, 고춧가루, 다진마늘을 넣고 10분 재운다.\n" +
                "팬에 기름을 두르고 양파와 대파를 먼저 볶는다.\n" +
                "재운 고기를 넣고 중불에서 익을 때까지 볶는다.\n" +
                "기호에 따라 고추/후추로 간을 맞추고 불을 끈다."
        )

    }

    data class RecipeDraft(
        val name: String,
        val content: String,
    )
}
