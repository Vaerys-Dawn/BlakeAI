package POGOS;

import Objects.GlobalFile;

import java.util.ArrayList;

/**
 * Created by Vaerys on 14/07/2017.
 */
public class Config extends GlobalFile {
    private String botName = "Bot Name";
    private String playingStatus = "Nothing.";
    private String prefix = "!";
    private String afkMessage = "ZZZzzz";
    private boolean afk = false;
    private long guildID = -1;
    private long botOwnerID = 153159020528533505L;
    private ArrayList<Long> approvedUserIDs = new ArrayList<Long>() {{
        add(botOwnerID);
    }};


    public String getPrefix() {
        return prefix;
    }

    public String getBotName() {
        return botName;
    }

    public String getPlayingStatus() {
        return playingStatus;
    }

    public long getGuildID() {
        return guildID;
    }

    public long getBotOwnerID() {
        return botOwnerID;
    }

    public ArrayList<Long> getApprovedUserIDs() {
        return approvedUserIDs;
    }

    public void addApprovedUser(long userID) {
        approvedUserIDs.add(userID);
        flushFile();
    }

    public void setPlayingStatus(String playingStatus) {
        this.playingStatus = playingStatus;
        flushFile();
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
        flushFile();
    }

    public void setAfkMessage(String afkMessage) {
        this.afkMessage = afkMessage;
        flushFile();
    }

    public String getAfkMessage() {
        return afkMessage;
    }

    public boolean isAFK() {
        return afk;
    }

    public void toggleAFK() {
        afk = !afk;
        flushFile();
    }
}
