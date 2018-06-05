import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.CreateQueueRequest;
import com.amazonaws.services.sqs.model.SendMessageRequest;

public class SimpleQueueService {
	public static String  QUEUENAME = "ANCHYQ";
	// Whenever you want to operate the SQS, you need a sqs client first.
	public static AmazonSQS SQS = AmazonSQSClientBuilder.standard().withEndpointConfiguration(new EndpointConfiguration("https://sqs.us-east-1.amazonaws.com", "")).build();
	public SimpleQueueService () {
	}
	private static void createQueue() {
		//  step2: create a queue
		CreateQueueRequest createQueueRequest = new CreateQueueRequest(QUEUENAME);
		SQS.createQueue(createQueueRequest);
	}
	private static void sendSingleMessage(String message) {
		// step1: get the url of the queue by name
		String queueUrl = SQS.getQueueUrl(QUEUENAME).getQueueUrl();
		//step2: create message sending request
		SendMessageRequest sendMessageRequest = new SendMessageRequest(queueUrl, message);
		// step3: send the message by SQS
		SQS.sendMessage(sendMessageRequest);
	}
	public static void main(String[] args) {
		createQueue();
		sendSingleMessage("Hello Queue!");
		
	}
}
