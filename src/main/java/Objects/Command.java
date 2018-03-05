package Objects;

import Main.Data;
import sx.blah.discord.api.internal.json.objects.EmbedObject;
import sx.blah.discord.util.EmbedBuilder;

/**
 * Created by Vaerys on 14/07/2017.
 */
public interface Command {

    String execute(CommandObject command, String args);

    String[] names();

    String description();

    String usage();

    int minArgs();

    default String info() {
        if (usage() == null || usage().isEmpty()) {
            return ">> **" + getCall() + "** <<";
        } else {
            return ">> **" + getCall() + " " + usage() + "** <<";
        }
    }

    default String noArgs() {
        return info();
    }

    default String validate() {
        if (names().length == 0 || names()[0].isEmpty()) {
            return "NAME IS EMPTY";
        }
        if (description() == null || description().isEmpty()) {
            return "DESCRIPTION IS EMPTY";
        }
        if (minArgs() > 0 && (usage() == null || usage().isEmpty())) {
            return "USAGE IS NULL WHEN MIN_ARGS IS NOT EQUAL TO 0";
        }
        return null;
    }

    default String getCall() {
        return Data.getConfig().getPrefix() + names()[0];
    }

    default boolean getCommand(String args) {
        SplitFirstObject command = new SplitFirstObject(args);
        for (String s : names()) {
            if ((Data.getConfig().getPrefix() + s).equalsIgnoreCase(command.getFirstWord())) {
                return true;
            }
        }
        return false;
    }

    default EmbedObject getHelp() {
        EmbedBuilder builder = new EmbedBuilder();
        builder.withTitle("Command: " + names()[0]);
        if (usage() != null && !usage().isEmpty()) {
            builder.appendField(getCall() + " " + usage(), "**Desc:** " + description(), false);
        } else {
            builder.appendField(getCall(), "**Desc:** " + description(), false);
        }
        return builder.build();
    }
}
