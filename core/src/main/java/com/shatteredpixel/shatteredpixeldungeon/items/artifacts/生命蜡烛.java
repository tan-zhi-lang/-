

package com.shatteredpixel.shatteredpixeldungeon.items.artifacts;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Badges;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Invulnerability;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.MagicImmune;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.再生;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.圣盾;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.effects.particles.ShadowParticle;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.能量之戒;
import com.shatteredpixel.shatteredpixeldungeon.journal.Catalog;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndOptions;
import com.watabou.noosa.audio.Sample;

import java.util.ArrayList;

public class 生命蜡烛 extends Artifact {

	{
		image = 物品表.生命蜡烛;
		defaultAction=AC_PRICK;
		levelCap = 6;
		charge = Math.min(1+等级(),6);
		partialCharge = 0;
		chargeCap = Math.min(1+等级(),6);
	}

	public static final String AC_PRICK = "PRICK";

	@Override
	public ArrayList<String> actions( Hero hero ) {
		ArrayList<String> actions = super.actions( hero );
		if (isEquipped( hero )
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
			
			if(hero.hasbuff(圣盾.class)){
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
				GameScene.show(new WndOptions(new ItemSprite(this),Messages.titleCase(name()),Messages.get(this,"prick_warn",minDmg,maxDmg,100*deathChance),Messages.get(this,"yes"),Messages.get(this,"no")){
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
		return (等级()+1)*2.5f;
	}

	private void prick(Hero hero){
		float damage = PrickDmg();

		charge--;
		hero.sprite.operate( hero.pos );
		hero.busy();
		hero.spend(Actor.TICK);
		GLog.w( Messages.get(this, "onprick") );
		if (damage <= 0){
			damage = 1;
		} else {
			Sample.INSTANCE.play(Assets.Sounds.CURSED);
			hero.sprite.emitter().burst( ShadowParticle.CURSE);
		}

		Catalog.countUse(getClass());
		hero.受伤时(damage, this);
		Buff.延长(hero,Invulnerability.class,4+等级()*2.5f);

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
		chargeCap = Math.min(1+等级(),6);

		return super.升级();
	}

	@Override
	protected ArtifactBuff passiveBuff() {
		return new 燃烧();
	}
	
	@Override
	public void charge(Hero target, float amount) {
		if (charge < chargeCap && !cursed && target.buff(MagicImmune.class) == null){
			partialCharge += 0.0067f*amount;
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
				desc += Messages.get(this, "desc",(等级()+1)*2.5f
						,4+等级()*2.5f);
		}

		return desc;
	}

	public class 燃烧 extends ArtifactBuff {
		@Override
		public boolean act() {
			if (charge < chargeCap
				&&!cursed
				&& target.buff(MagicImmune.class) == null
				&&再生.regenOn()) {
				//120 turns to charge at full, 60 turns to charge at 0/8
				float chargeGain = 1 / (165 - (chargeCap - charge)*15);
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
