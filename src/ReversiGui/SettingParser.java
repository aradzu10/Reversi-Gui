package ReversiGui;

import javafx.scene.paint.Color;

import java.io.*;
import java.util.Formatter;

public class SettingParser {


    private Settings ParseSettingsFromFile(String fileAddress) {
        Settings settings = new Settings();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileAddress));
            String first = reader.readLine();
            String second = reader.readLine();
            String third = reader.readLine();

            settings.setSize(Integer.parseInt(first));

            settings.setFirstPlayer(Color.valueOf(second));
            settings.setSecondPlayer(Color.valueOf(third));

        } catch (Exception e) {
            return settings;
        }
        return settings;
    }

    private void ParseSettingsToFile(Settings settings, String fileAddress) {
       try {
           Formatter writer = new Formatter(fileAddress);
           writer.format("%d\n%s\n%s\n", settings.getSize(), toRGBCode(settings.getFirstPlayer()),
                   toRGBCode(settings.getSecondPlayer()));
           writer.close();
       } catch (Exception e) {
           return;
       }
    }

    public static Settings GetSettingsFromFile(String fileAddress) {
        SettingParser settingParser = new SettingParser();
        return settingParser.ParseSettingsFromFile(fileAddress);
    }

    public static Settings GetSettingsFromFile() {
        return GetSettingsFromFile("settings.txt");
    }

    public static void WriteSettingsToFile(Settings settings, String fileAddress) {
        SettingParser settingParser = new SettingParser();
        settingParser.ParseSettingsToFile(settings, fileAddress);
    }

    public static void WriteSettingsToFile(Settings settings) {
        WriteSettingsToFile(settings, "settings.txt");
    }

    private String toRGBCode(Color color) {
        return String.format("#%02X%02X%02X",
                (int)(color.getRed() * 255),
                (int)(color.getGreen() * 255),
                (int)(color.getBlue() * 255 ));
    }
}
