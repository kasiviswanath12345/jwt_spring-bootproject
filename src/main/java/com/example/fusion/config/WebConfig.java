//
//package com.example.fusion.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.cors.*;
//import org.springframework.web.filter.CorsFilter;
//
//import java.util.Arrays;
//
//@Configuration
//public class WebConfig {
//
//    @Bean
//    public CorsFilter corsFilter() {
//        CorsConfiguration config = new CorsConfiguration();
//        
//        // 🔐 Allowed frontend origins (adjust as needed)
//        config.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
//
//        config.setAllowCredentials(true);
//        config.addAllowedHeader("*");     // Allow all headers
//        config.addAllowedMethod("*");     // Allow all HTTP methods (GET, POST, etc.)
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", config); // Apply to all paths
//        return new CorsFilter(source);
//    }
//}
package com.example.fusion.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // ✅ Apply to all endpoints
                .allowedOrigins("*") // 🔐 Allow all origins, or specify: "http://localhost:3000"
                .allowedMethods("GET"," POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*"); // ✅ Allow all headers (e.g. Authorization)
    }
}