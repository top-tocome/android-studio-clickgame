package tocome.clickgame;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.widget.ImageView;

import java.util.List;

public class Player {
    long attack;
    long money;

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


    Monster attack(Monster monster) {
        monster.blood -= attack * (Math.random() + 0.5);
        money += attack * (Math.random() + 0.5);
        soundPool.play(kickId[(int) (Math.random() * Resourse.kick.length)], 0.5f, 0.5f, 0, 0, 1.5f);
        return monster;
    }

    SoundPool soundPool;
    int[] kickId = new int[Resourse.kick.length];

    void setKickmusic(Context context) {
        SoundPool.Builder builder = new SoundPool.Builder();
        builder.setMaxStreams(3);
        soundPool = builder.build();
        for (int i = 0; i < Resourse.kick.length; i++) {
            kickId[i] = soundPool.load(context, Resourse.kick[i], 1);
        }

    }
    void playid(int soundid){
        soundPool.play(kickId[soundid], 0.5f, 0.5f, 0, 0, 1.5f);
    }

}




