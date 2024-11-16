package com.gourab.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileHandler {

	public static String getFileExtension(String fileName) {
		String[] temp = fileName.split("\\.");
		return temp[temp.length - 1];
	}

	public static boolean saveFile(InputStream inputStream, String outputFileName)
			throws FileNotFoundException, IOException {
		try (BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
				BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
						new FileOutputStream(outputFileName))) {
			byte[] bytes = new byte[8192];
			int bytesRead;
			while ((bytesRead = bufferedInputStream.read(bytes)) != -1)
				bufferedOutputStream.write(bytes, 0, bytesRead);
			return true;
		}
	}

	public static boolean deleteFile(String path) {
		File file = new File(path);
		return file.delete();
	}
}
