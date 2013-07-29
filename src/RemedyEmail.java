import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class RemedyEmail 
{
	private String asset;
	private String message;
	
	RemedyEmail(String asset)
	{
		this.asset = asset;
	}
	
	public boolean send()
	{
		// Recipient's email ID needs to be mentioned.
	      String to = "Rice_Justin_F@cat.com";

	      // Sender's email ID needs to be mentioned
	      String from = "ecaitsupport@cat.com";

	      // Assuming you are sending email from localhost
	      String host = "intramail.cis.cat.com";

	      // Get system properties
	      Properties properties = System.getProperties();

	      // Setup mail server
	      properties.setProperty("mail.smtp.host", host);

	      // Get the default Session object.
	      Session session = Session.getDefaultInstance(properties);

	      try
	      {
	         // Create a default MimeMessage object.
	         MimeMessage message = new MimeMessage(session);

	         // Set From: header field of the header.
	         message.setFrom(new InternetAddress(from));

	         // Set To: header field of the header.
	         message.addRecipient(Message.RecipientType.TO,
	                                  new InternetAddress(to));

	         // Set Subject: header field
	         message.setSubject("An asset has been sent to PI!");

	         // Now set the actual message
	         message.setText(getMessage());

	         // Send message
	         Transport.send(message);
	         
	      }
	      catch (MessagingException mex) 
	      {
	         return false;
	      }
	      
	      return true;
	}
	
	private String getMessage()
	{
		message = "Schema: HPD:IncidentInterface_Create\n" +
				  "Server: arlbmcid01.ecorp.cat.com\n" +
				  "Action: Submit\n" +
				  "Format: Short\n" +
				  "Key: 0000000\n" +
				  "z1D_Action !1000000076!: [$$\n" +
				  "CREATE\n" +
				  "$$]\n" +
				  "Reported Source !1000000215!: [$$\n" +
				  "Email\n" +
				  "$$]\n" +
				  "Support Company* !1000000251!: [$$\n" +
				  "Cat-Caterpillar\n" +
				  "$$]\n" +
				  "CWS Login ID !301921200!: [$$\n" +
				  "ricejf\n" +
				  "$$]\n" +
				  "Service*+ !303497300!: [$$\n" +
				  "Predictive Analysis Services\n" +
				  "$$]\n" +
				  "ServiceCI_ReconID !303519300!: [$$\n" +
				  "OI-22A4CBA260D211E196D90050569C00C8\n" +
				  "$$]\n" +
				  "Service Categorization Tier 1 !1000000063!: [$$\n" +
				  "Configure\n" +
				  "$$]\n" +
				  "Service Categorization Tier 2 !1000000064!: [$$\n" +
				  "Application\n" +
				  "$$]\n" +
				  "Service Type !1000000099!: [$$\n" + 
				  "User Service Request\n" +
				  "$$]\n" +
				  "Impact* !1000000163!: [$$\n" +
				  "4\n" +
				  "$$]\n" +
				  "Urgency* !1000000162!: [$$\n" +
				  "3\n" +
				  "$$]\n" +
				  "Support Organization* !1000000014!: [$$\n" +
				  "Corp\n" +
				  "$$]\n" +
				  "Assigned Group*+ !1000000217!: [$$\n" +
				  "PAS IT SUPPORT\n" +
				  "$$]\n" +
				  "Description !1000000000!: [$$\n" +
				  "A new asset has been sent to PI it needs to be onboarded. The serial number is " + asset + "\n" +
				  "$$]\n" +
				  "Details !1000000151!: [$$\n" +
				  "Onboard asset with serial number " + asset + "\n" +
				  "$$]";	  
				  
		return message;
	}
}
