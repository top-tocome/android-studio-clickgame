package tocome.clickgame;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;

public class SettingDialog extends Dialog {
    Player player;
    Chapter chapter;
    SharedPreferences sp;

    public SettingDialog(Context context, Player player, Chapter chapter, SharedPreferences sp) {
        super(context);
        this.player = player;
        this.chapter = chapter;
        this.sp = sp;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_setting);

        ImageView close = findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        SeekBar bgmvolume = findViewById(R.id.bgmvolumeSeekBar);
        SeekBar kickvolume = findViewById(R.id.kickvolumeSeekBar);

        bgmvolume.setProgress(sp.getInt("bgmvolumeprogress",100));
        kickvolume.setProgress(sp.getInt("kickvolumeprogress",100));
        bgmvolume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                chapter.setvolume((float) progress / 100);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                SharedPreferences.Editor editor = sp.edit();
                editor.putInt("bgmvolumeprogress", seekBar.getProgress());
                editor.apply();
            }
        });
        kickvolume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                player.setvolume((float) progress / 100);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                player.playid((int) (Math.random() * Resourse.kick.length));
                SharedPreferences.Editor editor = sp.edit();
                editor.putInt("kickvolumeprogress", seekBar.getProgress());
                editor.apply();
            }
        });
    }

}
