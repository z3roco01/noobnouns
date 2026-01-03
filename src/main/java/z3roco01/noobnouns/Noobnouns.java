package z3roco01.noobnouns;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Noobnouns implements ModInitializer {
	public static final String MOD_ID = "noobnouns";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("noob vs pro vs hacker vs god nouns battle");
	}
}