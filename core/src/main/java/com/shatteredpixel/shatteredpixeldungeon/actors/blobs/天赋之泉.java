

package com.shatteredpixel.shatteredpixeldungeon.actors.blobs;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.Badges;
import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.effects.BlobEmitter;
import com.shatteredpixel.shatteredpixeldungeon.effects.CellEmitter;
import com.shatteredpixel.shatteredpixeldungeon.effects.FloatingText;
import com.shatteredpixel.shatteredpixeldungeon.effects.Speck;
import com.shatteredpixel.shatteredpixeldungeon.effects.particles.ShaftParticle;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.journal.Notes.Landmark;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.shatteredpixel.shatteredpixeldungeon.windows.Wnd选择天赋层;
import com.watabou.noosa.Game;
import com.watabou.noosa.audio.Sample;

public class 天赋之泉 extends WellWater {
	
	@Override
	protected boolean affectHero( Hero hero ) {
		
		Badges.解锁逐姝();
		if (!hero.isAlive()) return false;
		
		Sample.INSTANCE.play( Assets.Sounds.DRINK );
		Game.runOnRenderThread(()->{
			GameScene.show(new Wnd选择天赋层());
		});
		hero.sprite.showStatusWithIcon(CharSprite.增强, "1", FloatingText.EXPERIENCE);
		CellEmitter.get( hero.pos ).start( ShaftParticle.FACTORY, 0.2f, 3 );

		Dungeon.hero.interrupt();
	
		GLog.p( Messages.get(this, "procced") );
		
		return true;
	}
	
	@Override
	protected Item affectItem( Item item, int pos ) {
		if (item.可升级()) {
			item.升级();
			CellEmitter.get( pos ).start( Speck.factory( Speck.UP ), 0.4f, 4 );
			Sample.INSTANCE.play( Assets.Sounds.DRINK );
			return item;
		}
		return null;
	}
	
	@Override
	public Landmark landmark() {
		return Landmark.天赋之泉;
	}
	
	@Override
	public void use( BlobEmitter emitter ) {
		super.use( emitter );
		emitter.start( Speck.factory( Speck.STAR ), 0.5f, 0 );
	}
	
	@Override
	public String tileDesc() {
		return Messages.get(this, "desc");
	}
}
