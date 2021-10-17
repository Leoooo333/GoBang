package com.bpf.gobang.algorithm;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

public class FileAlgorithm {
	/**
	 * 此方法创建文件
	 * 
	 * @param path
	 *            字符串 路径
	 */
	public static void createDiectory(String path) {
		File file = new File(path);
		file.mkdir();
	}

	/**
	 * 
	 * 此方法创建文件夹
	 * 
	 * @param path
	 *            字符串 路径
	 * @param name
	 *            字符串 文件名
	 * @return 创建成功返回true 创建失败返回false
	 */
	public static Boolean createFile(String path, String name) {
		Boolean flag = false;
		File file = new File(path + "\\" + name);
		try {
			file.createNewFile();
			flag = true;
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return flag;
	}

	/**
	 * 通过字节流写文件
	 * 
	 * @param path
	 *            字符串 路径
	 * @param content
	 *            字符串 保存要写入的内容
	 * @return 字符串 异常信息
	 */
	public static String saveFile(String path, String content) {
		String message = "";
		OutputStream os = null;
		try {
			os = new FileOutputStream(path);
			os.write(content.getBytes());
		} catch (FileNotFoundException e) {
			message = e.getMessage();
		} catch (IOException e) {
			message = e.getMessage();
		} finally {
			try {
				os.close();
			} catch (IOException e) {
				message = e.getMessage();
			}
		}
		return message;
	}

	/**
	 * 通过字节流读文件
	 * 
	 * @param path
	 *            字符串 路径
	 * @return 文件内容
	 */
	public static String readFile(String path) {
		String str = "";
		try {
			InputStream is = new FileInputStream(path);
			byte[] bytes = new byte[1024];
			int len = 0;
			while ((len = is.read(bytes)) != -1) {
				str = new String(bytes, 0, len);
			}
			is.close();
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return str;
	}

	/**
	 * 通过字符写入
	 * 
	 * @param path
	 *            字符串 路径
	 * @param content
	 *            字符串 写入内容
	 */
	public static void saveFileByChar(String path, String content) {
		try {
			Writer w = new FileWriter(path);
			w.write(content);
			w.close();
		} catch (IOException e) {
			e.getMessage();
		}
	}

	/**
	 * 通过字符流读取
	 * 
	 * @param path
	 *            字符串 路径
	 * @return 读取的信息
	 */
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
		} catch (IOException e) {
			e.printStackTrace();
		}
		return str;
	}
	
	public static void writeFileByObject(String path, Object obj) {
	      try
	      {
	         FileOutputStream fileOut = new FileOutputStream(path);
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(obj);
	         out.close();
	         fileOut.close();
	      }catch(IOException i)
	      {
	          i.printStackTrace();
	      }
	}
	
	public static Object readFileByObject(String path) {
	      Object obj = null;
	      try
	      {
	         FileInputStream fileIn = new FileInputStream("/tmp/employee.ser");
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         obj = (Object)in.readObject();
	         in.close();
	         fileIn.close();
	      }catch(IOException i)
	      {
	         i.printStackTrace();
	      }catch(ClassNotFoundException c)
	      {
	         System.out.println("Employee class not found");
	         c.printStackTrace();
	      }
	      return obj;
	}
}
