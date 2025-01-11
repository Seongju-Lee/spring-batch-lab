package batch8.service;

import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sj.batch.global.entity.user.User;
import sj.batch.global.entity.user.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> findByRegisteredDate(final LocalDate targetDate) {
        return userRepository.findByRegisteredDate(targetDate);
    }
}
