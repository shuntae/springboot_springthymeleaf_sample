package jp.co.introduction.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jp.co.introduction.base.filter.BaseInterceptor;

@Configuration
public class APIFilter implements WebMvcConfigurer {

    @Autowired
    private BaseInterceptor interceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 独自で作成したFilterクラス(BaseInterceptor)をBeanに登録
        registry.addInterceptor(interceptor);
    }
}