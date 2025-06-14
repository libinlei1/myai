package com.lbl.myai.repository;

import java.util.List;


public interface ChatHistoryRepository {

    /**
     * 保存会话记录
     * @param type 业务类型，如：chat,service,pdf
     * @param chatId
     */
    void save(String type,String chatId);

    /**
     * 获取会话id列表
     * @param type 业务类型，如：chat,service,pdf
     * @return
     */
    List<String> getChatIds(String type);



}
