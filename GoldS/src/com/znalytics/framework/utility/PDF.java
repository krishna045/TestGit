// Copyright - Znalytics (http://www.Znalytics.com/)

package com.znalytics.framework.utility;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.net.URL;
import java.util.logging.Level;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

import com.znalytics.framework.core.Logs;

/**
 * The Class PDF.
 *
 * @author: Nikesh Jauhari
 * @mail: najuahri@znalytics.com
 * @date: Mar 4, 2015
 */
public class PDF {

	/** The pdf file path. */
	private String pdfFilePath;

	/** The pdf url. */
	private URL pdfURL;

	/** The parser. */
	PDFParser parser;

	/** The cos doc. */
	COSDocument cosDoc;

	/** The pd doc. */
	PDDocument pdDoc;

	/** The parsed text. */
	String parsedText;

	/** The pdf stripper. */
	PDFTextStripper pdfStripper = null;

	/**
	 * Instantiates a new pdf.
	 *
	 * @param pdfFilePath
	 *            the pdf file path
	 */
	public PDF(String pdfFilePath) {
		this.pdfFilePath = pdfFilePath;
	}

	/**
	 * Instantiates a new pdf.
	 *
	 * @param pdfURL
	 *            the pdf url
	 */
	public PDF(URL pdfURL) {
		this.pdfURL = pdfURL;
	}

	/**
	 * Gets the PDF text.
	 *
	 * @return the PDF text
	 */
	public String getPDFText() {
		try {
			if (null != this.pdfURL) {
				parser = new PDFParser(new BufferedInputStream(
						this.pdfURL.openStream()));
			}
			if (null != this.pdfFilePath) {
				parser = new PDFParser(new FileInputStream(pdfFilePath));
			}
			parser.parse();
			cosDoc = parser.getDocument();
			pdDoc = new PDDocument(cosDoc);
			if (pdDoc.isEncrypted()) {
				Logs.LOGGER
						.warning("The PDF file is encrypted, cannot read it.");
				return null;
			} else {
				Logs.LOGGER.info("# of Pages in PDF file : "
						+ pdDoc.getNumberOfPages());
				pdfStripper = new PDFTextStripper();
				parsedText = pdfStripper.getText(pdDoc);
			}
		} catch (Exception e) {
			Logs.LOGGER.log(Level.SEVERE, "Problem in reading PDF.", e);
			try {
				if (cosDoc != null)
					cosDoc.close();
				if (pdDoc != null)
					pdDoc.close();
			} catch (Exception e1) {
				Logs.LOGGER
						.log(Level.SEVERE, "Problem in closing the PDF.", e1);
			}
			return null;
		}
		return parsedText;
	}
}
