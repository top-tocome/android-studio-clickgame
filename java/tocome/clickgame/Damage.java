package tocome.clickgame;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class Damage {
    TextView[] textViews;
    Animation[] animations;
    int num = 0;

    Damage(TextView[] textViews, int anim, Context context) {
        this.textViews=textViews;
        animations = new Animation[textViews.length];
        for (int i = 0; i < animations.length; i++) {
            animations[i] = AnimationUtils.loadAnimation(context, anim);
            int finalI = i;
            animations[i].setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    textViews[finalI].setAlpha(1f);
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    textViews[finalI].setAlpha(0f);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
        }
    }

    @SuppressLint("SetTextI18n")
    void start(long damage) {
        textViews[num].setText("-" + damage);
        textViews[num].startAnimation(animations[num]);
        num = (num + 1) % textViews.length;
    }


}
