package com.roshercs.screenmatch.service;

import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.ChatModel;
import com.openai.models.chat.completions.ChatCompletion;
import com.openai.models.chat.completions.ChatCompletionCreateParams;

public class ConsultChatGPT {
    public static String requestTraduction(String texto){
        OpenAIClient client = OpenAIOkHttpClient.builder()
            .apiKey("false_key")
            .build();



        //OpenAiService service=new OpenAiService("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");
        ChatCompletionCreateParams params = ChatCompletionCreateParams.builder()
            .model(ChatModel.GPT_4O_MINI)
            .addUserMessage("Traduce al español el siguiente texto: "+texto)
            .build();
        
        ChatCompletion result= client.chat().completions().create(params);
        return result.choices().get(0).message().content().orElse("");
    }
}
