package com.hanmanyi.invoice;

import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Log4j2
@SpringBootApplication
public class InvoiceApplication {

	@Value("${server.port}")
	private  String port;

	private static String portValue;

	@PostConstruct
	public void setPort(){
		portValue = this.port;
	}

	public static void main(String[] args) {
		SpringApplication.run(InvoiceApplication.class, args);
		StringBuffer bf = new StringBuffer();
		bf.append("启动访问： ").append("http://localhost:").append(portValue).append("/");
		log.info(bf.toString());

	}

}