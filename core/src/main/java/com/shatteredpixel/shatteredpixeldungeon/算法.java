package com.shatteredpixel.shatteredpixeldungeon;

import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.utils.Callback;
import com.watabou.utils.Random;

public class 算法 {
    /*
    XX 武器阶+级 法杖1+级 防具阶+级 神器1+级 最大攻击 最大防御 法杖充能15% 神器充能30%

        饮血剑和无尽都0.8


		ArrayList<Integer> grassCells = new ArrayList<>();
		for (int i : PathFinder.NEIGHBOURS9){
			grassCells.add(hero.pos+i);
		}
		Random.shuffle(grassCells);
		for (int grassCell : grassCells){
			if (Dungeon.level.map[grassCell] == Terrain.EMPTY ||
					Dungeon.level.map[grassCell] == Terrain.EMBERS ||
					Dungeon.level.map[grassCell] == Terrain.EMPTY_DECO){
				Level.set(grassCell, Terrain.GRASS);
				GameScene.updateMap(grassCell);
			}
			CellEmitter.get(grassCell).burst(LeafParticle.LEVEL_SPECIFIC, 4);
		}
		// 5 cells total
		int totalGrassCells = 5;
		while (grassCells.size() > totalGrassCells){
			grassCells.remove(0);
		}
		for (int grassCell : grassCells){
			int t = Dungeon.level.map[grassCell];
			if ((t == Terrain.EMPTY || t == Terrain.EMPTY_DECO || t == Terrain.EMBERS
					|| t == Terrain.GRASS || t == Terrain.FURROWED_GRASS)
					&& Dungeon.level.plants.get(grassCell) == null){
				Level.set(grassCell, Terrain.HIGH_GRASS);
				GameScene.updateMap(grassCell);
			}
		}
		Dungeon.observe();
		Sample.INSTANCE.play(Assets.Sounds.PLANT);
		
		
		
		
		ArtifactRecharge.chargeArtifacts(hero, 4f);
		充能卷轴.charge(hero);
		Sample.INSTANCE.play( Assets.Sounds.CHARGEUP );
		
		
		
		
		
		Buff.施加( hero, PhysicalEmpower.class).set(Math.max(2, hero.等级 /3), 2);
		Sample.INSTANCE.play(Assets.Sounds.UNLOCK);
		
		
		
		
		
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
    public static int x25=32*24;
    public static int x26=32*25;
    public static int x27=32*26;
    public static int x28=32*27;
    public static float f2(float x){
        int l = Math.round(x * 100);
        float d = (float)(l/100.00);
        return d;
    }
    public static Item 种子(){

        String seed = SPDSettings.customSeed();
        seed=seed.replaceAll("调试", "");
    try {
        Class<?> classn = Class.forName("com.shatteredpixel.shatteredpixeldungeon.items."+seed);

        Object item = classn.newInstance();
        
        return ((Item)item).鉴定();

    } catch (Exception e) {
        try {
            Class<?> classn = Class.forName("com.shatteredpixel.shatteredpixeldungeon.items.armor."+seed);

            Object item = classn.newInstance();
            
        return ((Item)item).鉴定();

        } catch (Exception e1) {

            try {
                Class<?> classn = Class.forName("com.shatteredpixel.shatteredpixeldungeon.items.artifacts."+seed);

                Object item = classn.newInstance();
                
        return ((Item)item).鉴定();

            } catch (Exception e2) {

                try {
                    Class<?> classn = Class.forName("com.shatteredpixel.shatteredpixeldungeon.items.bags."+seed);

                    Object item = classn.newInstance();
                    
        return ((Item)item).鉴定();

                } catch (Exception e3) {

                    try {
                        Class<?> classn = Class.forName("com.shatteredpixel.shatteredpixeldungeon.items.bombs."+seed);

                        Object item = classn.newInstance();
                        
        return ((Item)item).鉴定();

                    } catch (Exception e4) {

                        try {
                            Class<?> classn = Class.forName("com.shatteredpixel.shatteredpixeldungeon.items.food."+seed);

                            Object item = classn.newInstance();
                            
        return ((Item)item).鉴定();

                        } catch (Exception e5) {

                            try {
                                Class<?> classn = Class.forName("com.shatteredpixel.shatteredpixeldungeon.items.potions."+seed);

                                Object item = classn.newInstance();
                                
        return ((Item)item).鉴定();

                            } catch (Exception e6) {

                                try {
                                    Class<?> classn = Class.forName("com.shatteredpixel.shatteredpixeldungeon.items.remains."+seed);

                                    Object item = classn.newInstance();
                                    
        return ((Item)item).鉴定();

                                } catch (Exception e7) {

                                    try {
                                        Class<?> classn = Class.forName("com.shatteredpixel.shatteredpixeldungeon.items.rings."+seed);

                                        Object item = classn.newInstance();
                                        
        return ((Item)item).鉴定();

                                    } catch (Exception e8) {

                                        try {
                                            Class<?> classn = Class.forName("com.shatteredpixel.shatteredpixeldungeon.items.scrolls."+seed);

                                            Object item = classn.newInstance();
                                            
        return ((Item)item).鉴定();

                                        } catch (Exception e9) {

                                            try {
                                                Class<?> classn = Class.forName("com.shatteredpixel.shatteredpixeldungeon.items.spells."+seed);

                                                Object item = classn.newInstance();
                                                
        return ((Item)item).鉴定();

                                            } catch (Exception e10) {

                                                try {
                                                    Class<?> classn = Class.forName("com.shatteredpixel.shatteredpixeldungeon.items.stones."+seed);

                                                    Object item = classn.newInstance();
                                                    
        return ((Item)item).鉴定();

                                                } catch (Exception e11) {

                                                    try {
                                                        Class<?> classn = Class.forName("com.shatteredpixel.shatteredpixeldungeon.items.trinkets."+seed);

                                                        Object item = classn.newInstance();
                                                        
        return ((Item)item).鉴定();

                                                    } catch (Exception e12) {

                                                        try {
                                                            Class<?> classn = Class.forName("com.shatteredpixel.shatteredpixeldungeon.items.wands."+seed);

                                                            Object item = classn.newInstance();
                                                            
        return ((Item)item).鉴定();

                                                        } catch (Exception e13) {

                                                            try {
                                                                Class<?> classn = Class.forName("com.shatteredpixel.shatteredpixeldungeon.items.weapon."+seed);

                                                                Object item = classn.newInstance();
                                                                
        return ((Item)item).鉴定();

                                                            } catch (Exception e14) {

                                                                try {
                                                                    Class<?> classn = Class.forName("com.shatteredpixel.shatteredpixeldungeon.items.weapon.darts."+seed);

                                                                    Object item = classn.newInstance();
                                                                    
        return ((Item)item).鉴定();

                                                                } catch (Exception e15) {
                                                                        try {
                                                                            Class<?> classn = Class.forName("com.shatteredpixel.shatteredpixeldungeon.items.scrolls.exotic."+seed);

                                                                            Object item = classn.newInstance();
                                                                            
        return ((Item)item).鉴定();

                                                                        } catch (Exception e17) {
                                                                            try {
                                                                                Class<?> classn = Class.forName("com.shatteredpixel.shatteredpixeldungeon.items.potions.brews."+seed);

                                                                                Object item = classn.newInstance();
                                                                                
        return ((Item)item).鉴定();

                                                                            } catch (Exception e18) {
                                                                                try {
                                                                                    Class<?> classn = Class.forName("com.shatteredpixel.shatteredpixeldungeon.items.potions.elixirs."+seed);

                                                                                    Object item = classn.newInstance();
                                                                                    
        return ((Item)item).鉴定();

                                                                                } catch (Exception e19) {
                                                                                    try {
                                                                                        Class<?> classn = Class.forName("com.shatteredpixel.shatteredpixeldungeon.items.potions.exotic."+seed);

                                                                                        Object item = classn.newInstance();
                                                                                        
        return ((Item)item).鉴定();

                                                                                    } catch (Exception e20) {
                                                                                            try{
                                                                                                Class<?> classn = Class.forName("com.shatteredpixel.shatteredpixeldungeon.items.weapon.mm."+seed);
                                                                                                
                                                                                                Object item = classn.newInstance();
                                                                                                
        return ((Item)item).鉴定();
                                                                                            } catch (Exception e21) {
                                                                                            return null;
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
    public static Item 种子(String s){
    try {
        Class<?> classn = Class.forName("com.shatteredpixel.shatteredpixeldungeon.items."+s);

        Object item = classn.newInstance();
        
        return ((Item)item).鉴定();

    } catch (Exception e) {
        try {
            Class<?> classn = Class.forName("com.shatteredpixel.shatteredpixeldungeon.items.armor."+s);

            Object item = classn.newInstance();
            
        return ((Item)item).鉴定();

        } catch (Exception e1) {

            try {
                Class<?> classn = Class.forName("com.shatteredpixel.shatteredpixeldungeon.items.artifacts."+s);

                Object item = classn.newInstance();
                
        return ((Item)item).鉴定();

            } catch (Exception e2) {

                try {
                    Class<?> classn = Class.forName("com.shatteredpixel.shatteredpixeldungeon.items.bags."+s);

                    Object item = classn.newInstance();
                    
        return ((Item)item).鉴定();

                } catch (Exception e3) {

                    try {
                        Class<?> classn = Class.forName("com.shatteredpixel.shatteredpixeldungeon.items.bombs."+s);

                        Object item = classn.newInstance();
                        
        return ((Item)item).鉴定();

                    } catch (Exception e4) {

                        try {
                            Class<?> classn = Class.forName("com.shatteredpixel.shatteredpixeldungeon.items.food."+s);

                            Object item = classn.newInstance();
                            
        return ((Item)item).鉴定();

                        } catch (Exception e5) {

                            try {
                                Class<?> classn = Class.forName("com.shatteredpixel.shatteredpixeldungeon.items.potions."+s);

                                Object item = classn.newInstance();
                                
        return ((Item)item).鉴定();

                            } catch (Exception e6) {

                                try {
                                    Class<?> classn = Class.forName("com.shatteredpixel.shatteredpixeldungeon.items.remains."+s);

                                    Object item = classn.newInstance();
                                    
        return ((Item)item).鉴定();

                                } catch (Exception e7) {

                                    try {
                                        Class<?> classn = Class.forName("com.shatteredpixel.shatteredpixeldungeon.items.rings."+s);

                                        Object item = classn.newInstance();
                                        
        return ((Item)item).鉴定();

                                    } catch (Exception e8) {

                                        try {
                                            Class<?> classn = Class.forName("com.shatteredpixel.shatteredpixeldungeon.items.scrolls."+s);

                                            Object item = classn.newInstance();
                                            
        return ((Item)item).鉴定();

                                        } catch (Exception e9) {

                                            try {
                                                Class<?> classn = Class.forName("com.shatteredpixel.shatteredpixeldungeon.items.spells."+s);

                                                Object item = classn.newInstance();
                                                
        return ((Item)item).鉴定();

                                            } catch (Exception e10) {

                                                try {
                                                    Class<?> classn = Class.forName("com.shatteredpixel.shatteredpixeldungeon.items.stones."+s);

                                                    Object item = classn.newInstance();
                                                    
        return ((Item)item).鉴定();

                                                } catch (Exception e11) {

                                                    try {
                                                        Class<?> classn = Class.forName("com.shatteredpixel.shatteredpixeldungeon.items.trinkets."+s);

                                                        Object item = classn.newInstance();
                                                        
        return ((Item)item).鉴定();

                                                    } catch (Exception e12) {

                                                        try {
                                                            Class<?> classn = Class.forName("com.shatteredpixel.shatteredpixeldungeon.items.wands."+s);

                                                            Object item = classn.newInstance();
                                                            
        return ((Item)item).鉴定();

                                                        } catch (Exception e13) {

                                                            try {
                                                                Class<?> classn = Class.forName("com.shatteredpixel.shatteredpixeldungeon.items.weapon."+s);

                                                                Object item = classn.newInstance();
                                                                
        return ((Item)item).鉴定();

                                                            } catch (Exception e14) {

                                                                try {
                                                                    Class<?> classn = Class.forName("com.shatteredpixel.shatteredpixeldungeon.items.weapon.melee."+s);

                                                                    Object item = classn.newInstance();
                                                                    
        return ((Item)item).鉴定();

                                                                } catch (Exception e15) {
                                                                    try {
                                                                        Class<?> classn = Class.forName("com.shatteredpixel.shatteredpixeldungeon.items.weapon.missiles."+s);

                                                                        Object item = classn.newInstance();
                                                                        
        return ((Item)item).鉴定();

                                                                    } catch (Exception e16) {
                                                                        try {
                                                                            Class<?> classn = Class.forName("com.shatteredpixel.shatteredpixeldungeon.items.scrolls.exotic."+s);

                                                                            Object item = classn.newInstance();
                                                                            
        return ((Item)item).鉴定();

                                                                        } catch (Exception e17) {
                                                                            try {
                                                                                Class<?> classn = Class.forName("com.shatteredpixel.shatteredpixeldungeon.items.potions.brews."+s);

                                                                                Object item = classn.newInstance();
                                                                                
        return ((Item)item).鉴定();

                                                                            } catch (Exception e18) {
                                                                                try {
                                                                                    Class<?> classn = Class.forName("com.shatteredpixel.shatteredpixeldungeon.items.potions.elixirs."+s);

                                                                                    Object item = classn.newInstance();
                                                                                    
        return ((Item)item).鉴定();

                                                                                } catch (Exception e19) {
                                                                                    try {
                                                                                        Class<?> classn = Class.forName("com.shatteredpixel.shatteredpixeldungeon.items.potions.exotic."+s);

                                                                                        Object item = classn.newInstance();
                                                                                        
        return ((Item)item).鉴定();

                                                                                    } catch (Exception e20) {
return null;
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
    public static void 调试(String s){
        if(isDebug())
        GLog.p(s);
    }
    public static void 修复效果(Callback c){
        Actor.add(new Actor() {
            @Override
            protected boolean act() {
                c.call();
                Actor.remove(this);
                return true;
            }
        });
    }
    public static boolean 彩蛋(String s){
        String seed = SPDSettings.customSeed();
        if(s.equals("更小"))
        if(seed.equals("更小")||seed.equals("更小调试"))
            return true;

        return false;
    }
    public static boolean 彩蛋(){
        String seed = SPDSettings.customSeed();
        if(seed.equals("更小")||seed.equals("更小调试"))
            return true;
        else return false;
    }
    public static boolean isDebug(){
//        Game.version.contains("INDEV")
        String seed = SPDSettings.customSeed();
        if (seed.matches("\\d+调试")) {
            return true;
        }
        if (seed.matches(".*调试")) {
            return true;
        }
        if(seed.equals("更小")||seed.equals("更小调试"))
            return true;
        else return false;
    }
    public static boolean 概率学(int x){
        return Random.Int(1,100)<= x+ (x == 33 ? 1 : 0);
    }
    public static boolean 概率学(float x){
        return Random.Float()<= x+ (x > 0.33f&&x < 0.34f ? 0.01f : 0);
    }
    public static boolean 概率概率学(int x){
        return 概率学(x)&&概率学(x);
    }
    public static boolean 概率概率学(float x){
        return 概率学(x)&&概率学(x);
    }
    public static float 固衰(float x){
        if (x >= 1){
            x= (float)(Math.sqrt(8*x+1)-1)/2f;
        }
        return x;
    }
    public static float 固衰(float x,float 超过){
        if (x >= 超过){
            //takes 5/6/7/8/9/10 dmg at 5/7/10/14/19/25 incoming dmg
            x = (float)((超过-1)+(Math.sqrt(8*(x-(超过-1))+1)-1)/2);
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
