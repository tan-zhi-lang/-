

package com.shatteredpixel.shatteredpixeldungeon.items.weapon.枪械;

import static com.shatteredpixel.shatteredpixeldungeon.算法.kw2;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.ShatteredPixelDungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Buff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.PinCushion;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.effects.CellEmitter;
import com.shatteredpixel.shatteredpixeldungeon.effects.MagicMissile;
import com.shatteredpixel.shatteredpixeldungeon.effects.Speck;
import com.shatteredpixel.shatteredpixeldungeon.effects.Splash;
import com.shatteredpixel.shatteredpixeldungeon.effects.particles.BlastParticle;
import com.shatteredpixel.shatteredpixeldungeon.effects.particles.FlameParticle;
import com.shatteredpixel.shatteredpixeldungeon.effects.particles.LeafParticle;
import com.shatteredpixel.shatteredpixeldungeon.effects.particles.ShadowParticle;
import com.shatteredpixel.shatteredpixeldungeon.effects.particles.SmokeParticle;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.bombs.Bomb;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.WandOfBlastWave;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.Weapon;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.子弹.手枪子弹;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.CellSelector;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.ui.OptionSlider;
import com.shatteredpixel.shatteredpixeldungeon.ui.RenderedTextBlock;
import com.shatteredpixel.shatteredpixeldungeon.ui.Window;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.shatteredpixel.shatteredpixeldungeon.算法;
import com.watabou.noosa.audio.Sample;
import com.watabou.noosa.particles.Emitter;
import com.watabou.utils.Bundle;
import com.watabou.utils.PathFinder;

import java.util.ArrayList;

public abstract class 枪械 extends Weapon{
	public static final String AC_SHOOT		= "SHOOT";
	public static final String AC_换弹		= "换弹";
	
	{
		image = 物品表.手枪;

		tier = 1;
		伤害=0.6f;

		usesTargeting = true;
	}
	public float 枪伤= 1.75f;
	public boolean 无限子弹 = false;
	public boolean 掉落子弹 = false;
	public boolean 爆炸效果 = false;
	public boolean 箭矢发射 = false;
	public boolean 开火效果 = true;
	public boolean 霰弹效果 = false;
	public boolean 破甲弹 = false;
	public Item 子弹 = new 手枪子弹();
	public int 发射次数 = 1;
	public float 射速 = 6;
	public float 精度 = 1;
	public int image2 = 物品表.手枪子弹;
	public String 换弹声音 = Assets.Sounds.换弹;
	public String hitSound2 = Assets.Sounds.手枪;
	public String item_Miss2 = Assets.Sounds.手枪;
	@Override
	public ArrayList<String> actions(Hero hero) {
		ArrayList<String> actions = super.actions(hero);
		actions.add(AC_SHOOT);
		if(!无限子弹)
		actions.add(AC_换弹);

		if(箭矢发射)
			actions.add(AC_弓力);

		return actions;
	}@Override
	public String defaultAction() {
		if(curCharges>0||无限子弹)
		return AC_SHOOT;

		return AC_换弹;
	}
	@Override
	public String status() {
		if (!无限子弹) {
			if(levelKnown){
//				换弹回合()<=0&&
				Item 弹=Dungeon.hero.belongings.getItem(子弹.getClass());
				if(弹!=null&&弹.数量()>0&&Dungeon.hero()&&isEquipped(Dungeon.hero)){
					return curCharges+"/"+弹.数量();
				}
			}
			return curCharges + "/" + maxCharges;
		} else {
			return null;
		}
	}
	public int 弓力 = 2;

	private static final int WIDTH_P	    = 122;
	private static final float GAP          = 1;
	private static final int BTN_HEIGHT	    = 16;
	protected static final String AC_弓力 = "弓力";
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
		if (action.equals(AC_弓力)) {

			ShatteredPixelDungeon.scene().addToFront(new Window(){
				OptionSlider 弓力托条;
				RenderedTextBlock 弓力str;

				{


					弓力托条 = new OptionSlider("弓力",
											"0.5","1.5",1,3) {
						@Override
						protected void onChange() {
							弓力=getSelectedValue();
						}
					};
					弓力托条.setSelectedValue(弓力);
					add(弓力托条);

					弓力str = PixelScene.renderTextBlock("弓力越大伤害，但是攻速越慢",5);
					弓力str.hardlight(0x888888);
					add(弓力str);

					//layout

					resize(WIDTH_P, 0);

					弓力托条.setRect(0,  GAP, width, BTN_HEIGHT);

					弓力str.maxWidth(width);
					弓力str.setPos(0, 弓力托条.bottom()+1);

					resize(WIDTH_P, (int) 弓力str.bottom());
				}
			});
		}
		if (action.equals(AC_SHOOT)) {
			if(curCharges==0&&!无限子弹){
				换弹();
				return;
			}
			GameScene.selectCell( shooter );
		}
	}

	public float 换弹回合(){
		float x=4;
		if(箭矢发射)
			x=0;
		else if(this instanceof 火炮)
			x=1;
		else if(this instanceof 霰弹枪)
			x=2;
		return x;
	}
	public void 无限换弹(){

		curCharges=maxCharges;

		Sample.INSTANCE.play( 换弹声音 );

		curUser.spend(换弹回合());

		curUser.busy();
		(curUser.sprite).operate();
		updateQuickslot();
	}
	public void 换弹(){
		if(算法.isDebug()||无限子弹){
			无限换弹();
			return;
		}

			Item 弹 = curUser.belongings.getItem(子弹.getClass());
		if (弹 == null || 弹.数量() <= 0) {
			GLog.红("你需要" + 子弹.name() + "！");
			return;
		}

		int 消耗 = maxCharges - curCharges;
		if (消耗 <= 0) {
			GLog.黄("弹夹已满！");
			return;
		}

		int 可用 = 弹.数量();
		int 实际填充 = Math.min(消耗, 可用);
		curCharges += 实际填充;  // 这里正确更新了弹夹容量

		GLog.黄("已装备弹药！");
		if (可用 <= 消耗) {
			// 子弹全部用完（或刚好够），移除整组
			弹.detachAll(curUser.belongings.backpack);
		} else {
			// 子弹有剩余，只消耗需要的那部分
			弹.split(消耗).detachAll(curUser.belongings.backpack);
		}


		Sample.INSTANCE.play(换弹声音);

		curUser.spend(换弹回合());
		curUser.busy();
		curUser.sprite.operate();

		updateQuickslot();
	}

	@Override
	public float 延迟(){
		return super.延迟()*弓力();
	}

	public float 枪伤(){
		return augment.damageFactor(枪伤)*弓力();
	}
	public float 精度(){
		return augment.accuracyFactor(精度);
	}
	public float 射速(){
		return 射速/augment.delayFactor(1);
	}

	public float 弓力(){
		if(弓力==1)
		return 0.5f;
		if(弓力==2)
		return 1;
		if(弓力==3)
		return 1.5f;

		return 1;
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
							kw2(换弹回合()),
								kw2(最小枪械攻击()),
									kw2(最大枪械攻击()))+st();
	}
	public String st() {
		if(Messages.get(this, "st").equals(""))return "";
		return "\n\n"+Messages.get(this, "st");
	}
	public int maxCharges = initialCharges();
	public int initialCharges() {
		return 1;
	}
	protected int chargesPerCast() {
		return 1;
	}
	public Item knockArrow(){
		return new 子弹(枪().子弹);
	}

	public 枪械 枪(){
		return this;
	}
	public int curCharges = maxCharges;
	public float partialCharge = 0f;

	public static final String CHARGES          = "charges";
	private static final String PARTIALCHARGE   = "partialCharge";

	private static final String 弓力x	= "弓力";

	@Override
	public void storeInBundle(Bundle bundle) {
		super.storeInBundle(bundle);
		bundle.put(CHARGES, curCharges);
		bundle.put(PARTIALCHARGE, partialCharge);
		bundle.put( 弓力x, 弓力 );
	}

	@Override
	public void restoreFromBundle(Bundle bundle) {
		super.restoreFromBundle(bundle);
		curCharges = bundle.getInt(CHARGES);
		partialCharge = bundle.getFloat(PARTIALCHARGE);
		弓力 = bundle.getInt( 弓力x );
	}
	private CellSelector.Listener shooter = new CellSelector.Listener() {
		@Override
		public void onSelect( Integer target ) {
			if (target != null) {
				if(!枪().无限子弹)
				curCharges = Math.max(curCharges-chargesPerCast(),0);

				for(int x=1;x<=枪().发射次数;x++){

					knockArrow().cast(curUser, target);

					if(枪().爆炸效果)
					new Bomb.ConjuredBomb().heroexplode(target);
				}
				if(枪().换弹回合()<=0){
					枪().换弹();
				}
			}
		}
		@Override
		public String prompt() {
			return Messages.get(枪(),"prompt");
		}
	};
	private int targetPos;
	public class 子弹 extends Weapon {

		{
			image = image2;
			hitSound = hitSound2;
			item_Miss = item_Miss2;
		}

		public 子弹(Item 子弹){
			this.子弹=子弹;
		}

		public static Item 子弹 = new 手枪子弹();
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
			return 枪().accuracyFactor(owner,target)*(
					(
					枪() instanceof 冲锋枪||
					枪() instanceof 霰弹枪||
					枪() instanceof 火炮
					 )?1:0.5f
					)*精度();
		}
		@Override
		public boolean hasEnchant(Class<? extends Enchantment> type, Char owner) {
			return 枪().hasEnchant(type,owner);
		}

		@Override
		public float 投掷攻击时(Char attacker, Char defender, float damage) {
			if(defender!=null){
				if(枪().破甲弹)damage+=defender.最大防御();

				if(枪().霰弹效果){
					damage*=(1+2f/attacker.距离(defender));
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
		public Emitter emitter() {
			if(枪().开火效果){
				Emitter e=new Emitter();
				e.pos(5,5);
				e.fillTarget=false;
				e.pour(FlameParticle.FACTORY,0.01f);
				return e;
			}
			if(枪() instanceof 冰结短弓){
				Emitter e=new Emitter();
				e.pos(5,5);
				e.fillTarget=false;
				e.pour(MagicMissile.MagicParticle.FACTORY,0.01f);
				return e;
			}
			if(枪() instanceof 暗裔短弓){
				Emitter e=new Emitter();
				e.pos(5,5);
				e.fillTarget=false;
				e.pour(ShadowParticle.UP,0.01f);
				return e;
			}
			if(枪() instanceof 自然之力){
				Emitter e=new Emitter();
				e.pos(5,5);
				e.fillTarget=false;
				e.pour(LeafParticle.GENERAL,0.01f);
				return e;
			}
			if(枪() instanceof 炼金动力十字弩){
				Emitter e=new Emitter();
				e.pos(5,5);
				e.fillTarget=false;
				e.pour(Speck.factory(Speck.TOXIC),0.01f);
				return e;
			}
			return null;
		}

		@Override
		protected void onThrow( int cell ) {
			if (Dungeon.level != null && ShatteredPixelDungeon.scene() instanceof GameScene) {

				if(枪().爆炸效果){

					for(int n: PathFinder.自相邻)Dungeon.level.pressCell( cell+n );
				}else Dungeon.level.pressCell( cell );
			}

			if(枪().爆炸效果)
				WandOfBlastWave.BlastWave.blast(cell);


			if(枪().爆炸效果||
			   枪() instanceof 霰弹枪||
			   枪() instanceof 狙击枪
			   )
				PixelScene.shake(2,0.5f);


			if(枪().开火效果){
				if(枪().爆炸效果){

					for(int n: PathFinder.自相邻){
						Splash.at(cell+n,0xFF8800,1);
						CellEmitter.get(cell+n).burst(FlameParticle.FACTORY,4);
					}
				}else{
					Splash.at(cell,0xFF8800,1);
					CellEmitter.get(cell).burst(FlameParticle.FACTORY,4);
				}
				Char enemy=Actor.findChar(cell);
				if(enemy==null||enemy==curUser){

					if(Dungeon.level.heroFOV[cell]){
						if(枪().爆炸效果){
							for(int n: PathFinder.自相邻)
								CellEmitter.get(cell+n).burst(SmokeParticle.FACTORY,4);

						}else
							CellEmitter.get(cell).burst(SmokeParticle.FACTORY,4);
					}
				}else{
					if(!curUser.shoot(enemy,this)){
						if(Dungeon.level.heroFOV[cell]){
							if(枪().爆炸效果){
								for(int n: PathFinder.自相邻)
									CellEmitter.get(cell+n).burst(BlastParticle.FACTORY,4);

							}else
							CellEmitter.get(cell).burst(BlastParticle.FACTORY,4);
						}

					}
				}
			}else{

				if(Dungeon.level.heroFOV[cell]){

					if(枪() instanceof 冰结短弓){
						Splash.at(cell,0xFFFFFF,1);
						CellEmitter.get(cell).burst(MagicMissile.MagicParticle.FACTORY,4);
					}
					if(枪() instanceof 冰结短弓){
						Splash.at(cell,0x3399FF,1);
						CellEmitter.get(cell).burst(MagicMissile.MagicParticle.FACTORY,4);
					}
					if(枪() instanceof 暗裔短弓){
						Splash.at(cell,0x8800FF,1);
						CellEmitter.get(cell).burst(ShadowParticle.UP,4);
					}
					if(枪() instanceof 自然之力){
						Splash.at(cell,0x00FF00,1);
						CellEmitter.get(cell).burst(LeafParticle.GENERAL,4);
					}
					if(枪() instanceof 炼金动力十字弩){
						Splash.at(cell,0x00FF00,1);
						CellEmitter.get(cell).burst(Speck.factory(Speck.TOXIC),4);
					}
				}

			}



			if(枪().掉落子弹){
				Item item=枪().子弹;
				try {
					item = (Item)枪().子弹.getClass().newInstance();

				} catch (Exception ignored) {}

				Char enemy=Actor.findChar(cell);
				if(enemy==null||enemy==curUser){

					Dungeon.level.drop(item,cell).sprite.drop();
				}else{

					if(!curUser.shoot(enemy,this)){

						Dungeon.level.drop(item,cell).sprite().drop();
					}else{

						if(投掷粘()&&enemy.isActive()&&enemy.alignment!=Char.Alignment.ALLY){
							PinCushion p=Buff.施加(enemy,PinCushion.class);
							if(p.target==enemy){
								p.stick(item);
								return;
							}
						}
						Dungeon.level.drop(item,cell).sprite().drop();
					}

				}
			}else {

				if(箭矢发射){

					Char enemy=Actor.findChar(cell);
					if(enemy==null||enemy==curUser){

					}else{
						if(!curUser.shoot(enemy,this)){

						}

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
