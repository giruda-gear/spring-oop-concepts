package hello.core.member;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class MemberServiceTest {

    MemberService memberService = new MemberServiceImpl();

    @Test
    void join() {
        // given
        Member member = new Member(1L, "MemberA", Grade.VIP);

        // when
        memberService.join(member);
        Member foundMember = memberService.findMember(member.getId());

        // then
        assertThat(member).isEqualTo(foundMember);
    }
}
