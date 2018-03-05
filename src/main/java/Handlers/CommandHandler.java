package Handlers;

import Main.Data;
import Main.Utility;
import Objects.Command;
import Objects.CommandObject;
import Objects.SplitFirstObject;
import POGOS.Config;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IUser;

/**
 * Created by Vaerys on 14/07/2017.
 */
public class CommandHandler {

    public static void command(IMessage message) {

        CommandObject object = new CommandObject(message);
        Config config = Data.getConfig();

        //checks to see if is an approved user
        if (!config.getApprovedUserIDs().contains(message.getAuthor().getLongID())) {
            if (config.getBotOwnerID() != message.getAuthor().getLongID()) {
                return;
            }
        }


        SplitFirstObject args = new SplitFirstObject(message.getContent());
        for (Command c : Data.getCommands()) {
            //get correct command.
            if (c.getCommand(message.getContent())) {
                //execute if has enough arguments
                if ((c.minArgs() > 0) && (args.getRest() == null || c.minArgs() > args.getRest().split(" ").length)) {
                    Utility.sendMessage(c.noArgs(), object.channel);
                    return;
                } else {
                    Utility.sendMessage(c.execute(object, args.getRest()), object.channel);
                    return;
                }
            }
        }
    }

    public static void alertAll(IMessage message) {
        if (!Data.getConfig().getApprovedUserIDs().contains(Data.getConfig().getBotOwnerID())) {
            Data.getConfig().addApprovedUser(Data.getConfig().getBotOwnerID());
        }
        String alert = "We were mentioned in #" + message.getChannel().getName();
        for (long l : Data.getConfig().getApprovedUserIDs()) {
            IUser user = Data.getGuild().getUserByID(l);
            IChannel channel = user.getOrCreatePMChannel();
            Utility.sendMessage(alert,channel);
        }
    }
}
