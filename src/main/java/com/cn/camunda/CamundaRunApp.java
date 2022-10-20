package com.cn.camunda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableProcessApplication("submit-payment-request")
public class CamundaRunApp {

//  @Autowired
//  RuntimeService runtimeService;

  public static void main(String... args) {
    SpringApplication.run(CamundaRunApp.class, args);
  }

//  @Bean
//  public Gson gson() {
//    return new GsonBuilder().setPrettyPrinting().create();
//  }


//   This is only used for testing purposes
//  @Bean
//  public CommandLineRunner createDemoProcessInstance(){
//
//    return (String[] args) -> runtimeService.startProcessInstanceByKey("submit-payment-request");
//
//  }
}
