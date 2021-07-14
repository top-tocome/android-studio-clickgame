package top.tocome.clickgame;

import android.content.Context;
import android.media.SoundPool;
import android.widget.TextView;

public class Player {
    long attack;//攻击力
    long maxcombo;//最大连击数
    long damage;//造成的伤害

    Player(long attack, long maxcombo) {//构造函数
        this.attack = attack;
        this.maxcombo = maxcombo;
    }

    void maxcombo(TextView comboView) {//更新最大连击
        if (maxcombo < attack) {
            maxcombo = attack;
            comboView.setText(String.valueOf("最大连击：" + maxcombo));
        }
    }


    void attack(Monster monster) {//攻击事件
        damage = (long) (attack * (Math.random() * (1 + (float) maxcombo / 1000) + 0.5));
        monster.blood -= damage;
    }

    SoundPool soundPool;//音效
    int[] kickId = new int[Resourse.kick.length];//加载的音效id
    float volume;//音量

    void setKickmusic(Context context) {//初始化音效
        SoundPool.Builder builder = new SoundPool.Builder();
        builder.setMaxStreams(3);
        soundPool = builder.build();
        for (int i = 0; i < Resourse.kick.length; i++) {
            kickId[i] = soundPool.load(context, Resourse.kick[i], 1);
        }

    }

    //播放指定音效
    void playid(int soundid) {
        soundPool.play(kickId[soundid], volume, volume, 0, 0, 1.5f);
    }

    //设置音量
    void setvolume(float volume) {
        this.volume = volume * 0.5f;
    }
}




