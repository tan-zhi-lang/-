package com.shatteredpixel.shatteredpixeldungeon.effects;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.SPDSettings;
import com.shatteredpixel.shatteredpixeldungeon.actors.blobs.Blob;
import com.shatteredpixel.shatteredpixeldungeon.tiles.DungeonTilemap;
import com.watabou.gltextures.TextureCache;
import com.watabou.noosa.Group;
import com.watabou.noosa.Image;

import java.util.ArrayList;

public class BlobBorder extends Group {
	//线连接
	private final Blob blob;
	private int lastVersion = -1;
	private final ArrayList<Image> borders = new ArrayList<>();
	private int used = 0;

	public BlobBorder( Blob blob ) {
		super();
		this.blob = blob;
	}

	@Override
	public void update() {
		super.update();
		visible = SPDSettings.辅助格子();
		if (visible && blob.borderColor != 0 && blob.evolveVersion != lastVersion) {
			lastVersion = blob.evolveVersion;
			rebuild();
		}
	}

	private Image border( int used ) {
		while (borders.size() <= used) {
			Image img = new Image( TextureCache.createSolid( blob.borderColor ) );
			img.alpha( 0.85f );
			add( img );
			borders.add( img );
		}
		return borders.get( used );
	}

	private void rebuild() {
		used = 0;
		if (blob.volume > 0) {
			if (blob.area.isEmpty())
				blob.setupArea();

			int[] map = blob.cur;
			float size = DungeonTilemap.SIZE;
			float t = Math.max( 1, size / 16f );
			int w = Dungeon.level.width();
			int h = map.length / w;

			for (int i = blob.area.left; i < blob.area.right; i++) {
				for (int j = blob.area.top; j < blob.area.bottom; j++) {
					int cell = i + j * w;
					if (cell >= map.length || map[cell] <= 0) {
						continue;
					}
					float x = i * size;
					float y = j * size;
					boolean up    = j > 0 && map[cell - w] > 0;
					boolean down  = j + 1 < h && map[cell + w] > 0;
					boolean left  = i > 0 && map[cell - 1] > 0;
					boolean right = i + 1 < w && map[cell + 1] > 0;
					if (!up) {
						Image b = border( used++ );
						b.visible = true;
						b.x = x - t; b.y = y;
						b.scale.set( size + 2 * t, t );
					}
					if (!down) {
						Image b = border( used++ );
						b.visible = true;
						b.x = x - t; b.y = y + size - t;
						b.scale.set( size + 2 * t, t );
					}
					if (!left) {
						Image b = border( used++ );
						b.visible = true;
						b.x = x; b.y = y - t;
						b.scale.set( t, size + 2 * t );
					}
					if (!right) {
						Image b = border( used++ );
						b.visible = true;
						b.x = x + size - t; b.y = y - t;
						b.scale.set( t, size + 2 * t );
					}
				}
			}
		}
		for (int k = used; k < borders.size(); k++) {
			borders.get( k ).visible = false;
		}
	}
}