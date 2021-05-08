package tocome.clickgame;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class game_main extends AppCompatActivity implements View.OnTouchListener {
    Monster monster;
    Player player;
    Chapter chapter;

    View layout;
    TextView m_blood;
    TextView p_attack;
    TextView p_money;
    TextView chapter_num;
    Button start;
    ImageView playerimg;
    ImageView monsterimg;
    ProgressBar progressBar;
    TextView combo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_main);

        getUI();
        Init();
        setUI();
        setListener();
        musicSetting();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (chapter.mediaPlayer.isPlaying()) {
            chapter.mediaPlayer.pause();
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (chapter.mediaPlayer != null) {
            chapter.mediaPlayer.start();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        savedata();
    }

    void getUI() {
        layout = findViewById(R.id.game);
        m_blood = findViewById(R.id.monster_blood);
        p_attack = findViewById(R.id.player_attack);
        p_money = findViewById(R.id.player_money);
        chapter_num = findViewById(R.id.chapter_num);
        start = findViewById(R.id.start);
        playerimg = findViewById(R.id.player);
        monsterimg = findViewById(R.id.monster);
        progressBar = findViewById(R.id.progress_blood);
        combo = findViewById(R.id.combo);
    }

    Anime damage_anime;

    void Init() {

        monster = new Monster(0, 50);
        player = new Player(0, 0);
        chapter = Resourse.chapters[0];

        getdata();
        chapter.playbgm(game_main.this);
        player.setKickmusic(game_main.this);

        TextView[] damagetemp = new TextView[Resourse.damage_temp.length];
        for (int i = 0; i < Resourse.damage_temp.length; i++) {
            damagetemp[i] = findViewById(Resourse.damage_temp[i]);
        }
        damage_anime = new Anime(damagetemp, R.anim.damage, game_main.this);
        combo_anime = AnimationUtils.loadAnimation(game_main.this, R.anim.combo);
    }

    void setUI() {
        start.setText("开始游戏");
        p_attack.setText(String.valueOf("攻击力：" + player.attack));
        p_money.setText(String.valueOf("最大连击：" + player.maxcombo));
        chapter_num.setText(new String(Chapter.turn + "周目" + "第" + (chapter.num + 1) + "章"));

        layout.setBackgroundResource(chapter.bg);
        monsterimg.setImageResource(Resourse.monsters[monster.num]);
        updatePage();
    }

    Animation combo_anime;

    void updatePage() {
        m_blood.setText(String.valueOf(monster.blood + "/" + monster.maxblood));
        progressBar.setProgress((int) (progressBar.getMax() * monster.blood / monster.maxblood));
        combo.setText(new String("combo X" + player.attack));
        combo.startAnimation(combo_anime);
        p_attack.setText(String.valueOf("攻击力：" + player.attack));
    }

    Note[] notes = new Note[4];

    void musicSetting() {
        ImageView[] kicks = new ImageView[Resourse.kick_temp.length];
        for (int i = 0; i < Resourse.kick_temp.length; i++) {
            kicks[i] = findViewById(Resourse.kick_temp[i]);
        }
        notes[0] = new Note(kicks, game_main.this);


        ImageView[] claps = new ImageView[Resourse.clap_temp.length];
        for (int i = 0; i < Resourse.clap_temp.length; i++) {
            claps[i] = findViewById(Resourse.clap_temp[i]);
        }
        notes[1] = new Note(claps, game_main.this);


        ImageView[] snares = new ImageView[Resourse.snare_temp.length];
        for (int i = 0; i < Resourse.snare_temp.length; i++) {
            snares[i] = findViewById(Resourse.snare_temp[i]);
        }
        notes[2] = new Note(snares, game_main.this);


        ImageView[] hats = new ImageView[Resourse.hat_temp.length];
        for (int i = 0; i < Resourse.hat_temp.length; i++) {
            hats[i] = findViewById(Resourse.hat_temp[i]);
        }
        notes[3] = new Note(hats, game_main.this);
    }

    int button_num = 0;

    @SuppressLint({"ClickableViewAccessibility", "NonConstantResourceId"})
    @Override
    public boolean onTouch(View v, MotionEvent event) {

        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                switch (v.getId()) {
                    case R.id.kick:
                        button_num = 0;
                        break;
                    case R.id.clap:
                        button_num = 1;
                        break;
                    case R.id.snare:
                        button_num = 2;
                        break;
                    case R.id.hat:
                        button_num = 3;
                        break;
                    default:
                        break;
                }

                if (notes[button_num].level()) {
                    player.attack++;
                    player.maxcombo(p_money);
                    player.playid(button_num);
                    clicked_event();
                } else {
                    player.attack = 0;
                    updatePage();
                }
                return true;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                playerimg.setImageResource(R.mipmap.player_stand);
                return true;
        }

        return false;
    }

    static int bps = 4;

    void setListener() {
        findViewById(R.id.kick).setOnTouchListener(this);
        findViewById(R.id.clap).setOnTouchListener(this);
        findViewById(R.id.snare).setOnTouchListener(this);
        findViewById(R.id.hat).setOnTouchListener(this);

        start.setOnClickListener(new View.OnClickListener() {
            boolean state = true;
            Timer timer = new Timer();

            @Override
            public void onClick(View v) {

                if (state) {
                    start.setText("停止游戏");
                    timer.schedule(new TimerTask() {
                        public void run() {
                            int ran = (int) (Math.random() * 4);
                            if (ran == 0) {
                                notes[ran].startdownmode();
                            } else if (ran == 1) {
                                notes[ran].startdownmode();
                            } else if (ran == 2) {
                                notes[ran].startdownmode();
                            } else {
                                notes[ran].startdownmode();
                            }
                        }
                    }, 1000, 1000 / bps);
                } else {
                    start.setText("开始游戏");
                    timer.cancel();
                    timer = new Timer();
                }
                state = !state;
            }
        });
        findViewById(R.id.setting).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp = getSharedPreferences("data", MODE_PRIVATE);
                SettingDialog settingDialog = new SettingDialog(game_main.this, player, chapter, sp);
                settingDialog.show();
            }
        });

    }

    void clicked_event() {
        //攻击事件
        player.attack(monster);
        playerimg.setImageResource(Resourse.player_attack[(int) (Math.random() * Resourse.player_attack.length)]);
        damage_anime.start(player.damage);
        //死亡事件
        if (monster.isdead()) {
            monster.nextmonster(player.maxcombo);
            monsterimg.setImageResource(Resourse.monsters[monster.num]);
            if (monster.num == 0) {//下一章
                chapter = chapter.nextchapter();
                layout.setBackgroundResource(chapter.bg);
                if (chapter.num == 0) {//下一周目
                    Chapter.turn++;
                }
                chapter_num.setText(new String(Chapter.turn + "周目" + "第" + (chapter.num + 1) + "章"));
                chapter.playbgm(game_main.this);
            }
            savedata();
        }
        updatePage();
    }

    void getdata() {
        SharedPreferences sp = getSharedPreferences("data", MODE_PRIVATE);
        Chapter.turn = sp.getInt("Chapter.turn", 1);
        chapter = Resourse.chapters[sp.getInt("chapter.num", 0)];
        monster.num = sp.getInt("monster.num", 0);
        monster.blood = sp.getLong("monster.blood", 50);
        monster.maxblood = sp.getLong("monster.maxblood", 50);
        player.attack = sp.getLong("player.attack", 0);
        player.maxcombo = sp.getLong("player.money", 0);


        Chapter.volume = (float) sp.getInt("bgmvolumeprogress", 100) / 100;
        player.volume = (float) sp.getInt("kickvolumeprogress", 100) / 100;

        Note.duration = sp.getLong("duration", 1000);
        Note.perfect = sp.getFloat("perfect", 250);
        bps = sp.getInt("bps", 4);
    }

    void savedata() {
        SharedPreferences sp = getSharedPreferences("data", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("Chapter.turn", Chapter.turn);
        editor.putInt("chapter.num", chapter.num);
        editor.putInt("monster.num", monster.num);
        editor.putLong("monster.blood", monster.blood);
        editor.putLong("monster.maxblood", monster.maxblood);
        editor.putLong("player.attack", player.attack);
        editor.putLong("player.money", player.maxcombo);
        editor.apply();
    }


}