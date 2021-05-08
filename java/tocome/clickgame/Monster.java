package tocome.clickgame;

public class Monster {

    int num;
    long blood;
    long maxblood;

    Monster(int num, long maxblood) {
        this.num = num;
        this.maxblood = maxblood;
        blood = maxblood;
    }

    boolean isdead() {
        return blood <= 0;
    }

    void nextmonster(long maxcombo) {
        int combo = (int) Math.sqrt(maxblood * 2);
        System.out.println(combo);
        maxblood = (combo + (int) (Math.random() * 10 + 10))*(combo + (int) (Math.random() * 10 + 10)) ;
        blood = maxblood;
        num = (num + 1) % Resourse.monsters.length;
    }
}
