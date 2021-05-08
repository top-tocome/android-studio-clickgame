package tocome.clickgame;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

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

        volumeSetting();

        TextView duration = findViewById(R.id.duration);
        TextView bpm = findViewById(R.id.bpm);
        TextView perfect = findViewById(R.id.perfect);

        duration.setText(String.valueOf(Note.duration));
        bpm.setText(String.valueOf(game_main.bps));
        perfect.setText(String.valueOf(Note.perfect));
        duration.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (!v.getText().toString().equals("")) {
                    long duration = Long.parseLong(v.getText().toString());
                    if (duration >= 500 && duration <= 4000) {
                        Note.duration = Long.parseLong(v.getText().toString());
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putLong("duration", Note.duration);
                        editor.apply();
                    }
                }
                return false;
            }
        });
        bpm.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (!v.getText().toString().equals("")) {
                    int bps = Integer.parseInt(v.getText().toString());
                    if (bps > 0 && bps <= 10) {
                        game_main.bps = bps;
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putInt("bps", game_main.bps);
                        editor.apply();
                    }
                }
                return false;
            }
        });
        perfect.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (!v.getText().toString().equals("")) {
                    float perfect = Float.parseFloat(v.getText().toString());
                    if (perfect > 0 && perfect <= 300) {
                        Note.perfect = perfect;
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putFloat("perfect", Note.perfect);
                        editor.apply();
                    }
                }
                return false;
            }
        });

    }

    private void volumeSetting() {
        SeekBar bgmvolume = findViewById(R.id.bgmvolumeSeekBar);
        SeekBar kickvolume = findViewById(R.id.kickvolumeSeekBar);

        bgmvolume.setProgress(sp.getInt("bgmvolumeprogress", 100));
        kickvolume.setProgress(sp.getInt("kickvolumeprogress", 100));

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
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                player.setvolume((float) seekBar.getProgress() / 100);
                player.playid((int) (Math.random() * Resourse.kick.length));
                SharedPreferences.Editor editor = sp.edit();
                editor.putInt("kickvolumeprogress", seekBar.getProgress());
                editor.apply();
            }
        });
    }

}
