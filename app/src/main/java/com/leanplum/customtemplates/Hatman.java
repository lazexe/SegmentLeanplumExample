package com.leanplum.customtemplates;

import android.graphics.Bitmap;

/**
 * Created by Maksym Lazarenko on 07.11.16.
 * Skype: lazexe
 */

public class Hatman {

    private Bitmap mSelectedHatman;
    private Bitmap mUnselectedHatman;

    public Hatman(Bitmap selectedHatman, Bitmap unselectedHatman) {
        this.mSelectedHatman = selectedHatman;
        this.mUnselectedHatman = unselectedHatman;
    }

    public Bitmap getSelectedHatman() {
        return mSelectedHatman;
    }

    public Bitmap getUnselectedHatman() {
        return mUnselectedHatman;
    }

    @Override
    public String toString() {
        return "Hatman{" +
                "mSelectedHatman=" + mSelectedHatman +
                ", mUnselectedHatman=" + mUnselectedHatman +
                '}';
    }
}
