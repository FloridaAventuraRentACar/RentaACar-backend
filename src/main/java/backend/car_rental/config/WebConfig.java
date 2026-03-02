package backend.car_rental.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import backend.car_rental.security.interceptors.ApiKeyInterceptor;
import backend.car_rental.security.interceptors.BotApiKeyInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private ApiKeyInterceptor apiKeyInterceptor;

    @Autowired
    private BotApiKeyInterceptor botApiKeyInterceptor;
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        //API KEY interna
        registry.addInterceptor(apiKeyInterceptor)
                .addPathPatterns("/**") // Todas las rutas protegidas
                .excludePathPatterns("/actuator/health", "/public/**"); // Excepción para el endpoint de health

        //API KEY para bot
        registry.addInterceptor(botApiKeyInterceptor)
                .addPathPatterns("/public/**"); // Todas las rutas protegidas
    }
}