

package com.shatteredpixel.shatteredpixeldungeon.items.weapon.枪械;

import static com.shatteredpixel.shatteredpixeldungeon.算法.kw2;

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
import com.shatteredpixel.shatteredpixeldungeon.effects.CellEmitter;
import com.shatteredpixel.shatteredpixeldungeon.effects.particles.BlastParticle;
import com.shatteredpixel.shatteredpixeldungeon.effects.particles.SmokeParticle;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.bombs.Bomb;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfBlastWave;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.Weapon;
import com.shatteredpixel.shatteredpixeldungeon.items.手枪子弹;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.CellSelector;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.shatteredpixel.shatteredpixeldungeon.算法;
import com.watabou.noosa.audio.Sample;

import java.util.ArrayList;

public class 枪械 extends Weapon{
	public static final String AC_SHOOT		= "SHOOT";
	public static final String AC_换弹		= "换弹";
	
	{
		image = 物品表.十字弩;
		
		tier = 1;
		伤害=0.6f;
		投掷=1.5f;

		usesTargeting = true;
	}
	public boolean 掉落子弹 = false;
	public boolean 爆炸效果 = false;
	public boolean 霰弹效果 = false;
	public boolean 破甲弹 = false;
	public Item 子弹 = new 手枪子弹();
	public int 发射次数 = 1;
	public float 射速 = 6;
	public float 精度 = 1;
	public int image2 = 物品表.手枪子弹;
	public String hitSound2 = Assets.Sounds.手枪;
	public String item_Miss2 = Assets.Sounds.手枪;
	@Override
	public ArrayList<String> actions(Hero hero) {
		ArrayList<String> actions = super.actions(hero);
		actions.add(AC_SHOOT);
		actions.add(AC_换弹);
		return actions;
	}@Override
	public String defaultAction() {
		if(curCharges>0)
		return AC_SHOOT;
		return super.defaultAction();
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
		Item 弹=curUser.belongings.getItem(子弹.getClass());
		if(弹!=null&&弹.数量()>0){
			int 消耗=maxCharges-curCharges;
			if(弹.数量()<消耗){
				if(curCharges==0){
					curCharges=Math.min(maxCharges,弹.数量());
				}else if(curCharges>0){
					curCharges+=Math.min(maxCharges-curCharges,弹.数量());
				}
				弹.detachAll(curUser.belongings.backpack);
			}else{
				if(弹.数量()==消耗){
					弹.detachAll(curUser.belongings.backpack);
				}else{
					if(curCharges==0){
						curCharges=Math.min(maxCharges,弹.数量());
					}else if(curCharges>0){
						curCharges+=Math.min(maxCharges-curCharges,弹.数量());
					}
					弹.split(消耗).detachAll(curUser.belongings.backpack);
				}
			}
			Sample.INSTANCE.play( Assets.Sounds.换弹 );

			if(this instanceof 十字弩||this instanceof 火炮)
			curUser.spend(1);
			else
			curUser.spend(4);

			curUser.busy();
			(curUser.sprite).operate();
			updateQuickslot();
		}else
			GLog.橙("你需要"+子弹.name()+"！");
	}
	
	public float 最小枪械攻击() {
		return 最小枪械攻击(强化等级());
	}
	public float 最小枪械攻击(int lvl) {
		float dmg =最小+((tier()+1)+lvl)*伤害()*投掷();
		return Math.max(0, dmg);
	}
	
	public float 最大枪械攻击() {
		return 最大枪械攻击(强化等级());
	}
	public float 最大枪械攻击(int lvl) {
		float dmg =最大+(5*(tier()+1) +lvl*(tier()+1))*伤害()*投掷();
		return Math.max(0, dmg);
	}
	
	@Override
	public String desc() {
		return Messages.get(this, "desc",kw2(精度),
							kw2(射速),
								kw2(最小枪械攻击()),
									kw2(最大枪械攻击()))+st();
	}
	public String st() {
		if(Messages.get(this, "st").equals(""))return "";
		return "\n"+Messages.get(this, "st");
	}
	public int maxCharges = initialCharges();
	public int initialCharges() {
		return 1;
	}
	protected int chargesPerCast() {
		return 1;
	}
	public int curCharges = maxCharges;
	public float partialCharge = 0f;

	protected 枪械.Charger charger;

	public void gainCharge(){
		gainCharge(1);
	}
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
				partialCharge += (1f/turnsToCharge/3f);

			for (Recharging bonus : target.buffs(Recharging.class)){
				if (bonus != null && bonus.remainder() > 0f) {
					partialCharge += CHARGE_BUFF_BONUS * bonus.remainder();
				}
			}
		}

		public 枪械 枪(){
			return 枪械.this;
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

				for(int x=1;x<=发射次数;x++){
					knockArrow().cast(curUser, target);

					new Bomb.ConjuredBomb().heroexplode(target);
				}
			}
		}
		@Override
		public String prompt() {
			return Messages.get(枪械.class,"prompt");
		}
	};
	public 子弹 knockArrow(){
		return new 子弹();
	}
	
	private int targetPos;
	public class 子弹 extends Weapon {

		{
			image = image2;
			hitSound = hitSound2;
			item_Miss = item_Miss2;
		}
		@Override
		public float 最小投掷攻击(int lvl) {
			return 枪械.this.最小枪械攻击(lvl);
		}
		
		@Override
		public float 最大投掷攻击(int lvl) {
			return 枪械.this.最大枪械攻击(lvl);
			
		}
		
		@Override
		public float delayFactor(Char user) {
			return 枪械.this.delayFactor(user)/射速;
		}
		
		@Override
		public float accuracyFactor(Char owner, Char target) {
			return 枪械.this.accuracyFactor(owner,target)/2f*精度;
		}
		@Override
		public boolean hasEnchant(Class<? extends Enchantment> type, Char owner) {
			return 枪械.this.hasEnchant(type,owner);
		}

		@Override
		public float 投掷攻击时(Char attacker, Char defender, float damage) {
			if(defender!=null){
				if(破甲弹)damage+=defender.最大防御();

				damage*=(1+2f/attacker.距离(defender));
				if(霰弹效果){
					float x=0;
					if(算法.概率学(1/2f))
						x++;
					if(算法.概率学(1/2f))
						x++;
					if(算法.概率学(1/2f))
						x++;

					if(算法.概率学(1/2f))
						x++;
					if(算法.概率学(1/2f))
						x++;
					if(算法.概率学(1/2f))
						x++;

					if(算法.概率学(1/2f))
						x++;
					if(算法.概率学(1/2f))
						x++;
					if(算法.概率学(1/2f))
						x++;
					damage*=x;
				}
			}
			return 枪械.this.投掷攻击时(attacker,defender,damage);
		}

		@Override
		public float 力量(int lvl) {
			return 枪械.this.力量();
		}

		@Override
		protected void onThrow( int cell ) {
			if (Dungeon.level.heroFOV[cell]) {
				CellEmitter.center(cell).burst(BlastParticle.FACTORY, 4);
			}
			if (Dungeon.level != null && ShatteredPixelDungeon.scene() instanceof GameScene) {
				Dungeon.level.pressCell( cell );
			}
			if(枪械.this instanceof 十字弩)Sample.INSTANCE.play(Assets.Sounds.攻击弩);
			if(爆炸效果){
				WandOfBlastWave.BlastWave.blast(cell);
				PixelScene.shake(2,0.5f);
			}
			if(掉落子弹)
			Dungeon.level.drop(子弹,cell).sprite.drop();

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
			枪械.this.targetPos = cell;
				super.cast(user, dst);
		}
	}

}
