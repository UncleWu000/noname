package com.noname.config;


import com.noname.filter.CORSFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfiguration {

    @Bean
    FilterRegistrationBean filterRegistrationBean(){
        FilterRegistrationBean registrationBean = new  FilterRegistrationBean();

//        registrationBean.setFilter(new CORSFilter());
//        registrationBean.addUrlPatterns("/**");
//        registrationBean.setName("CORSFilter");
//        registrationBean.setEnabled(false);
//        registrationBean.setOrder(1);
        registrationBean.setFilter(new CORSFilter());
        registrationBean.addUrlPatterns("/**");
        registrationBean.setName("CORSFilter");
        registrationBean.setEnabled(false);
        registrationBean.setOrder(1);
        return registrationBean;
    }


}
