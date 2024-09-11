package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class RateDiscountPolicyTest {

    RateDiscountPolicy rateDiscountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP get a 10% discount")
    void vipDiscount() {
        // given
        Member member = new Member(1L, "memberVIP", Grade.VIP);
        // when
        int discount = rateDiscountPolicy.discount(member, 10000);
        // then
        assertThat(discount).isEqualTo(1000);
    }

    @Test
    @DisplayName("Non-VIP doesn't get a discount")
    void nonVIPDiscount() {
        Member member = new Member(2L, "memberNonVIP", Grade.BASIC);

        int discount = rateDiscountPolicy.discount(member, 10000);

        assertThat(discount).isEqualTo(0);
    }
}