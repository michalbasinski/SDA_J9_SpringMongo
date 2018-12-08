package pl.mb.mongo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Users")
@Getter
@Setter
public class UserEntity {

    @Id
    private String id;

    private String name;
    private String lastName;
}
