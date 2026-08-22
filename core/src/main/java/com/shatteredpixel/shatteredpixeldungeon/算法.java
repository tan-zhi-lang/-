package com.shatteredpixel.shatteredpixeldungeon;

import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.utils.Callback;
import com.watabou.utils.Random;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class 算法 {
    /*


        Buff延长
		public void extend(float extension){
			if (cooldown()+extension <= 2*DURATION){
				spend(extension);
			} else {
				postpone(2*DURATION);
			}
		}

        if(算法.概率学(1/4f)){
            Plant.Seed plant=new WandOfRegrowth.Seedpod.Seed();
                plant=new WandOfRegrowth.Dewcatcher.Seed();
            Dungeon.level.plant(plant,pos);
    }

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
    public static String 日期="8.22/15:53";
    public static float 金额=5;
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

    public static String zf(float x){
        String s="";
        if(x>0)s+="+";
        if(x<0)s+="-";
        return s+x;
    }
    public static String kw2(float x) {
        return kw2(SPDSettings.保留位数(),x);
    }
    public static String kw2(int x2,float x) {
        // 处理后缀
        float val;
        String suffix = "";
        if (x >= 1000_0000) {
            val = x / 1000_0000f;
            suffix = "kw";
        }else if (x >= 1_0000) {
            val = x / 1_0000f;
            suffix = "w";
        } else if (x >= 1000) {
            val = x / 1000f;
            suffix = "K";
        } else {
            val = x;
        }
        try{
            float absVal=Math.abs(val);
            // 用字符串构造 BigDecimal，避免二进制浮点误差
            BigDecimal bd=new BigDecimal(Float.toString(absVal));
            BigDecimal fracBD=bd.remainder(BigDecimal.ONE); // 小数部分

            int scale=2;
            int digits=x2;

            if(digits==0){
                scale=0;
            }else
                if(digits==1){
                    if(SPDSettings.四舍五入()){
                        // 整数 或 小数 ≥0.5 -> 舍入到整数，否则保留一位小数
                        if(fracBD.compareTo(BigDecimal.ZERO)==0||fracBD.compareTo(new BigDecimal("0.5"))>=0){
                            scale=0;
                        }else{
                            scale=1;
                        }
                    }else{
                        scale=1;
                    }
                }else
                    if(digits==2){
                        if(SPDSettings.四舍五入()){
                            // 整数 或 小数 ≥0.5 -> 整数
                            if(fracBD.compareTo(BigDecimal.ZERO)==0||fracBD.compareTo(new BigDecimal("0.5"))>=0){
                                scale=0;
                            }else
                                if(fracBD.compareTo(new BigDecimal("0.05"))>=0){
                                    scale=1;          // 小数 ≥0.05 -> 保留一位
                                }else{
                                    scale=2;          // 其他 -> 保留两位
                                }
                        }else{
                            scale=2;
                        }
                    }

            BigDecimal rounded=bd.setScale(scale,RoundingMode.HALF_UP);
            String formatted=rounded.toString();

            // 处理符号
            if(val<0){
                formatted="-"+formatted;
            }
            return formatted+suffix;
        }catch(Exception e){
        }
        return x+"";
    }
    public static Item 物品(){
        return 物品(SPDSettings.customSeed().replaceAll("调试", ""));
    }

    public static Item 物品(String input){

        String 名称 = input;
        int 数量 = 1;
        int 等级 = 0;
        boolean 需设数量 = false;
        boolean 需设等级 = false;


            java.util.regex.Matcher m;
            // 匹配 +数字 -> 等级
            if(input.contains("+")){
                m=java.util.regex.Pattern.compile("^(.+)\\+(\\d+)$").matcher(input);
                if(m.matches()){
                    名称=m.group(1);
                    等级=Integer.parseInt(m.group(2));
                    需设等级=true;
                }
            }
            if(input.contains("x") || input.contains("X")){
                // 匹配 x/X数字 -> 数量
                m = java.util.regex.Pattern.compile("^(.+)[xX](\\d+)$").matcher(input);
                if (m.matches()) {
                    名称 = m.group(1);
                    数量 = Integer.parseInt(m.group(2));
                    需设数量 = true;
                }
            }
        String 首="com.shatteredpixel.shatteredpixeldungeon.";
        String[] 包 = {
                "plants.",
                "items.",
                "items.物品.",
                "items.用品.",
                "items.armor.",
                "items.artifacts.",
                "items.bags.",
                "items.bombs.",
                "items.food.",
                "items.potions.",
                "items.remains.",
                "items.rings.",
                "items.scrolls.",
                "items.spells.",
                "items.stones.",
                "items.trinkets.",
                "items.wands.",
                "items.weapon.",
                "items.weapon.子弹.",
                "items.weapon.枪械.",
                "items.scrolls.exotic.",
                "items.potions.brews.",
                "items.potions.elixirs.",
                "items.potions.exotic."
        };
        for (String p : 包) {
            try {
                Item item = (Item)Class.forName(首+p
                                                + 名称+(p.equals("plants.")?"$Seed":"")
                ).newInstance();


                    item.鉴定();
                if (需设数量 && item.可堆叠) item.数量(数量);
                if (需设等级 && item.真可升级()) item.等级(等级);
                return item;
            } catch (Exception ignored) {}
        }
        return null;
    }

    public static void 调试(String s){
        if(isDebug())
        GLog.绿(s);
    }
    public static void 修复效果(Callback c){
        Actor.add(new Actor() {
            {
                actPriority = VFX_PRIO-1;//优先级
            }
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
        if(seed.contains("更小")){
            return true;
        }
        return false;
    }
    public static boolean 彩蛋(){
        String seed = SPDSettings.customSeed();
        if(seed.contains("更小")){
            return true;
        }
        return false;
    }
    public static boolean isDebug(){
//        Game.version.contains("INDEV")
        String seed = SPDSettings.customSeed();
        if (seed.contains("调试")) {
            return true;
        }

        return false;
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
