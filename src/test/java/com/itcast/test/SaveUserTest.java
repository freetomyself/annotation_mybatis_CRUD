package com.itcast.test;

import com.itcast.dao.IUserDao;
import com.itcast.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * @program: day04_eesy_03annotation_mybatis--com.itcast.test
 * @author: WaHotDog 2019-08-23 11:38
 **/


public class SaveUserTest{
    private InputStream in;
    private SqlSessionFactory factory;
    private SqlSession sqlSession;
    private IUserDao userDao;
    @Before
    public void init()  throws  Exception{
        //1添加配置文件
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2使用SqlSessionFactoryBuilder创建工厂
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        factory = builder.build(in);
        //创建sqlSession工厂
        sqlSession = factory.openSession(true);
        //使用sqlSession创建dao代理工厂
        userDao = sqlSession.getMapper(IUserDao.class);
    }
    @After
    public void clean() throws Exception {
        //释放资源
        sqlSession.close();
        in.close();
    }
    @Test
    public void saveUserTest(){
        User user = new User();
        user.setUsername("张三呵呵");
        user.setAddress("浙江绍兴测试");
        user.setSex("男");
        user.setBirthday(new Date());
        userDao.saveUser(user);
    }

    @Test
    public void updateUserTest(){
        User user = new User();
        user.setAddress("你配吗");
        user.setUsername("我卖钥匙");
        user.setId(70);
        userDao.updateUser(user);
    }

    @Test
    public void deleteUserTest(){
        userDao.deleteUser(71);
    }

    @Test
    public void findByIdTest(){
        User user = userDao.findById(70);
        System.out.println(user);
    }

    @Test
    public void findUserByName(){
        //字符串拼接
//        List<User> users = userDao.FindUserByName("%"+"三"+"%");
        //占位符
        List<User> users = userDao.findUserByName("张");
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void findTotalTest(){
        int total = userDao.findTotal();
        System.out.println(total);
    }
}
