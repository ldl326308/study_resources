package com.nf.redisDemo1.spring;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.io.IOException;

@Configuration
@PropertySource("classpath:jdbc.properties")
public class SpringDaoConfig{

    /**
     * 数据源配置
     * @param environment
     * @return
     * @throws PropertyVetoException
     */
    @Bean
    public DataSource dataSource(Environment environment) throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(environment.getProperty("jdbc.driver"));
        dataSource.setJdbcUrl(environment.getProperty("jdbc.url"));
        dataSource.setUser(environment.getProperty("jdbc.user"));
        dataSource.setPassword(environment.getProperty("jdbc.password"));
        return dataSource;
    }

    /**
     * mybatis配置
     * @param dataSource
     * @return
     * @throws IOException
     */
    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource) throws IOException {
        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        factory.setTypeAliasesPackage("com.nf.redisDemo1.entity"); //实体类扫描
        factory.setMapperLocations(resolver.getResources("classpath:mapper/*.xml")); //映射文件
        factory.setConfigLocation(new ClassPathResource("mybatis-config.xml")); //mybatis 配置

        factory.setDataSource(dataSource);
        return factory;
    }

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer(){
        MapperScannerConfigurer configurer = new MapperScannerConfigurer();
        configurer.setBasePackage("com.nf.redisDemo1.dao"); //扫描接口
        configurer.setSqlSessionFactoryBeanName("sqlSessionFactoryBean");
        return configurer;
    }

    /**
     * 封装模板
     * @return
     */
    @Bean
    JedisConnectionFactory jedisConnectionFactory(){
        return new JedisConnectionFactory();
    }

    /**
     * spring 封装的 缓冲管理器
     * @return
     */
    @Bean
    CacheManager redisCacheManager(RedisConnectionFactory factory){
        return RedisCacheManager.create(jedisConnectionFactory());
    }

}
