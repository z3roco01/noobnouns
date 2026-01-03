package z3roco01.noobnouns.command;

import com.google.gson.Gson;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.CommandManager;
import z3roco01.noobnouns.Noobnouns;
import z3roco01.noobnouns.PronounStore;
import z3roco01.noobnouns.util.StringUtil;

public class NoobnounsCommands {
    /**
     * Registers commands for the mod
     */
    public static void register() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(CommandManager.literal("pronouns")
                    .then(CommandManager.literal("set")
                            .then(CommandManager.argument("pronouns", StringArgumentType.greedyString()).executes(ctx -> {
                                String pronouns = StringUtil.sterilise(StringArgumentType.getString(ctx, "pronouns"));

                                PronounStore.setPronouns(ctx.getSource().getPlayer(), pronouns);
                                Noobnouns.LOGGER.info(new Gson().toJson(PronounStore.pronounMap));

                                return 1;
                            }))
                    )
            );
        });
    }
}
