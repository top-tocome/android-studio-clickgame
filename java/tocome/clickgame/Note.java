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
    ImageView[] imageViews;//音符显示控件
    Animation[] animations;//动画
    long[] time;//记录对应系统时间
    int num = 0;//最后一个播放的编号


    Note(ImageView[] imageViews, Context context) {//构造函数
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

    static long duration = 1000;//下落时间

    void start(int direction) {//开始动画
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

    static float perfect = 200;//有效点击时间范围
    static float miss = 400;//点击失败时间范围

  float level() {//判定点击是否有效
        long min = System.currentTimeMillis();
        int nownum = 0;

        for (int i = 0; i < animations.length; i++) {
            if (time[i] != 0 && time[i] < min) {
                min = time[i];
                nownum = i;
            }
        }

        if ((duration - System.currentTimeMillis() + min) < perfect) {
            imageViews[nownum].clearAnimation();
            return perfect;
        } else if ((duration - System.currentTimeMillis() + min) < miss) {
            imageViews[nownum].clearAnimation();
            return miss;
        }
        return 0;
    }
}
