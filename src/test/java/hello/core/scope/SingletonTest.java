package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonTest {

    @Test
    void findSingleTonBean() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SingleonBean.class);

        SingleonBean singletonBean1 = context.getBean(SingleonBean.class);
        SingleonBean singletonBean2 = context.getBean(SingleonBean.class);
        System.out.println("singletonBean1 = " + singletonBean1);
        System.out.println("singletonBean2 = " + singletonBean2);

        assertThat(singletonBean1).isSameAs(singletonBean2);

        context.close();
    }

    @Scope("singleton")
    static class SingleonBean {
        @PostConstruct
        public void init() {
            System.out.println("SingleonBean.init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("SingleonBean.destroy");
        }
    }

}
