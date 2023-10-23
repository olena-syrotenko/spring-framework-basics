package asd.syrotenko.autowiring.example.annotations;

import org.springframework.beans.factory.annotation.Autowired;

public class AutowiredBySetter {
	private MessageService messageService;
	private String initMessage = "";

	public AutowiredBySetter() {
	}

	public AutowiredBySetter(MessageService messageService) {
		this.messageService = messageService;
		initMessage = "Autowired by constructor";
	}

	public MessageService getMessageService() {
		return messageService;
	}

	@Autowired
	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
		initMessage = "Autowired by setter";
	}

	public String getInitMessage() {
		return initMessage;
	}
}
