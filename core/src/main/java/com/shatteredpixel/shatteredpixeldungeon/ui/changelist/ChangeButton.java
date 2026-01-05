

package com.shatteredpixel.shatteredpixeldungeon.ui.changelist;

import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.shatteredpixel.shatteredpixeldungeon.scenes.改动界面;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.MobSprite;
import com.watabou.noosa.Image;
import com.watabou.noosa.ui.Component;

//not actually a button, but functions as one.
public class ChangeButton extends Component {
	
	protected Image icon;
	protected CharSprite charsprite;
	protected MobSprite mobsprite;
	protected String title;
	protected String[] messages;
	
	public ChangeButton( Item item,String... messages){
		super();
		
		this.icon = new ItemSprite(item.image);
		add(this.icon);
		
		this.title = Messages.titleCase(item.name());
		this.messages =new String[]{item.desc()+messages};
		
		layout();
	}
	public ChangeButton(Mob mob,String... messages){
		super();
		
		this.icon = mob.sprite();
		add(this.icon);
		
		this.title = Messages.titleCase(mob.name());
		this.messages =new String[]{mob.description()+messages};
		
		layout();
	}
	public ChangeButton( Image icon, String title, String... messages){
		super();
		
		this.icon = icon;
		add(this.icon);
		
		this.title = Messages.titleCase(title);
		this.messages = messages;
		
		layout();
	}
	public ChangeButton(CharSprite charsprite, String title, String... messages){
		super();

		this.charsprite = charsprite;
		add(this.charsprite);

		this.title = Messages.titleCase(title);
		this.messages = messages;

		layout();
	}
	public ChangeButton(MobSprite mobsprite, String title, String... messages){
		super();

		this.mobsprite = mobsprite;
		add(this.mobsprite);

		this.title = Messages.titleCase(title);
		this.messages = messages;

		layout();
	}
	
	public ChangeButton(Item item, String message ){
		this( new ItemSprite(item), item.name(), message);
	}
	
	protected void onClick() {
		if(icon!=null) {
			改动界面.showChangeInfo(new Image(icon),title,messages);
		}

		if(charsprite!=null) {
			改动界面.showChangeInfo(new Image(charsprite), title, messages);
		}

		if(mobsprite!=null) {
			改动界面.showChangeInfo(new Image(mobsprite), title, messages);
		}
	}
	
	@Override
	protected void layout() {
		super.layout();
		if(icon!=null) {
			icon.x = x + (width - icon.width()) / 2f;
			icon.y = y + (height - icon.height()) / 2f;
			PixelScene.align(icon);
		}

		if(charsprite!=null) {
			charsprite.x = x + (width - charsprite.width()) / 2f;
			charsprite.y = y + (height - charsprite.height()) / 2f;
			PixelScene.align(charsprite);
		}

		if(mobsprite!=null) {
			mobsprite.x = x + (width - mobsprite.width()) / 2f;
			mobsprite.y = y + (height - mobsprite.height()) / 2f;
			PixelScene.align(mobsprite);
		}
	}
}
