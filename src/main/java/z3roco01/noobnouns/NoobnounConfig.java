package z3roco01.noobnouns;

import z3roco01.composed.annotation.Comment;
import z3roco01.composed.annotation.ConfigProperty;

public class NoobnounConfig {
    @Comment(comment = "Determines how the players pronouns will be displayed, not everything is needed to be added\n# %p will be replaced with their pronoun string or nothing\n# %n will be replaced with the name they set\n# %u will be replaced with their actual username")
    @ConfigProperty
    public String formattingString = "§7[%p] §r%n§8(%u)§r";

    @Comment(comment = "Just like formattingString, but it is how it will be displayed in the player list")
    @ConfigProperty
    public String playerListFormat = "%n§7(%u)§r";
}
