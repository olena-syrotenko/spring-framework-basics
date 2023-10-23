package asd.syrotenko.autowiring.example.annotations;

import org.springframework.beans.factory.annotation.Autowired;

public class AutowiredByConstructor {
	private MessageService messageService;
	private String initMessage = "";

	public AutowiredByConstructor() {
	}

	@Autowired
	public AutowiredByConstructor(MessageService messageService) {
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
