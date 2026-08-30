package com.shatteredpixel.shatteredpixeldungeon.android;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.shatteredpixel.shatteredpixeldungeon.算法;

import cat.ereza.customactivityoncrash.CustomActivityOnCrash;
import cat.ereza.customactivityoncrash.config.CaocConfig;

public class CustomErrorActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		final CaocConfig config = CustomActivityOnCrash.getConfigFromIntent(getIntent());

		String stackTrace = CustomActivityOnCrash.getStackTraceFromIntent(getIntent());
		if (stackTrace == null) {
			stackTrace = "未知异常";
		}

		//所有包名一律裁掉，只留简约类名（小写包名段+点，后面跟大写/中文类名）
		stackTrace = stackTrace.replaceAll("(?<![\\w$])(?:[a-z_]\\w*\\.)+(?=[A-Z一-鿿])", "");
		//at 改为中文
		stackTrace = stackTrace.replaceAll("(?m)^\\s*at ", "有关: ");

		//日志开头加版本日期标记
		stackTrace = "版本日期: " + 算法.日期() + "\n\n" + stackTrace;
		final String report = stackTrace;

		int pad = dp(16);

		LinearLayout root = new LinearLayout(this);
		root.setOrientation(LinearLayout.VERTICAL);
		root.setPadding(pad, pad, pad, pad);
		root.setBackgroundColor(Color.BLACK);

		TextView title = new TextView(this);
		title.setText("游戏崩溃了");
		title.setTextSize(20);
		title.setTextColor(Color.WHITE);
		root.addView(title);

		TextView tip = new TextView(this);
		tip.setText("请复制下面的崩溃日志，说清楚是怎么崩溃的，\n发给游戏作者Tan（Q群335966124）。\n崩溃日志与实际代码的位置就像高德地图导航一样会偏移。");
		tip.setTextColor(Color.LTGRAY);
		tip.setPadding(0, dp(8), 0, dp(8));
		root.addView(tip);

		ScrollView scroll = new ScrollView(this);
		TextView log = new TextView(this);
		log.setText(report);
		log.setTextColor(Color.WHITE);
		log.setTextSize(12);
		log.setTypeface(Typeface.MONOSPACE);
		log.setTextIsSelectable(true);
		scroll.addView(log);
		LinearLayout.LayoutParams scrollParams = new LinearLayout.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT, 0, 1f);
		root.addView(scroll, scrollParams);

		LinearLayout buttons = new LinearLayout(this);
		buttons.setOrientation(LinearLayout.HORIZONTAL);

		Button copy = new Button(this);
		copy.setText("复制到剪贴板");
		copy.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
				if (cm != null) {
					cm.setPrimaryClip(ClipData.newPlainText("崩溃日志", report));
					Toast.makeText(CustomErrorActivity.this, "复制成功", Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(CustomErrorActivity.this, "复制失败", Toast.LENGTH_SHORT).show();
				}
			}
		});
		buttons.addView(copy, new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f));

		Button restart = new Button(this);
		restart.setText("重启应用");
		restart.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				CustomActivityOnCrash.restartApplication(CustomErrorActivity.this, config);
			}
		});
		buttons.addView(restart, new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f));

		Button close = new Button(this);
		close.setText("关闭应用");
		close.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				CustomActivityOnCrash.closeApplication(CustomErrorActivity.this, config);
			}
		});
		buttons.addView(close, new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f));

		root.addView(buttons);
		setContentView(root);
	}

	private int dp(float d) {
		return (int) (d * getResources().getDisplayMetrics().density + 0.5f);
	}
}
