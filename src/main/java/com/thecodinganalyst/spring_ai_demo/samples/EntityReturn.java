package com.thecodinganalyst.spring_ai_demo.samples;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EntityReturn {
    public record ActorFilm(String actor, List<String> movies){}

    public static ActorFilm get(ChatClient chatClient){
        return chatClient.prompt()
                .user("Generate the filmography for a random actor")
                .call()
                .entity(ActorFilm.class);
    }

    public static List<ActorFilm> getMany(ChatClient chatClient) {
        return chatClient.prompt()
                .user("""
                Return exactly two actors:
                Tom Hanks and Tom Cruise.

                Include exactly 3 movies per actor.
                """)
                .call()
                .entity(new ParameterizedTypeReference<List<ActorFilm>>() {});
    }

}
