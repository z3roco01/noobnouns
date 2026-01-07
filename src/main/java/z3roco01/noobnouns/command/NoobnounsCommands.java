package z3roco01.noobnouns.command;

import com.mojang.brigadier.arguments.StringArgumentType;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import z3roco01.noobnouns.NounStore;
import z3roco01.noobnouns.util.StringUtil;

public class NoobnounsCommands {
    /**
     * Registers commands for the mod
     */
    public static void register() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(CommandManager.literal("nouns")
                .then(CommandManager.literal("pronouns")
                        .executes(ctx -> {
                            // show them their prns if they didnt put them in
                            ServerCommandSource source = ctx.getSource();
                            source.sendFeedback(() -> Text.of("§7your pronouns are: §r" + NounStore.getPronouns(source.getPlayer())), false);

                            return 1;
                        })
                        .then(CommandManager.argument("pronouns", StringArgumentType.greedyString()).executes(ctx -> {
                            String pronouns = StringUtil.sterilise(StringArgumentType.getString(ctx, "pronouns"));

                            NounStore.setPronouns(ctx.getSource().getPlayer(), pronouns);

                            return 1;
                        })))
                .then(CommandManager.literal("name").executes(ctx -> {
                    ServerCommandSource source = ctx.getSource();
                    source.sendFeedback(() -> Text.of("§7your name is: §r" + NounStore.getName(source.getPlayer())), false);

                    return 1;
                })
                .then(CommandManager.argument("name", StringArgumentType.greedyString()).executes(ctx -> {
                    String name = StringUtil.sterilise(StringArgumentType.getString(ctx, "name"));

                    NounStore.setName(ctx.getSource().getPlayer(), name);

                    return 1;
                }))
        )));
    }
}
