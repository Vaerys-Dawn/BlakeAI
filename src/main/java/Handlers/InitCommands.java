package Handlers;

import Commands.*;
import Objects.Command;

import java.util.ArrayList;

/**
 * Created by Vaerys on 14/07/2017.
 */
public class InitCommands {

    public static ArrayList<Command> init() {
        ArrayList<Command> commands = new ArrayList<>();
        commands.add(new Message());
        commands.add(new UpdateAvatar());
        commands.add(new SetPlayingStatus());
        commands.add(new SetPrefix());
        commands.add(new Commands());
        commands.add(new Help());
        commands.add(new SetAFKMessage());
        commands.add(new ToggleAFK());
        commands.add(new TrustUser());
        commands.add(new Info());
        commands.add(new Shutdown());

        return commands;
    }
}
