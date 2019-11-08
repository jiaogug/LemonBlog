package club.liujunmin.blog.siyuan.dao;

import club.liujunmin.blog.siyuan.entity.Type;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TypeRepository extends JpaRepository<Type, Long> {
    @Query("select t from Type t")
    List<Type> findTop(Pageable pageable);
}
