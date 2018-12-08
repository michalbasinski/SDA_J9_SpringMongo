package pl.mb.mongo;

import lombok.Value;

@Value
public class UserDto {
    String id;
    String name;
    String lastName;
}
