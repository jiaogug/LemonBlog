package club.liujunmin.blog.siyuan.service.impl;

import club.liujunmin.blog.siyuan.dao.UserRepository;
import club.liujunmin.blog.siyuan.entity.User;
import club.liujunmin.blog.siyuan.service.UserService;
import club.liujunmin.blog.siyuan.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;

    @Override
    public User checkUser(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, MD5Utils.code(password));
        return user;
    }
}
