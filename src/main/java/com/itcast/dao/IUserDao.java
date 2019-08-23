package com.itcast.dao;

import com.itcast.domain.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @program: day04_eesy_03annotation_mybatis--com.itcast.dao
 * @author: WaHotDog 2019-08-23 08:52
 **/

/**
 * 在mybatis中针对，CRUD一共有四个注解
 * @Selsect @Insert @Update @Delete
 */
public interface IUserDao {

    /**
     * 查询所有用户
     * @return
     */
    @Select("select * from user ")
    public List<User> finAll();

    /**
     * 添加一个用户
     * @param user
     */
    @Insert("insert into user(username,address,sex,birthday) values(#{username},#{address},#{sex},#{birthday})")
    public void saveUser(User user);

    /**
     * 更新用户
     * @param user
     */
    @Update("update user set username = #{username} ,address = #{address} where id =#{id}")
    public void updateUser(User user);

    /**
     * 删除用户
     * @param id
     */
    @Delete("delete from user where id = #{id}")
    public void deleteUser(Integer id);

    /**
     * 通过id查用户
     * @param id
     * @return
     */
    @Select("select * from user where id = #{id}")
    public User findById(Integer id);

    /**
     * 通过名字查询用户
     * @param name
     * @return
     */
    //字符串拼接
//    @Select("select * from user where username like #{username}")
    //占位符
    @Select("select * from user where username like '%${value}%' ")
    public List<User> findUserByName(String name);


    /**
     * 查询总数
     * @return
     */
    @Select("select count(id) from user")
    public int findTotal();
}
