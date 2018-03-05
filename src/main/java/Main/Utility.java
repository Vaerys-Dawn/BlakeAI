package Main;

import Handlers.FileHandler;
import Objects.SplitFirstObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sx.blah.discord.handle.obj.ActivityType;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.StatusType;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.Image;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RequestBuffer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by Vaerys on 14/07/2017.
 */
public class Utility {

    final static Logger logger = LoggerFactory.getLogger(Utility.class);

    public static RequestBuffer.RequestFuture<IMessage> sendMessage(String message, IChannel channel) {
        return RequestBuffer.request(() -> {
            IMessage error = null;
            if (message == null) {
                return error;
            }
            if (message.length() < 2000) {
                try {
                    if (StringUtils.containsOnly(message, "\n")) {
                        return error;
                    }
                    if (message != null || !message.equals("")) {
                        return channel.sendMessage(message);
                    }
                } catch (MissingPermissionsException e) {
                    logger.debug("Error sending message to channel with id: " + channel.getLongID() + " on guild with id: " + channel.getGuild().getLongID() +
                            ".\nReason: Missing permissions.");
                    return error;
                } catch (DiscordException e) {
                    if (e.getMessage().contains("CloudFlare")) {
                        return sendMessage(message, channel).get();
                    } else {
                        e.printStackTrace();
                        logger.error(message);
                        return error;
                    }
                }
            } else {
                logger.debug("Message to be sent to channel with id: " + channel.getStringID() + "on guild with id: " + channel.getGuild().getStringID() +
                        ".\nReason: Message to large.");
                return error;
            }
            return error;
        });
    }

    public static RequestBuffer.RequestFuture<IMessage> sendDM(String message, long userID) {
        return RequestBuffer.request(() -> {
            try {
                IChannel channel = Client.getClient().getOrCreatePMChannel(Client.getClient().getUserByID(userID));
                return sendMessage(message, channel).get();
            } catch (DiscordException e) {
                if (e.getMessage().contains("CloudFlare")) {
                    return sendDM(message, userID).get();
                } else {
                    e.printStackTrace();
                    return null;
                }
            } catch (NullPointerException e) {
                logger.debug("[sendDM] " + e.getMessage());
                return null;
            }
        });
    }

    public static RequestBuffer.RequestFuture<Boolean> updateAvatar(Image avatar) {
        return RequestBuffer.request(() -> {
            try {
                Client.getClient().changeAvatar(avatar);
            } catch (DiscordException e) {
                if (e.getMessage().contains("CloudFlare")) {
                    updateAvatar(avatar);
                } else {
                    e.printStackTrace();
                    return false;
                }
            }
            return true;
        });
    }

    public static RequestBuffer.RequestFuture<Boolean> updateUsername(String botName) {
        return RequestBuffer.request(() -> {
            try {
                Client.getClient().changeUsername(botName);
            } catch (DiscordException e) {
                if (e.getMessage().contains("CloudFlare")) {
                    updateUsername(botName);
                } else {
                    e.printStackTrace();
                    return true;
                }
            }
            return false;
        });
    }

    public static RequestBuffer.RequestFuture<Boolean> deleteMessage(IMessage message) {
        return RequestBuffer.request(() -> {
            try {
                message.delete();
            } catch (MissingPermissionsException e) {
                e.printStackTrace();
                return false;
            } catch (DiscordException e) {
                if (e.getMessage().contains("CloudFlare")) {
                    deleteMessage(message);
                } else {
                    e.printStackTrace();
                    return false;
                }
            }
            return true;
        });
    }

    public static IChannel getChannelByName(String args) {
        for (IChannel c : Data.getGuild().getChannels()) {
            if (("#" + c.getName()).equalsIgnoreCase(args)) {
                return c;
            }
        }
        return null;
    }

    public static boolean checkURL(String args) {
        List<String> blacklist = FileHandler.readFromFile("website.blacklist");
        for (String s : blacklist) {
            SplitFirstObject firstWord = new SplitFirstObject(s);
            if (!firstWord.getFirstWord().startsWith("//")) {
                if (firstWord.getFirstWord() != null && firstWord.getFirstWord().length() != 0) {
                    if (args.toLowerCase().contains(firstWord.getFirstWord().toLowerCase())) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static boolean isImageLink(String link) {
        if (!checkURL(link)) {
            return false;
        }
        ArrayList<String> suffixes = new ArrayList<String>() {{
            add(".png");
            add(".gif");
            add(".jpg");
            add(".webp");
        }};
        if (link.contains("\n") || link.contains(" ")) {
            return false;
        }
        for (String s : suffixes) {
            if (link.toLowerCase().endsWith(s)) {
                return true;
            }
        }
        return false;
    }

    public static String getVersion() {
        try {
            final Properties properties = new Properties();
            properties.load(Main.class.getClassLoader().getResourceAsStream("project.properties"));
            return properties.getProperty("version");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void setPresence(String presence) {
        setPresence(presence, StatusType.ONLINE);
    }

    public static void setPresence(String presence, StatusType status) {
        if (presence == null || presence.isEmpty()) {
            Client.getClient().changePresence(status);
        } else {
            Client.getClient().changePresence(status, ActivityType.PLAYING, presence);
        }
    }
}
