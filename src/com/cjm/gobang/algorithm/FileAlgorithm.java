package com.cjm.gobang.algorithm;

import java.io.FileNotFoundException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.io.Reader;
import java.io.Writer;

import javax.swing.JOptionPane;

public class FileAlgorithm {

	public static void saveFileByChar(String path, String content) {
		try {
			Writer w = new FileWriter(path);
			w.write(content);
			w.close();
		} catch (IOException e) {
			e.getMessage();
		}
	}

	public static String readFileByChar(String path) {
		String str = "";
		try {
			char[] chars = new char[1024];
			int l = 0;
			Reader r = new FileReader(path);
			while ((l = r.read(chars)) != -1) {
				str += new String(chars, 0, l);
			}
			r.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "数据文件缺失！请向管理员询问详情！\n管理员QQ：550708427");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return str;
	}
}
