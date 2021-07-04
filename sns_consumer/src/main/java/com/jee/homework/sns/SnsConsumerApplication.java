package com.jee.homework.sns;

import cn.hutool.core.convert.Convert;
import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.util.Scanner;

@Slf4j
@SpringBootApplication
@EnableDubboConfiguration
public class SnsConsumerApplication implements ApplicationRunner {

    public static void main(String[] args) {

        /**
         * 以下注释部分是打包成jar的形式的
         */
//        int dubboPort = 0;
//        int tomcatPort = 0;
//        int defaultDubboPort = 10881;
//        int defaultTomcatPort = 8080;
//        System.out.printf("请于5秒钟内输入dubbo服务监听端口号, 推荐  %d %n", defaultDubboPort);
//        Scanner scanner = new Scanner(System.in);
//        String strPort = scanner.nextLine();
//        dubboPort = Convert.toInt(strPort);
//
//        System.out.printf("请于5秒钟内输入tomcat监听端口号, 推荐  %d %n", defaultTomcatPort, defaultTomcatPort);
//        strPort = scanner.nextLine();
//        tomcatPort = Convert.toInt(strPort);
//        scanner.close();
//
//        new SpringApplicationBuilder(SnsConsumerApplication.class)
//                .properties("spring.dubbo.protocol.port=" + dubboPort)
//                .properties("server.port=" + tomcatPort)
//                .run(args);

        /**
         * 以docker形式发布的
         */
        int dubboPort = 10881;
        int tomcatPort = 8080;
        new SpringApplicationBuilder(SnsConsumerApplication.class)
                .properties("spring.dubbo.protocol.port=" + dubboPort)
                .properties("server.port=" + tomcatPort)
                .run(args);


    }
    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("All MoocClass: {}");

    }

}
