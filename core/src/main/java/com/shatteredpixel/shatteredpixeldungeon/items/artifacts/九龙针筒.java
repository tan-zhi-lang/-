

package com.shatteredpixel.shatteredpixeldungeon.items.artifacts;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Badges;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Invulnerability;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.MagicImmune;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.再生;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.护盾;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.effects.particles.ShadowParticle;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.能量之戒;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfLivingEarth;
import com.shatteredpixel.shatteredpixeldungeon.journal.Catalog;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.plants.Earthroot;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndOptions;
import com.watabou.noosa.audio.Sample;

import java.util.ArrayList;

public class 九龙针筒 extends Artifact {

	{
		image = 物品表.九龙针筒;
		defaultAction=AC_PRICK;
		levelCap = 9;
		charge = Math.min(1+等级(),9);
		partialCharge = 0;
		chargeCap = Math.min(1+等级(),9);
	}

	public static final String AC_PRICK = "PRICK";

	@Override
	public ArrayList<String> actions( Hero hero ) {
		ArrayList<String> actions = super.actions( hero );
		if (isEquipped( hero )
				&& 等级() < levelCap
				&& charge>0
				&& !cursed
				&& !hero.是无敌(getClass())
				&& hero.buff(MagicImmune.class) == null)
			actions.add(AC_PRICK);
		return actions;
	}
	
	@Override
	public void execute(Hero hero, String action ) {
		super.execute(hero, action);
		
		if (action.equals(AC_PRICK)
			&& charge>0
			&&isEquipped(hero)){

			float minDmg=PrickDmg();
			float maxDmg=PrickDmg();
			
			float totalHeroHP=hero.生命+hero.shielding()+hero.最大防御()+hero.护甲;
			if(hero.hasbuff(Invulnerability.class)){
				minDmg=0;
				maxDmg=0;
			}if(hero.hasbuff(护盾.class)){
				minDmg=0;
				maxDmg=0;
			}
			float deathChance=0;
			
			if(totalHeroHP<maxDmg){
				deathChance=(maxDmg-totalHeroHP)/(float)(maxDmg-minDmg);
				if(deathChance<0.5f){
					deathChance=(float)Math.pow(2*deathChance,2)/2f;
				}else if(deathChance<1f){
					deathChance=1f-deathChance;
					deathChance=(float)Math.pow(2*deathChance,2)/2f;
					deathChance=1f-deathChance;
				}else{
					deathChance=1;
				}
			}
			if(deathChance>0.85f){
				GameScene.show(new WndOptions(new ItemSprite(this),Messages.titleCase(name()),Messages.get(this,"prick_warn",minDmg,maxDmg,Messages.decimalFormat("#.##",100*deathChance)),Messages.get(this,"yes"),Messages.get(this,"no")){
					@Override
					protected void onSelect(int index){
						if(index==0)
							prick(Dungeon.hero);
					}
					
				});
			}else{
				prick(Dungeon.hero);
			}
		}
	}

	private float PrickDmg(){
		return (等级()+1)*5;
	}

	private void prick(Hero hero){
		float damage = PrickDmg();

		//need to process on-hit effects manually
		Earthroot.Armor armor = hero.buff(Earthroot.Armor.class);
		if (armor != null) {
			damage = armor.absorb(damage);
		}

		WandOfLivingEarth.RockArmor rockArmor = hero.buff(WandOfLivingEarth.RockArmor.class);
		if (rockArmor != null) {
			damage = rockArmor.absorb(damage);
		}
		charge--;
		damage=hero.防御(hero,damage);
		damage-=hero.护甲伤害(damage);
		hero.力量+=0.1f+等级()*0.01f;
		hero.sprite.operate( hero.pos );
		hero.busy();
		hero.spend(Actor.TICK);
		GLog.w( Messages.get(this, "onprick") );
		if (damage <= 0){
			damage = 1;
		} else {
			Sample.INSTANCE.play(Assets.Sounds.CURSED);
			hero.sprite.emitter().burst( ShadowParticle.CURSE, 4+(damage/10) );
		}

		Catalog.countUse(getClass());
		hero.受伤时(damage, this);

		if (!hero.isAlive()) {
			Badges.validateDeathFromFriendlyMagic();
			Dungeon.fail( this );
			GLog.n( Messages.get(this, "ondeath") );
		} else {
			if(等级() < levelCap)
			升级();
		}
	}

	@Override
	public Item 升级() {
		chargeCap = Math.min(1+等级(),9);
		return super.升级();
	}

	@Override
	protected ArtifactBuff passiveBuff() {
		return new 力量();
	}
	
	@Override
	public void charge(Hero target, float amount) {
		if (charge < chargeCap && !cursed && target.buff(MagicImmune.class) == null){
			partialCharge += 0.09f*amount;
			while (partialCharge >= 1){
				partialCharge--;
				charge++;
			}
			if (charge >= chargeCap){
				partialCharge = 0;
			}
			updateQuickslot();
		}
	}
	
	@Override
	public String desc() {
		String desc = "";

		if (isEquipped (Dungeon.hero)){
			desc += "\n\n";
			if (cursed)
				desc += Messages.get(this, "desc_cursed");
			else
				desc += Messages.get(this, "desc",(等级()+1)*5,String.format("%.2f",0.1f+等级()*0.01f));
		}

		return desc;
	}

	public class 力量 extends ArtifactBuff {
		@Override
		public boolean act() {
			if (charge < chargeCap
				&&!cursed
				&& target.buff(MagicImmune.class) == null
				&&再生.regenOn()) {
				//120 turns to charge at full, 60 turns to charge at 0/8
				float chargeGain = 1 / (99 - (chargeCap - charge)*9f);
				chargeGain *= 能量之戒.artifactChargeMultiplier(target);
				partialCharge += chargeGain;

				while (partialCharge >= 1) {
					partialCharge --;
					charge ++;

					if (charge == chargeCap){
						partialCharge = 0;
					}
				}
			}

			updateQuickslot();

			spend( TICK );

			return true;
		}
	}

}
