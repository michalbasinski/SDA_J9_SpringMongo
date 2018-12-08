package pl.mb.mongo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = MongoApplication.class)
public class UserControllerTest {

    @LocalServerPort
    private int localServerPort;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserController userController;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void shouldAddUser() {
        //given
        final String expectedUserName = "Jan";
        final String expectedUserLastName = "Kowalski";
        final String expectedUserId = "abcdef";

        UserEntity userEntity = new UserEntity();
        userEntity.setName(expectedUserName);
        userEntity.setLastName(expectedUserLastName);
        userEntity.setId(expectedUserId);

        when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity);

        //when
        ResponseEntity<UserDto> result = testRestTemplate.postForEntity(
                "http://localhost:" + localServerPort + "/users",
                new UserDto(null, expectedUserName, expectedUserLastName),
                UserDto.class);
        UserDto body = result.getBody();

        //then
        assertEquals(expectedUserId, body.getId());
        assertEquals(expectedUserName, body.getName());
        assertEquals(expectedUserLastName, body.getLastName());
    }

}
