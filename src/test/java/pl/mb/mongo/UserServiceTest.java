package pl.mb.mongo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Before
    public void setUp() {
        userService = new UserService(userRepository);
    }

    @Test
    public void shouldAddUser() {
        //given
        UserDto userDto = new UserDto(null, "Jan", "Kowalski");
        final String expectedUserName = "Jan";
        final String expectedUserLastName = "Kowalski";
        final String expectedUserId = "abcdef";

        UserEntity userEntity = new UserEntity();
        userEntity.setName(expectedUserName);
        userEntity.setLastName(expectedUserLastName);
        userEntity.setId(expectedUserId);

        when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity);

        //when
        UserDto result = userService.addUser(userDto);

        //then
        assertEquals(expectedUserId, result.getId());
        assertEquals(expectedUserName, result.getName());
        assertEquals(expectedUserLastName, result.getLastName());
    }
}