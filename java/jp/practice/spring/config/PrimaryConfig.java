package jp.practice.spring.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * DB接続設定
 * 
 */

@Configuration
@MapperScan(basePackages = { "jp.practice.spring.mybatis.mapper" }, sqlSessionFactoryRef = "sqlSessionFactory")
public class PrimaryConfig {

}
