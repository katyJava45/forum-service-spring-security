package telran.java45;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import java.util.Optional;

import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import telran.java45.accounting.dao.UserAccountRepository;

import telran.java45.accounting.model.UserAccount;

@SpringBootTest
@ExtendWith(MockitoExtension.class) 
class ForumServiceSpringSecurityApplicationTests {

	@Mock
	UserAccountRepository mockRepository;
	
	@InjectMocks
	UserAccount userAccount = new UserAccount("user", "1234", "John", "Smith");
	

	
	@Test
	void testAddUser() {
			
		when(mockRepository.save(userAccount)).thenReturn(userAccount);
		assertEquals(mockRepository.save(userAccount), userAccount);
		verify(mockRepository).save(userAccount);

	}

	
	@Test
	void testGetUser() {
	
		Optional<UserAccount> userAccountOpt = Optional.ofNullable(userAccount);
		when(mockRepository.findById(userAccount.getLogin())).thenReturn(userAccountOpt);
		Assertions.assertEquals(mockRepository.findById(userAccount.getLogin()), userAccountOpt);
		verify(mockRepository).findById(userAccount.getLogin());

	}
}
