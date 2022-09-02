package course.spring;

import course.spring.config.AppConfig;
import course.spring.service.Presenter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Slf4j
public class MainConfigClass {
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

        System.out.println("\n===================   Articles   ===================");
        Presenter articlePresenter = ctx.getBean("articlePresenter", Presenter.class);
        articlePresenter.present();

        System.out.println("\n===================   Users   ===================");
        Presenter userPresenter = ctx.getBean("userPresenter", Presenter.class);
        userPresenter.present();

    }
}
