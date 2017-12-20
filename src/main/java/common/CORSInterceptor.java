/*
package common;

import com.google.common.base.Joiner;
import com.souche.optimus.core.engine.BeanPathResolver;
import com.souche.weidian.common.annotation.CORS;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

*/
/**
 * 对 "/api/**" 的请求判断是否开启跨域
 *
 * @author Ye HaiNing
 * @date 2016/11/24
 *//*

public class CORSInterceptor implements HandlerInterceptor {

   final private static ApplicationContext applicationContext;
   final private static BeanPathResolver beanPathResolver;

   static {
       applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(ContextLoader.getCurrentWebApplicationContext().getServletContext());
       beanPathResolver = new BeanPathResolver(applicationContext);
   }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Object bean = CORSInterceptor.beanPathResolver.lookupBeanByPath(request.getRequestURI(), request.getMethod());
        Method method = beanPathResolver.getRequestMethod(bean, request.getRequestURI(), request.getMethod());
        if (method != null) {
            CORS annotation = method.getAnnotation(CORS.class);
            System.out.println(annotation);
        }
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;

            CORS cors = handlerMethod.getMethodAnnotation(CORS.class);
            if (cors == null) {
                cors = handlerMethod.getBeanType().getAnnotation(CORS.class);
            }

            if (cors != null) {
                for (String domain : cors.origin()) {
                    response.setHeader("Access-Control-Allow-Origin", domain);
                }
                response.setHeader("Access-Control-Allow-Methods", Joiner.on(',').join(cors.methods()));
                response.setHeader("Access-Control-Max-Age", String.valueOf(cors.maxAge()));
                response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
            }
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
*/
