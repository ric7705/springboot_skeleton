package app.service;

import app.module.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.*;
import java.util.function.Supplier;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ThreadPoolTest {

    @Autowired
    Order order;


    @Test
    void t(){
        order.handle();
    }

}

