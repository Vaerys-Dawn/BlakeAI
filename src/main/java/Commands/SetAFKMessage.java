package Commands;

import Main.Data;
import Objects.Command;
import Objects.CommandObject;

public class SetAFKMessage implements Command {

    @Override
    public String execute(CommandObject command, String args) {
        Data.getConfig().setAfkMessage(args);
        return "AFK Message Updated";
    }

    @Override
    public String[] names() {
        return new String[]{"SetAFKMessage"};
    }

    @Override
    public String description() {
        return "Allows the afk message to be updated.";
    }

    @Override
    public String usage() {
        return "[Message]";
    }

    @Override
    public int minArgs() {
        return 1;
    }
}