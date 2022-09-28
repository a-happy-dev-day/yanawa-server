package fashionable.simba.yanawaserver;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DataLoaderBootstrap implements ApplicationListener<ContextRefreshedEvent> {
    private DataLoader dataLoader;

    public DataLoaderBootstrap(DataLoader dataLoader) {
        this.dataLoader = dataLoader;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (dataLoader.isValid()) {
            dataLoader.loadData();
        }
    }
}
