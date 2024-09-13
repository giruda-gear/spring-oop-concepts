package hello.core.beanfinder;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixedDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class ApplicationContextInheritanceTest {

    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TestConfig.class);

    @Test
    @DisplayName("when find bean by parent type -> duplicate error")
    void findBeanByParentTypeDuplicate() {
        assertThrows(NoUniqueBeanDefinitionException.class,
                () -> context.getBean(DiscountPolicy.class));
    }

    @Test
    @DisplayName("find bean by parent type and name")
    void findBeanByParentTypeName() {
        DiscountPolicy discountPolicy = context.getBean("rateDiscountPolicy", DiscountPolicy.class);
        assertThat(discountPolicy).isInstanceOf(RateDiscountPolicy.class);
    }

    @Test
    @DisplayName("find bean by Subtype")
    void findBeanBySubType() {
        RateDiscountPolicy rateDiscountPolicy = context.getBean(RateDiscountPolicy.class);
        assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy.class);
    }

    @Test
    @DisplayName("find beans by parent type")
    void findBeanByParentType() {
        Map<String, DiscountPolicy> beansOfType = context.getBeansOfType(DiscountPolicy.class);
        assertThat(beansOfType).hasSize(2);
    }

    @Test
    @DisplayName("find beans by object type")
    void findBeanByObjectType() {
        Map<String, Object> beansOfType = context.getBeansOfType(Object.class);
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + ",value = " + beansOfType.get(key));
        }
    }

    @Configuration
    static class TestConfig {

        @Bean
        public DiscountPolicy rateDiscountPolicy() {
            return new RateDiscountPolicy();
        }

        @Bean
        public DiscountPolicy fixedDiscountPolicy() {
            return new FixedDiscountPolicy();
        }
    }
}
