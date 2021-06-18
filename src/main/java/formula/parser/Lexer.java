package formula.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexer {

    private static String patternString;
    private static Pattern pattern;
    Matcher matcher;
    Token buffer = null;

    Lexer(String input) {
        if (patternString == null) {
            StringBuilder patternStringBuilder = new StringBuilder();
            for(TokenType token : TokenType.values()) {
                patternStringBuilder.append("|(?<" + token.name() + ">" + token.pattern + ")");
            }
            patternString = patternStringBuilder.insert(1,"\\s*(").append(")").substring(1);
            pattern = Pattern.compile(patternString);
        }
        matcher = pattern.matcher(input);
    }

    public Token getToken() {
        if (buffer != null) {
            Token result = buffer;
            buffer = null;
            return result;
        }
        if (matcher.find()) {
            for (TokenType type : TokenType.values()){
                try {
                    if (matcher.group(type.name()) == null) {
                        continue;
                    }
                    System.out.println(type.name() + ":" + matcher.group(type.name()));
                    return new Token(type, matcher.group(type.name()));
                } catch (IllegalArgumentException e) { }
            }
        }
        return new Token(TokenType.FAIL);
    }

    public void putToken(Token token) {
        buffer = token;
    }
}
