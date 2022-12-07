package telran.java45.accounting.dao;

import org.springframework.data.repository.CrudRepository;

import telran.java45.accounting.model.UserAccount;

public interface UserAccountRepository extends CrudRepository<UserAccount, String> {

}
