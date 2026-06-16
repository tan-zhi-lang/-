

package com.shatteredpixel.shatteredpixeldungeon.ui;

import com.shatteredpixel.shatteredpixeldungeon.SPDSettings;
import com.shatteredpixel.shatteredpixeldungeon.scenes.PixelScene;
import com.shatteredpixel.shatteredpixeldungeon.utils.GLog;
import com.watabou.noosa.ui.Component;
import com.watabou.utils.Signal;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class GameLog extends Component implements Signal.Listener<String> {

	private static final int MAX_LINES = 3;

	private static final Pattern PUNCTUATION = Pattern.compile( ".*[.,;?! ]$" );

	private RenderedTextBlock lastEntry;
	private int lastColor;

	private static ArrayList<Entry> entries = new ArrayList<>();

	public GameLog() {
		super();
		GLog.update.replace( this );

		recreateLines();
	}
	
	private static ArrayList<String> textsToAdd = new ArrayList<>();
	
	@Override
	public synchronized void update() {

		synchronized (textsToAdd){
			if (!textsToAdd.isEmpty()){
				int maxLines = SPDSettings.interfaceSize() > 0 ? 5 : 3;//提示文本行
				maxLines*=4;
				for (String text : textsToAdd){
					if (length != entries.size()){
						clear();
						recreateLines();
					}

					if (text.equals( GLog.NEW_LINE )){
						lastEntry = null;
						continue;
					}

					int color = 0x00FF00;
					if (text.startsWith( GLog.绿色)) {
						text = text.substring( GLog.绿色.length());
						color = 0x00FF00;
					} else if (text.startsWith( GLog.红色)) {
						text = text.substring( GLog.红色.length());
						color = 0xFF4444;
					} else if (text.startsWith( GLog.橙色)) {
						text = text.substring( GLog.橙色.length());
						color = 0xFF8800;
					} else if (text.startsWith( GLog.黄色)) {
						text = text.substring( GLog.黄色.length());
						color = 0xFFFF00;
					} else if (text.startsWith( GLog.蓝色)) {
						text = text.substring( GLog.蓝色.length());
						color = 0x3399FF;
					} else if (text.startsWith( GLog.粉色)) {
						text = text.substring( GLog.粉色.length());
						color = 0xFF4488;
					} else if (text.startsWith( GLog.紫色)) {
						text = text.substring( GLog.紫色.length());
						color = 0x8800FF;
					} else if (text.startsWith( GLog.灰色)) {
						text = text.substring( GLog.灰色.length());
						color = 0x999999;
					} else if (text.startsWith( GLog.黑色)) {
						text = text.substring( GLog.黑色.length());
						color = 0x000000;
					} else if (text.startsWith( GLog.棕色)) {
						text = text.substring( GLog.棕色.length());
						color = 0x8F4E35;
					} else if (text.startsWith( GLog.青色)) {
						text = text.substring( GLog.青色.length());
						color = 0xb2f2ff;
					} else if (text.startsWith( GLog.靛色)) {
						text = text.substring( GLog.靛色.length());
						color = 0x2c0d49;
					}

					if (lastEntry != null && color == lastColor && lastEntry.nLines < maxLines) {

						String lastMessage = lastEntry.text();
						lastEntry.text( lastMessage.length() == 0 ? text : lastMessage + " " + text );
//						lastEntry.hardlight( color );//
						entries.get( entries.size() - 1 ).text = lastEntry.text();
//						entries.get( entries.size() - 1 ).color = color;//

					} else {

						lastEntry = PixelScene.renderTextBlock( text, 6 );
						lastEntry.hardlight( color );
						lastColor = color;
						add( lastEntry );

						entries.add( new Entry( text, color ) );

					}

					if (length > 0) {
						int nLines;
						do {
							nLines = 0;
							for (int i = 0; i < length-1; i++) {
								nLines += ((RenderedTextBlock) members.get(i)).nLines;
							}

							if (nLines > maxLines) {
								RenderedTextBlock r = ((RenderedTextBlock) members.get(0));
								remove(r);
								r.destroy();

								entries.remove( 0 );
							}
						} while (nLines > maxLines);
						if (entries.isEmpty()) {
							lastEntry = null;
						}
					}
				}

				layout();
				textsToAdd.clear();
			}
		}
		super.update();
	}
	
	private synchronized void recreateLines() {
		for (Entry entry : entries) {
			lastEntry = PixelScene.renderTextBlock( entry.text, 6 );
			lastEntry.hardlight( lastColor = entry.color );
			add( lastEntry );
		}
	}

	public synchronized void newLine() {
		lastEntry = null;
	}

	@Override
	public boolean onSignal( String text ) {
		synchronized (textsToAdd) {
			textsToAdd.add(text);
		}
		return false;
	}

	@Override
	protected void layout() {
		float pos = y;
		for (int i=length-1; i >= 0; i--) {
			RenderedTextBlock entry = (RenderedTextBlock)members.get( i );
			entry.setHightlighting(false);
			entry.maxWidth((int)width);
			entry.setPos(x, pos-entry.height());
			pos -= entry.height()+2;
		}
	}

	private static class Entry {
		public String text;
		public int color;
		public Entry( String text, int color ) {
			this.text = text;
			this.color = color;
		}
	}

	public static void wipe() {
		synchronized (textsToAdd) {
			entries.clear();
			textsToAdd.clear();
		}
	}
}
