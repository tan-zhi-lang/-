

package com.shatteredpixel.shatteredpixeldungeon.effects;

import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.watabou.noosa.Image;

public class BannerSprites {

	public enum  Type {
		TITLE_PORT,
		TITLE_GLOW_PORT,
		TITLE_LAND,
		TITLE_GLOW_LAND,
		BOSS_SLAIN,
		GAME_OVER,
	}

	public static Image get( Type type ) {
		Image icon = new Image( Assets.Interfaces.BANNERS );
		switch (type) {
			case TITLE_PORT:
				icon.frame( icon.texture.uvRect( 0, 0, 139, 100 ) );
				break;
			case TITLE_GLOW_PORT:
				icon.frame( icon.texture.uvRect( 139, 0, 278, 100 ) );
				break;
			case TITLE_LAND:
				icon.frame( icon.texture.uvRect( 0, 100, 240, 157) );
				break;
			case TITLE_GLOW_LAND:
				icon.frame( icon.texture.uvRect( 240, 100, 480, 157 ) );
				break;
			case BOSS_SLAIN:
				icon.frame( icon.texture.uvRect( 0, 157, 127, 225 ) );
				break;
			case GAME_OVER:
				icon.frame( icon.texture.uvRect( 128, 157, 256, 192 ) );
				break;
		}
		return icon;
	}
}
