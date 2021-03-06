package top.tocome.clickgame;

import android.content.Context;
import android.media.MediaPlayer;

public class Chapter {
    static int turn = 1;//周目
    int num;//章节编号
    int bg;//该章背景图
    int bgm;//该章背景音乐

    Chapter(int num, int bg, int bgm) {//构造函数
        this.num = num;
        this.bg = bg;
        this.bgm = bgm;
    }

    MediaPlayer mediaPlayer;//音频播放器
    static float volume;//音量

    void bgmStart(Context context) {//播放背景音乐
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(context, bgm);
            mediaPlayer.setVolume(Chapter.volume, Chapter.volume);
            mediaPlayer.setLooping(true);
        }
        mediaPlayer.start();
    }

    void bgmPause() {
        if (mediaPlayer.isPlaying()) {//暂停播放背景音乐
            mediaPlayer.pause();
        }
    }

    Chapter nextchapter() {//切换下一章
        mediaPlayer.release();
        return Resourse.chapters[(num + 1) % Resourse.chapters.length];
    }

    void setvolume(float volume) {//设置音量
        Chapter.volume = volume * 0.7f;
        mediaPlayer.setVolume(volume, volume);
    }
}
