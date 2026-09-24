package com.EmployeeManagement.Management.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI(HttpServletRequest request) {
        // Determine the correct scheme (http vs https) from the forwarded headers
        String scheme = request.getHeader("X-Forwarded-Proto");
        if (scheme == null) {
            scheme = request.getScheme();
        }

        String serverUrl = scheme + "://" + request.getServerName();
        int port = request.getServerPort();
        if ((scheme.equals("http") && port != 80) || (scheme.equals("https") && port != 443)) {
            serverUrl += ":" + port;
        }

        Server server = new Server();
        server.setUrl(serverUrl);
        server.setDescription("Auto-detected server");

        return new OpenAPI()
                .info(new Info()
                        .title("Employee Management API")
                        .version("1.0")
                        .description("Employee Management System REST API"))
                .servers(List.of(server));
    }
}
