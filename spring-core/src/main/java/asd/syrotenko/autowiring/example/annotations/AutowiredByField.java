package asd.syrotenko.autowiring.example.annotations;

import org.springframework.beans.factory.annotation.Autowired;

public class AutowiredByField {

	@Autowired
	private MessageService messageService;
	private String initMessage = "";

	public AutowiredByField() {
	}

	public AutowiredByField(MessageService messageService) {
		this.messageService = messageService;
		initMessage = "Autowired by constructor";
	}

	public MessageService getMessageService() {
		return messageService;
	}

	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
		initMessage = "Autowired by setter";
	}

	public String getInitMessage() {
		return initMessage;
	}
}
