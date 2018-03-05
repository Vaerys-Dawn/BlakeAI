package Objects;

import Main.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.*;

import java.util.List;


/**
 * Created by Vaerys on 29/01/2017.
 */
public class CommandObject {

    boolean isDM;
    public IMessage message;
    public long messageID;
    public IGuild guild;
    public long guildID;
    public IChannel channel;
    public long channelID;
    public IUser author;
    public long authorID;
    public String authorName;
    public String authorDisplayName;
    public String authorUserName;
    public List<IRole> authorRoles;
    public IUser botUser;
    public String notAllowed;

    public IDiscordClient client;

    final static Logger logger = LoggerFactory.getLogger(CommandObject.class);

    public CommandObject(IMessage message) {
        if (message.getChannel().isPrivate()) {
            isDM = true;
        }
        this.message = message;
        guild = message.getGuild();
        channel = message.getChannel();
        author = message.getAuthor();
        init();
    }

    public CommandObject(IMessage message, IGuild guild, IChannel channel, IUser author) {
        if (message.getChannel().isPrivate()) {
            isDM = true;
        }
        this.message = message;
        this.guild = guild;
        this.channel = channel;
        this.author = author;
        validate();
        init();
    }

    public CommandObject(CommandObject command) {
        if (message.getChannel().isPrivate()) {
            isDM = true;
        }
        this.message = command.message;
        this.channel = command.channel;
        this.author = command.author;
        this.guild = command.guild;
        validate();
        init();
    }

    private void init() {
        client = Client.getClient();
        botUser = client.getOurUser();
        messageID = message.getLongID();
        channelID = channel.getLongID();
        authorID = author.getLongID();
        authorName = author.getName();
        authorUserName = author.getName() + "#" + author.getDiscriminator();
        if (!isDM) {
            guildID = guild.getLongID();
            authorDisplayName = author.getDisplayName(guild);
            authorRoles = author.getRolesForGuild(guild);
        }
        notAllowed = "> I'm sorry " + author.getDisplayName(guild) + ", I'm afraid I can't let you do that.";
    }

    private void validate() throws IllegalStateException {
        if (message == null) throw new IllegalStateException("message can't be null");
        if (!isDM && guild == null) throw new IllegalStateException("guild can't be null");
        if (channel == null) throw new IllegalStateException("channel can't be null");
        if (author == null) throw new IllegalStateException("author can't be null");
    }

    public CommandObject setAuthor(IUser author) {
        this.author = author;
        authorName = author.getName();
        authorUserName = author.getName() + "#" + author.getDiscriminator();
        if (!isDM) {
            authorDisplayName = author.getDisplayName(guild);
            authorRoles = author.getRolesForGuild(guild);
        }
        notAllowed = "> I'm sorry " + author.getDisplayName(guild) + ", I'm afraid I can't let you do that.";
        return this;
    }

    public CommandObject setChannel(IChannel channel) {
        this.channel = channel;
        channelID = channel.getLongID();
        if (message.getChannel().isPrivate()) {
            isDM = true;
        } else {
            isDM = false;
        }
        return this;
    }

    public CommandObject setGuild(IGuild guild) {
        this.guild = guild;
        guildID = guild.getLongID();
        if (guild.getUserByID(authorID) != null) {
            authorRoles = author.getRolesForGuild(guild);
            authorDisplayName = author.getDisplayName(guild);
            notAllowed = "> I'm sorry " + author.getDisplayName(guild) + ", I'm afraid I can't let you do that.";
        }
        return this;
    }

    public CommandObject setMessage(IMessage message) {
        this.message = message;
        messageID = message.getLongID();
        return this;
    }
}
