package com.shatteredpixel.shatteredpixeldungeon.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.shatteredpixel.shatteredpixeldungeon.GamesInProgress;
import com.shatteredpixel.shatteredpixeldungeon.ShatteredPixelDungeon;
import com.watabou.utils.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

//存档导出/导入工具：导出/导入共用目录（按平台不同位置）
public class 存档工具 {

	//导出/导入共用目录名（按平台分流）
	//- Desktop：实际写到 用户主目录/缝合像素地牢/当前存档/（玩家能在文件管理器看到）
	//- Android：实际写到 Context.getExternalFilesDir(null)/当前存档/（scoped storage 标准位置）
	private static final String 桌面端目录 = "缝合像素地牢/当前存档";
	private static final String 移动端目录 = "当前存档";

	//当前平台的导出目录路径
	private static String 当前目录(){
		//Android 平台：Gdx.files.external 实际指向 Context.getExternalFilesDir(null)
		//通过判断是否存在 /Android/data/ 子串来识别 Android 平台
		String probe = Gdx.files.external(移动端目录).file().getAbsolutePath().replace('\\','/');
		boolean isAndroid = probe.contains("/Android/data/") || probe.contains("/data/data/");
		return isAndroid ? 移动端目录 : 桌面端目录;
	}

	//导出根目录：
	//- Desktop：Gdx.files.external = 用户主目录/缝合像素地牢/当前存档
	//- Android：Context.getExternalFilesDir(null)/当前存档
	//- 外存不可用时回退到 Gdx.files.local
	public static File 导出根目录(){
		String dir = 当前目录();
		try {
			FileHandle ext = Gdx.files.external(dir);
			if (!ext.exists()) ext.mkdirs();
			if (ext.exists() && ext.isDirectory()) return ext.file();
		} catch (Throwable ignored) {}
		return Gdx.files.local(dir).file();
	}

	//导出当前槽位存档到"当前存档"目录（覆盖旧内容），成功返回该文件夹
	public static File 导出(){
		try {
			int slot = GamesInProgress.curSlot;
			if (slot <= 0) return null;

			File src = FileUtils.getFileHandle(GamesInProgress.gameFolder(slot)).file();
			if (src == null || !src.isDirectory()) return null;

			File dst = 导出根目录();
			if (dst.exists()) {
				//清空旧内容（保留回溯/回档快照），再写入新内容
				for (File f : 文件列表(dst)) f.delete();
			} else if (!dst.mkdirs()) {
				return null;
			}

			int count = 0;
			for (File f : 文件列表(src)) {
				if (复制(f, new File(dst, f.getName()))) count++;
			}
			if (count == 0) return null;
			return dst;
		} catch (Exception e) {
			ShatteredPixelDungeon.reportException(e);
			return null;
		}
	}

	//从"当前存档"目录读取存档到当前槽位，成功返回true
	//注意：仅应在死亡后（存档已被删）或返回标题前导入；活着时导入会被当前局的下一次保存/死亡覆盖
	//关键约束：Android 14 (targetSdk 34) 沙箱禁止应用通过 new File("/storage/emulated/0/...") 读取公共目录，
	//即使有 READ_EXTERNAL_STORAGE 也只能读 media 文件。
	//本项目只能走 libGDX 封装好的 Context.getExternalFilesDir / getFilesDir 两个路径，玩家用文件管理器（开启"显示 Android/data"）把 game.dat 复制到 Android/data/<pkg>/files/当前存档/ 即可。
	public static boolean 导入(){
		try {
			int slot = GamesInProgress.curSlot;
			if (slot <= 0) slot = GamesInProgress.firstEmpty();
			if (slot <= 0) return false;

			File src = 定位导入目录();
			if (src == null) return false;

			File dst = FileUtils.getFileHandle(GamesInProgress.gameFolder(slot)).file();
			if (dst == null) return false;
			if (!dst.exists() && !dst.mkdirs()) return false;

			//第一步：先把导出文件全部复制为 .imp.tmp，任何失败都不动现有存档
			for (File f : 文件列表(src)) {
				if (!复制(f, new File(dst, f.getName() + ".imp.tmp"))) {
					清理临时文件(dst);
					return false;
				}
			}
			//第二步：清掉当前槽位旧文件（快照目录与刚生成的临时文件除外）
			for (File f : 文件列表(dst)) {
				if (f.getName().endsWith(".imp.tmp")) continue;
				f.delete();
			}
			//第三步：把临时文件改名生效
			for (File f : 文件列表(dst)) {
				if (!f.renameTo(new File(dst, f.getName().replace(".imp.tmp", "")))) {
					清理临时文件(dst);
					return false;
				}
			}
			//第四步：校验导入后的 game.dat 是真实存档而不是占位
			if (FileUtils.fileLength(GamesInProgress.gameFile(slot)) <= 100) {
				return false;
			}

			GamesInProgress.setUnknown(slot);
			return true;
		} catch (Exception e) {
			ShatteredPixelDungeon.reportException(e);
			return false;
		}
	}

	//删除目录下所有 .imp.tmp 临时文件
	private static void 清理临时文件(File dir){
		for (File f : 文件列表(dir)) {
			if (f.getName().endsWith(".imp.tmp")) f.delete();
		}
	}

	//按以下顺序查找"当前存档"目录（与导出写入同一位置），返回第一个含 game.dat 的目录：
	//1) 应用专属外部（Context.getExternalFilesDir(null)/当前存档）：Gdx.files.external
	//2) 应用内部存储（Context.getFilesDir/当前存档）：Gdx.files.local
	//两个路径都是 libGDX 封装好的 Context API，不会被 Android 14 (targetSdk 34) 沙箱拒绝
	private static File 定位导入目录(){
		String dir = 当前目录();
		//1) 应用专属外部
		File f = 导出根目录();
		if (是有效导入目录(f)) return f;
		//2) 应用内部存储回退
		f = Gdx.files.local(dir).file();
		if (是有效导入目录(f)) return f;
		return null;
	}

	private static boolean 是有效导入目录(File f){
		return f != null && f.isDirectory() && new File(f, "game.dat").isFile();
	}

	//列出目录下的文件与子目录，排除回溯/回档快照
	private static ArrayList<File> 文件列表(File dir){
		ArrayList<File> result = new ArrayList<>();
		File[] files = dir.listFiles();
		if (files == null) return result;
		for (File f : files) {
			if (f.getName().startsWith("海克斯回档") || f.getName().startsWith("回档")) continue;
			result.add(f);
		}
		return result;
	}

	//将绝对路径裁剪为用户友好的相对路径（玩家能照着在文件管理器里找到）：
	//- Android 外部: /storage/emulated/0/Android/data/<pkg>/files/当前存档 → Android/data/<pkg>/files/当前存档
	//- Android 内部: /data/data/<pkg>/files/当前存档 → Android/data/<pkg>/files/当前存档
	//- Desktop: C:\Users\<user>\缝合像素地牢\当前存档 → C:/Users/<user>/缝合像素地牢/当前存档
	public static String 友好路径(String absolutePath){
		if (absolutePath == null || absolutePath.isEmpty()) return "";
		String p = absolutePath.replace('\\','/');
		//Android 应用专属外部：保留 Android/data/<pkg>/files/当前存档
		int idx = p.indexOf("/Android/data/");
		if (idx >= 0){
			int filesIdx = p.indexOf("/files/", idx);
			if (filesIdx >= 0){
				String pkg = p.substring(idx + "/Android/data/".length(), filesIdx);
				return "Android/data/" + pkg + "/files/当前存档";
			}
		}
		//Android 内部存储回退：/data/data/<pkg>/files/...
		idx = p.indexOf("/data/data/");
		if (idx >= 0){
			int slash = p.indexOf('/', idx + "/data/data/".length());
			if (slash >= 0){
				String pkg = p.substring(idx + "/data/data/".length(), slash);
				return "Android/data/" + pkg + "/files/当前存档";
			}
		}
		//Desktop：保留盘符+用户主目录前缀（C:/Users/<user>/、/Users/<user>/、/home/<user>/）
		int spdIdx = p.indexOf("缝合像素地牢/当前存档");
		if (spdIdx >= 0){
			int userHome = -1;
			int winIdx = p.lastIndexOf(":/Users/", spdIdx);
			if (winIdx >= 0) userHome = winIdx - 1;  // Windows: 往前取 1 个字符保留 "C:/..."
			else {
				int macIdx = p.lastIndexOf("/Users/", spdIdx);
				if (macIdx >= 0) userHome = macIdx;
				else {
					int linuxIdx = p.lastIndexOf("/home/", spdIdx);
					if (linuxIdx >= 0) userHome = linuxIdx;
				}
			}
			if (userHome >= 0){
				return p.substring(userHome, spdIdx + "缝合像素地牢/当前存档".length());
			}
			return p.substring(0, spdIdx + "缝合像素地牢/当前存档".length());
		}
		return "缝合像素地牢/当前存档";
	}

	private static boolean 复制(File src, File dst){
		try (FileInputStream in = new FileInputStream(src);
		     FileOutputStream out = new FileOutputStream(dst)) {
			byte[] buf = new byte[8192];
			int n;
			while ((n = in.read(buf)) > 0) out.write(buf, 0, n);
			return true;
		} catch (Exception e) {
			ShatteredPixelDungeon.reportException(e);
			return false;
		}
	}
}