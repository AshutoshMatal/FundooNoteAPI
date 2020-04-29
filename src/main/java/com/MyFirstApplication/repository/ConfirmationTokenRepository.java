package com.MyFirstApplication.repository;



import org.springframework.data.repository.CrudRepository;

import com.MyFirstApplication.model.ConfirmationToken;

public interface ConfirmationTokenRepository extends CrudRepository<ConfirmationToken, String> {
	ConfirmationToken findByConfirmationToken(String confirmationToken);
}
