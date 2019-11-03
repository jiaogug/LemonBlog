package club.liujunmin.blog.siyuan.service;

import club.liujunmin.blog.siyuan.entity.Type;
import javassist.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TypeService {
    Type saveType(Type type);
    Type getType(Long id);
    Page<Type> listType(Pageable pageable);
    List<Type> listType();
    Type updateType(Long id, Type type) throws NotFoundException;
    void deleteType(Long id);
}
