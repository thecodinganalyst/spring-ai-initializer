package com.thecodinganalyst.spring_ai_demo;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class SpringAiDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringAiDemoApplication.class, args);
	}

	@Bean
	@Profile("!test")
	public CommandLineRunner runner(ChatClient.Builder builder) {
		return args -> {
			ChatClient chatClient = builder.build();

			long start = System.nanoTime();

			System.out.println("Calling Gemini...");

			try {
				String response = chatClient.prompt()
						.user("Tell me a joke")
						.call()
						.content();

				System.out.println("Response: " + response);
			} finally {
				long elapsedMs =
						(System.nanoTime() - start) / 1_000_000;

				System.out.println(
						"Gemini call duration: " + elapsedMs + " ms"
				);
			}
		};
	}

}
