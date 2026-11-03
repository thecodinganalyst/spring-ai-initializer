package com.thecodinganalyst.spring_ai_demo;

import com.thecodinganalyst.spring_ai_demo.samples.TellJoke;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.util.function.Supplier;

@SpringBootApplication
@Slf4j
public class SpringAiDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringAiDemoApplication.class, args);
	}

	@Bean
	@Profile("!test")
	public CommandLineRunner runner(ChatClient.Builder builder) {
		return args -> {
			ChatClient chatClient = builder.build();
			Supplier<String> result = TellJoke.get(chatClient);
			log.info(result.get());
		};
	}

}
