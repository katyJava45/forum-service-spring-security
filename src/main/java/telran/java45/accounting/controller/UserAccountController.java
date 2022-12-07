package telran.java45.accounting.controller;

import java.security.Principal;
import java.util.Base64;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import telran.java45.accounting.dto.RolesResponseDto;
import telran.java45.accounting.dto.UserAccountResponseDto;
import telran.java45.accounting.dto.UserRegisterDto;
import telran.java45.accounting.dto.UserUpdateDto;
import telran.java45.accounting.model.UserAccount;
import telran.java45.accounting.service.UserAccountService;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class UserAccountController {
	final UserAccountService accountService;
	
	@PostMapping("/register")
	public UserAccountResponseDto register(@RequestBody UserRegisterDto userRegisterDto) {
		return accountService.addUser(userRegisterDto);
	}
	
	@PostMapping("/login")
	public UserAccountResponseDto login(Principal principal) {
		
		return accountService.getUser(principal.getName());
	}

//	private String[] getCredentials(String token) {
//		String[] basicAuth = token.split(" ");
//		String decode = new String(Base64.getDecoder().decode(basicAuth[1]));
//		return decode.split(":");
//	}
	
	@DeleteMapping("/user/{login}")
	public UserAccountResponseDto deleteUser(@PathVariable String login) {
		return accountService.removeUser(login);  
	}
	
	@PutMapping("/user/{login}")
	public UserAccountResponseDto UpdateUser(@PathVariable String login,@RequestBody UserUpdateDto userUpdateDto) {
		return accountService.editUser(login, userUpdateDto);
	}
	
	@PutMapping("/user/{login}/role/{role}")
	public RolesResponseDto addRole(@PathVariable String login,@PathVariable String role, boolean isAddRole) {
		return accountService.changeRolesList(login, role, true);
	}
	
	@DeleteMapping("/user/{login}/role/{role}")
	public RolesResponseDto deleteRole(@PathVariable String login,@PathVariable String role, boolean isAddRole) {
		return accountService.changeRolesList(login, role, false);
	}
	
	@PutMapping("/password")
	public void changePassword(Principal principal,@RequestHeader("X-Password") String newPassword) {
//		String[] credentials = getCredentials(token);
		accountService.changePassword(principal.getName(), newPassword);
	}
}
