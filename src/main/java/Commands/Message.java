package Commands;

import Main.Utility;
import Objects.Command;
import Objects.CommandObject;
import Objects.SplitFirstObject;
import sx.blah.discord.handle.obj.IChannel;

public class Message implements Command {

    @Override
    public String execute(CommandObject command, String args) {
        SplitFirstObject object = new SplitFirstObject(args);
        IChannel channel = Utility.getChannelByName(object.getFirstWord());
        if (channel == null){
            return "Invalid channel.";
        }
        Utility.sendMessage(object.getRest(),channel);
        return "Message sent.";
    }

    @Override
    public String[] names() {
        return new String[]{"Msg","Echo"};
    }

    @Override
    public String description() {
        return "Sends a message to a channel of the specified guild.";
    }

    @Override
    public String usage() {
        return "[#Channel] [Message]";
    }

    @Override
    public int minArgs() {
        return 2;
    }
}