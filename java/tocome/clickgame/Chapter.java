package tocome.clickgame;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.widget.RelativeLayout;

import java.io.IOException;

public class Chapter {
    static int turn = 1;
    int num;
    int bg;
    int bgm;

    Chapter(int num, int bg, int bgm) {
        this.num = num;
        this.bg = bg;
        this.bgm = bgm;
    }

    MediaPlayer mediaPlayer;
    static float volume;

    void playbgm(Context context) {
        mediaPlayer = MediaPlayer.create(context, bgm);
        mediaPlayer.setVolume(Chapter.volume, Chapter.volume);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
    }

    Chapter nextchapter() {
        mediaPlayer.release();
        return Resourse.chapters[(num + 1) % Resourse.chapters.length];
    }

    void setvolume(float volume) {
        Chapter.volume=volume*0.7f;
        mediaPlayer.setVolume(volume, volume);
    }
}
