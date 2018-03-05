package Objects;

import Handlers.FileHandler;

/**
 * Created by Vaerys on 14/07/2017.
 */
public class GlobalFile {

    static String path;

    public static Object create(String newPath, Object object) {
        path = newPath;
        if (!FileHandler.exists(path)) {
            FileHandler.writeToJson(path, object);
        } else {
            object = FileHandler.readFromJson(path, object.getClass());
            FileHandler.writeToJson(path, object);
        }
        return object;
    }

    public void flushFile(){
        FileHandler.writeToJson(path,this);
    }
}

