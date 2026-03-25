package com.shatteredpixel.shatteredpixeldungeon.actors.hero;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.items.Item;
import com.watabou.utils.Bundle;

public class 海克斯 extends Item{
	public 海克斯(){
	}
	public 海克斯(String 名字,int 等级,int 权重){
		this.名字=名字;
		this.等级=等级;
		this.权重=权重;
	}
	public float 权重(){
		float 权重=this.权重;
		float 保底=1/12f;
		boolean 是保底=false;

		if(Dungeon.hero()){
			Hero hero=Dungeon.hero;
			if(等级==1)
			保底*=Dungeon.hero.海克斯表1.size();
			if(等级==2)
			保底*=Dungeon.hero.海克斯表2.size();
			if(等级==3)
			保底*=Dungeon.hero.海克斯表3.size();

			if(Dungeon.符文("潘多拉魔盒"))
			if(名字.equals("没啥用1")||
			   名字.equals("没啥用2")||
			   名字.equals("没啥用3")||
			   名字.equals("没啥用4")||
			   名字.equals("没啥用5")||
			   名字.equals("没啥用6")||
			   名字.equals("没啥用7")||
			   名字.equals("没啥用8")||
			   名字.equals("没啥用9")||
			   名字.equals("没啥用10"))是保底=true;

			if(Dungeon.符文("豪赌"))
			if(名字.equals("没卵用1")||
			   名字.equals("没卵用2")||
			   名字.equals("没卵用3")||
			   名字.equals("没卵用4")||
			   名字.equals("没卵用5"))是保底=true;

//			if(hero.belongings.hasItem(灵能短弓.class)&&名字.equals("升级灵能短弓"))是保底=true;
		}
		if(是保底){
			if(权重==0)
			权重=2;

			权重*=保底;
		}
		if(Dungeon.符文(名字))权重=0;

		return 权重;
	}

	public String 名字="";
	public String 描述="";
	public int 等级=1;
	public float  权重=1;
	private static final String 名字x		= "名字";
	private static final String 描述x		= "描述";
	private static final String 等级x		= "等级";
	private static final String 权重x		= "权重";
	@Override
	public void storeInBundle(Bundle bundle){
		bundle.put( 名字x, 名字 );
		bundle.put( 描述x, 描述 );
		bundle.put( 等级x, 等级 );
		bundle.put( 权重x, 权重 );

	}
	@Override
	public void restoreFromBundle(Bundle bundle){
		名字= bundle.getString( 名字x );
		描述= bundle.getString( 描述x );
		等级= bundle.getInt( 等级x );
		权重= bundle.getFloat( 权重x );

	}

}
