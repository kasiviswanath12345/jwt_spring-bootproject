
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//
//@SpringBootApplication
//public class JwtApplication {
//
//	public static void main(String[] args) {
//		SpringApplication.run(JwtApplication.class, args);
//	}
//
//}
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//
//@SpringBootApplication
//public class JwtApplication implements CommandLineRunner {
//
//    @Value("${spring.profiles.active:default}")
//    private String activeProfile;
//
//    public static void main(String[] args) {
//        SpringApplication.run(JwtApplication.class, args);
//    }
//
//    @Override
//    public void run(String... args) {
//        System.out.println("Active Spring Profile: " + activeProfile);
//    }
//}
package com.example.fusion;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JwtApplication implements CommandLineRunner {

    // Inject the active profile
    @Value("${spring.profiles.active:default}")
    private String activeProfile;

    public static void main(String[] args) {
        SpringApplication.run(JwtApplication.class, args);
    }

    // Runs automatically after app starts
    @Override
    public void run(String... args) {
        System.out.println("🔔 Active Spring Profile: " + activeProfile);
    }
}