package Commands;

import Main.Data;
import Main.Utility;
import Objects.Command;
import Objects.CommandObject;

public class Shutdown implements Command {

    @Override
    public String execute(CommandObject command, String args) {
        if (command.authorID == Data.getConfig().getBotOwnerID()){
            Utility.sendMessage("Shuting Down.",command.channel);
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.exit(2);
            return null;
        }else {
            return null;
        }
    }

    @Override
    public String[] names() {
        return new String[]{"Shutdown"};
    }

    @Override
    public String description() {
        return "Shuts the bot down.";
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