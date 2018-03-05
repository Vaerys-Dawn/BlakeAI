package Commands;

import Main.Data;
import Objects.Command;
import Objects.CommandObject;
import sx.blah.discord.util.EmbedBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

public class Commands implements Command {

    @Override
    public String execute(CommandObject command, String args) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.withTitle("Commands:");
        String list = "";
        ArrayList<String> commands = Data.getCommands().stream().map(c -> "\n" + c.getCall()).collect(Collectors.toCollection(ArrayList::new));
        Collections.sort(commands);
        for (String s : commands) {
            list += s;
        }
        list += "\n\n" + new Help().info();

        builder.withDesc(list);
        command.channel.sendMessage("",builder.build(),false);
        return null;
    }

    @Override
    public String[] names() {
        return new String[]{"Commands"};
    }

    @Override
    public String description() {
        return "Sends all the available commands";
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