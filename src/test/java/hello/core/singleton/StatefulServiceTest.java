package hello.core.singleton;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.*;

class StatefulServiceTest {
    
    @Test
    @DisplayName("stateful service")
    void statefulServiceSingleton() {
        ApplicationContext context = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = context.getBean("statefulService", StatefulService.class);
        StatefulService statefulService2 = context.getBean("statefulService", StatefulService.class);
        
        // Thread A
        int userAPrice = statefulService1.order("userA", 10000);
        // Thread B
        int userBPrice = statefulService2.order("userB", 20000);
        System.out.println("userAPrice = " + userAPrice);
        System.out.println("userBPrice = " + userBPrice);
//        assertThat(statefulService1.getPrice()).isNotEqualTo(10000);
    }
    
    static class TestConfig {
        
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}