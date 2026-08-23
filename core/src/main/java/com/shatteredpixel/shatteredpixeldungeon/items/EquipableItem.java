

package com.shatteredpixel.shatteredpixeldungeon.items;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.MagicImmune;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroClass;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroSubClass;
import com.shatteredpixel.shatteredpixeldungeon.effects.particles.ShadowParticle;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.Armor;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.Artifact;
import com.shatteredpixel.shatteredpixeldungeon.items.artifacts.心之钢;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.Bag;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.绒布袋;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.Ring;
import com.shatteredpixel.shatteredpixeldungeon.items.weapon.Weapon;
import com.shatteredpixel.shatteredpixeldungeon.items.涂药.传送药物;
import com.shatteredpixel.shatteredpixeldungeon.items.涂药.净化药物;
import com.shatteredpixel.shatteredpixeldungeon.items.涂药.寒霜药物;
import com.shatteredpixel.shatteredpixeldungeon.items.涂药.毒液药物;
import com.shatteredpixel.shatteredpixeldungeon.items.涂药.治疗药物;
import com.shatteredpixel.shatteredpixeldungeon.items.涂药.涂药;
import com.shatteredpixel.shatteredpixeldungeon.items.涂药.激素药物;
import com.shatteredpixel.shatteredpixeldungeon.items.涂药.燃烧药物;
import com.shatteredpixel.shatteredpixeldungeon.items.涂药.电击药物;
import com.shatteredpixel.shatteredpixeldungeon.items.涂药.神圣药物;
import com.shatteredpixel.shatteredpixeldungeon.items.涂药.腐莓药物;
import com.shatteredpixel.shatteredpixeldungeon.items.涂药.致盲药物;
import com.shatteredpixel.shatteredpixeldungeon.items.涂药.麻痹药物;
import com.shatteredpixel.shatteredpixeldungeon.journal.Document;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.plants.Blindweed;
import com.shatteredpixel.shatteredpixeldungeon.plants.Earthroot;
import com.shatteredpixel.shatteredpixeldungeon.plants.Fadeleaf;
import com.shatteredpixel.shatteredpixeldungeon.plants.Firebloom;
import com.shatteredpixel.shatteredpixeldungeon.plants.Icecap;
import com.shatteredpixel.shatteredpixeldungeon.plants.Mageroyal;
import com.shatteredpixel.shatteredpixeldungeon.plants.Plant;
import com.shatteredpixel.shatteredpixeldungeon.plants.Rotberry;
import com.shatteredpixel.shatteredpixeldungeon.plants.Sorrowmoss;
import com.shatteredpixel.shatteredpixeldungeon.plants.Starflower;
import com.shatteredpixel.shatteredpixeldungeon.plants.Stormvine;
import com.shatteredpixel.shatteredpixeldungeon.plants.Sungrass;
import com.shatteredpixel.shatteredpixeldungeon.plants.Swiftthistle;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndBag;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndOptions;
import com.shatteredpixel.shatteredpixeldungeon.派对设置;
import com.shatteredpixel.shatteredpixeldungeon.炼狱设置;
import com.watabou.noosa.audio.Sample;
import com.watabou.utils.Bundle;
import com.watabou.utils.Reflection;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public abstract class EquipableItem extends Item {

	public static final String AC_EQUIP		= "EQUIP";
	public static final String AC_EQUIP2		= "EQUIP2";
	public static final String AC_UNEQUIP	= "UNEQUIP";
	public static final String AC_UNEQUIP2	= "UNEQUIP2";
	protected static final String AC_TIP = "TIP";
	private static final String AC_CLEAN = "CLEAN";

	{
		遗产= true;
	}
	public boolean 不花费= false;
	public 涂药 涂药种类=null;
	public static final String 涂药种类x	= "涂药种类";

	@Override
	public void storeInBundle( Bundle bundle) {
		super.storeInBundle( bundle );
		bundle.put( 涂药种类x, 涂药种类 );
	}

	@Override
	public void restoreFromBundle( Bundle bundle ) {
		super.restoreFromBundle( bundle );
		涂药种类 = (涂药)bundle.get( 涂药种类x );
	}

	@Override
	public ArrayList<String> actions(Hero hero ) {
		ArrayList<String> actions = super.actions( hero );

		if(无动作){
			return new ArrayList<>();
		}
		boolean b=true;
		if(Dungeon.炼狱(炼狱设置.诅咒之戒)&&this instanceof Ring){
			b=false;
		}

		if(Dungeon.炼狱(炼狱设置.诅咒神器)&&this instanceof Artifact){
			b=false;
		}
		if(Dungeon.派对(派对设置.钢门联盟)&&this instanceof 心之钢)b=true;
		if(专属)b=true;

		if(b){
			if(isEquipped(hero)){
				if(幸运装备)
					actions.add(AC_UNEQUIP2);
				else
					actions.add(AC_UNEQUIP);
			}else{
				actions.add(AC_EQUIP);

				if(hero.belongings.幸运==null&&this instanceof Ring&&hero.男人())
				actions.add(AC_EQUIP2);
			}
		}
		if(this instanceof Weapon||this instanceof Armor){
			if(涂药种类!=null)
			actions.add( AC_CLEAN );
			else
			actions.add(AC_TIP);
		}

		if(isEquipped(hero)&&cursed&&cursedKnown&&!hero.heroClass(HeroClass.巫女)){
			//正装备的诅咒移除扔出和卸下
			actions.remove(AC_UNEQUIP2);
			actions.remove(AC_UNEQUIP);
			actions.remove(AC_DROP);
			actions.remove(AC_THROW);
		}
		return actions;
	}

	@Override
	public boolean doPickUp(Hero hero, int pos) {
		if (super.doPickUp(hero, pos)){
			if (!已鉴定() && !Document.ADVENTURERS_GUIDE.isPageRead(Document.GUIDE_IDING)){
				GameScene.flashForDocument(Document.ADVENTURERS_GUIDE, Document.GUIDE_IDING);
			}
			return true;
		} else {
			return false;
		}
	}

	protected static int slotOfUnequipped = -1;

	private final WndBag.ItemSelector itemSelector = new WndBag.ItemSelector() {

		@Override
		public String textPrompt() {
			return "选择一粒种子";
		}

		@Override
		public Class<?extends Bag> preferredBag(){
			return 绒布袋.class;
		}

		@Override
		public boolean itemSelectable(Item item) {
			return item instanceof Plant.Seed;
		}

		@Override
		public void onSelect(final Item item) {

			if (item == null) return;

			final String[] options;
			options = new String[]{
					"用1粒种子为此装备涂药",
					"取消"};

			涂药 tipResult = 涂药((Plant.Seed) item);

			GameScene.show(new WndOptions(new ItemSprite(item),
										  Messages.titleCase(item.name()),
										  Messages.get(EquipableItem.class,"tip_desc",tipResult.name())+"\n\n"+tipResult.desc(),
										  options){

				@Override
				protected void onSelect(int index) {
					super.onSelect(index);

					if (index == 0){
						item.detach( curUser.belongings.backpack );

						涂药种类=tipResult;

						if(涂药种类 instanceof 腐莓药物)涂药种类.涂药次数=10;
						else 涂药种类.涂药次数=15;

						curUser.spend( 1f );
						curUser.busy();
						curUser.sprite.operate();
					}
				}
			});

		}

	};

	public static final LinkedHashMap<Class<?extends Plant.Seed>, Class<?extends 涂药>> types = new LinkedHashMap<>();
	static {
		types.put(Rotberry.Seed.class,腐莓药物.class);
		types.put(Sungrass.Seed.class,治疗药物.class);
		types.put(Fadeleaf.Seed.class,传送药物.class);
		types.put(Icecap.Seed.class,寒霜药物.class);
		types.put(Firebloom.Seed.class,燃烧药物.class);
		types.put(Sorrowmoss.Seed.class,毒液药物.class);
		types.put(Swiftthistle.Seed.class,激素药物.class);
		types.put(Blindweed.Seed.class,致盲药物.class);
		types.put(Stormvine.Seed.class,电击药物.class);
		types.put(Earthroot.Seed.class,麻痹药物.class);
		types.put(Mageroyal.Seed.class,净化药物.class);
		types.put(Starflower.Seed.class,神圣药物.class);
	}

	public 涂药 涂药( Plant.Seed s ){
		return (涂药) Reflection.newInstance(types.get(s.getClass()));
	}

	public 涂药 随机涂药(){
		Plant.Seed s;
		do
		{
			s=(Plant.Seed)Generator.randomUsingDefaults(Generator.Category.SEED);
		}while(!types.containsKey(s.getClass()));

		return 涂药(s);
	}
		@Override
	public void execute( Hero hero, String action ) {

		super.execute( hero, action );

		if (action.equals(AC_TIP)){
			GameScene.selectItem(itemSelector);
		}
		if (action.equals( AC_CLEAN )){

			String[] options = new String[]{
					"清洗",
					"取消"
			};

			GameScene.show(new WndOptions(new ItemSprite(this),
										  Messages.titleCase(name()),
										  Messages.get(this, "clean_desc"),
										  options){
				@Override
				protected void onSelect(int index) {
					if (index == 0){
						涂药种类=null;

						hero.spend( 1f );
						hero.busy();
						hero.sprite.operate();
					}
				}
			});

		}
		if (action.equals( AC_EQUIP )) {
			//In addition to equipping itself, item reassigns itself to the quickslot
			//This is a special case as the item is being removed from inventory, but is staying with the hero.
			int slot = Dungeon.quickslot.getSlot( this );
			slotOfUnequipped = -1;
			doEquip(hero);
			curItem=this;
			curUser=hero;
			Dungeon.quickslot.alphaItem(this,false);
			if (slot != -1) {
				Dungeon.quickslot.setSlot( slot, this );
				updateQuickslot();
			//if this item wasn't quickslotted, but the item it is replacing as equipped was
			//then also have the item occupy the unequipped item's quickslot
			} else if (slotOfUnequipped != -1 && defaultAction() != null) {
				Dungeon.quickslot.setSlot( slotOfUnequipped, this );
				updateQuickslot();
			}
		} else if (action.equals( AC_UNEQUIP )) {
			doUnequip( hero, true );
			curItem=null;
			curUser=null;
		}
		if (action.equals( AC_EQUIP2 )) {
			//In addition to equipping itself, item reassigns itself to the quickslot
			//This is a special case as the item is being removed from inventory, but is staying with the hero.
			int slot = Dungeon.quickslot.getSlot( this );
			slotOfUnequipped = -1;
			doEquip2(hero);
			curItem=this;
			curUser=hero;
			Dungeon.quickslot.alphaItem(this,false);
			if (slot != -1) {
				Dungeon.quickslot.setSlot( slot, this );
				updateQuickslot();
			//if this item wasn't quickslotted, but the item it is replacing as equipped was
			//then also have the item occupy the unequipped item's quickslot
			} else if (slotOfUnequipped != -1 && defaultAction() != null) {
				Dungeon.quickslot.setSlot( slotOfUnequipped, this );
				updateQuickslot();
			}
		} else if (action.equals( AC_UNEQUIP2 )) {
			doUnequip2( hero, true );
			curItem=null;
			curUser=null;
		}
	}

	@Override
	public void doDrop( Hero hero ) {
		if (!isEquipped( hero ) || doUnequip( hero, false, false )) {
			super.doDrop( hero );
		}
	}

	@Override
	public void cast( final Hero user, int dst ) {

		if (isEquipped( user )) {
			if(this instanceof Weapon||this instanceof Ring){//戒指和武器不花费卸下时间
				不花费=true;
			}
			if(消受投掷){
				super.cast( user, dst );
				return;
			}
			if (quantity == 1 && !this.doUnequip( user, false, false )) {
				return;
			}
		}

		super.cast( user, dst );
	}

	public static void equipCursed( Hero hero ) {
		hero.sprite.emitter().burst( ShadowParticle.CURSE, 6 );
		Sample.INSTANCE.play( Assets.Sounds.CURSED );
	}

	protected float timeToEquip( Hero hero ) {
		if(不花费||Dungeon.符文("快手")){
			return 0;
		}
		if(hero.subClass(HeroSubClass.轻装步兵)){
				if(this instanceof Armor)
			return 0;
		}

		return hero.攻击延迟();
	}

	public abstract boolean doEquip( Hero hero );
	public boolean doEquip2( Hero hero ){
		return true;
	};

	public boolean doUnequip( Hero hero, boolean collect, boolean single ) {

		if ((cursed
				&& hero.buff(MagicImmune.class) == null
				&& (!hero.belongings.lostInventory() || keptThroughLostInventory()))) {
			GLog.橙(Messages.get(EquipableItem.class,"unequip_cursed"));
			Dungeon.hero.sprite.哭泣();
			return false;
		}

		if (single) {
			hero.spendAndNext( timeToEquip( hero ) );
		} else {
			hero.spend( timeToEquip( hero ) );
		}
		首次装备=false;
		slotOfUnequipped = Dungeon.quickslot.getSlot(this);

		//temporarily keep this item so it can be collected
		boolean wasKept = keptThoughLostInvent;
		keptThoughLostInvent = true;
		if (!collect || !放背包( hero.belongings.backpack )) {
			onDetach();
			Dungeon.quickslot.alphaItem(this,true);
			updateQuickslot();
			if (collect) Dungeon.level.drop( this, hero.pos ).sprite().drop();
		}
		keptThoughLostInvent = wasKept;

		return true;
	}
	public boolean doUnequip2( Hero hero, boolean collect, boolean single ) {

		if ((cursed
				&& hero.buff(MagicImmune.class) == null
				&& (!hero.belongings.lostInventory() || keptThroughLostInventory()))) {
			GLog.橙(Messages.get(EquipableItem.class,"unequip_cursed"));
			Dungeon.hero.sprite.哭泣();
			return false;
		}

		if (single) {
			hero.spendAndNext( timeToEquip( hero ) );
		} else {
			hero.spend( timeToEquip( hero ) );
		}
		首次装备=false;
		slotOfUnequipped = Dungeon.quickslot.getSlot(this);

		//temporarily keep this item so it can be collected
		boolean wasKept = keptThoughLostInvent;
		keptThoughLostInvent = true;
		if (!collect || !放背包( hero.belongings.backpack )) {
			onDetach();
			Dungeon.quickslot.alphaItem(this,true);
			updateQuickslot();
			if (collect) Dungeon.level.drop( this, hero.pos ).sprite().drop();
		}
		keptThoughLostInvent = wasKept;

		return true;
	}

	final public boolean doUnequip( Hero hero, boolean collect ) {
		return doUnequip( hero, collect, true );
	}
	final public boolean doUnequip2( Hero hero, boolean collect ) {
		return doUnequip2( hero, collect, true );
	}

	public void activate( Char ch ){}

}
