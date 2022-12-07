package telran.java45.accounting.service;


import java.time.LocalDate;

import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import telran.java45.accounting.dao.UserAccountRepository;
import telran.java45.accounting.dto.RolesResponseDto;
import telran.java45.accounting.dto.UserAccountResponseDto;
import telran.java45.accounting.dto.UserRegisterDto;
import telran.java45.accounting.dto.UserUpdateDto;
import telran.java45.accounting.dto.exceptions.UserExistsException;
import telran.java45.accounting.dto.exceptions.UserNotFoundException;
import telran.java45.accounting.model.UserAccount;

@Service
@RequiredArgsConstructor
public class UserAccountServiceImpl implements UserAccountService, CommandLineRunner {

	final UserAccountRepository repository;
	final ModelMapper modelMapper;
	final PasswordEncoder passwordEncoder;
	
	@Override
	public UserAccountResponseDto addUser(UserRegisterDto userRegisterDto) {
		if(repository.existsById(userRegisterDto.getLogin())) {
			throw new UserExistsException(userRegisterDto.getLogin());
		}
		UserAccount userAccount = modelMapper.map(userRegisterDto, UserAccount.class);
		String password = passwordEncoder.encode(userRegisterDto.getPassword());
		userAccount.setPassword(password);
		userAccount.setChangePasswordDate(LocalDate.now());
		repository.save(userAccount);
		return modelMapper.map(userAccount, UserAccountResponseDto.class);
	}

	@Override
	public UserAccountResponseDto getUser(String login) {
		UserAccount userAccount = repository.findById(login).orElseThrow(() -> new UserNotFoundException(login));
		return modelMapper.map(userAccount, UserAccountResponseDto.class);
	}

	@Override
	public UserAccountResponseDto removeUser(String login) {
		UserAccount userAccount = repository.findById(login).orElseThrow(() -> new UserNotFoundException(login));
		repository.delete(userAccount);
		return modelMapper.map(userAccount, UserAccountResponseDto.class);

	}

	@Override
	public UserAccountResponseDto editUser(String login, UserUpdateDto userUpdateDto) {
		UserAccount userAccount = repository.findById(login).orElseThrow(() -> new UserNotFoundException(login));
		if(userUpdateDto.getFirstName()!=null) {
			userAccount.setFirstName(userUpdateDto.getFirstName());
		}
		if(userUpdateDto.getLastName()!=null) {
			userAccount.setLastName(userUpdateDto.getLastName());
		}
		System.out.println(userAccount.getChangePasswordDate());
		repository.save(userAccount);
		return modelMapper.map(userAccount, UserAccountResponseDto.class);
	}

	@Override
	public RolesResponseDto changeRolesList(String login, String role, boolean isAddRole) {
		UserAccount userAccount = repository.findById(login).orElseThrow(() -> new UserNotFoundException(login));
		boolean res;
		if(isAddRole) { 
			res = userAccount.addRole(role.toUpperCase());
		} else {
				res = userAccount.removeRole(role.toUpperCase());			
		}
		if(res) {
			repository.save(userAccount);
		}
		
		return modelMapper.map(userAccount, RolesResponseDto.class);
	}

	@Override
	public void changePassword(String login, String newPassword) {
		UserAccount userAccount = repository.findById(login).orElseThrow(() -> new UserNotFoundException(login));
		if(newPassword!=null) {
			userAccount.setPassword(newPassword);
		}
		String password = passwordEncoder.encode(newPassword);
		userAccount.setPassword(password);
		userAccount.setChangePasswordDate(LocalDate.now());
		repository.save(userAccount);

	}

	@Override
	public void run(String... args) throws Exception {
		if(!repository.existsById("admin")) {
			String password = passwordEncoder.encode("admin");
			UserAccount userAccount = new UserAccount("admin", password, "", "");
			userAccount.addRole("MODERATOR");
			userAccount.addRole("ADMINISTRATOR");
			repository.save(userAccount);
		}
		
	}

}
