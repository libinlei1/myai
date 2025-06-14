package com.lbl.myai.controller;

import com.lbl.myai.constants.ChatTypeEnum;
import com.lbl.myai.repository.ChatHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor;
import org.springframework.ai.content.Media;
import org.springframework.util.MimeType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/ai")
@RequiredArgsConstructor
@CrossOrigin
public class ChatController {
    private final ChatClient chatClient;

    private final ChatHistoryRepository chatHistoryRepository;

    @RequestMapping(value = "/chat", produces = "text/html;charset=utf-8")
    public Flux<String> chat(@RequestParam("prompt") String prompt,
                             @RequestParam("chatId") String chatId,
                             @RequestParam(value = "files",required = false) List<MultipartFile> files) {
        //保存会话id
        chatHistoryRepository.save(ChatTypeEnum.CHAT.toString(), chatId);
        //请求模型
        if (files != null && !files.isEmpty()) {
            return multiModalChat(prompt, chatId, files);
        } else {
            return textChat(prompt, chatId);
        }

    }

    private Flux<String> textChat(String prompt, String chatId) {
        return chatClient.prompt()
                .user(prompt)
                .advisors(advisorSpec -> advisorSpec.param(AbstractChatMemoryAdvisor.CHAT_MEMORY_CONVERSATION_ID_KEY, chatId))
                .stream()
                .content();
    }

    private Flux<String> multiModalChat(String prompt, String chatId, List<MultipartFile> files) {
        //解析多媒体
        List<Media> medias = files.stream()
                .map(file -> new Media(
                        MimeType.valueOf(Objects.requireNonNull(file.getContentType())),
                        file.getResource()
                ))
                .toList();
        //请求模型
        return chatClient.prompt()
                .user(p ->p.text(prompt).media(medias.toArray(Media[]::new)))
                .advisors(advisorSpec -> advisorSpec.param(AbstractChatMemoryAdvisor.CHAT_MEMORY_CONVERSATION_ID_KEY, chatId))
                .stream()
                .content();
    }


}
