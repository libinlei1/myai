package com.lbl.myai.constants;

import lombok.Getter;

@Getter

public enum ChatTypeEnum {

    CHAT("会话机器人"),
    SERVICE("智能客服");


    private final String type;

    ChatTypeEnum(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
