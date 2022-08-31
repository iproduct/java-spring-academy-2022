package course.spring.service;

import course.spring.qualifiers.RepositoryBacked;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service("userPresenter")
@Slf4j
public class ConsoleUserPresenter implements Presenter {
    private UserProvider provider;

    @Autowired
    public void setProvider(UserProvider provider) {
        this.provider = provider;
    }

    @Override
    public void present() {
        provider.getUsers().forEach(System.out::println);
    }

}
