package org.nlpcn.chatbot;

import org.nutz.mvc.annotation.Encoding;
import org.nutz.mvc.annotation.IocBy;
import org.nutz.mvc.annotation.Modules;
import org.nutz.mvc.annotation.SetupBy;
import org.nutz.mvc.ioc.provider.ComboIocProvider;

@Modules
@IocBy(type = ComboIocProvider.class, args = {"*js", "ioc/", "*anno", "org.nlpcn.chatbot"})
@Encoding(input = "UTF-8", output = "UTF-8")
@SetupBy(MvcSetup.class)
public class MainModule {
}
