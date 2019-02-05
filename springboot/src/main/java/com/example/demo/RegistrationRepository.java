package com.example.demo;

import org.springframework.data.repository.CrudRepository;

public interface RegistrationRepository extends CrudRepository<RegistrationDetails, String> {
	
}
