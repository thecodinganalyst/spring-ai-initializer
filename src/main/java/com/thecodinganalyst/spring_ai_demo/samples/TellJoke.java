package com.thecodinganalyst.spring_ai_demo.samples;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Component;

@Component
public class TellJoke {
    public static String get(ChatClient chatClient) {
        try {
            return chatClient.prompt()
                    .user("Tell me a joke")
                    .call()
                    .content();
        }catch (Exception exception){
            return exception.getLocalizedMessage();
        }

    }
}
