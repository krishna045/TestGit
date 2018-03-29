// Copyright - Znalytics (http://www.Znalytics.com/)

package com.znalytics.framework.core;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.logging.Level;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * The Class SendMail.
 *
 * @author: Nikesh Jauhari
 * @mail: najuahri@znalytics.com
 * @date: Mar 4, 2015
 */
public class SendMail {

	/**
	 * Send status mail.
	 */
	public void sendStatusMail(String message) {

		if (DataSource.globalConfig.get("mail.send").equalsIgnoreCase("yes")) {
			DateFormat dateFormat = new SimpleDateFormat("yyyy:MM:dd");
			Date date = new Date();
			String currentDate = dateFormat.format(date).toString();
			Logs.LOGGER.info("Sending status mail to: "
					+ DataSource.globalConfig.get("mail.to"));
			ArrayList<String> recipientList = new ArrayList<String>();
			StringTokenizer tokens = new StringTokenizer(
					DataSource.globalConfig.get("mail.to"), ",");
			while (tokens.hasMoreTokens()) {
				recipientList.add(tokens.nextToken().trim());
			}
			try {
				Properties props = new Properties();
				props.put("mail.smtp.host",
						DataSource.globalConfig.get("mail.server"));
				props.put("mail.smtp.port",
						DataSource.globalConfig.get("mail.port"));
				props.put("mail.smtp.auth", "true");
				Session session = Session.getDefaultInstance(props,
						new javax.mail.Authenticator() {
							protected PasswordAuthentication getPasswordAuthentication() {
								return new PasswordAuthentication(
										DataSource.globalConfig
												.get("mail.username"),
										DataSource.globalConfig
												.get("mail.password"));
							}
						});
				session.setDebug(false);
				Message msg = new MimeMessage(session);
				InternetAddress addressFrom = new InternetAddress(
						DataSource.globalConfig.get("mail.username"));
				msg.setFrom(addressFrom);
				InternetAddress[] addressTo = new InternetAddress[recipientList
						.size()];
				for (int i = 0; i < recipientList.size(); i++) {
					addressTo[i] = new InternetAddress(recipientList.get(i)
							.toString());
				}
				msg.addRecipients(Message.RecipientType.TO, addressTo);
				String mailBody = message;
				String status = Constants.TESTRESULTS.contains("f") ? "[FAILED]"
						: "[PASSED]";
				msg.setSubject(status + " Zbra Automation: "
						+ Constants.CURRENT_TESTING + " | "
						+ Constants.CURRENT_CLIENT + " [" + currentDate + "]");
				msg.setContent(mailBody, "text/html");
				Transport.send(msg);
				Logs.LOGGER
						.info("Status mail sent successfully for client/tests : "
								+ Constants.CURRENT_CLIENT
								+ " <<>> "
								+ Constants.CURRENT_TESTING);

			} catch (Exception e) {
				Logs.LOGGER.log(Level.SEVERE,
						"Something went wrong in sending the mail.", e);
			}
		} else {
			Logs.LOGGER.info("No configuration found for sending status mail.");
		}
	}
}
