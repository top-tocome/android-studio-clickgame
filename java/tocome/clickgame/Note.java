package tocome.clickgame;

import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.util.Arrays;


public class Note {

    static final int up = 0;
    static final int down = 1;
    static final int left = 2;
    static final int right = 3;

    Context context;
    ImageView[] imageViews;
    Animation[] animations;
    long[] time;
    int num = 0;


    Note(ImageView[] imageViews, Context context) {
        this.context = context;
        this.imageViews = imageViews;
        animations = new Animation[imageViews.length];
        time = new long[imageViews.length];
        Arrays.fill(time, 0);
    }

    void startdownmode() {
        start(down);
    }

    void startRandom2(int direction1, int direction2) {

//        if (ran == 0) {
//            music_buttons[ran].startRandom2(Music_button.down, Music_button.left);
//        } else if (ran == 1) {
//            music_buttons[ran].startRandom2(Music_button.down, Music_button.right);
//        } else if (ran == 2) {
//            music_buttons[ran].startRandom2(Music_button.up, Music_button.left);
//        } else {
//            music_buttons[ran].startRandom2(Music_button.up, Music_button.right);
//        }
        if (Math.random() * 2 < 1) {
            start(direction1);
        } else {
            start(direction2);
        }
    }

    static long duration = 1000;

    void start(int direction) {
        animations[num] = AnimationUtils.loadAnimation(context, Resourse.Music_anime[direction]);
        animations[num].setDuration(duration);
        imageViews[num].startAnimation(animations[num]);
        animations[num].setAnimationListener(new Animation.AnimationListener() {
            final int nownum = num;

            @Override
            public void onAnimationStart(Animation animation) {
                time[nownum] = System.currentTimeMillis();
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                time[nownum] = 0;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        num = (num + 1) % imageViews.length;
    }

    static float perfect = 250;

    boolean level() {
        long min = 0;

        for (int i = 0; i < animations.length; i++) {
            if (time[i] != 0) min = time[i];
        }

        for (int i = 0; i < animations.length; i++) {
            if (time[i] != 0 && time[i] < min) {
                min = time[i];
            }
        }

        return (duration - System.currentTimeMillis() + min) < perfect && min != 0;
    }
}
