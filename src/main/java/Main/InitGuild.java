package Main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.ReadyEvent;
import sx.blah.discord.handle.obj.IGuild;

public class InitGuild {

    final static Logger logger = LoggerFactory.getLogger(Data.class);

    @EventSubscriber
    public void onReadyEvent(ReadyEvent event){
        IGuild guild = Client.getClient().getGuildByID(Data.getConfig().getGuildID());
        if (guild == null) {
            logger.error("!!!GUILD ID IS INVALID!!!");
            System.exit(1);
        }
        Data.setGuild(guild);
    }
}
