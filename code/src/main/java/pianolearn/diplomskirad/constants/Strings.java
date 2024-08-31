package pianolearn.diplomskirad.constants;

public class Strings {

    public static final String empty = "";

    public static final String appTitle = "Learn piano";

    // Title screen
    public static final String uploadButtonTooltip = "Load file from computer";
    public static final String libraryButtonTooltip = "Play song from our library";
    public static final String keyboardButtonTooltip = "Setup your keyboard";

    // Upload screen
    public static final String loadLabel = "Load .xml file";
    public static final String chooseFile = "Choose a file";
    public static final String fileChosen = "File chosen: ";
    public static final String confirm = "Confirm";
    public static final String or = "or";
    public static final String loadAnotherFile = "Load another file";
    public static final String fileChooserXmlFiles = "XML files";
    public static String xmlLoadError(String fileName) {
        return String.format("Error: file %s can't be properly loaded for this app.\n" +
                "Please choose MusicXML file with <score-partwise> root element.", fileName);
    }
    public static String xmlPartsError(String fileName) {
        return String.format("Error: file %s does not contain piano part.\n" +
                "Please choose file with only 1 part or one that contains part with \"Piano\" in name.", fileName);
    }
    public static String xmlStavesError(String fileName, int staves) {
        return String.format("Error: file %s contains too much staves: %d.\n" +
                "Please choose file with 1 or 2 staves (score uses 1 hand or both).", fileName, staves);
    }

    // Library screen
    public static final String libraryLabel = "Pick a song";
    public static final String classicRow = "classic:";
    public static final String modernRow = "modern:";

    // Setup keyboard screen
    public static final String selectKeyboard = "Select your keyboard";
    public static final String refresh = "Refresh";
    public static final String playThe = "Play the ";
    public static final String lowest = "lowest ";
    public static final String andThe = "and the ";
    public static final String highest = "highest ";
    public static final String noteOnYourKeyboard = "note on your keyboard";

    // Play screen
    public static final String playButtonTooltip = "Play";
    public static final String pauseButtonTooltip = "Pause";
    public static final String stopButtonTooltip = "Stop";
    public static final String speedLabel = "Select the speed";
    public static final String sliderWait = "wait";
    public static final String sliderSpeed50 = "50%";
    public static final String sliderSpeed80 = "80%";
    public static final String sliderSpeed100 = "100%";
    public static final String leftHandButtonTooltip = "Left hand";
    public static final String rightHandButtonTooltip = "Right hand";
    public static final String titlePlaceholder = "Title";
    public static final String artistPlaceholder = "Artist";
}
