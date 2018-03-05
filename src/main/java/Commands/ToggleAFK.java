package Commands;

import Main.Client;
import Main.Data;
import Main.Utility;
import Objects.Command;
import Objects.CommandObject;
import POGOS.Config;
import jdk.nashorn.internal.objects.Global;
import sx.blah.discord.handle.obj.StatusType;

public class ToggleAFK implements Command {

    @Override
    public String execute(CommandObject command, String args) {
        Data.getConfig().toggleAFK();
        if (Data.getConfig().isAFK()){
            Utility.setPresence(Data.getConfig().getPlayingStatus(), StatusType.IDLE);
            return "The bot is now AFK";
        }else {
            Utility.setPresence(Data.getConfig().getPlayingStatus(), StatusType.ONLINE);
            return "The bot is no longer AFK";
        }
    }

    @Override
    public String[] names() {
        return new String[]{"ToggleAFK"};
    }

    @Override
    public String description() {
        return "Allows to toggle the presence of the bot.";
    }

    @Override
    public String usage() {
        return null;
    }

    @Override
    public int minArgs() {
        return 0;
    }
}