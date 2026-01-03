package z3roco01.noobnouns.command;

import com.mojang.brigadier.arguments.StringArgumentType;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.CommandManager;
import net.minecraft.text.Text;
import z3roco01.noobnouns.util.StringUtil;

public class NoobnounsCommands {
    /**
     * Registers commands for the mod
     */
    public static void register() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(CommandManager.literal("pronouns")
                    .then(CommandManager.literal("set")
                            .then(CommandManager.argument("pronouns", StringArgumentType.string()).executes(ctx -> {
                                String pronouns = StringUtil.sterilise(StringArgumentType.getString(ctx, "pronouns"));

                                ctx.getSource().sendFeedback(() -> Text.of(pronouns), false);

                                return 1;
                            }))
                    )
            );
        });
    }
}
