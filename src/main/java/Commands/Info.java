package Commands;

import Main.Utility;
import Objects.Command;
import Objects.CommandObject;

public class Info implements Command {

    @Override
    public String execute(CommandObject command, String args) {
        return "Bot Created by user: **@Dawn Felstar#1235**.\n" +
                "This bot is written entirely with java using the Discord4J Libraries.\n" +
                "Support Discord: <https://discord.gg/XSyQQrR>\n\n" +
                "Bot Version: " + Utility.getVersion();
    }

    @Override
    public String[] names() {
        return new String[]{"Info"};
    }

    @Override
    public String description() {
        return "Gives some information about the bot.";
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