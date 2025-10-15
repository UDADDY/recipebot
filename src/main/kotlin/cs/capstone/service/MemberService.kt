package cs.capstone.service

import cs.capstone.entity.Member
import cs.capstone.repository.MemberRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class MemberService(
    private val memberRepository: MemberRepository,
) {
    @Transactional
    fun 회원가입(memberId: String) {
        val member = Member(id = memberId, name = memberId)
        memberRepository.save(member)
    }

    fun getByMemberId(memberId: String): Member {
        return memberRepository.findById(memberId).get()
    }
}
