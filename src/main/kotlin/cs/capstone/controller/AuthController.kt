package cs.capstone.controller

import cs.capstone.entity.Member
import cs.capstone.repository.MemberRepository
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseCookie
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
    private val memberRepsoitory: MemberRepository,
) {

    data class LoginRequest(val userId: String)

    @PostMapping("/login")
    fun login(@RequestBody req: LoginRequest): ResponseEntity<Unit> {
        val userId = req.userId

        // 회원가입
        val member = Member(id = userId, name = userId)
        memberRepsoitory.save(member)

        // 아무 플래그 없이, path=/ 만 설정
        val cookie = ResponseCookie.from("sid", userId)
            .path("/")
            .build()


        return ResponseEntity.noContent()
            .header(HttpHeaders.SET_COOKIE, cookie.toString())
            .build()
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