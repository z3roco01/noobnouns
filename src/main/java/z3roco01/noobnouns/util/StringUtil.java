package z3roco01.noobnouns.util;

public class StringUtil {
    /**
     * Removes disallowed sub strings ( like text formatting )
     */
    public static String sterilise(String original) {
        String clean = original;
        // clean text formatting
        clean = clean.replaceAll("§[0123456789abcdef]?", "");

        return clean;
    }
}
