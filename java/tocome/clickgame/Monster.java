package tocome.clickgame;

public class Monster {

    int num;
    long blood;
    long maxblood;

    Monster(int num,long maxblood) {
        this.num=num;
        this.maxblood = maxblood;
        blood = maxblood;
    }

    boolean isdead() {
        return blood <= 0;
    }

    Monster nextmonster(long attack){
        maxblood += attack * (Math.random() * 10 + 20);
        return new Monster((num + 1) % Resourse.monsters.length, maxblood);
    }
}
