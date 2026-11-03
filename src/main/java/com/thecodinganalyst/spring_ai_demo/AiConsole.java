package com.thecodinganalyst.spring_ai_demo;

import com.thecodinganalyst.spring_ai_demo.samples.EntityReturn;
import com.thecodinganalyst.spring_ai_demo.samples.TellJoke;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
@Slf4j
public class AiConsole {

    private final ChatClient gemini;
    private final ChatClient ollama;
    private final ApplicationContext context;
    private ChatClient selectedChatClient;

    public AiConsole(
            @Qualifier("geminiChatClient") ChatClient gemini,
            @Qualifier("ollamaChatClient") ChatClient ollama,
            ApplicationContext context) {

        this.gemini = gemini;
        this.ollama = ollama;
        this.context = context;
    }

    public void start(){
        try(Scanner scanner = new Scanner(System.in)){
            selectedChatClient = chooseModel(scanner);
            boolean exit = false;
            while(!exit){
                exit = chooseWork(scanner);
            }
            SpringApplication.exit(context);
        }
    }

    private boolean chooseWork(Scanner scanner){
        log.info("What do you want to do?");
        log.info("1. Tell a joke");
        log.info("2. Test entity return");
        log.info("3. Test multiple entity returns");
        log.info("m. Choose another model");
        log.info("x. Exit");
        log.info("Enter 1, 2, 3, m or x");
        String choice = scanner.nextLine();
        return switch (choice) {
            case "1" -> {
                log.info(TellJoke.get(selectedChatClient));
                yield false;
            }
            case "2" -> {
                log.info(EntityReturn.get(selectedChatClient).toString());
                yield false;
            }
            case "3" -> {
                List<EntityReturn.ActorFilm> actorFilmList = EntityReturn.getMany(selectedChatClient);
                actorFilmList.forEach(actorFilm -> log.info(actorFilm.toString()));
                yield false;
            }
            case "m", "M" -> {
                selectedChatClient = chooseModel(scanner);
                yield false;
            }
            case "x", "X" -> true;
            default -> {
                log.info("Not a valid input");
                yield false;
            }
        };
    }

    private ChatClient chooseModel(Scanner scanner) {
        while(true){
            log.info("Select your AI model");
            log.info("1. Gemini 3.1 flash lite");
            log.info("2. Ollama qwen3:1.7b");
            log.info("Enter 1 or 2");

            String choice = scanner.nextLine().trim();
            switch (choice){
                case "1":
                    log.info("Gemini 3.1 flash lite chosen");
                    return gemini;
                case "2":
                    log.info("Ollama qwen3:1.7b chosen");
                    return ollama;
                default:
                    log.info("Not a valid input");
            }
        }

    }
}
