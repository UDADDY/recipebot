package cs.capstone.controller

import cs.capstone.service.MemberService
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseCookie
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val memberService: MemberService,
) {

    data class LoginRequest(val memberId: String)
    data class LoginSuccessResponse(
        val success: Boolean,
        val message: String,
    )

    @PostMapping("/login")
    fun login(@RequestBody req: LoginRequest): ResponseEntity<LoginSuccessResponse> {
        val memberId = req.memberId

        // 회원가입
        memberService.회원가입(memberId)

        // 아무 플래그 없이, path=/ 만 설정
        val cookie = ResponseCookie.from("user-id", memberId)
            .path("/")
            .build()

        val body = LoginSuccessResponse(
            success = true,
            message = "로그인 성공",
        )


        return ResponseEntity.ok()
            .header(HttpHeaders.SET_COOKIE, cookie.toString())
            .body(body)
    }



    @PostMapping("/logout")
    fun logout(): ResponseEntity<Unit> {
        val expired = ResponseCookie.from("sid", "")
            .path("/")
            .maxAge(0)
            .build()
        return ResponseEntity.noContent()
            .header(HttpHeaders.SET_COOKIE, expired.toString())
            .build()
    }

}