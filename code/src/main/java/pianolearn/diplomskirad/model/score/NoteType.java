package pianolearn.diplomskirad.model.score;

public enum NoteType {

    WHOLE("whole"),
    HALF("half"),
    QUARTER("quarter"),
    EIGHTH("eighth"),
    TYPE16("16th"),
    TYPE32("32nd"),
    TYPE64("64th"),
    OTHER("other");

    private final String type;

    NoteType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static NoteType fromType(String type) {
        for (NoteType noteType : NoteType.values()) {
            if (noteType.getType().equalsIgnoreCase(type)) {
                return noteType;
            }
        }
        return OTHER;
    }
}
