package tocome.clickgame;

public class Resourse {
    static float soundVolume=0.5f;
    static int[] monsters = new int[]{
            R.drawable.monster_00,
            R.drawable.monster_01,
            R.drawable.monster_02,
            R.drawable.monster_03,
            R.drawable.monster_04,
            R.drawable.monster_05
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
            R.drawable.bg_00,
            R.drawable.bg_01,
            R.drawable.bg_02,
            R.drawable.bg_03,
            R.drawable.bg_04,
            R.drawable.bg_05
    };
    static int[] kick = new int[]{
            R.raw.kick1,
            R.raw.kick2,
            R.raw.kick3,
            R.raw.kick4,
            R.raw.kick5
    };
    static int[] player_attack = new int[]{
            R.drawable.player_attack
    };
    static Chapter[] chapters = new Chapter[]{
            new Chapter(0,bg[0], bgm[0]),
            new Chapter(1,bg[1], bgm[1]),
            new Chapter(2,bg[2], bgm[2]),
            new Chapter(3,bg[3], bgm[3]),
            new Chapter(4,bg[4], bgm[4]),
            new Chapter(5,bg[5], bgm[5])
    };

}
