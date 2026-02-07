

package com.shatteredpixel.shatteredpixeldungeon.items.artifacts;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.ShatteredPixelDungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Actor;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.CorrosiveGas;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.AllyBuff;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Invisibility;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.MagicImmune;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.再生;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.燃烧;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Belongings;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Talent;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.spells.Stasis;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.npcs.DirectableAlly;
import com.shatteredpixel.shatteredpixeldungeon.effects.CellEmitter;
import com.shatteredpixel.shatteredpixeldungeon.effects.Speck;
import com.shatteredpixel.shatteredpixeldungeon.effects.particles.ShaftParticle;
import com.shatteredpixel.shatteredpixeldungeon.items.EquipableItem;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.Armor;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.Bag;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.能量之戒;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.exotic.ScrollOfPsionicBlast;
import com.shatteredpixel.shatteredpixeldungeon.items.scrolls.复仇卷轴;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.Weapon;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.CellSelector;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.替身动画;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;
import com.shatteredpixel.shatteredpixeldungeon.ui.ItemButton;
import com.shatteredpixel.shatteredpixeldungeon.ui.RenderedTextBlock;
import com.shatteredpixel.shatteredpixeldungeon.ui.Window;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.shatteredpixel.shatteredpixeldungeon.windows.IconTitle;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndBag;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndInfoItem;
import com.watabou.utils.Bundle;
import com.watabou.utils.PathFinder;
import com.watabou.utils.Random;

import java.util.ArrayList;

public class 召唤物品 extends Artifact {

	{
		image=物品表.ITEM;
		charge = 100;
		chargeCap = 100;
		
		遗产= true;
		物品 = true;
		defaultAction = AC_SUMMON;
	}
	
	private 随从 召唤= null;
	private int 随从ID= 0;
	private Item item= null;
	private Item item2= null;
	private Item item3= null;
	private Item item4= null;
	private Item item5= null;
	private Item item6= null;
	private Item item7= null;
	private Item item8= null;
	private Item item9= null;

	public static final String AC_SUMMON = "SUMMON";
	public static final String AC_DIRECT = "DIRECT";
	public static final String AC_OUTFIT = "OUTFIT";

	@Override
	public ArrayList<String> actions( Hero hero ) {
		ArrayList<String> actions = new ArrayList<>();
		actions.add( AC_DROP );
		actions.add( AC_THROW );
		actions.add( AC_RENAME );
		if (charge == chargeCap
			&& hero.buff(MagicImmune.class) == null
			&&随从ID==0) {
			actions.add(AC_SUMMON);
		}
		if (随从ID!=0){
			actions.add(AC_DIRECT);
		}
		actions.add(AC_OUTFIT);
		
		return actions;
	}
	
	@Override
	public boolean 放背包(Bag container){
		if(Dungeon.hero())
		activate(Dungeon.hero);
		return super.放背包(container);
	}
	
	@Override
	protected void onDetach(){
		if (passiveBuff != null) {
			if (passiveBuff.target != null) passiveBuff.detach();
			passiveBuff = null;
		}
		super.onDetach();
	}
	
	@Override
	public String defaultAction() {
		if (召唤!=null){
			return AC_DIRECT;
		} else {
			return AC_SUMMON;
		}
	}

	@Override
	public void execute( Hero hero, String action ) {

		super.execute(hero, action);

		if (action.equals(AC_SUMMON)) {

			if (hero.buff(MagicImmune.class) != null) return;
			
			if (召唤!=null)         GLog.i(Messages.get(this,"spawned"));
			else if (charge != chargeCap)   GLog.i( Messages.get(this, "no_charge") );
			else {
				ArrayList<Integer> spawnPoints = new ArrayList<>();
				for (int i = 0; i < PathFinder.NEIGHBOURS8.length; i++) {
					int p = hero.pos + PathFinder.NEIGHBOURS8[i];
					if (Actor.findChar(p) == null && (Dungeon.level.passable[p] || Dungeon.level.avoid[p])) {
						spawnPoints.add(p);
					}
				}

				if (spawnPoints.size() > 0) {
					召唤= new 随从(this );
					随从ID= 召唤.id();
					召唤.pos = Random.element(spawnPoints);

					GameScene.add(召唤,1f);
					Dungeon.level.occupyCell(召唤);
					
					CellEmitter.get(召唤.pos).start(ShaftParticle.FACTORY,0.3f,4);
					CellEmitter.get(召唤.pos).start(Speck.factory(Speck.LIGHT),0.2f,3);

					hero.spend(1f);
					hero.busy();
					hero.sprite.operate(hero.pos);


					Invisibility.dispel(hero);
					Talent.onArtifactUsed(hero);
					charge = 0;
					partialCharge = 0;
					updateQuickslot();

				} else
					GLog.i( Messages.get(this, "no_space") );
			}

		} else if (action.equals(AC_DIRECT)){
			if (召唤==null&&随从ID!=0){
				find随从();
			}
			if (召唤!=null&&召唤!=Stasis.getStasisAlly()){
				GameScene.selectCell(随从指引);
			}
			
		} else if (action.equals(AC_OUTFIT)){
			GameScene.show( new Wnd(this));
		}
	}

	private void find随从(){
		Actor a = Actor.findById(随从ID);
		if (a != null){
			召唤= (随从)a;
		} else {
			if (Stasis.getStasisAlly() instanceof 随从){
				召唤= (随从) Stasis.getStasisAlly();
				随从ID= 召唤.id();
			} else {
				随从ID= 0;
			}
		}
	}
	
	@Override
	public String desc() {
		String desc = super.desc();
			if (item!=null) {
				desc += "\n" + Messages.get(this, "desc_item", Messages.titleCase(item.title()));
			}
		
		return desc;
	}
	
	@Override
	public int 金币() {
		if (item!=null){
			return -1;
		}
		return super.金币();
	}

	@Override
	public String status() {
		if (召唤==null&&随从ID!=0){
			try {
				find随从();
			} catch ( ClassCastException e ){
				ShatteredPixelDungeon.reportException(e);
				随从ID= 0;
			}
		}
		if (召唤==null){
			return super.status();
		} else {
			return ((召唤.生命*100)/召唤.最大生命)+"%";
		}
	}
	
	@Override
	protected ArtifactBuff passiveBuff() {
		return new 物品充能();
	}
	
	@Override
	public void charge(Hero target, float amount) {
		if (target.buff(MagicImmune.class) != null) return;

		if (召唤==null){
			if (charge < chargeCap) {
				partialCharge += 4*amount;
				while (partialCharge >= 1f){
					charge++;
					partialCharge--;
				}
				if (charge >= chargeCap) {
					charge = chargeCap;
					partialCharge = 0;
					GLog.p(Messages.get(召唤物品.class,"charged"));
				}
				updateQuickslot();
			}
		} else if (召唤.生命<召唤.最大生命) {
			int heal = Math.round((1 + 等级()/3f)*amount);
			召唤.回血(heal);
			updateQuickslot();
		}
	}
	
	
	public Item item(){
		return item;
	}

	private static final String 随从IDx=       "随从ID";
	
	private static final String ITEM=        "item";
	private static final String ITEM2=        "item2";
	private static final String ITEM3=        "item3";
	private static final String ITEM4=        "item4";
	private static final String ITEM5=        "item5";
	private static final String ITEM6=        "item6";
	private static final String ITEM7=        "item7";
	private static final String ITEM8=        "item8";
	private static final String ITEM9=        "item9";

	@Override
	public void storeInBundle( Bundle bundle ) {
		super.storeInBundle(bundle);

		bundle.put(随从IDx,随从ID);
		
		if (item!=null) bundle.put(ITEM,item);
		if (item2!=null) bundle.put(ITEM2,item2);
		if (item3!=null) bundle.put(ITEM3,item3);
		if (item4!=null) bundle.put(ITEM4,item4);
		if (item5!=null) bundle.put(ITEM5,item5);
		if (item6!=null) bundle.put(ITEM6,item6);
		if (item7!=null) bundle.put(ITEM7,item7);
		if (item8!=null) bundle.put(ITEM8,item8);
		if (item9!=null) bundle.put(ITEM9,item9);
	}

	@Override
	public void restoreFromBundle( Bundle bundle ) {
		super.restoreFromBundle(bundle);

		随从ID= bundle.getInt(随从IDx);

		if (bundle.contains(ITEM)) item= (Item)bundle.get(ITEM);
		if (bundle.contains(ITEM2)) item2= (Item)bundle.get(ITEM2);
		if (bundle.contains(ITEM3)) item3= (Item)bundle.get(ITEM3);
		if (bundle.contains(ITEM4)) item4= (Item)bundle.get(ITEM4);
		if (bundle.contains(ITEM5)) item5= (Item)bundle.get(ITEM5);
		if (bundle.contains(ITEM6)) item6= (Item)bundle.get(ITEM6);
		if (bundle.contains(ITEM7)) item7= (Item)bundle.get(ITEM7);
		if (bundle.contains(ITEM8)) item8= (Item)bundle.get(ITEM8);
		if (bundle.contains(ITEM9)) item9= (Item)bundle.get(ITEM9);
	}

	public class 物品充能 extends ArtifactBuff {

		@Override
		public boolean act() {
			
			spend( TICK );
			
			if (召唤==null&&随从ID!=0){
				find随从();
			}

			if (召唤!=null&&!召唤.isAlive()){
				召唤= null;
			}
			
			//rose does not charge while ghost hero is alive
			if (召唤!=null&&target.buff(MagicImmune.class)==null){
				
				//heals to full over 500 turns
				if (召唤.生命<召唤.最大生命&&再生.regenOn()) {
					partialCharge +=(召唤.最大生命/500f)*能量之戒.artifactChargeMultiplier(target);
					updateQuickslot();
					
					while (partialCharge > 1) {
						召唤.生命++;
						partialCharge--;
						if (召唤.生命 == 召唤.最大生命){
							partialCharge = 0;
						}
					}
				} else {
					partialCharge = 0;
				}
				
				return true;
			}
			
			if (charge < chargeCap
					&& target.buff(MagicImmune.class) == null
					&& 再生.regenOn()) {
				//500 turns to a full charge
				partialCharge += (1/5f * 能量之戒.artifactChargeMultiplier(target));
				while (partialCharge > 1){
					charge++;
					partialCharge--;
					if (charge == chargeCap){
						partialCharge = 0f;
						GLog.p( Messages.get(召唤物品.class,"charged"));
					}
				}
			}
			updateQuickslot();

			return true;
		}
	}
	
	public CellSelector.Listener 随从指引= new CellSelector.Listener(){
		
		@Override
		public void onSelect(Integer cell) {
			if (cell == null) return;
			
			召唤.directTocell(cell);

		}
		
		@Override
		public String prompt() {
			return "\""+Messages.get(随从.class,"direct_prompt")+"\"";
		}
	};

	public static class 随从 extends DirectableAlly {

		{
			spriteClass = 替身动画.class;

			flying = true;
			
			state = HUNTING;
			
			properties.add(Property.UNDEAD);
			properties.add(Property.INORGANIC);
		}
		
		private 召唤物品
				召唤物品= null;
		public int armTier;
		
		public 随从(){
			super();
		}

		public 随从(召唤物品 召唤物品){
			super();
			this.召唤物品=
					召唤物品;
			更新数据();
			生命 = 最大生命;
		}

		@Override
		public void defendPos(int cell) {
			super.defendPos(cell);
		}

		@Override
		public void followHero() {
			super.followHero();
		}

		@Override
		public void targetChar(Char ch) {
			super.targetChar(ch);
		}

		private void 更新数据(){
			if (召唤物品==null) {
				召唤物品= Dungeon.hero.belongings.getItem(召唤物品.class);
				if (召唤物品!=null) {
					召唤物品.召唤 = this;
					召唤物品.随从ID = id();
				}
			}
			
			//same dodge as the hero
			defenseSkill = (Dungeon.hero.等级 +4);
			if (召唤物品==null) return;
			最大生命 = 20 +8*召唤物品.等级();
		}

		public Item item(){
			if (召唤物品!=null)   return 召唤物品.item;
			else                return null;
		}

		@Override
		protected boolean act() {
			更新数据();
			if (召唤物品==null
				|| Dungeon.hero.buff(MagicImmune.class) != null){
				受伤时(1, new 类());
			}
			
			if (!isAlive()) {
				return true;
			}
			return super.act();
		}
		public class 类{}

		@Override
		public int 最大命中(Char target) {
			
			//same accuracy as the hero.
			int acc = Dungeon.hero.等级 + 9;
			
			return acc;
		}
		
		@Override
		public float 攻击延迟() {
			float delay = super.攻击延迟();
			return delay;
		}
		
		@Override
		protected boolean canAttack(Char enemy) {
			return super.canAttack(enemy);
		}
		
		@Override
		public float 最大攻击() {
			int dmg = 0;
				dmg += Random.NormalIntRange(0, 5);
			
			return dmg;
		}
		
		@Override
		public float 攻击时(final Char enemy, float damage) {
			damage = super.攻击时(enemy, damage);

			return damage;
		}
		
		@Override
		public float 防御时(Char enemy, float damage) {
			
			return super.防御时(enemy, damage);
		}
		
		@Override
		public void 受伤时(float dmg, Object src) {
			super.受伤时( dmg, src );
			
			//for the rose status indicator
			Item.updateQuickslot();
		}
		
		@Override
		public float 移速() {
			float speed = super.移速();

			//moves 2 tiles at a time when returning to the hero
			if (state == WANDERING
					&& defendingPos == -1
					&& Dungeon.level.distance(pos, Dungeon.hero.pos) > 1){
				speed *= 2;
			}
			
			return speed;
		}
		
		@Override
		public int 最大闪避(Char enemy) {
			int defense = super.最大闪避(enemy);

			
			return defense;
		}
		
		@Override
		public float 最大防御() {
			float dr = super.最大防御();
			
			return dr;
		}

		@Override
		public int glyphLevel(Class<? extends Armor.Glyph> cls) {
			return super.glyphLevel(cls);
		}

		@Override
		public boolean interact(Char c) {
			更新数据();
				return super.interact(c);
		}

		@Override
		public void 死亡时(Object cause) {
			super.死亡时(cause);
		}

		@Override
		public void destroy() {
			更新数据();
			//TODO stasis?
			if (召唤物品!=null) {
				召唤物品.召唤= null;
				召唤物品.charge = 0;
				召唤物品.partialCharge = 0;
				召唤物品.随从ID= -1;
			}
			super.destroy();
		}
		
		
		
		{
			immunities.add( CorrosiveGas.class );
			immunities.add( 燃烧.class );
			immunities.add( 复仇卷轴.class);
			immunities.add( ScrollOfPsionicBlast.class );
			immunities.add( AllyBuff.class );
		}

	}
	
	private static class Wnd extends Window{
		
		private static final int BTN_SIZE	= 32;
		private static final float GAP		= 2;
		private static final float BTN_GAP	= 12;
		private static final int WIDTH		= 112;
		
		private ItemButton btnitem;
		private ItemButton btnitem2;
		private ItemButton btnitem3;
		private ItemButton btnitem4;
		private ItemButton btnitem5;
		private ItemButton btnitem6;
		private ItemButton btnitem7;
		private ItemButton btnitem8;
		private ItemButton btnitem9;
		
		Wnd(final 召唤物品 rose){
			
			IconTitle titlebar = new IconTitle();
			titlebar.icon( new ItemSprite(rose) );
			titlebar.label( Messages.get(this, "title") );
			titlebar.setRect( 0, 0, WIDTH, 0 );
			add( titlebar );
			
			RenderedTextBlock message =
					PixelScene.renderTextBlock(Messages.get(this, "desc"), 6);
			message.maxWidth( WIDTH );
			message.setPos(0, titlebar.bottom() + GAP);
			add( message );
			
			//region 1
			btnitem= new ItemButton(){
				@Override
				protected void onClick() {
					if (rose.item!=null){
						item(new WndBag.Placeholder(物品表.ITEM));
						if (!rose.item.doPickUp(Dungeon.hero)){
							Dungeon.level.drop(rose.item,Dungeon.hero.pos);
						}
						rose.item= null;
					} else {
						GameScene.selectItem(new WndBag.ItemSelector() {

							@Override
							public String textPrompt() {
								return Messages.get(Wnd.class,"item_prompt");
							}

							@Override
							public Class<?extends Bag> preferredBag(){
								return Belongings.Backpack.class;
							}

							@Override
							public boolean itemSelectable(Item item) {
								return !(item instanceof 召唤物品);
							}

							@Override
							public void onSelect(Item item) {
									if (item!=null&&item.isEquipped(Dungeon.hero)){
										((Weapon) item).doUnequip(Dungeon.hero, false, false);
									} else {
										item.detach(Dungeon.hero.belongings.backpack);
									}
									rose.item= item;
									item(rose.item);
							}
						});
					}
				}

				@Override
				protected boolean onLongClick() {
					if (item() != null && item().name() != null){
						GameScene.show(new WndInfoItem(item()));
						return true;
					}
					return false;
				}
			};
			btnitem.setRect(GAP/2+3,message.top()+message.height()+GAP*4,BTN_SIZE,BTN_SIZE);
			if (rose.item!=null) {
				btnitem.item(rose.item);
			} else {
				btnitem.item(new WndBag.Placeholder(物品表.ITEM));
			}
			add(btnitem);
			//endregion
			
			//region 2
			btnitem2= new ItemButton(){
				@Override
				protected void onClick() {
					if (rose.item2!=null){
						item(new WndBag.Placeholder(物品表.ITEM));
						if (!rose.item2.doPickUp(Dungeon.hero)){
							Dungeon.level.drop(rose.item2,Dungeon.hero.pos);
						}
						rose.item2= null;
					} else {
						GameScene.selectItem(new WndBag.ItemSelector() {

							@Override
							public String textPrompt() {
								return Messages.get(Wnd.class,"item_prompt");
							}

							@Override
							public Class<?extends Bag> preferredBag(){
								return Belongings.Backpack.class;
							}

							@Override
							public boolean itemSelectable(Item item) {
								return !(item instanceof 召唤物品);
							}

							@Override
							public void onSelect(Item item) {
									if (item!=null&&item.isEquipped(Dungeon.hero)){
										((Weapon) item).doUnequip(Dungeon.hero, false, false);
									} else {
										item.detach(Dungeon.hero.belongings.backpack);
									}
									rose.item2= item;
									item(rose.item2);
							}
						});
					}
				}

				@Override
				protected boolean onLongClick() {
					if (item() != null && item().name() != null){
						GameScene.show(new WndInfoItem(item()));
						return true;
					}
					return false;
				}
			};
			btnitem2.setRect( btnitem.right() + BTN_GAP/3, btnitem.top(), BTN_SIZE, BTN_SIZE );
			if (rose.item2!=null) {
				btnitem2.item(rose.item2);
			} else {
				btnitem2.item(new WndBag.Placeholder(物品表.ITEM));
			}
			add(btnitem2);
			//endregion
			
			//region 3
			btnitem3= new ItemButton(){
				@Override
				protected void onClick() {
					if (rose.item3!=null){
						item(new WndBag.Placeholder(物品表.ITEM));
						if (!rose.item3.doPickUp(Dungeon.hero)){
							Dungeon.level.drop(rose.item3,Dungeon.hero.pos);
						}
						rose.item3= null;
					} else {
						GameScene.selectItem(new WndBag.ItemSelector() {

							@Override
							public String textPrompt() {
								return Messages.get(Wnd.class,"item_prompt");
							}

							@Override
							public Class<?extends Bag> preferredBag(){
								return Belongings.Backpack.class;
							}

							@Override
							public boolean itemSelectable(Item item) {
								return !(item instanceof 召唤物品);
							}

							@Override
							public void onSelect(Item item) {
									if (item!=null&&item.isEquipped(Dungeon.hero)){
										((Weapon) item).doUnequip(Dungeon.hero, false, false);
									} else {
										item.detach(Dungeon.hero.belongings.backpack);
									}
									rose.item3= item;
									item(rose.item3);
							}
						});
					}
				}

				@Override
				protected boolean onLongClick() {
					if (item() != null && item().name() != null){
						GameScene.show(new WndInfoItem(item()));
						return true;
					}
					return false;
				}
			};
			btnitem3.setRect( btnitem2.right() + BTN_GAP/3, btnitem2.top(), BTN_SIZE, BTN_SIZE );
			if (rose.item3!=null) {
				btnitem3.item(rose.item3);
			} else {
				btnitem3.item(new WndBag.Placeholder(物品表.ITEM));
			}
			add(btnitem3);
			//endregion
			
			
			//region 4
			btnitem4= new ItemButton(){
				@Override
				protected void onClick() {
					if (rose.item4!=null){
						item(new WndBag.Placeholder(物品表.ITEM));
						if (!rose.item4.doPickUp(Dungeon.hero)){
							Dungeon.level.drop(rose.item4,Dungeon.hero.pos);
						}
						rose.item4= null;
					} else {
						GameScene.selectItem(new WndBag.ItemSelector() {
							
							@Override
							public String textPrompt() {
								return Messages.get(Wnd.class,"item_prompt");
							}
							
							@Override
							public Class<?extends Bag> preferredBag(){
								return Belongings.Backpack.class;
							}
							
							@Override
							public boolean itemSelectable(Item item) {
								return !(item instanceof 召唤物品);
							}
							
							@Override
							public void onSelect(Item item) {
								
								if(item!=null){
									if(item.isEquipped(Dungeon.hero)){
										((EquipableItem)item).doUnequip(Dungeon.hero,false,false);
									}else{
										item.detach(Dungeon.hero.belongings.backpack);
									}
								}
								rose.item4= item;
								item(rose.item4);
							}
						});
					}
				}
				
				@Override
				protected boolean onLongClick() {
					if (item() != null && item().name() != null){
						GameScene.show(new WndInfoItem(item()));
						return true;
					}
					return false;
				}
			};
			btnitem4.setRect(GAP/2+3,message.top()+message.height()+GAP*6+btnitem.height(),BTN_SIZE,BTN_SIZE);
			if (rose.item4!=null) {
				btnitem4.item(rose.item4);
			} else {
				btnitem4.item(new WndBag.Placeholder(物品表.ITEM));
			}
			add(btnitem4);
			//endregion
			
			//region 5
			btnitem5= new ItemButton(){
				@Override
				protected void onClick() {
					if (rose.item5!=null){
						item(new WndBag.Placeholder(物品表.ITEM));
						if (!rose.item5.doPickUp(Dungeon.hero)){
							Dungeon.level.drop(rose.item5,Dungeon.hero.pos);
						}
						rose.item5= null;
					} else {
						GameScene.selectItem(new WndBag.ItemSelector() {
							
							@Override
							public String textPrompt() {
								return Messages.get(Wnd.class,"item_prompt");
							}
							
							@Override
							public Class<?extends Bag> preferredBag(){
								return Belongings.Backpack.class;
							}
							
							@Override
							public boolean itemSelectable(Item item) {
								return !(item instanceof 召唤物品);
							}
							
							@Override
							public void onSelect(Item item) {
								
								if(item!=null){
									if(item.isEquipped(Dungeon.hero)){
										((EquipableItem)item).doUnequip(Dungeon.hero,false,false);
									}else{
										item.detach(Dungeon.hero.belongings.backpack);
									}
								}
								rose.item5= item;
								item(rose.item5);
							}
						});
					}
				}
				
				@Override
				protected boolean onLongClick() {
					if (item() != null && item().name() != null){
						GameScene.show(new WndInfoItem(item()));
						return true;
					}
					return false;
				}
			};
			btnitem5.setRect( btnitem4.right() + BTN_GAP/3, btnitem4.top(), BTN_SIZE, BTN_SIZE );
			if (rose.item5!=null) {
				btnitem5.item(rose.item5);
			} else {
				btnitem5.item(new WndBag.Placeholder(物品表.ITEM));
			}
			add(btnitem5);
			//endregion
			
			//region 6
			btnitem6= new ItemButton(){
				@Override
				protected void onClick() {
					if (rose.item6!=null){
						item(new WndBag.Placeholder(物品表.ITEM));
						if (!rose.item6.doPickUp(Dungeon.hero)){
							Dungeon.level.drop(rose.item6,Dungeon.hero.pos);
						}
						rose.item6= null;
					} else {
						GameScene.selectItem(new WndBag.ItemSelector() {
							
							@Override
							public String textPrompt() {
								return Messages.get(Wnd.class,"item_prompt");
							}
							
							@Override
							public Class<?extends Bag> preferredBag(){
								return Belongings.Backpack.class;
							}
							
							@Override
							public boolean itemSelectable(Item item) {
								return !(item instanceof 召唤物品);
							}
							
							@Override
							public void onSelect(Item item) {
								
								if(item!=null){
									if(item.isEquipped(Dungeon.hero)){
										((EquipableItem)item).doUnequip(Dungeon.hero,false,false);
									}else{
										item.detach(Dungeon.hero.belongings.backpack);
									}
								}
								rose.item6= item;
								item(rose.item6);
							}
						});
					}
				}
				
				@Override
				protected boolean onLongClick() {
					if (item() != null && item().name() != null){
						GameScene.show(new WndInfoItem(item()));
						return true;
					}
					return false;
				}
			};
			btnitem6.setRect( btnitem5.right() + BTN_GAP/3, btnitem4.top(), BTN_SIZE, BTN_SIZE );
			if (rose.item6!=null) {
				btnitem6.item(rose.item6);
			} else {
				btnitem6.item(new WndBag.Placeholder(物品表.ITEM));
			}
			add(btnitem6);
			//endregion
			
			//region 7
			btnitem7= new ItemButton(){
				@Override
				protected void onClick() {
					if (rose.item7!=null){
						item(new WndBag.Placeholder(物品表.ITEM));
						if (!rose.item7.doPickUp(Dungeon.hero)){
							Dungeon.level.drop(rose.item7,Dungeon.hero.pos);
						}
						rose.item7= null;
					} else {
						GameScene.selectItem(new WndBag.ItemSelector() {
							
							@Override
							public String textPrompt() {
								return Messages.get(Wnd.class,"item_prompt");
							}
							
							@Override
							public Class<?extends Bag> preferredBag(){
								return Belongings.Backpack.class;
							}
							
							@Override
							public boolean itemSelectable(Item item) {
								return !(item instanceof 召唤物品);
							}
							
							@Override
							public void onSelect(Item item) {
								
								if(item!=null){
									if(item.isEquipped(Dungeon.hero)){
										((EquipableItem)item).doUnequip(Dungeon.hero,false,false);
									}else{
										item.detach(Dungeon.hero.belongings.backpack);
									}
								}
								rose.item7= item;
								item(rose.item7);
							}
						});
					}
				}
				
				@Override
				protected boolean onLongClick() {
					if (item() != null && item().name() != null){
						GameScene.show(new WndInfoItem(item()));
						return true;
					}
					return false;
				}
			};
			btnitem7.setRect(GAP/2+3,message.top()+message.height()+GAP*8+btnitem4.height()*2,BTN_SIZE,BTN_SIZE);
			if (rose.item7!=null) {
				btnitem7.item(rose.item7);
			} else {
				btnitem7.item(new WndBag.Placeholder(物品表.ITEM));
			}
			add(btnitem7);
			//endregion
			
			//region 8
			btnitem8= new ItemButton(){
				@Override
				protected void onClick() {
					if (rose.item8!=null){
						item(new WndBag.Placeholder(物品表.ITEM));
						if (!rose.item8.doPickUp(Dungeon.hero)){
							Dungeon.level.drop(rose.item8,Dungeon.hero.pos);
						}
						rose.item8= null;
					} else {
						GameScene.selectItem(new WndBag.ItemSelector() {
							
							@Override
							public String textPrompt() {
								return Messages.get(Wnd.class,"item_prompt");
							}
							
							@Override
							public Class<?extends Bag> preferredBag(){
								return Belongings.Backpack.class;
							}
							
							@Override
							public boolean itemSelectable(Item item) {
								return !(item instanceof 召唤物品);
							}
							
							@Override
							public void onSelect(Item item) {
								
								if(item!=null){
									if(item.isEquipped(Dungeon.hero)){
										((EquipableItem)item).doUnequip(Dungeon.hero,false,false);
									}else{
										item.detach(Dungeon.hero.belongings.backpack);
									}
								}
								rose.item8= item;
								item(rose.item8);
							}
						});
					}
				}
				
				@Override
				protected boolean onLongClick() {
					if (item() != null && item().name() != null){
						GameScene.show(new WndInfoItem(item()));
						return true;
					}
					return false;
				}
			};
			btnitem8.setRect( btnitem4.right() + BTN_GAP/3, btnitem7.top(), BTN_SIZE, BTN_SIZE );
			if (rose.item5!=null) {
				btnitem8.item(rose.item5);
			} else {
				btnitem8.item(new WndBag.Placeholder(物品表.ITEM));
			}
			add(btnitem8);
			//endregion
			
			//region 9
			btnitem9= new ItemButton(){
				@Override
				protected void onClick() {
					if (rose.item9!=null){
						item(new WndBag.Placeholder(物品表.ITEM));
						if (!rose.item9.doPickUp(Dungeon.hero)){
							Dungeon.level.drop(rose.item9,Dungeon.hero.pos);
						}
						rose.item9= null;
					} else {
						GameScene.selectItem(new WndBag.ItemSelector() {
							
							@Override
							public String textPrompt() {
								return Messages.get(Wnd.class,"item_prompt");
							}
							
							@Override
							public Class<?extends Bag> preferredBag(){
								return Belongings.Backpack.class;
							}
							
							@Override
							public boolean itemSelectable(Item item) {
								return !(item instanceof 召唤物品);
							}
							
							@Override
							public void onSelect(Item item) {
								
								if(item!=null){
									if(item.isEquipped(Dungeon.hero)){
										((EquipableItem)item).doUnequip(Dungeon.hero,false,false);
									}else{
										item.detach(Dungeon.hero.belongings.backpack);
									}
								}
								rose.item9= item;
								item(rose.item9);
							}
						});
					}
				}
				
				@Override
				protected boolean onLongClick() {
					if (item() != null && item().name() != null){
						GameScene.show(new WndInfoItem(item()));
						return true;
					}
					return false;
				}
			};
			btnitem9.setRect( btnitem8.right() + BTN_GAP/3, btnitem7.top(), BTN_SIZE, BTN_SIZE );
			if (rose.item9!=null) {
				btnitem9.item(rose.item9);
			} else {
				btnitem9.item(new WndBag.Placeholder(物品表.ITEM));
			}
			add(btnitem9);
			//endregion
			
			resize(WIDTH, (int)(btnitem9.bottom() + GAP*2));
		}
	
	}
}
