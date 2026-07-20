

package com.shatteredpixel.shatteredpixeldungeon.ui.changelist;

import static com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero.tier;
import static com.shatteredpixel.shatteredpixeldungeon.sprites.HeroSprite.avatar;

import com.shatteredpixel.shatteredpixeldungeon.actors.hero.HeroClass;
import com.shatteredpixel.shatteredpixeldungeon.actors.mobs.Mob;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.shatteredpixel.shatteredpixeldungeon.items.rings.Ring;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.shatteredpixel.shatteredpixeldungeon.scenes.改动界面;
import com.shatteredpixel.shatteredpixeldungeon.sprites.CharSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSprite;
import com.shatteredpixel.shatteredpixeldungeon.sprites.MobSprite;
import com.watabou.noosa.Image;
import com.watabou.noosa.RenderedText;
import com.watabou.noosa.ui.Component;

//not actually a button, but functions as one.
public class ChangeButton extends Component {
	
	protected Image icon;
	protected CharSprite charsprite;
	protected MobSprite mobsprite;
	protected String title;
	protected String[] messages;
	
	public ChangeButton( Item item){
		super();

		item.鉴定();

		this.icon = new ItemSprite(item.image);
		add(this.icon);
		
		this.title = Messages.titleCase(item.name());
		if(item instanceof Ring r)
			this.messages =new String[]{r.statsInfo()};
		else
			this.messages =new String[]{item.info()};
		
		layout();
	}
	public ChangeButton( Item item, String messages){
		super();

		item.鉴定();

		this.icon = new ItemSprite(item.image);
		add(this.icon);

		this.title = Messages.titleCase(item.name());

		if(item instanceof Ring r)
			this.messages =new String[]{messages,r.statsInfo()};
		else
			this.messages =new String[]{messages,item.info()};

		layout();
	}
	public ChangeButton(HeroClass heroClass){
		super();
		this.icon = new Image(avatar(heroClass,tier(heroClass)));
		add(this.icon);

		this.title = Messages.titleCase(heroClass.name());
		this.messages =new String[]{heroClass.unlockMsg(),heroClass.desc()};

		layout();
	}
	public ChangeButton(Mob mob, String... messages){
		super();
		
		this.icon = mob.sprite();
		add(this.icon);
		
		this.title = Messages.titleCase(mob.name());
		this.messages = messages;
		
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

	public ChangeButton( String it, String title, String... messages){
		super();
		if(it.equals(""))
			this.icon = new RenderedText(title,32).scale(1f/(title.length()+1));
		else
			this.icon = new RenderedText(it,32).scale(1f/(it.length()+1));

		add(this.icon);

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
