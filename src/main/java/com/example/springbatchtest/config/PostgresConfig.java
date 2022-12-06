package com.example.springbatchtest.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

@Configuration
@Qualifier("postgres")
@Slf4j
@PropertySource(value = "classpath:application.yml")
public class PostgresConfig
{
    @Bean
    @ConfigurationProperties(prefix = "postgres.datasource")
    @Primary
    public DataSource dataSource()
    {
        return DataSourceBuilder.create()
                .build();
    }

    @Bean("postgresJdbc")
    @Primary
    public NamedParameterJdbcTemplate getJdbcTemplate()
    {
        return new NamedParameterJdbcTemplate(dataSource());
    }

}