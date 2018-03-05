package Handlers;

import Commands.SetPlayingStatus;
import Main.Client;
import Main.Data;
import Main.Utility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.ReadyEvent;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.StatusType;

/**
 * Created by Vaerys on 14/07/2017.
 */
public class AnnotationListener {

    final static Logger logger = LoggerFactory.getLogger(AnnotationListener.class);

    @EventSubscriber
    public void messageRecivedEvent(MessageReceivedEvent event) {
        if (event.getChannel().isPrivate()) {
            if (Data.getGuild() == null) {
                Utility.sendMessage("Bot is not ready yet, try again in a second.", event.getChannel());
                return;
            }
            CommandHandler.command(event.getMessage());
        } else {
            if (Data.getGuild() == null) return;
//            logger.info("Message Received");
            if (event.getMessage().getMentions().size() > 0) {
                if (event.getMessage().getMentions().get(0).getLongID() == Client.client.getOurUser().getLongID()) {
                    if (Data.getConfig().isAFK()) {
                        Utility.sendMessage(Data.getConfig().getAfkMessage(), event.getMessage().getChannel());
                    } else {
                        CommandHandler.alertAll(event.getMessage());
                    }
                }
            }
        }
    }

    @EventSubscriber
    public void readyEvent(ReadyEvent event) {
        new SetPlayingStatus().execute(null, Data.getConfig().getPlayingStatus());
        if (!Client.client.getOurUser().getName().equals(Data.getConfig().getBotName())) {
            Utility.updateUsername(Data.getConfig().getBotName());
        }
        if (Data.getConfig().isAFK()) {
            Utility.setPresence(Data.getConfig().getPlayingStatus(), StatusType.IDLE);
        }else {
            Utility.setPresence(Data.getConfig().getPlayingStatus());
        }
    }
}
