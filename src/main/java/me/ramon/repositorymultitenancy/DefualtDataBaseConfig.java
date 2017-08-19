package me.ramon.repositorymultitenancy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * Created by Romon on 1/14/2017.
 */
@Configuration
public class DefualtDataBaseConfig {

    @Autowired
    private Environment environment;
}
