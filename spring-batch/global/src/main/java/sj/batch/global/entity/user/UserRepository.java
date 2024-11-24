package sj.batch.global.entity.user;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByRegisteredAtBetween(LocalDateTime start, LocalDateTime end);
}
