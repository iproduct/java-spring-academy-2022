package coredemo.events;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EntityCreationEventListener2 implements ApplicationListener<EntityCreationEvent> {
//    final static Logger log = LoggerFactory.getLogger(EntityCreationEventListener.class);

    @Override
    public void onApplicationEvent(EntityCreationEvent event) {
        log.info("!!!!!!! SECOND Listener: Entity created [{}]: {}", event.getEntityName(), event.getEntity().toString());
    }
}
