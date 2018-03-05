package Commands;

import Main.Data;
import Objects.CommandObject;
import Objects.MentionCommand;
import Objects.SplitFirstObject;

public class SetPrefix implements MentionCommand {

    @Override
    public String execute(CommandObject command, String args) {
        SplitFirstObject object = new SplitFirstObject(args);
        if (object.getRest().contains(" ") || object.getRest().contains("\n")) {
            return "Invalid Prefix.";
        } else {
            Data.getConfig().setPrefix(object.getRest());
            return "Prefix is now set to **" + object.getRest() + "**";
        }
    }

    @Override
    public String[] names() {
        return new String[]{"SetPrefix"};
    }

    @Override
    public String description() {
        return "Allows for the prefix of the bot to be changed.";
    }

    @Override
    public String usage() {
        return "[Prefix]";
    }

    @Override
    public int minArgs() {
        return 2;
    }
}