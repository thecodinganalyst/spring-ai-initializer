package com.thecodinganalyst.spring_ai_demo.samples;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

@Component
public class TellJoke {
    public static Supplier<String> get(ChatClient chatClient) {
        return () ->  chatClient.prompt()
                .user("Tell me a joke")
                .call()
                .content();
    }
}
