package club.liujunmin.blog.siyuan.service;

import club.liujunmin.blog.siyuan.entity.User;

public interface UserService {
    User checkUser(String username, String password);
}
