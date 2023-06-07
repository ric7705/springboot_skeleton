package app.service;

import app.repository.UserDao;
import app.service.Impl.UserServiceImpl;
import app.vo.response.UserRes;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.internal.verification.Times;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @Mock
    UserDao userDao;

    @Spy
    BookService bookService;

    @InjectMocks
    UserService userService = new UserServiceImpl();

//    @Spy
//    List<String> realList = new ArrayList<>();

    @Test
    public void t() throws Exception {
        userService.getUsers();

    }

    @Test
    public void testGetUsers() throws Exception {
        List<UserRes> users = userService.getUsers();
        Mockito.verify(userDao).findAll();

        Mockito.when(userService.getUsers())
                .thenReturn(Arrays.asList(new UserRes(1L, "tom","tom@mail"),
                new UserRes(2L, "jack","jack@mail")));

        Mockito.verify(userDao, Mockito.times(1)).findAll();


    }
}
