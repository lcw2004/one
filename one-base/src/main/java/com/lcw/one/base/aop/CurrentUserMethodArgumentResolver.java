package com.lcw.one.base.aop;

import com.lcw.one.base.utils.LoginUserUtils;
import com.lcw.one.user.entity.UserInfoEO;
import com.lcw.one.util.annotation.CurrentUser;
import com.lcw.one.util.bean.LoginUser;
import com.lcw.one.util.exception.OneBaseException;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;

import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

/**
 * 增加方法注入，将含有 @LoginUser 注解的方法参数注入当前登录用户
 * 目前暂时无用，原因如下：
 *  @SpringBootApplication
 *  public class MyBootApplication extends WebMvcConfigurerAdapter{
 *      public static void main(String[] args) {
 *      SpringApplication.run(MyBootApplication.class, args);
 *      }
 *
 *      @Override
 *       public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
 *           super.addArgumentResolvers(argumentResolvers);
 *           argumentResolvers.add(new LoginUserInfoMethodArgumentResolver());
 *       }
 *   }
 *  这种方式注入之后会导致一系列其他问题，什么问题忘了，以后在研究下。
 *
 */
@Component
public class CurrentUserMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(CurrentUser.class) && (
                parameter.getParameterType().isAssignableFrom(String.class) ||
                parameter.getParameterType().isAssignableFrom(LoginUser.class) ||
                parameter.getParameterType().isAssignableFrom(UserInfoEO.class)
        );
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);

        if (parameter.getParameterType().isAssignableFrom(String.class)) {
            return LoginUserUtils.getLoginUserId(request);
        } else if (parameter.getParameterType().isAssignableFrom(LoginUser.class)) {
            return LoginUserUtils.getCurrentUser(request);
        } else if (parameter.getParameterType().isAssignableFrom(UserInfoEO.class)) {
            return LoginUserUtils.getLoginUserInfo(request);
        } else {
            throw new OneBaseException("@CurrentUser不支持类型[" + parameter.getParameterType().getSimpleName() + "]");
        }
    }
}
