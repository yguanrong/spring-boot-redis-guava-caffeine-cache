package com.zy.dao;

import com.zy.entity.Info;
import com.zy.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ：ygr
 * @date ：Created in 2020-9-3
 */
@Mapper
public interface UserDao {

    List<User> querylist();

    User findUserById(Long id);

    Info findInfoById(Long id);

    void updateUser(@Param("item") User user);

    void removeUser(Long id);

}
