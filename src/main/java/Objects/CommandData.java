package Objects;

/**
 * Created by Vaerys on 14/07/2017.
 */
public class CommandData {
    String[] names;
    String description;
    String usage;


    public CommandData setNames(String[] names) {
        this.names = names;
        return this;
    }

    public CommandData setDescription(String description) {
        this.description = description;
        return this;
    }

    public CommandData setUsage(String usage) {
        this.usage = usage;
        return this;
    }
}
