package telran.java45.accounting.service;

import telran.java45.accounting.dto.RolesResponseDto;
import telran.java45.accounting.dto.UserAccountResponseDto;
import telran.java45.accounting.dto.UserRegisterDto;
import telran.java45.accounting.dto.UserUpdateDto;

public interface UserAccountService {
	UserAccountResponseDto addUser(UserRegisterDto userRegisterDto);
	
	UserAccountResponseDto getUser(String login);
	
	UserAccountResponseDto removeUser(String login);
	
	UserAccountResponseDto editUser(String login, UserUpdateDto userUpdateDto);
	
	RolesResponseDto changeRolesList(String login, String role, boolean isAddRole);
	
	void changePassword(String login, String newPassword);
}
