package Commands;

import Main.Data;
import Objects.Command;
import Objects.CommandObject;

public class Help implements Command {

    @Override
    public String execute(CommandObject command, String args) {
        for (Command c : Data.getCommands()) {
            for (String s : c.names()) {
                if (args.equalsIgnoreCase(s)) {
                    command.channel.sendMessage("", c.getHelp(), false);
                    return null;
                }
            }
        }
        return "Could not find command.";
    }

    @Override
    public String[] names() {
        return new String[]{"Help"};
    }

    @Override
    public String description() {
        return "Shows the information for a certain command.";
    }

    @Override
    public String usage() {
        return "[Command Name]";
    }

    @Override
    public int minArgs() {
        return 1;
    }
}