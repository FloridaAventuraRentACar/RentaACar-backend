package backend.car_rental.security.interceptors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import backend.car_rental.exceptions.UnauthorizedException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class BotApiKeyInterceptor implements HandlerInterceptor{

    @Value("${BOT_API_KEY}")
    private String botApiKeyEnv;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        
        String apiKeyHeader = request.getHeader("apiKey");
        
        if (apiKeyHeader == null || !apiKeyHeader.equals(botApiKeyEnv)) {
            throw new UnauthorizedException("Invalid or missing API key");
        }

        return true; // continúa con el flujo normal
    }
}
