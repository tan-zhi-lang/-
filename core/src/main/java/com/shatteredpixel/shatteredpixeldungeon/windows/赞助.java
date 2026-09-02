package com.shatteredpixel.shatteredpixeldungeon.windows;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.shatteredpixel.shatteredpixeldungeon.Assets;
import com.shatteredpixel.shatteredpixeldungeon.ShatteredPixelDungeon;
import com.shatteredpixel.shatteredpixeldungeon.ui.RedButton;
import com.shatteredpixel.shatteredpixeldungeon.ui.Window;
import com.watabou.gltextures.SmartTexture;
import com.watabou.noosa.Image;

public class 赞助 extends Window{


	private static final int WIDTH_P = 120;

	private static final int MARGIN  = 2;
	static  赞助 INSTANCE;

	//XOR 加密用 32 字节滚动密钥（与生成 赞助.bin 时使用的密钥完全一致）
	private static final byte[] 密钥 = {
		(byte)0x5A,(byte)0xA5,(byte)0x3C,(byte)0xC3,(byte)0x7E,(byte)0xE7,(byte)0x14,(byte)0x41,
		(byte)0xB6,(byte)0x6B,(byte)0xD2,(byte)0x2D,(byte)0x89,(byte)0x98,(byte)0x0F,(byte)0xF0,
		(byte)0x55,(byte)0xAA,(byte)0x33,(byte)0xCC,(byte)0x77,(byte)0xEE,(byte)0x11,(byte)0x11,
		(byte)0xBE,(byte)0xEB,(byte)0xD0,(byte)0x0D,(byte)0x80,(byte)0x7F,(byte)0x06,(byte)0xF9
	};

	//PNG 文件头 8 字节（\x89PNG\r\n\x1a\n），解密后必须匹配，不匹配视为图片被篡改
	private static final byte[] PNG_MAGIC = {
		(byte)0x89, (byte)0x50, (byte)0x4E, (byte)0x47,
		(byte)0x0D, (byte)0x0A, (byte)0x1A, (byte)0x0A
	};

	//从加密 .bin 加载并解密为 Pixmap（破坏者无法替换图片冒用：替换 .png 无效，伪造 .bin 也无法通过 PNG 头校验）
	private static Pixmap 解密赞助() {
		try {
			byte[] enc = Gdx.files.internal(Assets.赞助).readBytes();
			byte[] dec = new byte[enc.length];
			for (int i = 0; i < enc.length; i++) {
				//加密算法：b ^ KEY[i % 32] ^ ((i * 0x9E3779B1) & 0xFF)
				int k = 密钥[i % 密钥.length] & 0xFF;
				int p = (int)((i * 0x9E3779B1L) & 0xFF);
				dec[i] = (byte)((enc[i] ^ k ^ p) & 0xFF);
			}
			//校验 PNG magic；不是合法 PNG 直接抛错（说明 .bin 被篡改或密钥错配）
			if (dec.length < 8) throw new RuntimeException("赞助图片解密结果过短");
			for (int i = 0; i < 8; i++) {
				if (dec[i] != PNG_MAGIC[i]) {
					throw new RuntimeException("赞助图片校验失败（疑似被篡改）");
				}
			}
			return new Pixmap(dec, 0, dec.length);
		} catch (Exception e) {
			ShatteredPixelDungeon.reportException(e);
			return null;
		}
	}

	public 赞助(){
		super();
		INSTANCE=this;

		int width = WIDTH_P;

		float pos = MARGIN;

		Image background;
		Pixmap pm = 解密赞助();
		if (pm != null) {
			background = new Image(new SmartTexture(pm));
		} else {
			//解密失败时回退到空 Image，避免窗口崩溃；贴图缺失的提示由 reportException 上报
			background = new Image();
		}
		background.scale.set(0.09f);
		background.x=(width-background.width())/2;
		background.y=pos;
		add(background);
		pos=background.height()+MARGIN+3;


		RedButton 确定=new RedButton("自行截图二维码并微信扫码",6){
			@Override
			protected void onClick(){
				super.onClick();

				hide();
			}
		};
		确定.leftJustify=true;
		确定.multiline=true;
		确定.setSize(width,确定.reqHeight());
		确定.setRect(0,pos,width*4/5f,确定.reqHeight()+6);
		add(确定);

		RedButton 取消=new RedButton("取消",6){
			@Override
			protected void onClick(){
				super.onClick();

				hide();
			}
		};
		取消.leftJustify=true;
		取消.multiline=true;
		取消.setSize(width,取消.reqHeight());
		取消.setRect(确定.width()+1,pos,width*1f/5f,取消.reqHeight()+6);
		add(取消);
		pos=取消.bottom()+MARGIN;

		resize(width, (int)pos);

	}
	@Override
	public void onBackPressed() {

	}
}
