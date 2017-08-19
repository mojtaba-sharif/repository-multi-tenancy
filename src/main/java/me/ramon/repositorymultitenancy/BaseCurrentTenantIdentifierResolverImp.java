package me.ramon.repositorymultitenancy;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.stereotype.Component;

@Component
public class BaseCurrentTenantIdentifierResolverImp implements CurrentTenantIdentifierResolver {

    public static final String DEFAULT_TENANT_ID = "NON_DEFUALT_TENANT_ID";


    @Override
    public String resolveCurrentTenantIdentifier() {
        if (TenantContext.getCurrent() != null) {
            System.out.println("tenant-resolver :: " + TenantContext.getCurrent());
            return (String) TenantContext.getCurrent();
        }
        System.out.println("tenant-resolver :: " + TenantContext.getCurrent());
        return DEFAULT_TENANT_ID;
    }

    @Override
    public boolean validateExistingCurrentSessions() {
        return true;
    }
}