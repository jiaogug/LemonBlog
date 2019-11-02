package club.liujunmin.blog.siyuan.dao;

import club.liujunmin.blog.siyuan.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsernameAndPassword(String username, String password);
}
