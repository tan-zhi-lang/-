package com.shatteredpixel.shatteredpixeldungeon;

import com.watabou.utils.Random;

public class 算法 {

    public static int x2=32;
    public static int x3=64;
    public static int x4=96;
    public static int x5=128;
    public static int x6=160;
    public static boolean 概率学(int x){
        return Random.Int(1,100)<= x;
    }
    public static int 固衰(int x){
        x = 4 + (int)(Math.sqrt(8*(x - 4) + 1) - 1)/2;
        return x;
    }
    public static int 固衰(int x,int 超过){
        if (x >= 超过){
            //takes 5/6/7/8/9/10 dmg at 5/7/10/14/19/25 incoming dmg
            x = 4 + (int)(Math.sqrt(8*(x - 4) + 1) - 1)/2;
        }
        return x;
    }
}
