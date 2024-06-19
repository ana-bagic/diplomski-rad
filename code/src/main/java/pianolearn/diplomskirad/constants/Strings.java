package pianolearn.diplomskirad.constants;

public class Strings {

    public static final String empty = "";

    public static final String appTitle = "Learn piano";

    // Title screen
    public static final String uploadButtonTooltip = "Load file from computer";
    public static final String libraryButtonTooltip = "Play song from our library";
    public static final String settingsButtonTooltip = "Settings";

    // Upload screen
    public static final String loadLabel = "Load any .xml file";
    public static final String chooseFileLabel = "Choose a file";
    public static final String confirm = "Confirm";
    public static final String fileChooserXmlFiles = "XML files";
    public static String xmlLoadError(String fileName) {
        return String.format("Error: file %s can't be properly loaded for this app.\n" +
                "Please use MusicXML file with <score-partwise> root element.", fileName);
    }
    public static final String fileNotChosenError = "Error: file not chosen.";

    // Library screen
    public static final String libraryLabel = "Pick a song";
    public static final String classicRow = "classic:";
    public static final String modernRow = "modern:";

    // Settings screen
    public static final String settingsLabel = "Settings";
}
