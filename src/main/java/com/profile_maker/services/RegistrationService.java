package com.profile_maker.services;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.profile_maker.dao.RegistrationDao;
import com.profile_maker.models.Registration;

public class RegistrationService {
	
	RegistrationDao registrationDao = new RegistrationDao();

	public int register(Registration registrationVo) throws ClassNotFoundException, SQLException, IOException {
		int registration_insert = registrationDao.registration_insert(registrationVo);
		return registration_insert;
	}

	public boolean fetch_register(Registration registrationVO) throws ClassNotFoundException, SQLException, IOException {
		Registration registration = registrationDao.fetch_registration(registrationVO);
		if (registration.getUsername().equals(registrationVO.getUsername()) && registration.getPassword().equals(registrationVO.getPassword())) {
			return true;
		}
		return false;
	}
}
