package com.lbl.myai.controller;

import com.lbl.myai.entity.vo.MessageVo;
import com.lbl.myai.repository.ChatHistoryRepository;
import com.lbl.myai.utils.RedisChatMemory;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.messages.Message;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ai/history")
@RequiredArgsConstructor
@CrossOrigin
public class ChatHistoryController {

    private final ChatHistoryRepository chatHistoryRepository;

    private final RedisChatMemory redisChatMemory;

    @GetMapping("/{type}")
    public List<String> getChatIds(@PathVariable("type") String type) {
        return chatHistoryRepository.getChatIds(type);
    }

    @GetMapping("/{type}/{chatId}")
    public List<MessageVo> getChatHistory(@PathVariable("type") String type,@PathVariable("chatId") String chatId) {
        List<Message> messages= redisChatMemory.get(chatId,Integer.MAX_VALUE);
        if (messages==null){
            return List.of();
        }
        return messages.stream().map(MessageVo::new).toList();
    }

}
