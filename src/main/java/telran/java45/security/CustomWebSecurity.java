package telran.java45.security;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import telran.java45.accounting.dao.UserAccountRepository;
import telran.java45.accounting.model.UserAccount;
import telran.java45.post.dao.PostRepository;
import telran.java45.post.model.Post;

@Service
//@Service("customSecurity") - esli hotim dat` svoe nazvanie bean
@RequiredArgsConstructor
public class CustomWebSecurity {
	final PostRepository repository;
	final UserAccountRepository userAccountRepository;
	
	public boolean checkPostAuthor(String postId, String userName) {
		Post post = repository.findById(postId).orElse(null);
		return post != null && userName.equalsIgnoreCase(post.getAuthor());
	}
	
	public boolean checkChangePasswordDate(String login) {
		UserAccount userAccount = userAccountRepository.findById(login).orElse(null);
		
		return userAccount != null && userAccount.getChangePasswordDate().plusMonths(1).isAfter(LocalDate.now());
	}
}
