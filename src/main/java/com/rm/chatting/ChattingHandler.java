package com.rm.chatting;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.rm.controller.RoomController;

import lombok.extern.log4j.Log4j;

public class ChattingHandler extends TextWebSocketHandler{

	private static final Logger log = LoggerFactory.getLogger(RoomController.class);

	private List<WebSocketSession> sessionList = new ArrayList<WebSocketSession>();
	
	//Ŭ���̾�Ʈ�� ���� �Ǿ��� �� ����
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessionList.add(session);
        log.info("{} �����", session.getId()); 
    }

    //Ŭ���̾�Ʈ�� ������ ������ �޽����� �������� �� ����
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        log.info("{}�� ���� {} ����", session.getId(), message.getPayload());
        //��� �������� �޼��� ���
        for(WebSocketSession sess : sessionList){
            sess.sendMessage(new TextMessage(message.getPayload()));
        }
    }

    //Ŭ���̾�Ʈ ������ ������ �� ����
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessionList.remove(session);
        log.info("{} ���� ����.", session.getId());
    }
}
