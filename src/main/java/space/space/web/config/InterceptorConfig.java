package space.space.web.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import space.space.web.interceptor.FaviconInterceptor;
import space.space.web.interceptor.TitleInterceptor;

@AllArgsConstructor
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    private final FaviconInterceptor faviconInterceptor;
    private final TitleInterceptor titleInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(faviconInterceptor);
        registry.addInterceptor(titleInterceptor);
    }


}
