package com.shatteredpixel.shatteredpixeldungeon.actors.buffs.机制;

import static com.shatteredpixel.shatteredpixeldungeon.Dungeon.时间;
import static com.shatteredpixel.shatteredpixeldungeon.Dungeon.符文;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.FlavourBuff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Hunger;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.items.Generator;
import com.shatteredpixel.shatteredpixeldungeon.items.用品.海克斯秘卷;
import com.shatteredpixel.shatteredpixeldungeon.levels.Level;
import com.shatteredpixel.shatteredpixeldungeon.levels.Terrain;
import com.shatteredpixel.shatteredpixeldungeon.plants.Plant;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.watabou.utils.Random;

public class 地牢时间 extends FlavourBuff{
    @Override
    public boolean act(){
        spend( 1 );
        Dungeon.地牢时间++;
        if(Dungeon.地牢时间==时间(24)){
            Dungeon.地牢时间=0;
            Dungeon.地牢天数++;
            if(Dungeon.hero()){
                Hero hero=Dungeon.hero;
                if(hero.符文("海克斯获取:幸运")&&Random.Int(1)==0)
                    new 海克斯秘卷(true).放背包();
                if(hero.符文("广告复活"))
                    Buff.detach(hero,广告复活冷却.class);

                if(hero.符文("每日属性翻倍")&&hero.属性翻倍!=1024)
                    hero.属性翻倍*=2;
                if(hero.符文("勤劳的一天")){
                    hero.回百分比血(0.5f);
                    hero.回百分比护甲(0.5f);
                    hero.buff(Hunger.class).吃饭(Hunger.STARVING/2f);
                }
                if(符文("解除人体限制的诅咒")){
                    hero.属性成长+=0.001f;
                    if(Dungeon.地牢天数>=Dungeon.地牢寿命)
                        hero.死亡时(null);
                }
                if(符文("春暖花开"))
                    for(int i=0;i<Dungeon.level.length();i++){
                        if(Dungeon.level.map[i]==Terrain.EMPTY||Dungeon.level.map[i]==Terrain.EMPTY_DECO||Dungeon.level.map[i]==Terrain.EMBERS||Dungeon.level.map[i]==Terrain.WATER||Dungeon.level.map[i]==Terrain.GRASS){
                            if(Random.Int(7)==0){

                                Dungeon.level.plant((Plant.Seed)Generator.randomUsingDefaults(Generator.Category.SEED),i);
                            }else{

                                Level.set(i,Terrain.HIGH_GRASS);
                                GameScene.updateMap(i);
                            }
                        }
                    }

                if(符文("对冲基金"))
                    Dungeon.gold(Math.round(Dungeon.gold*0.025f*Dungeon.地牢天数));
            }
        }
        return true;
    }
}