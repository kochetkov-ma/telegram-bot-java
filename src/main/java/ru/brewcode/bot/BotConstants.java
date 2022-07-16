package ru.brewcode.bot;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class BotConstants {

    private static final Properties properties = load();
    public static String BOT_TOKEN = properties.getProperty("bot.token");
    public static String BOT_NAME = properties.getProperty("bot.name");

    private static Properties load() {
        final Properties properties = new Properties();
        try (var reader = Files.newBufferedReader(Path.of("gradle.properties"))) {
            properties.load(reader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties;
    }
}
