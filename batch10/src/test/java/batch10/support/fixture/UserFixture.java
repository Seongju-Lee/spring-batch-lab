package batch10.support.fixture;

import java.time.LocalDateTime;
import sj.batch.global.entity.user.User;

public class UserFixture {

    public static User create(
        final String name,
        final LocalDateTime registeredAt
    ) {
        return User.builder()
            .name(name)
            .registeredAt(registeredAt)
            .build();
    }

}
