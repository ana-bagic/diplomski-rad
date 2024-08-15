package pianolearn.diplomskirad.model.score;

import org.audiveris.proxymusic.Step;

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

    private final int chromaNumber;
    private final String name;

    NoteAlphabet(int chromaNumber, String name) {
        this.chromaNumber = chromaNumber;
        this.name = name;
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
}