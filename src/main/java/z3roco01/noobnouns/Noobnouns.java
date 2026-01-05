package z3roco01.noobnouns;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import z3roco01.composed.ConfigFile;
import z3roco01.noobnouns.command.NoobnounsCommands;

import java.io.IOException;

public class Noobnouns implements ModInitializer {
	public static final String MOD_ID = "noobnouns";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static final NoobnounConfig config = new NoobnounConfig();

	@Override
	public void onInitialize() {
		LOGGER.info("noob vs pro vs hacker vs god nouns battle");

        // load up small config
        try {
            ConfigFile.load("./config/noobnouns.conf", config);
        } catch (IOException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        NounStore.registerFileEvents();
        NoobnounsCommands.register();
	}
}