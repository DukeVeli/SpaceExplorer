package space.space.web.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class FaviconInterceptor extends HandlerInterceptorAdapter {

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler, ModelAndView modelAndView) {

        String favicon = "http://icons.iconarchive.com/icons/google/noto-emoji-travel-places/48/42598-rocket-icon.png";

        if (modelAndView != null) {
            modelAndView.addObject("favicon", favicon);
        }
    }
}
