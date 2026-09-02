package com.shatteredpixel.shatteredpixeldungeon.utils;

import com.shatteredpixel.shatteredpixeldungeon.GamesInProgress;
import com.shatteredpixel.shatteredpixeldungeon.ShatteredPixelDungeon;
import com.watabou.utils.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

//存档导出/导入工具：把当前槽位存档（game.dat + depth*.dat）复制到 用户目录/SPD存档导出/
public class 存档工具 {

	//导出根目录：用户主目录下 SPD存档导出（Android 上为应用私有外部存储下的同名目录）
	public static File 导出根目录(){
		return new File(System.getProperty("user.home"), "SPD存档导出");
	}

	//导出当前槽位存档到带时间戳的子文件夹，成功返回该文件夹
	public static File 导出(){
		try {
			int slot = GamesInProgress.curSlot;
			if (slot <= 0) return null;

			File src = FileUtils.getFileHandle(GamesInProgress.gameFolder(slot)).file();
			if (src == null || !src.isDirectory()) return null;

			String time = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
			File dst = new File(导出根目录(), "slot" + slot + "_" + time);
			if (!dst.exists() && !dst.mkdirs()) return null;

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

	//导入最近一次导出的存档到当前槽位，成功返回true
	//注意：仅应在死亡后（存档已被删）或返回标题前导入；活着时导入会被当前局的下一次保存/死亡覆盖
	public static boolean 导入(){
		try {
			int slot = GamesInProgress.curSlot;
			if (slot <= 0) slot = GamesInProgress.firstEmpty();
			if (slot <= 0) return false;

			File root = 导出根目录();
			File[] subs = root.listFiles(File::isDirectory);
			if (subs == null || subs.length == 0) return false;

			//按修改时间取最新的导出文件夹
			Arrays.sort(subs, (a, b) -> Long.compare(b.lastModified(), a.lastModified()));
			File src = subs[0];

			//导出内容必须含 game.dat
			if (!new File(src, "game.dat").isFile()) return false;

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

	//列出目录下的文件与子目录，排除回溯/回档快照
	private static ArrayList<File> 文件列表(File dir){
		ArrayList<File> result = new ArrayList<>();
		File[] files = dir.listFiles();
		if (files == null) return result;
		for (File f : files) {
			if (f.getName().startsWith("回溯") || f.getName().startsWith("回档")) continue;
			result.add(f);
		}
		return result;
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
