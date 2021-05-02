package tocome.clickgame;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class game_main extends AppCompatActivity {
    Monster monster;
    Player player;
    Chapter chapter;

    View layout;
    TextView m_blood;
    TextView p_attack;
    TextView p_money;
    TextView chapter_num;
    Button buy;
    ImageView playerimg;
    ImageView monsterimg;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_main);

        getUI();
        Init();
        setUI();
        setListener();

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


    void getUI() {
        layout = findViewById(R.id.game);
        m_blood = findViewById(R.id.monster_blood);
        p_attack = findViewById(R.id.player_attack);
        p_money = findViewById(R.id.player_money);
        chapter_num = findViewById(R.id.chapter_num);
        buy = findViewById(R.id.buy);
        playerimg = findViewById(R.id.player);
        monsterimg = findViewById(R.id.monster);
        progressBar = findViewById(R.id.progress_blood);

    }

    Anime damage_anime;

    void Init() {

        monster = new Monster(0, 50);
        player = new Player(1, 0);
        chapter = Resourse.chapters[0];

        getdata();
        chapter.playbgm(game_main.this);
        player.setKickmusic(game_main.this);

        TextView[] damagetemp = new TextView[Resourse.damage_temp.length];
        for (int i = 0; i < Resourse.damage_temp.length; i++) {
            damagetemp[i] = findViewById(Resourse.damage_temp[i]);
        }
        damage_anime = new Anime(damagetemp, R.anim.damage, game_main.this);
    }

    void setUI() {
        buy.setText(new String("升级￥" + player.attack));
        p_attack.setText(String.valueOf("攻击力：" + player.attack));
        chapter_num.setText(new String(Chapter.turn + "周目" + "第" + (chapter.num + 1) + "章"));

        layout.setBackgroundResource(chapter.bg);
        monsterimg.setImageResource(Resourse.monsters[monster.num]);
        updatePage();
    }


    void updatePage() {
        m_blood.setText(String.valueOf(monster.blood + "/" + monster.maxblood));
        p_money.setText(String.valueOf("金钱：￥" + player.money));
        progressBar.setProgress((int) (progressBar.getMax() * monster.blood / monster.maxblood));
    }


    void setListener() {
        layout.setOnTouchListener(new View.OnTouchListener() {
            long time = 0;

            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getActionMasked()) {

                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_POINTER_DOWN:
                        //限制点击速度
                        if (System.currentTimeMillis() - time < 100) return false;
                        else time = System.currentTimeMillis();
                        //攻击事件
                        monster = player.attack(monster);
                        playerimg.setImageResource(Resourse.player_attack[(int) (Math.random() * Resourse.player_attack.length)]);
                        damage_anime.start(player.damage);
                        //死亡事件
                        if (monster.isdead()) {
                            monster = monster.nextmonster(player.attack);
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

                        return true;

                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_POINTER_UP:
                        playerimg.setImageResource(R.mipmap.player_stand);
                        return true;

                    case MotionEvent.ACTION_MOVE:
                        return true;
                }
                return false;
            }
        });

        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                while (player.attackup()) {
                }
                buy.setText(new String("升级￥" + player.attack));
                p_attack.setText(String.valueOf("攻击力：" + player.attack));
                p_money.setText(String.valueOf("金钱：￥" + player.money));
            }
        });


    }


    void getdata() {
        SharedPreferences sp = getSharedPreferences("data", MODE_PRIVATE);
        Chapter.turn = sp.getInt("Chapter.turn", 1);
        chapter = Resourse.chapters[sp.getInt("chapter.num", 0)];
        monster.num = sp.getInt("monster.num", 0);
        monster.blood = sp.getLong("monster.blood", 50);
        monster.maxblood = sp.getLong("monster.maxblood", 50);
        player.attack = sp.getLong("player.attack", 1);
        player.money = sp.getLong("player.money", 0);

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
        editor.putLong("player.money", player.money);
        editor.apply();
    }
}