package Main;

import Handlers.InitCommands;
import Objects.Command;
import POGOS.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sx.blah.discord.handle.obj.IGuild;

import java.util.ArrayList;

/**
 * Created by Vaerys on 14/07/2017.
 */
public class Data {

    private static ArrayList<Command> commands = new ArrayList<>();
    private static IGuild guild;
    private static Config config;

    final static Logger logger = LoggerFactory.getLogger(Data.class);


    public Data() {
        config = (Config) Config.create(FilePaths.FILE_CONFIG, new Config());
        commands = InitCommands.init();

        for (Command c : commands) {
            if (c.validate() != null) {
                logger.error(c.getClass().getName().toUpperCase() + " - " + c.validate());
                System.exit(1);
            }
        }
    }

    public static Config getConfig() {
        return config;
    }

    public static ArrayList<Command> getCommands() {
        return commands;
    }

    public static IGuild getGuild() {
        return guild;
    }

    public static void setGuild(IGuild guild) {
        Data.guild = guild;
    }
}
