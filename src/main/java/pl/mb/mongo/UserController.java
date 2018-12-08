package pl.mb.mongo;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @PostMapping("/users")
    public ResponseEntity addUser(@RequestBody UserDto user) {
        UserDto userDto = userService.addUser(user);
        return new ResponseEntity(userDto, HttpStatus.OK);
    }

}
