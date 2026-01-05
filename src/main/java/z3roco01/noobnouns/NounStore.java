package z3roco01.noobnouns;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.entity.player.PlayerEntity;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Holds the pronouns for all players
 */
public class NounStore {
    private static final File jsonFile = new File("./pronouns.json");
    /**
     * Maps a players UUID tho their pronouns
     */
    private static final HashMap<UUID, Nouns> nounMap = new HashMap<>();

    /**
     * Gets or creates a new Nouns object for this player
     */
    private static Nouns getOrCreate(PlayerEntity player) {
        UUID uuid = player.getUuid();
        if(!nounMap.containsKey(uuid))
            nounMap.put(uuid, new Nouns("", ""));

        return nounMap.get(uuid);
    }

    /**
     * Sets a players pronouns with the player object instead of raw uuid
     */
    public static void setPronouns(PlayerEntity player, String pronouns) {
        Noobnouns.LOGGER.info(new Gson().toJson(NounStore.nounMap));
        getOrCreate(player).pronouns = pronouns;
    }

    /**
     * Gets a players pronouns with the player object
     */
    public static String getPronouns(PlayerEntity player) {
        return getOrCreate(player).pronouns;
    }

    /**
     * Sets a players name with the player object instead of raw uuid
     */
    public static void setName(PlayerEntity player, String name) {
        getOrCreate(player).name = name;
    }

    /**
     * Gets a players name with the player object
     */
    public static String getName(PlayerEntity player) {
        String name = getOrCreate(player).name;
        // if there name would be empty, return their accounts name
        if(name.isBlank())
            return player.getGameProfile().name();

        return name;
    }

    /**
     * Regsiters events to save/load the file per world
     */
    public static void registerFileEvents() {
        ServerLifecycleEvents.SERVER_STARTED.register(server -> {
            try {
                // ensure the file exists
                if(createIfNotPresent()) {
                    // if it needed to be created, write empty json
                    saveToFile();
                }
                // then read from it
                loadFromFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        ServerLifecycleEvents.SERVER_STOPPED.register(server -> {
            try {
                saveToFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    /**
     * Creates the json file if it does not exist
     */
    private static boolean createIfNotPresent() throws IOException {
        // create any parent directories if needed
        jsonFile.toPath().getParent().toFile().mkdirs();

        if(!jsonFile.exists())
            return jsonFile.createNewFile();
        return false;
    }

    /**
     * Saves the pronouns to the file
     */
    private static void saveToFile() throws IOException {
        FileWriter writer = new FileWriter(jsonFile);
        // write it then save it
        writer.write(new Gson().toJson(nounMap));

        writer.close();
    }

    /**
     * Loads the pronouns from the file
     */
    private static void loadFromFile() throws IOException {
        // read in the contetns, kinda scuffed way but whatever
        String contents = new String(Files.readAllBytes(jsonFile.toPath()));

        // type needed for parsing json maps
        Type type = new TypeToken<Map<UUID, Nouns>>(){}.getType();
        nounMap.clear();
        nounMap.putAll(new Gson().fromJson(contents, type));
    }

    /**
     * Object to store players name and pronouns, used for serializiation
     */
    static class Nouns {
        public String name;
        public String pronouns;

        public Nouns(String name, String pronouns) {
            this.name = name;
            this.pronouns = pronouns;
        }
    }
}
