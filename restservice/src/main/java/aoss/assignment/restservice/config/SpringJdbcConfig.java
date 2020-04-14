package aoss.assignment.restservice.config;

/* Created by Kuyan Kirill
   Email: progingisfun@gmail.com
   Date: 12.04.2020 */

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@ComponentScan("aoss.assignment.restservice")
public class SpringJdbcConfig {

    @Bean(name = "inventorySource")
    public DataSource mysqlInventoryDataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/inventory?serverTimezone=UTC");
        dataSource.setUsername("root");
        dataSource.setPassword("adminadmin");

        return dataSource;
    }

    @Bean(name = "orderInfoSource")
    public DataSource mysqlOrderInfoDataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/orderinfo?serverTimezone=UTC");
        dataSource.setUsername("root");
        dataSource.setPassword("adminadmin");

        return dataSource;
    }

    @Bean(name = "usersInfoSource")
    public DataSource mysqlUsersDataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/users?serverTimezone=UTC");
        dataSource.setUsername("root");
        dataSource.setPassword("adminadmin");

        return dataSource;
    }

    @Bean(name = "inventoryJdbcTemplate")
    public JdbcTemplate inventoryJdbcTemplate(@Qualifier("inventorySource") DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = "orderInfoJdbcTemplate")
    public JdbcTemplate orderInfoJdbcTemplate(@Qualifier("orderInfoSource") DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = "usersJdbcTemplate")
    public JdbcTemplate usersJdbcTemplate(@Qualifier("usersInfoSource") DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }
}
