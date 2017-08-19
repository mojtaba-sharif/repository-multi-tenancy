package me.ramon.repositorymultitenancy;


import com.mchange.v2.c3p0.C3P0Registry;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.PooledDataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.engine.jdbc.connections.spi.AbstractDataSourceBasedMultiTenantConnectionProviderImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

@Component
public class BaseMultiTenantConnectionProviderImp extends AbstractDataSourceBasedMultiTenantConnectionProviderImpl {

    public static final String DEFAULT_TENANT_ID = "NON_DEFUALT_TENANT_ID";
    Log logger = LogFactory.getLog(getClass());

    private String key;
    private String driver;
    private String url;
    private String username;
    private String password;

    protected DriverManagerDataSource defaultDataSource;

    @Bean
    @Override
    public DataSource selectAnyDataSource() {
        try {
            setProperties();
            defaultDataSource = new DriverManagerDataSource();
            defaultDataSource.setUrl(url);
            defaultDataSource.setUsername(username);
            defaultDataSource.setPassword(password);
            defaultDataSource.setDriverClassName(driver);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("select any :: base-multitenant-select-any");
        return defaultDataSource;
    }

    private void setProperties() {
        key = "key-1";
        url = "jdbc:mysql://localhost:3306/boot-mysql-1";
        driver = "com.mysql.jdbc.Driver";
        username = "root";
        password = "";
    }

    private PooledDataSource proccessDataSource(String tenantIdentifier) {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource("key-2");
        try {
            comboPooledDataSource.setJdbcUrl("jdbc:mysql://localhost:3306/boot-mysql-2");
            comboPooledDataSource.setDriverClass("com.mysql.jdbc.Driver");
            comboPooledDataSource.setUser("root");
            comboPooledDataSource.setPassword("");
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        return comboPooledDataSource;
    }

    @Override
    public synchronized DataSource selectDataSource(String tenantIdentifier) {
        PooledDataSource pooledDataSource = C3P0Registry.pooledDataSourceByName(tenantIdentifier);
        if (pooledDataSource == null) {
            pooledDataSource = proccessDataSource(tenantIdentifier);
        }
        System.out.println("select-data-source-base-multi-tenant");
        return pooledDataSource;
    }

}