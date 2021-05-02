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

    void nextmonster(long bloodup) {
        maxblood += bloodup * (Math.random() * 20 + 20);
        num = (num + 1) % Resourse.monsters.length;
        blood = maxblood;
    }
}
