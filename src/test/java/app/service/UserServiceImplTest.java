package app.service;

import app.repository.UserDao;
import app.service.Impl.UserServiceImpl;
import app.vo.response.UserRes;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @Mock
    UserDao userDao;

    @InjectMocks
    UserService userService = new UserServiceImpl();

    @Test
    public void testGetUsers() throws Exception {
        List<UserRes> users = userService.getUsers();
        verify(userDao).findAll();
    }
}
