package ktb.week4.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByNickName(String nickName);
    boolean existsByEmail(String email);

}
