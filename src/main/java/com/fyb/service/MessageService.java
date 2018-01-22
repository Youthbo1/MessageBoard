package com.fyb.service;

import com.fyb.bean.Message;
import com.fyb.dao.MessageDAO;

import java.util.Date;
import java.util.List;

/**
 * \Date: 2018/1/20
 * \
 * \Description:
 * \
 */
public class MessageService {

    private MessageDAO messageDAO;

    public MessageService() {
      messageDAO = new MessageDAO();
    }

    /**
     * 分页查询全部留言
     * @param page 当前页码
     * @param pageSize 每页记录数
     * @return
     */
    public List<Message> getMessages(int page, int pageSize) {

        return messageDAO.getMessages(page, pageSize);
    }

    /**
     * 计算所有留言数量
     * @return
     */
    public int countMessages() {
        return messageDAO.countMessages();
    }

    public boolean addMessage(Message message) {
        message.setCreateTime(new Date());
        return messageDAO.save(message);
    }

}
