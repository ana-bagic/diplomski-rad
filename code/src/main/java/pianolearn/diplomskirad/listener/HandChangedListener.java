package pianolearn.diplomskirad.listener;

import pianolearn.diplomskirad.model.Hand;

public interface HandChangedListener {

    void onAction(Hand hand, boolean shows);
}
