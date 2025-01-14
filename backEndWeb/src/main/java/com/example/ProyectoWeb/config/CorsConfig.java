package com.example.ProyectoWeb.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

import java.util.Arrays;

import org.springframework.web.filter.CorsFilter;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
public class CorsConfig {

    @Bean
    public FilterRegistrationBean<CorsFilter> CorsFilter() 
    {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowCredentials(true);

        config.addAllowedOrigin("http://localhost");

        config.setAllowedHeaders(Arrays.asList(
            HttpHeaders.AUTHORIZATION, 
            HttpHeaders.CONTENT_TYPE, 
            HttpHeaders.ACCEPT
        ));

        config.setAllowedMethods(Arrays.asList(
            HttpMethod.GET.name(),
            HttpMethod.DELETE.name(),
            HttpMethod.POST.name(),
            HttpMethod.PUT.name()
            
        ));

        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<CorsFilter>(new CorsFilter(source));

        bean.setOrder(-102);

        return bean;
    }
}
