package org.koar.koariloggerbeta.ilogger;


import org.koar.koariloggerbeta.ilogger.AOP.Levels;
import org.springframework.boot.ansi.AnsiColor;
import org.springframework.boot.ansi.AnsiOutput;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class ILayout {

    private static final String PATTERN_LAYOUT = "yyyy-MM-dd HH:mm:ss SSS";
    public static LocalDateTime getLocalDateTime() {
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN_LAYOUT);
        String formattedDateTime = dateTime.format(formatter);
        return LocalDateTime.parse(formattedDateTime, formatter);
    }

    public static String TYPE(Levels level) {
        final Map<String, String> COLORS = Map.of(
                Levels.INFO.name(), AnsiOutput.encode(AnsiColor.BLUE),
                Levels.WARNING.name(), AnsiOutput.encode(AnsiColor.YELLOW),
                Levels.SEVERE.name(), AnsiOutput.encode(AnsiColor.RED),
                Levels.WTF.name(), AnsiOutput.encode(AnsiColor.MAGENTA),
                Levels.FINE.name(), AnsiOutput.encode(AnsiColor.GREEN),
                Levels.FINEST.name(), AnsiOutput.encode(AnsiColor.GREEN),
                Levels.NORMAL.name(), AnsiOutput.encode(AnsiColor.DEFAULT)
        );
        return COLORS.get(level.name());
    }

    public static String EMOJI(Levels level) {
        final Map<String, String> EMOJIS = Map.of(
                Levels.INFO.name(), "🔵",
                Levels.WARNING.name(), "🟡",
                Levels.SEVERE.name(), "🔴",
                Levels.WTF.name(), "🟣",
                Levels.FINE.name(), "🟢",
                Levels.NORMAL.name(), "⚪",
                Levels.FINEST.name(), "🟢"
        );
        return EMOJIS.get(level.name());
    }
}
