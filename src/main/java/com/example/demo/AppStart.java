package com.example.demo;

import com.google.ortools.Loader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class AppStart {

    public static void main(String[] args) {
//        try {
//            // 设置本地库路径
//            System.setProperty("java.library.path", "C:/libs");
//            Loader.loadNativeLibraries();
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.exit(1);
//        }
        SpringApplication.run(AppStart.class, args);
    }
}
