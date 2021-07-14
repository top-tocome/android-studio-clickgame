package top.tocome.clickgame;

public class Monster {

    int num;//怪物编号
    long blood;//怪物当前血量
    long maxblood;//怪物最大血量

    Monster(int num, long maxblood) {//构造函数
        this.num = num;
        this.maxblood = maxblood;
        blood = maxblood;
    }

    boolean isdead() {
        return blood <= 0;
    }//判断怪物是否死亡

    void nextmonster() {//切换下一个怪物
        int combo = (int) Math.sqrt(maxblood * 2);
        maxblood = (combo + (int) (Math.random() * 10 + 10)) * (combo + (int) (Math.random() * 10 + 10)) / 2;
        blood = maxblood;
        num = (num + 1) % Resourse.monsters.length;
    }
}
