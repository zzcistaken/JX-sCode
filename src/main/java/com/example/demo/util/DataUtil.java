package com.example.demo.util;

import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class DataUtil {

    private static DataSource dataSource = null;
    private static JdbcTemplate jdbcTemplate = null;

    private static DataSource getDataSource() {
        if(dataSource == null) {
            DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
            driverManagerDataSource.setDriverClassName("org.sqlite.JDBC");




//             提取 SQLite 文件到临时目录
            ClassPathResource resource = new ClassPathResource("demo.db");
            File tempFile = null;
            try {
                tempFile = File.createTempFile("demo", ".db");
            } catch (IOException e) {
                e.printStackTrace();
            }
            tempFile.deleteOnExit(); // JVM 退出时删除临时文件

            try (InputStream inputStream = resource.getInputStream()) {
                Files.copy(inputStream, tempFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }

            // 使用临时文件的路径
            String jdbcUrl = "jdbc:sqlite:" + tempFile.getAbsolutePath();
            driverManagerDataSource.setUrl(jdbcUrl);


//            driverManagerDataSource.setUrl("jdbc:sqlite:D:\\sqliteDB\\demo.db");
            dataSource = driverManagerDataSource;
        }
        return dataSource;
    }

    public static JdbcTemplate getJdbcTemplate () {
        if(jdbcTemplate == null) {
            jdbcTemplate = new JdbcTemplate(getDataSource());
        }
        return jdbcTemplate;
    }
}
