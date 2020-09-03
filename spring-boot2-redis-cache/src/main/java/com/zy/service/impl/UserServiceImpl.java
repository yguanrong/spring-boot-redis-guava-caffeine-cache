package com.zy.service.impl;

import com.zy.dao.UserDao;
import com.zy.entity.Info;
import com.zy.entity.User;
import com.zy.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p></p>
 * Created by zhezhiyong@163.com on 2017/9/21.
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

//    private Map<Long, User> userMap = new HashMap<>();
//    private Map<Long, Info> infoMap = new HashMap<>();
//
//    public UserServiceImpl() {
//        userMap.put(1L, new User(1L, "aaa", "666666"));
//        userMap.put(2L, new User(2L, "bbb", "666666"));
//        userMap.put(3L, new User(3L, "ccc", "666666"));
//        infoMap.put(1L, new Info("18559198715", "福州市"));
//    }

    @Autowired
    UserDao userDao;

    @Override
    @Cacheable(value = "userList", key = "methodName")
    public List<User> list() {
        log.info("findAll from db");
        return userDao.querylist();
    }

    @Override
    @Cacheable(value = "user", key = "'user'.concat(#id.toString())")
    public User findUserById(Long id) {
        log.info("findUserById query from db, id: {}", id);
        return userDao.findUserById(id);
    }

    @Override
    @Cacheable(value = "info", key = "'info'.concat(#id.toString())")
    public Info findInfoById(Long id) {
        log.info("findInfoById query from db, id: {}", id);
        return userDao.findInfoById(id);
    }

    @Override
    @CachePut(value = "user", key = "'user'.concat(#user.id.toString())")
    public User update(User user) {
        log.info("update db, user: {}", user.toString());
        userDao.updateUser(user);
        return user;
    }

    @Override
    @CacheEvict(value = "user", key = "'user'.concat(#id.toString())")
    public void remove(Long id) {
        log.info("remove from db, id: {}", id);
        userDao.removeUser(id);
    }
}
