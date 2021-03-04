package cn.tianqb.config;

import cn.tianqb.interceptor.SsoInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author tianqingbo3
 * @date 2021/3/4 21:57  
 * @version v1.0
 */
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private SsoInterceptor ssoInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(ssoInterceptor).addPathPatterns("/api/**");
    }
}
