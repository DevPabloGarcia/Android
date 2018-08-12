package pablogarcia.timetolivetogether.CustomAnimation;

import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

/**
 * Created by V on 31/10/17.
 */

public class BlinkAnimation extends AlphaAnimation {

    public BlinkAnimation(float fromAlpha, float toAlpha, Integer duration) {

        super(fromAlpha, toAlpha);
        this.setDuration(duration);
        this.setRepeatMode(Animation.REVERSE);
        this.setRepeatCount(Animation.INFINITE);
    }

}
