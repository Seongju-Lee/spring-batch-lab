package sj.batch.global.entity.user;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByRegisteredAtBetween(LocalDateTime start, LocalDateTime end);

    @Query("SELECT u FROM User u WHERE DATE(u.registeredAt) = :targetDate")
    List<User> findByRegisteredDate(@Param("targetDate") LocalDate targetDate);
}
