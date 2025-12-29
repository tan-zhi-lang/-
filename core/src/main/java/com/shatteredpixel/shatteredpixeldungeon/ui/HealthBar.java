

package com.shatteredpixel.shatteredpixeldungeon.ui;

import com.shatteredpixel.shatteredpixeldungeon.actors.Char;
import com.shatteredpixel.shatteredpixeldungeon.actors.buffs.Invisibility;
import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.watabou.noosa.BitmapText;
import com.watabou.noosa.ColorBlock;
import com.watabou.noosa.ui.Component;

public class HealthBar extends Component {

	private static final int COLOR_BG	= 0xFFCC0000;
	private static final int COLOR_HP	= 0xFF00EE00;
	private static final int COLOR_SHLD = 0xFFFFFFFF;
	
	private static final int HEIGHT	= 2;
	
	private ColorBlock Bg;
	private ColorBlock Shld;
	private ColorBlock Hp;
	private BitmapText hpText;
	
	private float health;
	private float 生命;
	private float 护盾;
	private float max;
	private float shield;
	private boolean 隐形;
	
	@Override
	protected void createChildren() {
		
		Bg = new ColorBlock( 1, 1, COLOR_BG );
		add( Bg );

		Shld = new ColorBlock( 1, 1, COLOR_SHLD );
		add( Shld );
		
		Hp = new ColorBlock( 1, 1, COLOR_HP );
		add( Hp );
		
		
		hpText = new BitmapText(PixelScene.pixelFont);
		hpText.alpha(0.6f);
		add(hpText);
		
		if(隐形){
			Bg.alpha(0);
			Shld.alpha(0);
			Hp.alpha(0);
			hpText.alpha(0);
		}else{
			Bg.alpha(1);
			Shld.alpha(1);
			Hp.alpha(1);
			hpText.alpha(0.6f);
		}
		height = HEIGHT;
	}
	
	@Override
	public synchronized void update(){
		
		Bg.x = Shld.x = Hp.x = x;
		Bg.y = Shld.y = Hp.y = y;
		Bg.size( width, height );
		
		
		//logic here rounds up to the nearest pixel
		float pixelWidth = width;
		if (camera() != null) pixelWidth *= camera().zoom;
		Shld.size( width * (float)Math.ceil(shield * pixelWidth)/pixelWidth, height );
		Hp.size( width * (float)Math.ceil(shield * pixelWidth)/pixelWidth, height );
		if (护盾 <= 0){
			hpText.text(Math.round(生命) + "/" + Math.round(max));
		} else {
			hpText.text(Math.round(生命) + "+" + Math.round(护盾) +  "/" + Math.round(max));
		}
		hpText.measure();
		hpText.x = Hp.x+0.5f;
		hpText.y = Hp.y +0.5f+ (Hp.height - (hpText.baseLine()+hpText.scale.y))/2f;
		
		if(隐形){
			Bg.alpha(0);
			Shld.alpha(0);
			Hp.alpha(0);
			hpText.alpha(0);
		}else{
			Bg.alpha(1);
			Shld.alpha(1);
			Hp.alpha(1);
			hpText.alpha(0.6f);
		}
		super.update();
	}
	
	@Override
	protected void layout() {
		Bg.x = Shld.x = Hp.x = x;
		Bg.y = Shld.y = Hp.y = y;
		Bg.size( width, height );
		
		//logic here rounds up to the nearest pixel
		float pixelWidth = width;
		if (camera() != null) pixelWidth *= camera().zoom;
		Shld.size( width * (float)Math.ceil(shield * pixelWidth)/pixelWidth, height );
		Hp.size( width * (float)Math.ceil(health * pixelWidth)/pixelWidth, height );
		
		hpText.scale.set(PixelScene.align(0.34f));
		hpText.x = Hp.x+0.5f;
		hpText.y = Hp.y +0.5f+ (Hp.height - (hpText.baseLine()+hpText.scale.y))/2f;
		
		if(隐形){
			Bg.alpha(0);
			Shld.alpha(0);
			Hp.alpha(0);
			hpText.alpha(0);
		}else{
			Bg.alpha(1);
			Shld.alpha(1);
			Hp.alpha(1);
			hpText.alpha(0.6f);
		}
		
	}
	
	public void level( float value ) {
		level( value, 0f ,0f,0f,0f,false);
	}

	public void level( float health, float shield , float health2, float shield2 , float max ,boolean 隐形){
		this.health = health;
		this.生命 = health2;
		this.护盾 = shield2;
		this.max = max;
		this.shield = shield;
		this.隐形 = 隐形;
		layout();
	}

	public void level(Char c){
		float health = c.生命;
		float maxx = c.最大生命;
		float shield = c.shielding();
		float max = Math.max(health+shield, c.最大生命);
		boolean 隐形=c.hasbuff(Invisibility.class);
		level(health/max, (health+shield)/max,health,shield,maxx,隐形);
	}
}
