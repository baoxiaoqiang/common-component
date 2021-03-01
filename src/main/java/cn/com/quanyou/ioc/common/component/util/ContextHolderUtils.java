package cn.com.quanyou.ioc.common.component.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Description //service层获取request工具类
 * @Author yangli
 * @Date 2019/9/4-17:39
 **/
public class ContextHolderUtils {

    public static String getCurrentUserName() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("gw_userName");
    }

    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

    }

    public static HttpSession getSession() {
        return getRequest().getSession();
    }
}
