package com.block.springboot.data.config;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import javax.servlet.Servlet;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wangrongsong
 * @title: DruidConfig
 * @projectName springboot-data
 * @description: 通过配置一下servlet和filter,可以登录http://localhost:8080/druid/，监控连接池信息
 * @date 2021-10-11 01:06
 */
@Configuration
public class DruidConfig {


    /**
     * 配置Druid的监控
     * 配置一个管理后台的Servlet
     * @return
     */
    @Bean
    public ServletRegistrationBean statViewServlet(){
        ServletRegistrationBean<Servlet> bean = new ServletRegistrationBean<>(new StatViewServlet(),"/druid/*");
        Map<String,String> initParam=new HashMap<>();
        initParam.put("loginUsername","admin");
        initParam.put("loginPassword","123456");
        initParam.put("allow","127.0.0.1");
//        initParam.put("deny","192.168.")
        bean.setInitParameters(initParam);
        return bean;
    }

    @Bean
    public FilterRegistrationBean webStatFilter(){
        FilterRegistrationBean<Filter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new WebStatFilter());
        Map<String,String> initParam=new HashMap<>();
        initParam.put("exclusions","*.js,*.css./druid/*");
        bean.setInitParameters(initParam);
        bean.setUrlPatterns(Arrays.asList("/*"));
        return bean;
    }


}
