package com.shatteredpixel.shatteredpixeldungeon;

import com.badlogic.gdx.Gdx;

import java.net.URLEncoder;

/**
 * 邮件发送工具类
 */
public class 邮件系统{

	public static void 发送邮件(String body) {
		try {
			// 1. 直接使用字符串"UTF-8"替代StandardCharsets.UTF_8（适配API 16）
			String encodedSubject = URLEncoder.encode("【游戏反馈】来自玩家的问题/建议", "UTF-8");
			String encodedBody = URLEncoder.encode("请在这里描述你的问题或建议："+body, "UTF-8");

			// 3. 拼接mailto协议链接
			String mailtoUri = "mailto:" + "tzj944313630@163.com" +
							   "?subject=" + encodedSubject +
							   "&body="+encodedBody;

			// 3. 调用系统默认邮件客户端
			Gdx.net.openURI(mailtoUri);

		} catch (Exception e) {
			// 处理异常（如设备无邮件客户端）
//			Gdx.app.error("邮箱", "未能打开邮箱", e);
			// 可提示玩家："未检测到邮件客户端，请手动发送邮件至 xxx@xxx.com"
		}
	}
}