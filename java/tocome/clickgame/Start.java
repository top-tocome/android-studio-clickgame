package tocome.clickgame;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Start extends AppCompatActivity implements View.OnClickListener {

    Button newgame;
    Button latestgame;
    Button exit;
    Intent game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        newgame = findViewById(R.id.newgame);
        latestgame = findViewById(R.id.continuegame);
        exit = findViewById(R.id.exit);
        game = new Intent(this, game_main.class);

        newgame.setOnClickListener(this);
        latestgame.setOnClickListener(this);
        exit.setOnClickListener(this);
        findViewById(R.id.startmenu).setBackgroundResource(Resourse.bg[(int) (Math.random() * Resourse.bg.length)]);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.newgame:
                SharedPreferences sp = getSharedPreferences("data", MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.clear();
                editor.apply();
            case R.id.continuegame:
                startActivity(game);
                break;
            case R.id.exit:
                System.exit(0);
                break;
            default:
                break;
        }
    }
}