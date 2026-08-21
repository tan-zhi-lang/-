

package com.shatteredpixel.shatteredpixeldungeon.items.weapon.枪械;

import static com.shatteredpixel.shatteredpixeldungeon.算法.kw2;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.ShatteredPixelDungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
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
import com.watabou.utils.Bundle;

import java.util.ArrayList;

abstract class 枪械 extends Weapon{
	public static final String AC_SHOOT		= "SHOOT";
	public static final String AC_换弹		= "换弹";
	
	{
		image = 物品表.手枪;

		tier = 1;
		伤害=0.6f;

		usesTargeting = true;
	}
	public float 枪伤= 1.75f;
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

		return AC_换弹;
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
				if(算法.isDebug())无限换弹();
				else 换弹();
				return;
			}
		}
		if (action.equals(AC_SHOOT)&&isEquipped(curUser)) {
			if(curCharges==0){
				if(算法.isDebug())无限换弹();
				else 换弹();
				return;
			}
			GameScene.selectCell( shooter );
		}
	}
	public float 装弹回合(){
		float x=4;
		if(this instanceof 十字弩||this instanceof 火炮)
			x=1;
		else if(this instanceof 霰弹枪)
			x=2;
		return x;
	}
	public void 无限换弹(){

		curCharges=maxCharges;

		Sample.INSTANCE.play( Assets.Sounds.换弹 );

		curUser.spend(装弹回合());

		curUser.busy();
		(curUser.sprite).operate();
		updateQuickslot();
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

			curUser.spend(装弹回合());

			curUser.busy();
			(curUser.sprite).operate();
			updateQuickslot();
		}else
			GLog.橙("你需要"+子弹.name()+"！");
	}

	public float 枪伤(){
		return 枪伤;
	}
	public float 精度(){
		return 精度;
	}
	public float 射速(){
		return 射速;
	}

	public float 最小枪械攻击() {
		return 最小枪械攻击(强化等级());
	}
	public float 最小枪械攻击(int lvl) {
		float dmg =最小+((tier()+1)+lvl)*伤害()*投掷()*枪伤();
		return Math.max(0, dmg);
	}
	
	public float 最大枪械攻击() {
		return 最大枪械攻击(强化等级());
	}
	public float 最大枪械攻击(int lvl) {
		float dmg =最大+(5*(tier()+1) +lvl*(tier()+1))*伤害()*投掷()*枪伤();
		return Math.max(0, dmg);
	}
	
	@Override
	public String desc() {
		return super.desc()+"\n"+Messages.get(this, "descq",
//											  kw2(枪伤()),
											  kw2(精度()),
							kw2(射速()),
							kw2(装弹回合()),
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

	public static final String CHARGES          = "charges";
	private static final String PARTIALCHARGE   = "partialCharge";

	@Override
	public void storeInBundle(Bundle bundle) {
		super.storeInBundle(bundle);
		bundle.put(CHARGES, curCharges);
		bundle.put(PARTIALCHARGE, partialCharge);
	}

	@Override
	public void restoreFromBundle(Bundle bundle) {
		super.restoreFromBundle(bundle);
		curCharges = bundle.getInt(CHARGES);
		partialCharge = bundle.getFloat(PARTIALCHARGE);
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
			return Messages.get(枪(),"prompt");
		}
	};
	public 子弹 knockArrow(){
		return new 子弹();
	}

	public 枪械 枪(){
		return 枪械.this;
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
			return 枪().最小枪械攻击(lvl);
		}
		
		@Override
		public float 最大投掷攻击(int lvl) {
			return 枪().最大枪械攻击(lvl);
			
		}
		
		@Override
		public float delayFactor(Char user) {
			return 枪().delayFactor(user)/射速();
		}
		
		@Override
		public float accuracyFactor(Char owner, Char target) {
			return 枪().accuracyFactor(owner,target)/2f*精度();
		}
		@Override
		public boolean hasEnchant(Class<? extends Enchantment> type, Char owner) {
			return 枪().hasEnchant(type,owner);
		}

		@Override
		public float 投掷攻击时(Char attacker, Char defender, float damage) {
			if(defender!=null){
				if(破甲弹)damage+=defender.最大防御();

				damage*=(1+2f/attacker.距离(defender));
				if(霰弹效果){
					float x=0;
					float 命中率=0.6f;
					if(算法.概率学(命中率))
						x++;
					if(算法.概率学(命中率))
						x++;
					if(算法.概率学(命中率))
						x++;
					if(算法.概率学(命中率))
						x++;

					if(算法.概率学(命中率))
						x++;
					if(算法.概率学(命中率))
						x++;
					if(算法.概率学(命中率))
						x++;
					if(算法.概率学(命中率))
						x++;

					if(算法.概率学(命中率))
						x++;
					if(算法.概率学(命中率))
						x++;
					if(算法.概率学(命中率))
						x++;
					if(算法.概率学(命中率))
						x++;
					damage*=x;
				}
			}
			return 枪().投掷攻击时(attacker,defender,damage);
		}

		@Override
		public float 力量(int lvl) {
			return 枪().力量();
		}

		@Override
		protected void onThrow( int cell ) {
			if (Dungeon.level.heroFOV[cell]) {
				CellEmitter.center(cell).burst(BlastParticle.FACTORY, 4);
			}
			if (Dungeon.level != null && ShatteredPixelDungeon.scene() instanceof GameScene) {
				Dungeon.level.pressCell( cell );
			}
			if(枪() instanceof 十字弩)Sample.INSTANCE.play(Assets.Sounds.攻击弩);
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
			枪().targetPos = cell;
				super.cast(user, dst);
		}
	}

}
