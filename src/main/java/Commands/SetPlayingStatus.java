package Commands;

import Main.Client;
import Main.Data;
import Main.Utility;
import Objects.Command;
import Objects.CommandObject;
import sx.blah.discord.handle.obj.StatusType;

public class SetPlayingStatus implements Command {

    @Override
    public String execute(CommandObject command, String args) {
        StatusType type = Client.getClient().getOurUser().getPresence().getStatus();
        if (args == null || args.isEmpty()) {
            Utility.setPresence("", type);
            Data.getConfig().setPlayingStatus("");
            return "Status removed.";
        } else {
            Utility.setPresence(args, type);
            Data.getConfig().setPlayingStatus(args);
            return "Status updated.";
        }
    }

    @Override
    public String[] names() {
        return new String[]{"SetPlayingStatus", "SetStatus"};
    }

    @Override
    public String description() {
        return "Allows for the bot's status to be set.";
    }

    @Override
    public String usage() {
        return "(Status)";
    }

    @Override
    public int minArgs() {
        return 0;
    }
}