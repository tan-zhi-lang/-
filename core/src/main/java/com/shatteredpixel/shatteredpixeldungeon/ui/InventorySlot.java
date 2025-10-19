

package com.shatteredpixel.shatteredpixeldungeon.ui;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.items.EquipableItem;
import com.shatteredpixel.shatteredpixeldungeon.items.Gold;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.bags.Bag;
import com.shatteredpixel.shatteredpixeldungeon.items.wands.Wand;
import com.watabou.gltextures.TextureCache;
import com.watabou.noosa.ColorBlock;
import com.watabou.noosa.audio.Sample;

public class InventorySlot extends ItemSlot {

	private static final int NORMAL		= 0x9953564D;
	private static final int EQUIPPED	= 0x9991938C;

	private ColorBlock bg;

	public InventorySlot( Item item ) {

		super( item );
	}

	@Override
	protected void createChildren() {
		bg = new ColorBlock( 1, 1, NORMAL );
		add( bg );

		super.createChildren();
	}

	@Override
	protected void layout() {
		bg.size(width, height);
		bg.x = x;
		bg.y = y;

		super.layout();
	}

	@Override
	public void alpha(float value) {
		super.alpha(value);
		bg.alpha(value);
	}

	@Override
	public void item( Item item ) {

		super.item( item );

		bg.visible = !(item instanceof Gold || item instanceof Bag);

		if (item != null) {

			boolean equipped = item.isEquipped(Dungeon.hero) ||
					item == Dungeon.hero.belongings.weapon ||
					item == Dungeon.hero.belongings.armor ||
					item == Dungeon.hero.belongings.misc ||
					item == Dungeon.hero.belongings.misc2 ||
					item == Dungeon.hero.belongings.misc3 ||
					item == Dungeon.hero.belongings.secondWep;

			bg.texture( TextureCache.createSolid( equipped ? EQUIPPED : NORMAL ) );
			bg.resetColor();
			if (item.cursed && item.cursedKnown) {
				bg.诅咒();
			} else if (!item.已鉴定()) {
				if((item instanceof EquipableItem || item instanceof Wand)){
					if (item.cursedKnown){
						bg.无诅咒();
					} else {
						bg.诅咒未知();
					}
				}else{
					bg.无诅咒();//非装备是必定无诅咒
				}
			}
			//底色
			if(item.已鉴定()){
				if(item.黑色){
					bg.黑色();
				}
				if(item.白色){
					bg.白色();
				}
				if(item.黄色){
					bg.黄色();
				}
				if(item.红色){
					bg.红色();
				}
				if(item.绿色){
					bg.绿色();
				}
				if(item.蓝色){
					bg.蓝色();
				}
				if(item.紫色){
					bg.紫色();
				}
				if(item.青色){
					bg.青色();
				}
			}

			if (item.name() == null) {
				enable( false );
			} else if (Dungeon.hero.belongings.lostInventory()
					&& !item.keptThroughLostInventory()){
				enable(false);
			}
		} else {
			bg.texture( TextureCache.createSolid( NORMAL ) );
			bg.resetColor();
		}
	}

	public Item item(){
		return item;
	}

	@Override
	protected void onPointerDown() {
		bg.brightness( 1.5f );
		Sample.INSTANCE.play( Assets.Sounds.CLICK, 0.7f, 0.7f, 1.2f );
	}

	protected void onPointerUp() {
		bg.brightness( 1.0f );
	}

}
