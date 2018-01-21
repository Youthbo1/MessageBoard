package com.fyb.servlet;

import com.fyb.bean.Message;
import com.fyb.service.MessageService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2018/1/20.
 */
@WebServlet(name = "MessageListServlet",urlPatterns = "/message/list.do")
public class MessageListServlet extends HttpServlet {
    private MessageService messageService;
    @Override
    public void init() throws ServletException {
        super.init();

        messageService=new MessageService();
    }



    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pageStr = request.getParameter("page");//当前页码
        int page=1;//默认页码

        if(null!=pageStr&&(!"".equals(pageStr))){

            try {
                page=Integer.parseInt(pageStr);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        List<Message> messages = messageService.getMessages(page,5);//分页查询全部留言
        int count = messageService.countMessages();
        int last = count % 5 == 0 ? (count / 5) : ((count / 5) + 1);
        request.setAttribute("last", last);
        request.setAttribute("messages", messages);
        request.setAttribute("page", page);
        request.getRequestDispatcher("/WEB-INF/views/biz/message_list.jsp").forward(request, response);

    }



    @Override
    public void destroy() {
        super.destroy();
        messageService=null;
    }
}
