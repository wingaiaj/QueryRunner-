package com.zx.dbutils;

import com.zx.bean.Customer;
import com.zx.util.JDBCUtils;
import com.zx.util.JDBCUtilsDruid;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.*;
import org.junit.Test;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @ClassName QueryRunner的测试
 * @Description TODO
 * @Author xpower
 * @Date 2022/6/1 10:31
 * @Version 1.0
 */
public class QueryRunnerTest {
    //增删改测试
    @Test
    public void TestInsert() {
        Connection connection = null;
        try {
            QueryRunner queryRunner = new QueryRunner();
            connection = JDBCUtilsDruid.getConnection();
            //修改为读已提交
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            //自动提交
            connection.setAutoCommit(true);
//            String sql = "update customers set name = 'zxs' where id = ?";
            String sql = "insert into customers(name,email,birth)values(?,?,?)";

            int zxStart = queryRunner.update(connection, sql, "query", "query@123.com", "1900-1-1");

            System.out.println(zxStart);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection, null);
        }
    }

    @Test
    public void selectTest() {
        Connection connection = null;
        try {
            QueryRunner queryRunner = new QueryRunner();
            connection = JDBCUtilsDruid.getConnection();
            String sql = "select id,name,email,birth from customers where id = ? ";
            BeanHandler<Customer> beanHandler = new BeanHandler<>(Customer.class);
            Customer query = queryRunner.query(connection, sql, beanHandler, 31);
            System.out.println(query);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection, null);
        }
    }

    @Test
    public void selectTest1() {
        Connection connection = null;
        try {
            QueryRunner queryRunner = new QueryRunner();
            connection = JDBCUtilsDruid.getConnection();
            String sql = "select id,name,email,birth from customers where id < ? ";
//            BeanHandler<Customer> beanHandler = new BeanHandler<>(Customer.class);
            BeanListHandler<Customer> beanListHandler = new BeanListHandler<>(Customer.class);
            List<Customer> query = queryRunner.query(connection, sql, beanListHandler, 31);
            query.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection, null);
        }
    }

    @Test
    public void selectTest2() {
        Connection connection = null;
        try {
            QueryRunner queryRunner = new QueryRunner();
            connection = JDBCUtilsDruid.getConnection();
            String sql = "select id,name,email,birth,photo from customers where id = ? ";
            MapHandler handler = new MapHandler();
            Map<String, Object> query = queryRunner.query(connection, sql, handler, 32);
            System.out.println(query);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection, null);
        }
    }

    @Test
    public void selectTest3() {
        Connection connection = null;
        try {
            QueryRunner queryRunner = new QueryRunner();
            connection = JDBCUtilsDruid.getConnection();
            String sql = "select id,name,email,birth,photo from customers where id < ? ";
            MapListHandler mapListHandler = new MapListHandler();
            List<Map<String, Object>> query = queryRunner.query(connection, sql, mapListHandler, 32);
            query.forEach(System.out::println);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection, null);
        }
    }

    @Test
    public void selectTest4() {
        Connection connection = null;
        try {
            QueryRunner queryRunner = new QueryRunner();
            connection = JDBCUtilsDruid.getConnection();
            String sql = "select id,name,email,birth,photo from customers where id = ? ";
            ArrayHandler arrayHandler = new ArrayHandler();
            Object[] query = queryRunner.query(connection, sql, arrayHandler, 24);
            System.out.println(Arrays.toString(query));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection, null);
        }
    }

    @Test
    public void selectTest5() {
        Connection connection = null;
        try {
            QueryRunner queryRunner = new QueryRunner();
            connection = JDBCUtilsDruid.getConnection();
            String sql = "select id,name,email,birth,photo from customers where id < ? ";
            ArrayListHandler arrayHandler = new ArrayListHandler();
            List<Object[]> query = queryRunner.query(connection, sql, arrayHandler, 24);
            for (Object[] objects : query) {
                System.out.println(Arrays.toString(objects));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection, null);
        }
    }
}
