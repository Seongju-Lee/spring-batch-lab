package batch9.support.fixture;

import java.time.LocalDateTime;
import sj.batch.global.entity.user.User;

public class UserFixture {

    public static User create(
        final String name
    ) {
        return User.builder()
            .name(name)
            .registeredAt(LocalDateTime.now())
            .build();
    }

}
