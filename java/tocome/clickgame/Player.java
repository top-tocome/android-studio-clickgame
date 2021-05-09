package tocome.clickgame;

import android.content.Context;
import android.media.SoundPool;
import android.widget.TextView;

public class Player {
    long attack;
    long maxcombo;
    long damage;

    Player(long attack, long maxcombo) {
        this.attack = attack;
        this.maxcombo = maxcombo;
    }

    void maxcombo(TextView comboView) {
        if (maxcombo < attack) {
            maxcombo = attack;
            comboView.setText(String.valueOf("最大连击：" + maxcombo));
        }
    }


    void attack(Monster monster) {
        damage = (long) (attack * (Math.random()*(1+maxcombo/1000) + 0.5));
        monster.blood -= damage;
    }

    SoundPool soundPool;
    int[] kickId = new int[Resourse.kick.length];
    float volume;

    void setKickmusic(Context context) {
        SoundPool.Builder builder = new SoundPool.Builder();
        builder.setMaxStreams(3);
        soundPool = builder.build();
        for (int i = 0; i < Resourse.kick.length; i++) {
            kickId[i] = soundPool.load(context, Resourse.kick[i], 1);
        }

    }

    void playid(int soundid) {
        soundPool.play(kickId[soundid], volume, volume, 0, 0, 1.5f);
    }

    void setvolume(float volume) {
        this.volume = volume * 0.5f;
    }
}




