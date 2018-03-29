// Copyright - Znalytics (http://www.Znalytics.com/)

package com.znalytics.framework.utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.DecimalFormat;
import java.util.Set;
import java.util.logging.Level;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import com.znalytics.framework.core.Logs;

/**
 * The Class FileIO.
 *
 * @author: Nikesh Jauhari
 * @mail: najuahri@znalytics.com
 * @date: Mar 4, 2015
 */
@SuppressWarnings("deprecation")
public class FileIO {

	/** The driver. */
	private WebDriver driver;

	/**
	 * The Enum Attributes.
	 */
	public enum Attributes {

		/** The creationtime. */
		CREATIONTIME,
		/** The lastaccesstime. */
		LASTACCESSTIME,
		/** The lastmodifiedtime. */
		LASTMODIFIEDTIME,
		/** The isregularfile. */
		ISREGULARFILE,
		/** The isdirectory. */
		ISDIRECTORY,
		/** The filesize. */
		FILESIZE
	}

	/**
	 * Delete file.
	 *
	 * @param filePath
	 *            the file path
	 * @return true, if successful
	 */
	public static boolean deleteFile(String filePath) {
		File file = new File(filePath);
		return (file.delete());

	}

	/**
	 * Delete directory.
	 *
	 * @param path
	 *            the path
	 * @return true, if successful
	 */
	public static boolean deleteDirectory(File path) {
		if (path.exists()) {
			File[] files = path.listFiles();
			for (int i = 0; i < files.length; i++) {
				if (files[i].isDirectory()) {
					deleteDirectory(files[i]);
				} else {
					files[i].delete();
				}
			}
		}
		return (path.delete());
	}

	/**
	 * Format file size.
	 *
	 * @param size
	 *            the size
	 * @return the string
	 */
	public static String formatFileSize(long size) {
		if (size <= 0)
			return "0";
		final String[] units = new String[] { "B", "KB", "MB", "GB", "TB" };
		int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
		return new DecimalFormat("#,##0.#").format(size
				/ Math.pow(1024, digitGroups))
				+ " " + units[digitGroups];
	}

	/**
	 * Gets the text from file.
	 *
	 * @param pathFile
	 *            the path file
	 * @return the text from file
	 */
	public static String getTextFromFile(String pathFile) {
		String strLine = "";
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					new FileInputStream(pathFile), "UTF8"));
			String tmp;
			while ((tmp = br.readLine()) != null) {
				strLine += tmp;
			}
			br.close();
		} catch (Exception e) {
			Logs.LOGGER.log(Level.SEVERE, "Problem in reading the file.", e);
		}
		return strLine;
	}

	/**
	 * Checks if is file exist.
	 *
	 * @param pathAndFileName
	 *            the path and file name
	 * @return true, if is file exist
	 */
	public static boolean isFileExist(String pathAndFileName) {
		Logs.LOGGER.info(String.format("isFileExist: %s", pathAndFileName));
		File findFile = new File(pathAndFileName);
		return findFile.isFile();
	}

	/**
	 * Instantiates a new file io.
	 *
	 * @param driver
	 *            the driver
	 */
	public FileIO(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * Gets the file attributes.
	 *
	 * @param att
	 *            the att
	 * @param filePath
	 *            the file path
	 * @return the file attributes
	 */
	public static String getFileAttributes(Attributes att, String filePath) {
		try {
			java.nio.file.Path path = Paths.get(filePath);
			BasicFileAttributes attributes = Files.readAttributes(path,
					BasicFileAttributes.class);
			switch (att) {
			case CREATIONTIME:
				return attributes.creationTime().toString();
			case LASTACCESSTIME:
				return attributes.lastAccessTime().toString();
			case LASTMODIFIEDTIME:
				return attributes.lastModifiedTime().toString();
			case ISREGULARFILE:
				return String.valueOf(attributes.isRegularFile());
			case ISDIRECTORY:
				return String.valueOf(attributes.isDirectory());
			case FILESIZE:
				return String.valueOf(attributes.size());
			default:
				Logs.LOGGER.severe("Invalid Attribute.");
				return "Invalid Attribute";
			}
		} catch (Exception e) {
			Logs.LOGGER.severe("Problem in getting file Attribute: " + e);
			return e.getMessage();
		}
	}

	/**
	 * Check file download from url.
	 *
	 * @param downloadUrl
	 *            - url of file to download
	 * @param outputFilePath
	 *            - file path for output
	 * @throws Exception
	 *             the exception
	 */
	public void downloadFile(String downloadUrl, String outputFilePath)
			throws Exception {
		Logs.LOGGER.log(Level.INFO, "",
				String.format("Download file form url: %s", downloadUrl));

		CookieStore cookieStore = seleniumCookiesToCookieStore();
		@SuppressWarnings("resource")
		DefaultHttpClient httpClient = new DefaultHttpClient();
		httpClient.setCookieStore(cookieStore);
		HttpGet httpGet = new HttpGet(downloadUrl);
		HttpResponse response = httpClient.execute(httpGet);
		HttpEntity entity = response.getEntity();
		if (entity != null) {
			File outputFile = new File(outputFilePath);
			InputStream inputStream = entity.getContent();
			FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
			int read;
			byte[] bytes = new byte[1024];
			while ((read = inputStream.read(bytes)) != -1) {
				fileOutputStream.write(bytes, 0, read);
			}
			fileOutputStream.close();
		} else {
			Logs.LOGGER.severe("Problem in downloading the file: "
					+ downloadUrl);
		}
	}

	/**
	 * Get Cookie from WebDriver browser session.
	 *
	 * @return cookieStore from WebDriver browser session.
	 */
	private CookieStore seleniumCookiesToCookieStore() {
		Set<Cookie> seleniumCookies = driver.manage().getCookies();
		CookieStore cookieStore = new BasicCookieStore();
		for (Cookie seleniumCookie : seleniumCookies) {
			BasicClientCookie basicClientCookie = new BasicClientCookie(
					seleniumCookie.getName(), seleniumCookie.getValue());
			basicClientCookie.setDomain(seleniumCookie.getDomain());
			basicClientCookie.setExpiryDate(seleniumCookie.getExpiry());
			basicClientCookie.setPath(seleniumCookie.getPath());
			cookieStore.addCookie(basicClientCookie);
		}
		return cookieStore;
	}

	/**
	 * Copy files and folders.
	 *
	 * @param source
	 *            the source
	 * @param destination
	 *            the destination
	 * @return true, if successful
	 */
	public static boolean copyFilesAndFolders(String source, String destination) {
		Logs.LOGGER.info("Starting copying file : " + source);
		try {
			File Source = new File(source);
			File Destination = new File(destination);
			if (Source.isFile()) {
				FileUtils.copyFile(Source, Destination);
			} else {
				FileUtils.copyDirectoryToDirectory(Source, Destination);
			}
		} catch (Exception e) {
			Logs.LOGGER.severe("Problem in copy operation" + e);
			return false;
		}
		return true;
	}

	/**
	 * Clean folder.
	 *
	 * @param filename
	 *            the filename
	 * @return true, if successful
	 */
	public static boolean cleanFolder(String filename) {
		Logs.LOGGER.info("Deleting : " + filename);
		try {
			File dir = new File(filename);
			FileUtils.cleanDirectory(dir);
		} catch (Exception e) {
			Logs.LOGGER.severe("Problem in delete operation" + e);
			return false;
		}
		return true;
	}

	/**
	 * File check sum.
	 *
	 * @param fileName
	 *            the file name
	 * @return the long
	 */
	public static long fileCheckSum(String fileName) {
		long checkSum = -1;
		try {
			File file = new File(fileName);
			if (file.exists() && file.isFile()) {
				checkSum = FileUtils.checksumCRC32(file);
				Logs.LOGGER.info(fileName + " checksum: " + checkSum);
			}
		} catch (Exception e) {
			Logs.LOGGER.log(Level.SEVERE, "Problem in checking the file", e);
		}
		return checkSum;
	}

}
