package org.nlpcn.chatbot.socketio;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import org.alicebot.ab.Chat;
import org.nlpcn.chatbot.constant.CallType;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@IocBean
public class EventHandler {

    private static final Log LOG = Logs.get();

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Inject
    private SocketIOServer server;

    @Inject
    private Chat chatSession;

    @OnConnect
    public void onConnect(SocketIOClient client) {
        LOG.infof("==========%s connected", client.getRemoteAddress());
    }

    @OnDisconnect
    public void onDisconnect(SocketIOClient client) {
        LOG.infof("==========%s disconnected", client.getRemoteAddress());
    }

    @OnEvent("message")
    public void onEvent(SocketIOClient client, AckRequest ar, Message data) {
        LOG.infof("==========receive: %s", data.getMessage());

        // 用户的消息
        data.setCallType(CallType.in);
        data.setCreateTime(LocalDateTime.now().format(FORMATTER));
        client.sendEvent("message", data);

        // 系统回复
        String message = data.getMessage();
        LOG.infof("STATE=%s:THAT=%s:TOPIC=", message, chatSession.getThatHistory().get(0).get(0), chatSession.getPredicates().get("topic"));
        String response = chatSession.multisentenceRespond(message);
        data.setMessage(response);
        data.setCallType(CallType.out);
        data.setCreateTime(LocalDateTime.now().format(FORMATTER));
        client.sendEvent("message", data);
    }
}
