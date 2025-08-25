package com.shatteredpixel.shatteredpixeldungeon;

import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Frost;
import com.shatteredpixel.shatteredpixeldungeon.effects.MagicMissile;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.mechanics.Ballistica;
import com.watabou.noosa.Game;
import com.watabou.utils.Callback;
import com.watabou.utils.Random;

public class 算法 {
    /*

    * */
    public static int x2=32;
    public static int x3=64;
    public static int x4=96;
    public static int x5=128;
    public static int x6=160;
    public static int x7=192;
    public static int x8=224;
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
                                                                    Class<?> classn = Class.forName("com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee."+SPDSettings.customSeed());

                                                                    Object item = classn.newInstance();
                                                                    ((Item)item).放背包();

                                                                } catch (Exception e15) {
                                                                    try {
                                                                        Class<?> classn = Class.forName("com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles."+SPDSettings.customSeed());

                                                                        Object item = classn.newInstance();
                                                                        ((Item)item).放背包();

                                                                    } catch (Exception e16) {
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
        return SPDSettings.customSeed().equals("调试")||Game.version.contains("INDEV");
    }
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
