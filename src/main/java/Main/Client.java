package Main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.util.DiscordException;

/**
 * Created by Vaerys on 19/05/2016.
 */
public class Client {

    public static IDiscordClient client;

    final static Logger logger = LoggerFactory.getLogger(Client.class);

    public static IDiscordClient getClient(String token, boolean login) throws DiscordException {
        ClientBuilder clientBuilder = new ClientBuilder();
        clientBuilder.withToken(token);
        clientBuilder.setMaxReconnectAttempts(4000);
        if (login) {
            logger.info("Logging in to Discord");
            client = clientBuilder.login();
        } else {
            client = clientBuilder.build();
        }

        return client;
    }

    public static IDiscordClient getClient() {
        return client;
    }
}
