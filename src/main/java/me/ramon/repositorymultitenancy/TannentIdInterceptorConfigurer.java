package me.ramon.repositorymultitenancy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.MappedInterceptor;

/**
 * Created by Ramon on 01/21/2017.
 */

@Configuration
public class TannentIdInterceptorConfigurer extends WebMvcConfigurerAdapter {
    @Autowired
    TannentIdInterceptor tannentIdInterceptor;

    @Bean
    public MappedInterceptor myMappedInterceptor() {
        return new MappedInterceptor(new String[]{"/**"}, tannentIdInterceptor);
    }
}