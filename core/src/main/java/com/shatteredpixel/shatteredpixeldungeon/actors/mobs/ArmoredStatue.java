

package com.shatteredpixel.shatteredpixeldungeon.actors.mobs;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.items.Generator;
import com.shatteredpixel.shatteredpixeldungeon.items.armor.Armor;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.StatueSprite;
import com.watabou.utils.Bundle;

public class ArmoredStatue extends Statue {

	{
		spriteClass = StatueSprite.class;
	}

	protected Armor armor;

	public ArmoredStatue(){
		super();

		//double HP
		生命 = 最大生命 = 30 + Dungeon.depth * 10;
	}

	@Override
	public void createWeapon(boolean useDecks) {
		super.createWeapon(useDecks);

		armor = Generator.randomArmor();
		armor.cursed = false;
		armor.inscribe(Armor.Glyph.random());
	}

	private static final String ARMOR	= "armor";

	@Override
	public void storeInBundle( Bundle bundle ) {
		super.storeInBundle( bundle );
		bundle.put( ARMOR, armor );
	}

	@Override
	public void restoreFromBundle( Bundle bundle ) {
		super.restoreFromBundle( bundle );
		armor = (Armor)bundle.get( ARMOR );
	}

	@Override
	public int 最小防御() {
		return super.最小防御()+armor.最小防御();
	}
	@Override
	public int 最大防御() {
		return super.最大防御()+armor.最大防御();
	}

	//used in some glyph calculations
	public Armor armor(){
		return armor;
	}

	@Override
	public int 防御时(Char enemy, int damage) {
		damage = armor.防御时(enemy, this, damage);
		return super.防御时(enemy, damage);
	}

	@Override
	public int glyphLevel(Class<? extends Armor.Glyph> cls) {
		if (armor != null && armor.hasGlyph(cls, this)){
			return Math.max(super.glyphLevel(cls), armor.强化等级());
		} else {
			return super.glyphLevel(cls);
		}
	}

	@Override
	public CharSprite sprite() {
		CharSprite sprite = super.sprite();
		if (armor != null) {
			((StatueSprite) sprite).setArmor(armor.tier);
		} else {
			((StatueSprite) sprite).setArmor(3);
		}
		return sprite;
	}

	@Override
	public int 最大闪避(Char enemy) {
		return Math.round(armor.evasionFactor(this, super.最大闪避(enemy)));
	}

	@Override
	public void 死亡时(Object cause ) {
		armor.鉴定(false);
		Dungeon.level.drop( armor, pos ).sprite.drop();
		super.死亡时( cause );
	}

	@Override
	public String description() {
		String desc = Messages.get(this, "desc");
		if (weapon != null && armor != null){
			desc += "\n\n" + Messages.get(this, "desc_arm_wep", weapon.name(), armor.name());
		}
		return desc;
	}

}
