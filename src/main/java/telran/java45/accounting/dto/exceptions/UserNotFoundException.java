package telran.java45.accounting.dto.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 2630959220683018827L;

	public UserNotFoundException(String login) {
		super("User with login " + login + " not found");
	}

}
