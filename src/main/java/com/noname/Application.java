package com.noname;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.sql.DataSource;
import java.sql.*;

@Controller
@SpringBootApplication
@MapperScan("com.noname.mapper")
@RequestMapping("/")
public class Application extends SpringBootServletInitializer {

    @Autowired
    DataSource dataSource;

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Application.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @RequestMapping("/test")
    @ResponseBody
    public String test() {
        return "hello spring boot";
    }




    @RequestMapping("/testDataSource")
    @ResponseBody
    public String getArticle(){
        String sql = "SELECT * FROM article";

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            String rsStr = "Data:";
            while (rs.next()){
                rsStr +="{";
                rsStr += rs.getInt("id")+",";
                rsStr += rs.getString("type")+",";
                rsStr += rs.getString("title")+",";
                rsStr += rs.getString("main")+",";
                rsStr += rs.getInt("view_count")+",";
                rsStr += rs.getInt("like_count")+"} & ";
            }
            return rsStr;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "no data";
    }
}
