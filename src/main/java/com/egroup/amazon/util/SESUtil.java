package com.egroup.amazon.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClient;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;
import com.egroup.amazon.cloudwatch.logback.entity.LogReport;
import com.egroup.credential.BasicConfig;
import com.google.gson.Gson;

public class SESUtil extends BasicConfig {
	private static Logger logger = LoggerFactory.getLogger(SESUtil.class);

	private static final String FROM = "EDS<egroupjob@gmail.com>";
	private static final Region REGION = Region.getRegion(Regions.US_WEST_2);

	public boolean send(String TO, String SUBJECT, String BODY, boolean isHtml) throws Exception {
		// Construct an object to contain the recipient address.
		final Destination destination = new Destination().withToAddresses(new String[] { TO });

		// Create the subject and body of the message.
		final Content subject = new Content().withData(SUBJECT);
		final Content textBody = new Content().withData(BODY);
		Body body = new Body().withHtml(textBody);
		if (isHtml) {
			body = new Body().withHtml(textBody);
		} else {
			body = new Body().withText(textBody);
		}

		// Create a message with the specified subject and body.
		final Message message = new Message().withSubject(subject).withBody(body);

		// Assemble the email.
		final SendEmailRequest request = new SendEmailRequest().withSource(FROM).withDestination(destination)
				.withMessage(message);

		try {
			final AmazonSimpleEmailServiceClient client = new AmazonSimpleEmailServiceClient(AWS_CREDENTIALS);
			// 選擇SES REGION
			client.setRegion(REGION);
			// Send the email.
			client.sendEmail(request);
			System.out.println("Email sent!");
		} catch (Exception e) {
			final LogReport logReport = new LogReport();
			logReport.setMessage("SESUtil寄信模組");
			logReport.setFunction("client.sendEmail(request)");
			logReport.setAttributes(request);
			logger.error(new Gson().toJson(logReport), e);
			return false;
		}
		return true;
	}
}
