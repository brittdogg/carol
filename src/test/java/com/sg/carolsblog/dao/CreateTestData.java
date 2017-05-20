/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.carolsblog.dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author trevor
 */
public class CreateTestData {

    private static ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
    private static JdbcTemplate jdbcTemplate = ctx.getBean("jdbcTemplate", JdbcTemplate.class);
    private static final Object LOCK = new Object();

    private static final String TEST_DATABASE_FILE = "testData/carolTest.sql";
    private static final String TEST_DATABASE_DATA_FILE = "testData/carolTestData.sql";

    public static void rebuildDatabase() {

        try (Scanner sc = new Scanner(
                new BufferedReader(
                        new FileReader(TEST_DATABASE_FILE))).useDelimiter("\\s*;\\s*")) {
            while (sc.hasNext()) {
                synchronized(LOCK) {
                    jdbcTemplate.update(sc.next() + ";");
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        
    }

    public static void loadTestData() {

        try (Scanner sc = new Scanner(
                new BufferedReader(
                        new FileReader(TEST_DATABASE_DATA_FILE))).useDelimiter("\\s*;\\s*")) {
            while (sc.hasNext()) {
                synchronized(LOCK) {
                    jdbcTemplate.update(sc.next() + ";");
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        
    }

//    public static void loadTagData() {
//
//        String[] LOAD_TAG_SQL = {
//            "INSERT INTO `carol`.`tag` (`tagId`, `tagName`) VALUES ('1', 'myNewTag')",
//            "INSERT INTO `carol`.`tag` (`tagId`, `tagName`) VALUES ('2', 'tagNo2')"
//        };
//
//        jdbcTemplate.batchUpdate(LOAD_TAG_SQL);
//    }
//
//    public static void testTearDown() {
//        String[] TEARDOWN_SQL = {
//            "DELETE FROM blogPost WHERE blogPostId > 0",
//            "DELETE FROM category WHERE categoryId > 0",
//            "DELETE FROM blogUser WHERE blogUserId > 0"
//        };
//
//        jdbcTemplate.batchUpdate(TEARDOWN_SQL);
//    }

}
