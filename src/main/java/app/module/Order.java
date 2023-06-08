package app.module;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Order {


    public void handle(){

        createOrder();
        createOrder();

    }

    @Async("asyncTaskExecutor")
    void createOrder()  {
        try {
            log.info("sleep");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
