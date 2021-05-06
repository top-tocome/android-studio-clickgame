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
        maxblood += (maxcombo + 1) * maxcombo / 2;
        num = (num + 1) % Resourse.monsters.length;
        blood = maxblood;
    }
}
