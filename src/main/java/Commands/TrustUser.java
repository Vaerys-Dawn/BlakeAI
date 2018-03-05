package Commands;

import Main.Data;
import Objects.Command;
import Objects.CommandObject;
import sx.blah.discord.handle.obj.IUser;

public class TrustUser implements Command {

    @Override
    public String execute(CommandObject command, String args) {
        if (command.authorID == Data.getConfig().getBotOwnerID()) {
            try {
                long userID = Long.parseLong(args);
                IUser user = Data.getGuild().getUserByID(userID);
                if (user == null) {
                    return "Not a valid ID.";
                } else {
                    if (user.getLongID() == Data.getConfig().getBotOwnerID()) {
                        return "You cant Un-Trust yourself.";
                    } else {
                        if (Data.getConfig().getApprovedUserIDs().contains(userID)) {
                            for (int i = 0; i < Data.getConfig().getApprovedUserIDs().size(); i++) {
                                if (Data.getConfig().getApprovedUserIDs().get(i) == userID){
                                    Data.getConfig().getApprovedUserIDs().remove(i);
                                    Data.getConfig().flushFile();
                                    return "User Un-Trusted.";
                                }
                            }
                            return "ERROR: Unknown Error.";
                        } else {
                            Data.getConfig().addApprovedUser(userID);
                            return "User Trusted.";
                        }
                    }
                }
            } catch (NumberFormatException e) {
                return "Not a valid ID.";
            }
        } else {
            return "Only the bot owner can use this command.";
        }
    }

    @Override
    public String[] names() {
        return new String[]{"TrustUser"};
    }

    @Override
    public String description() {
        return "Allows the adding and removing of users to the trusted users.\n" +
                "Only the bot owner can use this command.";
    }

    @Override
    public String usage() {
        return "[User ID]";
    }

    @Override
    public int minArgs() {
        return 1;
    }
}