package com.kingboy.common.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * @author kingboy--KingBoyWorld@163.com
 * @date 2017/11/20 上午12:52
 * @desc  德鲁伊数据库连接池配置文件.
 */
@Log4j
@Configuration
public class DruidConfiguration {

    @Bean
    public ServletRegistrationBean druidServlet() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        // IP白名单/IP黑名单 name:allow/deny  value:192.168.2.25,127.0.0.1
        servletRegistrationBean.addInitParameter("loginUsername", "kingboy");
        servletRegistrationBean.addInitParameter("loginPassword", "123456");
        servletRegistrationBean.addInitParameter("resetEnable", "false");
        return servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }

    /**
     * 德鲁伊连接池属性配置
     */
    @Setter @Getter
    @ConfigurationProperties(prefix = "spring.datasource")
    class DruidProperties {

        private String url;

        private String username;

        private String password;

        private String driverClassName;

        private int initialSize;

        private int minIdle;

        private int maxActive;

        private int maxWait;

        private int timeBetweenEvictionRunsMillis;

        private int minEvictableIdleTimeMillis;

        private String validationQuery;

        private boolean testWhileIdle;

        private boolean testOnBorrow;

        private boolean testOnReturn;

        private boolean poolPreparedStatements;

        private int maxPoolPreparedStatementPerConnectionSize;

        private String filters;

        private String connectionProperties;


        @Bean
        @Primary
        @SneakyThrows
        public DataSource dataSource() {
            DruidDataSource datasource = new DruidDataSource();
            datasource.setUrl(url);
            datasource.setUsername(username);
            datasource.setPassword(password);
            datasource.setDriverClassName(driverClassName);

            //configuration
            datasource.setInitialSize(initialSize);
            datasource.setMinIdle(minIdle);
            datasource.setMaxActive(maxActive);
            datasource.setMaxWait(maxWait);
            datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
            datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
            datasource.setValidationQuery(validationQuery);
            datasource.setTestWhileIdle(testWhileIdle);
            datasource.setTestOnBorrow(testOnBorrow);
            datasource.setTestOnReturn(testOnReturn);
            datasource.setPoolPreparedStatements(poolPreparedStatements);
            datasource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
            datasource.setConnectionProperties(connectionProperties);
            datasource.setFilters(filters);
            return datasource;
        }

    }

}
