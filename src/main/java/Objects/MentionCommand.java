package Objects;

import Main.Client;

/**
 * Created by Vaerys on 15/07/2017.
 */
public interface MentionCommand extends Command {
    @Override
    default String info() {
        return ">> **@" + Client.client.getOurUser().getName() + " " + names()[0] + " " + usage() + "** <<";
    }

    @Override
    default String getCall() {
        return "@" + Client.client.getOurUser().getName() + " " + names()[0];
    }

    @Override
    default boolean getCommand(String args) {
        SplitFirstObject firstObject = new SplitFirstObject(args);
        if (firstObject.getRest() == null) {
            return false;
        }
        SplitFirstObject secondObject = new SplitFirstObject(firstObject.getRest());
        String toTest = firstObject.getFirstWord() + " " + secondObject.getFirstWord();
        for (String s : names()) {
            String commandCall = Client.client.getOurUser().mention(false) + " " + s;
            if (toTest.equalsIgnoreCase(commandCall)) {
                return true;
            }
        }
        return false;
    }
}
