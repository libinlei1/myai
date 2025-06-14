package com.lbl.myai.entity.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.ai.chat.messages.Message;

@Data
@NoArgsConstructor
public class MessageVo {
    private String role;
    private String content;

    public MessageVo(Message message) {
        switch (message.getMessageType()){
            case USER -> role="user";
            case ASSISTANT -> role="assistant";
            default -> role="";
        }
        this.content=message.getText();
    }
}
