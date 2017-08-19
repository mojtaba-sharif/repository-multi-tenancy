package me.ramon.repositorymultitenancy;


import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class TannentIdInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String tenantId = request.getHeader("tenantId");
        System.out.println("tenant-interceptor ::  " + tenantId);
        TenantContext.setCurrent(tenantId);
        return true;
    }

}