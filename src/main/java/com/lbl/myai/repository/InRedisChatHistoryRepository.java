package com.lbl.myai.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InRedisChatHistoryRepository implements ChatHistoryRepository{

    private final String key="chathistory:";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Override
    public void save(String type, String chatId) {
        List<String> chatIds = stringRedisTemplate.opsForList().range(key + type, 0, -1);
        if (chatIds.contains(chatId)) {
            return;
        }
        stringRedisTemplate.opsForList().rightPush(key+type, chatId);
    }

    @Override
    public List<String> getChatIds(String type) {
        List<String> chatIds = stringRedisTemplate.opsForList().range(key + type, 0, -1);
        return chatIds==null?List.of():chatIds;
    }
}
