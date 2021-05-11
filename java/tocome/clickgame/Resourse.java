package tocome.clickgame;

public class Resourse {
    static int[] monsters = new int[]{//怪物图片
            R.mipmap.monster_00,
            R.mipmap.monster_01,
            R.mipmap.monster_02,
            R.mipmap.monster_03,
            R.mipmap.monster_04,
            R.mipmap.monster_05,
            R.mipmap.monster_06
    };
    static int[] bgm = new int[]{//背景音乐
            R.raw.bgm_00,
            R.raw.bgm_01,
            R.raw.bgm_02,
            R.raw.bgm_03,
            R.raw.bgm_04,
            R.raw.bgm_05,
            R.raw.bgm_06
    };
    static int[] bg = new int[]{
            R.mipmap.bg_00,
            R.mipmap.bg_01,
            R.mipmap.bg_02,
            R.mipmap.bg_03,
            R.mipmap.bg_04,
            R.mipmap.bg_05,
            R.mipmap.bg_06
    };
    static int[] kick = new int[]{
            R.raw.kick,
            R.raw.kick_clap,
            R.raw.kick_hat,
            R.raw.kick_snare,
            R.raw.kick_all
    };
    static int[] player_attack = new int[]{
            R.mipmap.player_attack
    };
    static Chapter[] chapters = new Chapter[]{
            new Chapter(0, bg[0], bgm[0]),
            new Chapter(1, bg[1], bgm[1]),
            new Chapter(2, bg[2], bgm[2]),
            new Chapter(3, bg[3], bgm[3]),
            new Chapter(4, bg[4], bgm[4]),
            new Chapter(5, bg[5], bgm[5]),
            new Chapter(6, bg[6], bgm[6])
    };


    static int[] damage_temp = new int[]{
            R.id.damage0,
            R.id.damage1,
            R.id.damage2,
            R.id.damage3,
            R.id.damage4
    };

    static int[] kick_temp = new int[]{
            R.id.kick1,
            R.id.kick2,
            R.id.kick3,
            R.id.kick4,
            R.id.kick5
    };
    static int[] clap_temp = new int[]{
            R.id.clap1,
            R.id.clap2,
            R.id.clap3,
            R.id.clap4,
            R.id.clap5
    };
    static int[] snare_temp = new int[]{
            R.id.snare1,
            R.id.snare2,
            R.id.snare3,
            R.id.snare4,
            R.id.snare5
    };
    static int[] hat_temp = new int[]{
            R.id.hat1,
            R.id.hat2,
            R.id.hat3,
            R.id.hat4,
            R.id.hat5
    };

    static int[] Music_anime = new int[]{
            R.anim.move_up,
            R.anim.move_down,
            R.anim.move_left,
            R.anim.move_right
    };
}
