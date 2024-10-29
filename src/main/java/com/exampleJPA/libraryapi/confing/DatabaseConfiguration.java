package com.exampleJPA.libraryapi.confing;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfiguration {

    @Value("${spring.datasource.url}")
    String url;
    @Value("${spring.datasource.username}")
    String userName;
    @Value("${spring.datasource.password}")
    String password;
    @Value("${spring.datasource.driver-class-name}")
    String driver;

    //Data Source padrão
   // @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dmd = new DriverManagerDataSource();
        dmd.setUrl(url);
        dmd.setUsername(userName);
        dmd.setPassword(password);
        dmd.setDriverClassName(driver);
        return dmd;
    }

    // Configurando o data source com o hikari, o data source que é recomendado com o Spring
    @Bean
    public HikariDataSource hikariDataSource(){
        HikariDataSource hds = new HikariDataSource();

        hds.setJdbcUrl(url);
        hds.setUsername(userName);
        hds.setPassword(password);
        hds.setDriverClassName(driver);

        // configurando o pool
        hds.setMaximumPoolSize(10); //maximo de conexões liberadas
        hds.setMinimumIdle(1); // tamanho inicial do pool
        hds.setPoolName("library-db-pool");
        hds.setMaxLifetime(600000); // 600 mil mile segundos (10 minutos)
        hds.setConnectionTimeout(100000); // timeout para conseguir uma conexão
        hds.setConnectionTestQuery("select 1"); //query de teste

        return hds;
    }
}
