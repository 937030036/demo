/*
 * @Description: Data source config for test
 * @Version: 
 * @Autor: Zhangchunhao
 * @Date: 2022-04-13 15:20:38
 * @LastEditors: Zhanchunhao
 * @LastEditTime: 2022-04-23 21:54:17
 */

package com.example.demo.Config;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan
@Configuration
public class DataSourceConfig {

    @ConfigurationProperties(prefix = "spring.datasource.druid")
    @Bean
    public DataSource getDataSource() {
        return new DruidDataSource();
    }
}
