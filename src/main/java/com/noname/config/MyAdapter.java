package com.noname.config;

import com.noname.interceptor.PaginationInterceptor;
import com.noname.interceptor.ParamInterceptor;
import com.noname.interceptor.RequestInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MyAdapter extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //registry.addInterceptor(new RequestInterceptor()).addPathPatterns("/**").excludePathPatterns("/article/list/**");
        registry.addInterceptor(new ParamInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(new PaginationInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }
}
