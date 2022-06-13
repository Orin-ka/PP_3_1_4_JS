package com.orinka.springboot;

import com.orinka.springboot.entity.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

// включает автоматическую настройку и сканирует ресурсы в текущем пакете
@SpringBootApplication
public class Application {

    public static void main(String[] args) throws IOException{

        org.springframework.boot.SpringApplication.run(Application.class, args);
        openHomePage();
    }
    private static void openHomePage() throws IOException {
        Runtime rt = Runtime.getRuntime();
        rt.exec("rundll32 url.dll,FileProtocolHandler " + "http://localhost:8080/");

    }

}
