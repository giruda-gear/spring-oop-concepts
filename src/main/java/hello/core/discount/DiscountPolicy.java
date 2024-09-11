package hello.core.discount;

import hello.core.member.Member;

public interface DiscountPolicy {

    /**
     *
     * @param member
     * @param price
     * @return discount price
     */
    int discount(Member member, int price);
}
