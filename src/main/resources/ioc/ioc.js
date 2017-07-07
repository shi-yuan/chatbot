var ioc = {
    conf: {
        type: "org.nutz.ioc.impl.PropertiesProxy",
        fields: {
            paths: ["application.properties"]
        }
    },

    /**
     * SocketIO
     */
    socketIOConfig: {
        type: "com.corundumstudio.socketio.Configuration",
        fields: {
            hostname: {java: "$conf.get('socketio.host')"},
            port: {java: "$conf.get('socketio.port')"}
        }
    },
    socketIOServer: {
        type: "com.corundumstudio.socketio.SocketIOServer",
        args: [
            {refer: "socketIOConfig"}
        ],
        fields: {}
    },

    /**
     * Alice
     */
    bot: {
        type: "org.alicebot.ab.Bot",
        args: [
            {java: "$conf.get('bot.name')"},
            {java: "$conf.get('bot.aimlpath')"},
            {java: "$conf.get('bot.configpath')"},
            {java: "$conf.get('bot.setspath')"},
            {java: "$conf.get('bot.mapspath')"},
            {java: "$conf.get('bot.propspath')"}
        ]
    },
    chatSession: {
        type: "org.alicebot.ab.Chat",
        args: [
            {refer: "bot"}
        ]
    }
};
