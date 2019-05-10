package app;

import app.repository.User;
import app.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StartupRunner implements CommandLineRunner {

    @Autowired
    UserDao userDao;

    @Override
    public void run(String... args){
        System.out.println("init data...");
        userDao.save(new User("Mary","mary@com","123"));
        userDao.save(new User("Tom","tom@com","456"));
        userDao.save(new User("Jack","jack@com","789"));
    }
}
