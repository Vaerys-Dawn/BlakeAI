package Main;

import Handlers.AnnotationListener;
import Handlers.FileHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.EventDispatcher;
import sx.blah.discord.util.DiscordException;

/**
 * Created by Vaerys on 14/07/2017.
 */
public class Main {

    // TODO: 14/07/2017 That being said that it can only be allowed by a select number of people (confirgurable) | (in the works)
    // TODO: 14/07/2017 if the person who is controlling the bot, leaves they can enter in a command, so that when someone tags the bot it sends a static message back for example "zzzZZZzzz"
    // TODO: 15/07/2017 Then enter the command again to turn it off

    final static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        //init files
        FileHandler.createDirectory(FilePaths.DIR_STORAGE);
        FileHandler.initFile(FilePaths.FILE_TOKEN);

        //get token, test if token exists
        String token = FileHandler.readFromFile(FilePaths.FILE_TOKEN).get(0);

        //create client
        IDiscordClient client = Client.getClient(token, false);

        //register EventListener
        EventDispatcher dispatcher = client.getDispatcher();
        dispatcher.registerTemporaryListener(new InitGuild());
        dispatcher.registerListener(new AnnotationListener());

        //log client in

        try {
            client.login();
        } catch (DiscordException e) {
            if (e.getErrorMessage().contains("Unauthorized")) {
                logger.error("!!!BOT TOKEN NOT VALID PLEASE CHECK \"Storage/Token.txt\" AND UPDATE THE TOKEN!!!");
                return;
            } else {
                e.printStackTrace();
                return;
            }
        }

        new Data();
    }
}
