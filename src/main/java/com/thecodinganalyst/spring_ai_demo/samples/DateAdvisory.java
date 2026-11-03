package com.thecodinganalyst.spring_ai_demo.samples;

import com.thecodinganalyst.spring_ai_demo.samples.tools.WonderlandDateTool;
import org.springframework.ai.chat.client.ChatClient;

public class DateAdvisory {
    public static String get(ChatClient chatClient){
        return chatClient.prompt("What day is tomorrow in Wonderland")
                .tools(new WonderlandDateTool())
                .call()
                .content();
    }
}
