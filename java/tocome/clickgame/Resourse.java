package tocome.clickgame;

public class Resourse {
    static float soundVolume = 0.5f;
    static int[] monsters = new int[]{
            R.mipmap.monster_00,
            R.mipmap.monster_01,
            R.mipmap.monster_02,
            R.mipmap.monster_03,
            R.mipmap.monster_04,
            R.mipmap.monster_05,
            R.mipmap.monster_06
    };
    static int[] bgm = new int[]{
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
            R.mipmap.bg_05
    };
    static int[] kick = new int[]{
            R.raw.kick1,
            R.raw.kick2,
            R.raw.kick3,
            R.raw.kick4,
            R.raw.kick5
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
            new Chapter(5, bg[5], bgm[5])
    };


    static int[] damage_temp = new int[]{
            R.id.damage0,
            R.id.damage1,
            R.id.damage2,
            R.id.damage3,
            R.id.damage4
    };
}
