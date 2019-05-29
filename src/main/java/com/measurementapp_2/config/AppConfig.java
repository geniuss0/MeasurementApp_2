package com.measurementapp_2.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan(basePackages = "com")
@PropertySource("classpath:mysql-data.properties")
public class AppConfig implements WebMvcConfigurer {

    @Autowired
    private Environment env;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/resources/**")
                .addResourceLocations("/resources/");
    }

    @Bean
    public ViewResolver viewResolver() {

        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();

        viewResolver.setPrefix("/WEB-INF/view/");
        viewResolver.setSuffix(".jsp");

        return viewResolver;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(securityDataSource());
        sessionFactory.setPackagesToScan("com.measurementapp_2.entity");
        sessionFactory.setHibernateProperties(hibernateProperties());

        return sessionFactory;
    }

    private final Properties hibernateProperties() {
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        hibernateProperties.setProperty("hibernate.id.new_generator_mappings", "false");
        hibernateProperties.setProperty("hibernate.show_sql", "true");
        hibernateProperties.setProperty("hibernate.connection.CharSet", "utf8");
        hibernateProperties.setProperty("hibernate.connection.characterEncoding", "utf8");
        hibernateProperties.setProperty("hibernate.connection.useUnicode", "true");

        return hibernateProperties;
    }

    @Bean
    public PlatformTransactionManager hibernateTransactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();

        transactionManager.setSessionFactory(sessionFactory().getObject());

        return transactionManager;
    }

    @Bean
    public DataSource securityDataSource() {

        // create connection pool
        ComboPooledDataSource securityDataSourcePool = new ComboPooledDataSource("conf");

        // set the jdbc class
        try {
            securityDataSourcePool.setDriverClass(env.getProperty("jdbc.driver"));
        } catch (PropertyVetoException exc) {
            throw new RuntimeException(exc);
        }

        // set database connection props
        securityDataSourcePool.setJdbcUrl(env.getProperty("jdbc.url"));
        securityDataSourcePool.setUser(env.getProperty("jdbc.user"));
        securityDataSourcePool.setPassword(env.getProperty("jdbc.password"));

        // set connection pool props
        securityDataSourcePool.setInitialPoolSize(getIntProperty(
                "connection.pool.initialPoolSize"));
        securityDataSourcePool.setMinPoolSize(getIntProperty(
                "connection.pool.minPoolSize"));
        securityDataSourcePool.setMaxPoolSize(getIntProperty(
                "connection.pool.maxPoolSize"));
        securityDataSourcePool.setMaxIdleTime(getIntProperty(
                "connection.pool.maxIdleTime"));

        return securityDataSourcePool;
    }

    private int getIntProperty(String propName) {

        return Integer.parseInt(env.getProperty(propName));
    }
}
