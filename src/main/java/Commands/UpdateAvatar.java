package Commands;

import Main.Utility;
import Objects.Command;
import Objects.CommandObject;
import org.apache.commons.io.FilenameUtils;
import sx.blah.discord.util.Image;

public class UpdateAvatar implements Command {

    @Override
    public String execute(CommandObject command, String args) {
        if (Utility.isImageLink(args)) {
            String fileType = FilenameUtils.getExtension(args);
            Image avatar = Image.forUrl(fileType,args);
            if (!Utility.updateAvatar(avatar).get()){
                return "ERROR: Avatar failed to update.";
            }
            return "Avatar updated.";
        } else {
            return "ERROR: Invalid Url.";
        }
    }

    @Override
    public String[] names() {
        return new String[]{"UpdateAvatar"};
    }

    @Override
    public String description() {
        return "Allows for updating of the avatar.";
    }

    @Override
    public String usage() {
        return "[Image URl]";
    }

    @Override
    public int minArgs() {
        return 1;
    }
}