package cn.tianqb.interceptor;

import cn.tianqb.common.LoginContext;
import cn.tianqb.exception.NotLoginException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author tianqingbo3
 * @date 2021/3/4 21:57  
 * @version v1.0
 */
@Component
@Slf4j
public class SsoInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        if (ObjectUtils.isEmpty(token)) {
            throw new NotLoginException(HttpStatus.UNAUTHORIZED.value(), "not login!");
        }
        HttpSession session = request.getSession();
        Object object = session.getAttribute(token);
        if (ObjectUtils.isEmpty(object)) {
            throw new NotLoginException(HttpStatus.UNAUTHORIZED.value(), "not login!");
        }
        LoginContext loginContext = (LoginContext) object;
        log.info("当前登录用户：{}", loginContext);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        LoginContext.remove();
    }
}
