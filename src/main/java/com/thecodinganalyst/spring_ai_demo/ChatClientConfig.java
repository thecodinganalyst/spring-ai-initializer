package com.thecodinganalyst.spring_ai_demo;

import io.micrometer.observation.ObservationRegistry;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.ToolCallingAdvisor;
import org.springframework.ai.chat.client.advisor.observation.AdvisorObservationConvention;
import org.springframework.ai.chat.client.observation.ChatClientObservationConvention;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.google.genai.GoogleGenAiChatModel;
import org.springframework.ai.model.chat.client.autoconfigure.ChatClientBuilderConfigurer;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatClientConfig {
    @Bean
    public ChatClient geminiChatClient(
            GoogleGenAiChatModel model,
            ChatClientBuilderConfigurer configurer,
            ObjectProvider<ObservationRegistry> registry,
            ObjectProvider<ChatClientObservationConvention> convention,
            ObjectProvider<AdvisorObservationConvention> advisorConvention,
            ObjectProvider<ToolCallingAdvisor.Builder<?>> toolAdvisor) {

        return createClient(model, configurer, registry,
                convention, advisorConvention, toolAdvisor);
    }

    @Bean
    public ChatClient ollamaChatClient(
            OllamaChatModel model,
            ChatClientBuilderConfigurer configurer,
            ObjectProvider<ObservationRegistry> registry,
            ObjectProvider<ChatClientObservationConvention> convention,
            ObjectProvider<AdvisorObservationConvention> advisorConvention,
            ObjectProvider<ToolCallingAdvisor.Builder<?>> toolAdvisor) {

        return createClient(model, configurer, registry,
                convention, advisorConvention, toolAdvisor);
    }

    private ChatClient createClient(
            ChatModel model,
            ChatClientBuilderConfigurer configurer,
            ObjectProvider<ObservationRegistry> registry,
            ObjectProvider<ChatClientObservationConvention> convention,
            ObjectProvider<AdvisorObservationConvention> advisorConvention,
            ObjectProvider<ToolCallingAdvisor.Builder<?>> toolAdvisor) {

        ChatClient.Builder builder = ChatClient.builder(
                model,
                registry.getIfUnique(() -> ObservationRegistry.NOOP),
                convention.getIfUnique(),
                advisorConvention.getIfUnique(),
                toolAdvisor.getIfAvailable()
        );

        return configurer.configure(builder).build();
    }

}
