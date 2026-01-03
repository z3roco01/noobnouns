package z3roco01.noobnouns;

import z3roco01.composed.annotation.Comment;
import z3roco01.composed.annotation.ConfigProperty;

public class NoobnounConfig {
    @Comment(comment = "Determines how the players pronouns will be displayer\n# %p will be replaced with what they set the pronouns to and %n will be replaced with their normal name")
    @ConfigProperty
    public String formattingString = "§8[%p]§r %n";
}
