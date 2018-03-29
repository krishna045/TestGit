// Copyright - Znalytics (http://www.Znalytics.com/)

package com.znalytics.framework.core;

import static org.monte.media.FormatKeys.EncodingKey;
import static org.monte.media.FormatKeys.FrameRateKey;
import static org.monte.media.FormatKeys.KeyFrameIntervalKey;
import static org.monte.media.FormatKeys.MIME_AVI;
import static org.monte.media.FormatKeys.MediaTypeKey;
import static org.monte.media.FormatKeys.MimeTypeKey;
import static org.monte.media.VideoFormatKeys.CompressorNameKey;
import static org.monte.media.VideoFormatKeys.DepthKey;
import static org.monte.media.VideoFormatKeys.ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE;
import static org.monte.media.VideoFormatKeys.QualityKey;

import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.util.logging.Level;

import org.monte.media.Format;
import org.monte.media.FormatKeys.MediaType;
import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;

/**
 * The Class RecordVideo.
 *
 * @author: Nikesh Jauhari
 * @mail: najuahri@znalytics.com
 * @date: Mar 4, 2015
 */
public class RecordVideo {

	/** The screen recorder. */
	private ScreenRecorder screenRecorder = null;

	/**
	 * Instantiates a new record video.
	 */
	public RecordVideo() {
		Logs.LOGGER.info("Initializing Video Recorder ...");
		try {
			GraphicsConfiguration gc = GraphicsEnvironment
					.getLocalGraphicsEnvironment().getDefaultScreenDevice()
					.getDefaultConfiguration();

			this.screenRecorder = new ScreenRecorder(gc, new Format(
					MediaTypeKey, MediaType.FILE, MimeTypeKey, MIME_AVI),
					new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey,
							ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
							CompressorNameKey,
							ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE, DepthKey,
							24, FrameRateKey, Rational.valueOf(15), QualityKey,
							1.0f, KeyFrameIntervalKey, 15 * 60), new Format(
							MediaTypeKey, MediaType.VIDEO, EncodingKey,
							"black", FrameRateKey, Rational.valueOf(30)), null);
		} catch (Exception e) {
			Logs.LOGGER.log(Level.SEVERE,
					"problem in Initializing Video recorder.", e);
		}
	}

	/**
	 * Start video recording.
	 */
	public void startVideoRecording() {
		try {
			Logs.LOGGER.info("Starting Video Recording ....");
			this.screenRecorder.start();
		} catch (IOException e) {
			Logs.LOGGER.log(Level.SEVERE,
					"problem in Starting the Video Recording", e);
		}
	}

	/**
	 * Stop video recording.
	 */
	public void stopVideoRecording() {
		try {
			Logs.LOGGER.info("Stopping Video Recording.");
			this.screenRecorder.stop();
		} catch (IOException e) {
			Logs.LOGGER.log(Level.SEVERE,
					"problem in stopping the Video Recording", e);
		}
	}
}
