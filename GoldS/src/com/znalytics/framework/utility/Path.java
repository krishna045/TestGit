// Copyright - Znalytics (http://www.Znalytics.com/)

package com.znalytics.framework.utility;

/**
 * The Class Path.
 *
 * @author Nikesh Jauhari
 * @Email: njauhari@znalytics.com
 * @Date: 02/22/2015
 */
public class Path {

	/** The path. */
	private final String path;

	/** The dir name. */
	private final String dirName;

	/** The file name. */
	private final String fileName;

	/** The base file name. */
	private final String baseFileName;

	/** The file extension. */
	private final String fileExtension;

	/**
	 * Instantiates a new path.
	 *
	 * @param path
	 *            the path
	 */
	public Path(String path) {
		super();
		this.path = path;
		int lastSlash = lastSlashIndex();
		this.dirName = path.substring(0, lastSlash + 1);
		this.fileName = path.substring(lastSlash + 1);
		this.baseFileName = path
				.substring(lastSlash + 1, path.lastIndexOf("."));
		this.fileExtension = path.substring(path.lastIndexOf(".") + 1);
	}

	/**
	 * Gets the base dir.
	 *
	 * @return the base dir
	 */
	public String getBaseDir() {
		return dirName;
	}

	/**
	 * Gets the base file name.
	 *
	 * @return the base file name
	 */
	public String getBaseFileName() {
		return baseFileName;
	}

	/**
	 * Gets the file extension.
	 *
	 * @return the file extension
	 */
	public String getFileExtension() {
		return fileExtension;
	}

	/**
	 * Gets the file name.
	 *
	 * @return the file name
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * Last slash index.
	 *
	 * @return the int
	 */
	private int lastSlashIndex() {
		int index = 0;
		index = path.lastIndexOf(47);
		if (index < path.lastIndexOf(92)) {
			index = path.lastIndexOf(92);
		}
		return index;
	}
}
