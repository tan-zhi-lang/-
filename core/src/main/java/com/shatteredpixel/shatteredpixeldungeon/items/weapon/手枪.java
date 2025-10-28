

package com.shatteredpixel.shatteredpixeldungeon.items.weapon;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.ShatteredPixelDungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.MagicImmune;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Recharging;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.再生;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.effects.CellEmitter;
import com.shatteredpixel.shatteredpixeldungeon.effects.particles.BlastParticle;
import com.shatteredpixel.shatteredpixeldungeon.effects.particles.SmokeParticle;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.RingOfSharpshooting;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.能量之戒;
import com.shatteredpixel.shatteredpixeldungeon.items.手枪子弹;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.CellSelector;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.audio.Sample;

import java.util.ArrayList;

public class 手枪 extends Weapon{
	public static final String AC_SHOOT		= "SHOOT";
	public static final String AC_换弹		= "换弹";
	
	{
		image = 物品表.手枪;
		hitSound = Assets.Sounds.HIT_STAB;
		
		tier = 1;
		伤害= 0.6f;

		defaultAction = AC_SHOOT;
		usesTargeting = true;
	}
	@Override
	public int 金币() {
		return Math.round(super.金币()*1.34f);
	}
	@Override
	public ArrayList<String> actions(Hero hero) {
		ArrayList<String> actions = super.actions(hero);
		actions.add(AC_SHOOT);
		actions.add(AC_换弹);
		return actions;
	}
	@Override
	public String status() {
		if (levelKnown) {
			return curCharges + "/" + maxCharges;
		} else {
			return null;
		}
	}
	@Override
	public void execute(Hero hero, String action) {
		super.execute(hero, action);
		curUser = hero;
		curItem = this;
		if(!isEquipped(curUser)){
			GLog.w("你需要装备枪械！");
		}
		if (action.equals(AC_换弹)) {
			if(curCharges==0){
				换弹();
				return;
			}
		}
		if (action.equals(AC_SHOOT)&&isEquipped(curUser)) {
			if(curCharges==0){
				换弹();
				return;
			}
			GameScene.selectCell( shooter );
		}
	}
	public void 换弹(){
		Item 子弹=curUser.belongings.getItem(手枪子弹.class);
		if(子弹!=null&&子弹.数量()>0){
			int 消耗=maxCharges-curCharges;
			if(子弹.数量()<消耗){
				if(curCharges==0){
					curCharges=Math.min(maxCharges,子弹.数量());
				}else if(curCharges>0){
					curCharges+=Math.min(maxCharges-curCharges,子弹.数量());
				}
				子弹.detachAll(curUser.belongings.backpack);
			}else{
				if(子弹.数量()==消耗){
					子弹.detachAll(curUser.belongings.backpack);
				}else{
					if(curCharges==0){
						curCharges=Math.min(maxCharges,子弹.数量());
					}else if(curCharges>0){
						curCharges+=Math.min(maxCharges-curCharges,子弹.数量());
					}
					子弹.split(消耗).detachAll(curUser.belongings.backpack);
				}
			}
			Sample.INSTANCE.play( Assets.Sounds.换弹 );
			
			curUser.spend(4);
			curUser.busy();
			(curUser.sprite).operate();
			updateQuickslot();
		}
	}
	
	public int 最小枪械攻击() {
		return 最小枪械攻击(强化等级());
	}
	public int 最小枪械攻击(int lvl) {
		int dmg =1+Dungeon.hero.等级(0.2f)
				 +RingOfSharpshooting.levelDamageBonus(Dungeon.hero)
				 +(curseInfusionBonus ? 1 + Dungeon.hero.等级(0.2f) : 0);
		return Math.max(0, dmg);
	}
	
	public int 最大枪械攻击() {
		return 最大枪械攻击(强化等级());
	}
	public int 最大枪械攻击(int lvl) {
		int dmg = 6 + Dungeon.hero.等级(0.4f)
				  + 2*RingOfSharpshooting.levelDamageBonus(Dungeon.hero)
				  + (curseInfusionBonus ? 2 + Dungeon.hero.等级(0.2f) : 0);
		return Math.max(0, dmg);
	}
	
	@Override
	public String desc() {
		return Messages.get(this, "desc",最小枪械攻击(),最大枪械攻击());
	}
	public int maxCharges = initialCharges();
	public int initialCharges() {
		return 5;
	}
	protected int chargesPerCast() {
		return 1;
	}
	public int curCharges = maxCharges;
	public float partialCharge = 0f;

	protected 手枪.Charger charger;
	public void gainCharge( float amt ){
		gainCharge( amt, false );
	}

	public void gainCharge( float amt, boolean overcharge ){
		partialCharge += amt;
		while (partialCharge >= 1) {
			if (overcharge) curCharges = Math.min(maxCharges+(int)amt, curCharges+1);
			else curCharges = Math.min(maxCharges, curCharges+1);
			partialCharge--;
			updateQuickslot();
		}
	}

	public void charge( Char owner ) {
		if (charger == null) charger = new Charger();
		charger.attachTo( owner );
	}

	public void charge( Char owner, float chargeScaleFactor ){
		charge( owner );
		charger.setScaleFactor( chargeScaleFactor );
	}
	public class Charger extends Buff {

		private static final float BASE_CHARGE_DELAY = 10f;
		private static final float SCALING_CHARGE_ADDITION = 40f;
		private static final float NORMAL_SCALE_FACTOR = 0.875f;

		private static final float CHARGE_BUFF_BONUS = 0.25f;

		float scalingFactor = NORMAL_SCALE_FACTOR;

		@Override
		public boolean attachTo( Char target ) {
			if (super.attachTo( target )) {
				//if we're loading in and the hero has partially spent a turn, delay for 1 turn
				if(target instanceof Hero){
					if (Dungeon.hero == null && cooldown() == 0 && target.cooldown() > 0) {
						spend(TICK);
					}
				}
				return true;
			}
			return false;
		}

		@Override
		public boolean act() {
			if (curCharges < maxCharges && target.buff(MagicImmune.class) == null)
				recharge();

			while (partialCharge >= 1 && curCharges < maxCharges) {
				partialCharge--;
				curCharges++;
				updateQuickslot();
			}

			if (curCharges == maxCharges){
				partialCharge = 0;
			}

			spend( TICK );

			return true;
		}

		private void recharge(){
			int missingCharges = maxCharges - curCharges;
			missingCharges = Math.max(0, missingCharges);

			float turnsToCharge = (float) (BASE_CHARGE_DELAY
					+ (SCALING_CHARGE_ADDITION * Math.pow(scalingFactor, missingCharges)));

			if (再生.regenOn())
				partialCharge += (1f/turnsToCharge) * 能量之戒.wandChargeMultiplier(target)*(1+Dungeon.hero.天赋点数(Talent.强能处消,0.25f));

			for (Recharging bonus : target.buffs(Recharging.class)){
				if (bonus != null && bonus.remainder() > 0f) {
					partialCharge += CHARGE_BUFF_BONUS * bonus.remainder();
				}
			}
		}

		public 手枪 枪(){
			return 手枪.this;
		}

		public void gainCharge(float charge){
			if (curCharges < maxCharges) {
				partialCharge += charge;
				while (partialCharge >= 1f) {
					curCharges++;
					partialCharge--;
				}
				if (curCharges >= maxCharges){
					partialCharge = 0;
					curCharges = maxCharges;
				}
				updateQuickslot();
			}
		}

		private void setScaleFactor(float value){
			this.scalingFactor = value;
		}
	}
	private CellSelector.Listener shooter = new CellSelector.Listener() {
		@Override
		public void onSelect( Integer target ) {
			if (target != null) {
				curCharges = Math.max(curCharges-chargesPerCast(),0);
				knockArrow().cast(curUser, target);
			}
		}
		@Override
		public String prompt() {
			return Messages.get(手枪.class, "prompt");
		}
	};
	public 子弹 knockArrow(){
		return new 子弹();
	}
	
	private int targetPos;
	public class 子弹 extends Weapon {

		{
			image = 物品表.子弹;

			hitSound = Assets.Sounds.手枪;
			item_Miss = Assets.Sounds.手枪;
		}
		@Override
		public int 最小投掷攻击(int lvl) {
			return 手枪.this.最小枪械攻击(lvl);
		}
		
		@Override
		public int 最大投掷攻击(int lvl) {
			return 手枪.this.最大枪械攻击(lvl);
			
		}
		
		@Override
		public boolean hasEnchant(Class<? extends Enchantment> type, Char owner) {
			return 手枪.this.hasEnchant(type, owner);
		}

		@Override
		public int 投掷攻击时(Char attacker, Char defender, int damage) {
			return 手枪.this.投掷攻击时(attacker, defender, damage);
		}

		@Override
		public int 力量(int lvl) {
			return 手枪.this.力量();
		}

		@Override
		protected void onThrow( int cell ) {
			if (Dungeon.level.heroFOV[cell]) {
				CellEmitter.center(cell).burst(BlastParticle.FACTORY, 4);
			}
			if (Dungeon.level != null && ShatteredPixelDungeon.scene() instanceof GameScene) {
				Dungeon.level.pressCell( cell );
			}
			Char enemy = Actor.findChar( cell );
			if (enemy == null || enemy == curUser) {
				
				if (Dungeon.level.heroFOV[cell]) {
					CellEmitter.get(cell).burst(SmokeParticle.FACTORY,4);
				}
			} else {
				if (!curUser.shoot( enemy, this )) {
					if (Dungeon.level.heroFOV[cell]) {
						CellEmitter.get(cell).burst(SmokeParticle.FACTORY, 4);
					}

				}
			}
		}
		@Override
		public void cast(final Hero user, final int dst) {
			final int cell = throwPos( user, dst );
			手枪.this.targetPos = cell;
				super.cast(user, dst);
		}
	}

}
