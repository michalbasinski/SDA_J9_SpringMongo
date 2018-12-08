package pl.mb.mongo;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;

    public UserDto addUser(UserDto userDto) {
        UserEntity userToSave = new UserEntity();
        userToSave.setName(userDto.getName());
        userToSave.setLastName(userDto.getLastName());

        userRepository.save(userToSave);
        return entityToDto(userToSave);
    }

    private UserDto entityToDto(UserEntity userEntity) {
        return new UserDto(userEntity.getId(),
                userEntity.getName(),
                userEntity.getLastName());
    }

}
