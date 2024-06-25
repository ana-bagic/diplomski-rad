package pianolearn.diplomskirad.model.note;

public enum NoteAlphabet {

    C(0, "C"),
    CSH(1, "C#"),
    D(2, "D"),
    DSH(3, "D#"),
    E(4, "E"),
    F(5, "F"),
    FSH(6, "F#"),
    G(7, "G"),
    GSH(8, "G#"),
    A(9, "A"),
    ASH(10, "A#"),
    B(11, "B");

    public static final NoteAlphabet[] KEYS = new NoteAlphabet[] {C, CSH, D, DSH, E, F, FSH, G, GSH, A, ASH, B};

    private final int chromaNumber;
    private final String name;

    NoteAlphabet(int chromaNumber, String name) {
        this.chromaNumber = chromaNumber;
        this.name = name;
    }

    public int getChromaNumber() {
        return chromaNumber;
    }

    public String getName() {
        return name;
    }
}