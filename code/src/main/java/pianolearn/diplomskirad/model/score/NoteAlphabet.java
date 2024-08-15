package pianolearn.diplomskirad.model.score;

import org.audiveris.proxymusic.Step;

public enum NoteAlphabet {

    C(0, "C", false),
    CSH(1, "C#", true),
    D(2, "D", false),
    DSH(3, "D#", true),
    E(4, "E", false),
    F(5, "F", false),
    FSH(6, "F#", true),
    G(7, "G", false),
    GSH(8, "G#", true),
    A(9, "A", false),
    ASH(10, "A#", true),
    B(11, "B", false);

    private final int chromaNumber;
    private final String name;
    private final boolean isBlack;

    NoteAlphabet(int chromaNumber, String name, boolean isBlack) {
        this.chromaNumber = chromaNumber;
        this.name = name;
        this.isBlack = isBlack;
    }

    public static NoteAlphabet fromStep(Step step) {
        return switch (step) {
            case C -> C;
            case D -> D;
            case E -> E;
            case F -> F;
            case G -> G;
            case A -> A;
            case B -> B;
        };
    }

    public int getChromaNumber() {
        return chromaNumber;
    }

    public String getName() {
        return name;
    }

    public boolean isBlack() {
        return isBlack;
    }
}