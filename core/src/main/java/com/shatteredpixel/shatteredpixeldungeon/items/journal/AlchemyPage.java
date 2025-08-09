

package com.shatteredpixel.shatteredpixeldungeon.items.journal;

import com.shatteredpixel.shatteredpixeldungeon.journal.Document;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.sprites.物品表;

public class AlchemyPage extends DocumentPage {
	
	{
		image = 物品表.ALCH_PAGE;
	}
	
	@Override
	public Document document() {
		return Document.ALCHEMY_GUIDE;
	}
	
	@Override
	public String desc() {
		return Messages.get(this, "desc", document().pageTitle(page()));
	}
}
