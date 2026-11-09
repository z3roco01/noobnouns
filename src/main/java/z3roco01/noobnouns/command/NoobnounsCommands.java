package z3roco01.noobnouns.command;

import com.mojang.brigadier.arguments.StringArgumentType;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import z3roco01.noobnouns.NounStore;
import z3roco01.noobnouns.util.StringUtil;

public class NoobnounsCommands {
    /**
     * Registers commands for the mod
     */
    public static void register() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) ->
                dispatcher.register(Commands.literal("nouns")
                .then(Commands.literal("pronouns")
                        .executes(ctx -> {
                            // show them their prns if they didnt put them in
                            CommandSourceStack source = ctx.getSource();
                            source.sendSuccess(() -> Component.literal("§7your pronouns are: §r" + NounStore.getPronouns(source.getPlayer())), false);

                            return 1;
                        })
                        .then(Commands.argument("pronouns", StringArgumentType.greedyString()).executes(ctx -> {
                            String pronouns = StringUtil.sterilise(StringArgumentType.getString(ctx, "pronouns"));

                            NounStore.setPronouns(ctx.getSource().getPlayer(), pronouns);

                            return 1;
                        })))
                .then(Commands.literal("name").executes(ctx -> {
                    CommandSourceStack source = ctx.getSource();
                    source.sendSuccess(() -> Component.literal("§7your name is: §r" + NounStore.getName(source.getPlayer())), false);
                    source.getPlayer().setCustomName(Component.literal("wassup"));

                    return 1;
                })
                .then(Commands.argument("name", StringArgumentType.greedyString()).executes(ctx -> {
                    String name = StringUtil.sterilise(StringArgumentType.getString(ctx, "name"));

                    NounStore.setName(ctx.getSource().getPlayer(), name);

                    return 1;
                }))
        )));
    }
}
