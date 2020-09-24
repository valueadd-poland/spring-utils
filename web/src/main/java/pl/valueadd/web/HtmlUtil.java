package pl.valueadd.web;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HtmlUtil {
    private static final Map<String, Pattern> patternCache = new HashMap<>();

    private HtmlUtil() {
    }

    public static Optional<String> findTagValue(String html, String tag) {
        final String beginTag = String.format("<%s(.+|.?)>", tag);
        final String endTag = String.format("</%s>", tag);
        if (!patternCache.containsKey(tag)) {
            patternCache.put(tag, Pattern.compile(String.format("%s.+%s", beginTag, endTag)));
        }
        final Pattern pattern = patternCache.get(tag);
        final Matcher matcher = pattern.matcher(html);
        if (!matcher.find()) {
            return Optional.empty();
        }
        String raw = matcher.group();
        raw = raw.substring(raw.indexOf('>') + 1, raw.length() - endTag.length());
        return Optional.of(raw);
    }
}
