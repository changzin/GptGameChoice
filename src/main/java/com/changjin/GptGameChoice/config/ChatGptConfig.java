package com.changjin.GptGameChoice.config;

import org.springframework.context.annotation.Configuration;

import java.io.*;

@Configuration
public class ChatGptConfig {
    static public String apiKey;
    static public String basicPrompt;
    static {
        try {
            apiKey = new BufferedReader(new FileReader("C:\\Users\\san81\\OneDrive\\pgm\\GptGameChoice\\GptGameChoice\\src\\main\\resources\\chatGPTApiKey")).readLine();
            BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\san81\\OneDrive\\pgm\\GptGameChoice\\GptGameChoice\\src\\main\\resources\\prompt.txt"));

            basicPrompt = "";
            String str;
            while ((str = br.readLine()) != null) {
                basicPrompt = basicPrompt + str;
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
