package com.itcast.test;

import com.itcast.dao.IUserDao;
import com.itcast.domain.User;
import com.sun.javaws.security.Resource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

/**
 * @program: day04_eesy_03annotation_mybatis--com.itcast.test
 * @author: WaHotDog 2019-08-23 09:26
 **/


public class MybatisAnnoTest {
    /**
     * 测试基于注解mybatis使用
     * @param args
     */
    public static void main(String[] args)throws  Exception{
        //1.引入配置文件
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.创建sqlSessionFactory
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        //3.使用sqlSessionFactory创建sqlsession
        SqlSession sqlSession = factory.openSession(true);
        //4.使用sqlsession创建dao的代理工厂
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        //5.执行DAO
        List<User> users = userDao.finAll();
        for (User user : users) {
            System.out.println(user);
        }
        //6.释放资源
        sqlSession.close();
        in.close();

    }

}
