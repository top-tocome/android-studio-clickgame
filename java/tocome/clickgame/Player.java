package tocome.clickgame;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.widget.ImageView;

import java.util.List;

public class Player {
    long attack;
    long money;
    long damage;

    Player(long attack, long money) {
        this.attack = attack;
        this.money = money;
    }

    boolean attackup() {
        if (money >= attack) {
            money -= attack;
            attack += 1;
            return true;
        }
        return false;
    }


    void attack(Monster monster) {
        damage = (long) (attack * (Math.random() + 0.5));
        monster.blood -= damage;
        money += attack * (Math.random() + 0.5);
        playid((int) (Math.random() * Resourse.kick.length));
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
        this.volume = volume*0.5f;
    }
}




