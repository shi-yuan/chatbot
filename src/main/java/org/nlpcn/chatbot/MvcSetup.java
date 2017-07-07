package org.nlpcn.chatbot;

import com.corundumstudio.socketio.SocketIOServer;
import org.alicebot.ab.Bot;
import org.nlpcn.chatbot.socketio.EventHandler;
import org.nutz.ioc.Ioc;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.NutConfig;
import org.nutz.mvc.Setup;

public class MvcSetup implements Setup {

    private static final Log LOG = Logs.get();

    @Override
    public void init(NutConfig nc) {
        Ioc ioc = nc.getIoc();
        SocketIOServer server = ioc.get(SocketIOServer.class);
        server.addListeners(ioc.get(EventHandler.class));
        server.start();

        //
        Bot bot = ioc.get(Bot.class);
        bot.getBrain().printgraph();
        bot.getBrain().nodeStats();
    }

    @Override
    public void destroy(NutConfig nc) {
        nc.getIoc().get(SocketIOServer.class).stop();
    }
}
