package com.alex.pattern.events;

/**
 * Created by Alex
 */

public class TransitionEvent {

    private int mIdFragment;

    public TransitionEvent(int idFragment) {
        mIdFragment = idFragment;
    }

    public int getIdFragment() {
        return mIdFragment;
    }

    public void setIdFragment(int mIdFragment) {
        mIdFragment = mIdFragment;
    }

}
