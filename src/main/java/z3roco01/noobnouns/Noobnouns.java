package z3roco01.noobnouns;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.server.MinecraftServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import z3roco01.composed.file.ConfigFile;
import z3roco01.noobnouns.command.NoobnounsCommands;

import java.io.IOException;

public class Noobnouns implements ModInitializer {
	public static final String MOD_ID = "noobnouns";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static final NoobnounConfig CONFIG = new NoobnounConfig();
    public static MinecraftServer server = null;

	@Override
	public void onInitialize() {
		LOGGER.info("noob vs pro vs hacker vs god nouns battle");

        // load up small config
        try {
            ConfigFile.load("./config/noobnouns.conf", CONFIG);
        } catch (IOException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        // register event to get access to the server object
        ServerLifecycleEvents.SERVER_STARTING.register(server -> Noobnouns.server = server);

        // null the server reference once the server is stopped
        ServerLifecycleEvents.SERVER_STOPPED.register(server -> Noobnouns.server = null);

        NounStore.registerFileEvents();
        NoobnounsCommands.register();
	}
}