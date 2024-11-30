package batch2.support.fixture;

import java.time.LocalDateTime;
import sj.batch.global.entity.user.User;

public class UserFixture {

    public static User create(final LocalDateTime registeredAt) {
        return User.builder()
            .id(1L)
            .name("sj")
            .registeredAt(registeredAt)
            .build();
    }

}
