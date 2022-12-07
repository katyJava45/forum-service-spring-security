package telran.java45.post.model;

import java.time.LocalDateTime;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@EqualsAndHashCode(of = { "user", "dateCreated" })
public class Comment {
	@Setter
	String user;
	@Setter
	String message;
	LocalDateTime dateCreated;
	int likes;

	public Comment() {
		dateCreated = LocalDateTime.now();
	}

	public Comment(String user, String message) {
		this();
		this.user = user;
		this.message = message;
	}

	public void addLike() {
		likes++;
	}
}
