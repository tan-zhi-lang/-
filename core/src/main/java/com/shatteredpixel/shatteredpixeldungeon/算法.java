package com.shatteredpixel.shatteredpixeldungeon;

import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Frost;
import com.shatteredpixel.shatteredpixeldungeon.effects.MagicMissile;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.mechanics.Ballistica;
import com.watabou.utils.Callback;
import com.watabou.utils.Random;

public class 算法 {
    /*

    * */
    public static int x2=32;
    public static int x3=32*2;
    public static int x4=32*3;
    public static int x5=32*4;
    public static int x6=32*5;
    public static int x7=32*6;
    public static int x8=32*7;
    public static int x9=32*8;
    public static int x10=32*9;
    public static int x11=32*10;
    public static int x12=32*11;
    public static int x13=32*12;
    public static int x14=32*13;
    public static int x15=32*14;
    public static int x16=32*15;
    public static int x17=32*16;
    public static int x18=32*17;
    public static int x19=32*18;
    public static int x20=32*19;
    public static int x21=32*20;
    public static int x22=32*21;
    public static int x23=32*22;
    public static int x24=32*23;
    public static void 修复Buff(Char a,Integer i,Callback c){
        final Ballistica shot = new Ballistica( a.pos, i, Ballistica.PROJECTILE);
        MagicMissile.boltFromChar( a.sprite.parent,
                MagicMissile.WARD_CONE,
                a.sprite,
                shot.collisionPos,
                c);
    }
    public static void 种子(){
    try {
        Class<?> classn = Class.forName("com.shatteredpixel.shatteredpixeldungeon.items."+SPDSettings.customSeed());

        Object item = classn.newInstance();
        ((Item)item).放背包();

    } catch (Exception e) {
        try {
            Class<?> classn = Class.forName("com.shatteredpixel.shatteredpixeldungeon.items.armor."+SPDSettings.customSeed());

            Object item = classn.newInstance();
            ((Item)item).放背包();

        } catch (Exception e1) {

            try {
                Class<?> classn = Class.forName("com.shatteredpixel.shatteredpixeldungeon.items.artifacts."+SPDSettings.customSeed());

                Object item = classn.newInstance();
                ((Item)item).放背包();

            } catch (Exception e2) {

                try {
                    Class<?> classn = Class.forName("com.shatteredpixel.shatteredpixeldungeon.items.bags."+SPDSettings.customSeed());

                    Object item = classn.newInstance();
                    ((Item)item).放背包();

                } catch (Exception e3) {

                    try {
                        Class<?> classn = Class.forName("com.shatteredpixel.shatteredpixeldungeon.items.bombs."+SPDSettings.customSeed());

                        Object item = classn.newInstance();
                        ((Item)item).放背包();

                    } catch (Exception e4) {

                        try {
                            Class<?> classn = Class.forName("com.shatteredpixel.shatteredpixeldungeon.items.food."+SPDSettings.customSeed());

                            Object item = classn.newInstance();
                            ((Item)item).放背包();

                        } catch (Exception e5) {

                            try {
                                Class<?> classn = Class.forName("com.shatteredpixel.shatteredpixeldungeon.items.potions."+SPDSettings.customSeed());

                                Object item = classn.newInstance();
                                ((Item)item).放背包();

                            } catch (Exception e6) {

                                try {
                                    Class<?> classn = Class.forName("com.shatteredpixel.shatteredpixeldungeon.items.remains."+SPDSettings.customSeed());

                                    Object item = classn.newInstance();
                                    ((Item)item).放背包();

                                } catch (Exception e7) {

                                    try {
                                        Class<?> classn = Class.forName("com.shatteredpixel.shatteredpixeldungeon.items.rings."+SPDSettings.customSeed());

                                        Object item = classn.newInstance();
                                        ((Item)item).放背包();

                                    } catch (Exception e8) {

                                        try {
                                            Class<?> classn = Class.forName("com.shatteredpixel.shatteredpixeldungeon.items.scrolls."+SPDSettings.customSeed());

                                            Object item = classn.newInstance();
                                            ((Item)item).放背包();

                                        } catch (Exception e9) {

                                            try {
                                                Class<?> classn = Class.forName("com.shatteredpixel.shatteredpixeldungeon.items.spells."+SPDSettings.customSeed());

                                                Object item = classn.newInstance();
                                                ((Item)item).放背包();

                                            } catch (Exception e10) {

                                                try {
                                                    Class<?> classn = Class.forName("com.shatteredpixel.shatteredpixeldungeon.items.stones."+SPDSettings.customSeed());

                                                    Object item = classn.newInstance();
                                                    ((Item)item).放背包();

                                                } catch (Exception e11) {

                                                    try {
                                                        Class<?> classn = Class.forName("com.shatteredpixel.shatteredpixeldungeon.items.trinkets."+SPDSettings.customSeed());

                                                        Object item = classn.newInstance();
                                                        ((Item)item).放背包();

                                                    } catch (Exception e12) {

                                                        try {
                                                            Class<?> classn = Class.forName("com.shatteredpixel.shatteredpixeldungeon.items.wands."+SPDSettings.customSeed());

                                                            Object item = classn.newInstance();
                                                            ((Item)item).放背包();

                                                        } catch (Exception e13) {

                                                            try {
                                                                Class<?> classn = Class.forName("com.shatteredpixel.shatteredpixeldungeon.items.weapon."+SPDSettings.customSeed());

                                                                Object item = classn.newInstance();
                                                                ((Item)item).放背包();

                                                            } catch (Exception e14) {

                                                                try {
                                                                    Class<?> classn = Class.forName("com.shatteredpixel.shatteredpixeldungeon.items.weapon.darts."+SPDSettings.customSeed());

                                                                    Object item = classn.newInstance();
                                                                    ((Item)item).放背包();

                                                                } catch (Exception e15) {
                                                                        try {
                                                                            Class<?> classn = Class.forName("com.shatteredpixel.shatteredpixeldungeon.items.scrolls.exotic."+SPDSettings.customSeed());

                                                                            Object item = classn.newInstance();
                                                                            ((Item)item).放背包();

                                                                        } catch (Exception e17) {
                                                                            try {
                                                                                Class<?> classn = Class.forName("com.shatteredpixel.shatteredpixeldungeon.items.potions.brews."+SPDSettings.customSeed());

                                                                                Object item = classn.newInstance();
                                                                                ((Item)item).放背包();

                                                                            } catch (Exception e18) {
                                                                                try {
                                                                                    Class<?> classn = Class.forName("com.shatteredpixel.shatteredpixeldungeon.items.potions.elixirs."+SPDSettings.customSeed());

                                                                                    Object item = classn.newInstance();
                                                                                    ((Item)item).放背包();

                                                                                } catch (Exception e19) {
                                                                                    try {
                                                                                        Class<?> classn = Class.forName("com.shatteredpixel.shatteredpixeldungeon.items.potions.exotic."+SPDSettings.customSeed());

                                                                                        Object item = classn.newInstance();
                                                                                        ((Item)item).放背包();

                                                                                    } catch (Exception e20) {
                                                                                            try{
                                                                                                Class<?> classn = Class.forName("com.shatteredpixel.shatteredpixeldungeon.items.weapon.mm."+SPDSettings.customSeed());
                                                                                                
                                                                                                Object item = classn.newInstance();
                                                                                                ((Item)item).放背包();
                                                                                            } catch (Exception e21) {
                                                                                            
                                                                                            }
                                                                                    }
                                                                                }
                                                                                }
                                                                            }
                                                                    }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    }
    public static void 种子(String s){
    try {
        Class<?> classn = Class.forName("com.shatteredpixel.shatteredpixeldungeon.items."+s);

        Object item = classn.newInstance();
        ((Item)item).放背包();

    } catch (Exception e) {
        try {
            Class<?> classn = Class.forName("com.shatteredpixel.shatteredpixeldungeon.items.armor."+s);

            Object item = classn.newInstance();
            ((Item)item).放背包();

        } catch (Exception e1) {

            try {
                Class<?> classn = Class.forName("com.shatteredpixel.shatteredpixeldungeon.items.artifacts."+s);

                Object item = classn.newInstance();
                ((Item)item).放背包();

            } catch (Exception e2) {

                try {
                    Class<?> classn = Class.forName("com.shatteredpixel.shatteredpixeldungeon.items.bags."+s);

                    Object item = classn.newInstance();
                    ((Item)item).放背包();

                } catch (Exception e3) {

                    try {
                        Class<?> classn = Class.forName("com.shatteredpixel.shatteredpixeldungeon.items.bombs."+s);

                        Object item = classn.newInstance();
                        ((Item)item).放背包();

                    } catch (Exception e4) {

                        try {
                            Class<?> classn = Class.forName("com.shatteredpixel.shatteredpixeldungeon.items.food."+s);

                            Object item = classn.newInstance();
                            ((Item)item).放背包();

                        } catch (Exception e5) {

                            try {
                                Class<?> classn = Class.forName("com.shatteredpixel.shatteredpixeldungeon.items.potions."+s);

                                Object item = classn.newInstance();
                                ((Item)item).放背包();

                            } catch (Exception e6) {

                                try {
                                    Class<?> classn = Class.forName("com.shatteredpixel.shatteredpixeldungeon.items.remains."+s);

                                    Object item = classn.newInstance();
                                    ((Item)item).放背包();

                                } catch (Exception e7) {

                                    try {
                                        Class<?> classn = Class.forName("com.shatteredpixel.shatteredpixeldungeon.items.rings."+s);

                                        Object item = classn.newInstance();
                                        ((Item)item).放背包();

                                    } catch (Exception e8) {

                                        try {
                                            Class<?> classn = Class.forName("com.shatteredpixel.shatteredpixeldungeon.items.scrolls."+s);

                                            Object item = classn.newInstance();
                                            ((Item)item).放背包();

                                        } catch (Exception e9) {

                                            try {
                                                Class<?> classn = Class.forName("com.shatteredpixel.shatteredpixeldungeon.items.spells."+s);

                                                Object item = classn.newInstance();
                                                ((Item)item).放背包();

                                            } catch (Exception e10) {

                                                try {
                                                    Class<?> classn = Class.forName("com.shatteredpixel.shatteredpixeldungeon.items.stones."+s);

                                                    Object item = classn.newInstance();
                                                    ((Item)item).放背包();

                                                } catch (Exception e11) {

                                                    try {
                                                        Class<?> classn = Class.forName("com.shatteredpixel.shatteredpixeldungeon.items.trinkets."+s);

                                                        Object item = classn.newInstance();
                                                        ((Item)item).放背包();

                                                    } catch (Exception e12) {

                                                        try {
                                                            Class<?> classn = Class.forName("com.shatteredpixel.shatteredpixeldungeon.items.wands."+s);

                                                            Object item = classn.newInstance();
                                                            ((Item)item).放背包();

                                                        } catch (Exception e13) {

                                                            try {
                                                                Class<?> classn = Class.forName("com.shatteredpixel.shatteredpixeldungeon.items.weapon."+s);

                                                                Object item = classn.newInstance();
                                                                ((Item)item).放背包();

                                                            } catch (Exception e14) {

                                                                try {
                                                                    Class<?> classn = Class.forName("com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee."+s);

                                                                    Object item = classn.newInstance();
                                                                    ((Item)item).放背包();

                                                                } catch (Exception e15) {
                                                                    try {
                                                                        Class<?> classn = Class.forName("com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles."+s);

                                                                        Object item = classn.newInstance();
                                                                        ((Item)item).放背包();

                                                                    } catch (Exception e16) {
                                                                        try {
                                                                            Class<?> classn = Class.forName("com.shatteredpixel.shatteredpixeldungeon.items.scrolls.exotic."+s);

                                                                            Object item = classn.newInstance();
                                                                            ((Item)item).放背包();

                                                                        } catch (Exception e17) {
                                                                            try {
                                                                                Class<?> classn = Class.forName("com.shatteredpixel.shatteredpixeldungeon.items.potions.brews."+s);

                                                                                Object item = classn.newInstance();
                                                                                ((Item)item).放背包();

                                                                            } catch (Exception e18) {
                                                                                try {
                                                                                    Class<?> classn = Class.forName("com.shatteredpixel.shatteredpixeldungeon.items.potions.elixirs."+s);

                                                                                    Object item = classn.newInstance();
                                                                                    ((Item)item).放背包();

                                                                                } catch (Exception e19) {
                                                                                    try {
                                                                                        Class<?> classn = Class.forName("com.shatteredpixel.shatteredpixeldungeon.items.potions.exotic."+s);

                                                                                        Object item = classn.newInstance();
                                                                                        ((Item)item).放背包();

                                                                                    } catch (Exception e20) {

                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    }
    public static void 修复效果(int a,int d,Class s){
        final Ballistica shot = new Ballistica( a,d, Ballistica.MAGIC_BOLT);
        Char enemy = Actor.findChar( shot.collisionPos );
        Buff.施加(enemy, s, Frost.DURATION);
    }
    public static boolean isDebug(){
//        Game.version.contains("INDEV")
        return SPDSettings.customSeed().equals("调试");
    }
    public static boolean 概率学(int x){
        return Random.Int(1,100)<= x+ (x == 33 ? 1 : 0);
    }
    public static boolean 概率学(float x){
        return Random.Float()<= x+ (x == 0.33f ? 0.01f : 0);
    }
    public static boolean 概率概率学(int x){
        return 概率学(x)&&概率学(x);
    }
    public static boolean 概率概率学(float x){
        return 概率学(x)&&概率学(x);
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
    public static float 变1值(float value) {
        
        if(value==1.0f){
            value=0.0f;
        }
        if(value==0.0f){
            value=0.1f;
        }
        if(value==0.1){
            value=0.2f;
        }
        if(value==0.2){
            value=0.3f;
        }
        if(value==0.4){
            value=0.5f;
        }
        if(value==0.5){
            value=0.6f;
        }
        if(value==0.5){
            value=0.6f;
        }
        if(value==0.6){
            value=0.7f;
        }
        if(value==0.7){
            value=0.8f;
        }
        if(value==0.8){
            value=0.9f;
        }
        if(value==0.9){
            value=1.0f;
        }
        return value;
    }
    public static float 变2值(float value) {
        
        if(value==0.1f){
            value=0.00f;
        }
        if(value==0.00f){
            value=0.01f;
        }
        if(value==0.01){
            value=0.02f;
        }
        if(value==0.02){
            value=0.03f;
        }
        if(value==0.04){
            value=0.05f;
        }
        if(value==0.05){
            value=0.06f;
        }
        if(value==0.05){
            value=0.06f;
        }
        if(value==0.06){
            value=0.07f;
        }
        if(value==0.07){
            value=0.08f;
        }
        if(value==0.08){
            value=0.09f;
        }
        if(value==0.09){
            value=0.1f;
        }
        return value;
    }
    public static int 取大小( int min, int value, int max ) {
        if (value < min) {
            return min;
        } else if (value > max) {
            return max;
        } else {
            return value;
        }
    }
}
