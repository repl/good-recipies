package com.defmacros.goodrecipes.websvc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@SpringBootApplication
public class WebServiceApplication {
	private static final Logger logger = LoggerFactory.getLogger(WebServiceApplication.class);

	@Autowired
	void printJDBCUrl(Environment env) {
		logger.info("JDBC Url = " + env.getProperty("spring.datasource.url"));
	}

	public static void main(String[] args) {
		SpringApplication.run(WebServiceApplication.class, args);
	}

	@Bean(name = "restTemplate")
	public RestTemplate restTemplate() {
		final RestTemplate template = new RestTemplate();
		MappingJackson2HttpMessageConverter jsonHttpMessageConverter = new MappingJackson2HttpMessageConverter();
		template.setMessageConverters(Arrays.asList(jsonHttpMessageConverter, new FormHttpMessageConverter(),
				new StringHttpMessageConverter()));
		return template;
	}

}

