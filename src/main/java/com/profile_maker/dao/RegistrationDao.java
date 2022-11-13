package com.profile_maker.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.profile_maker.models.Registration;
import com.profile_maker.utils.ConfigLoader;

public class RegistrationDao {

	public int registration_insert(Registration registrationVo) throws ClassNotFoundException, SQLException, IOException {
		Connection dbConnection = new DbConnection().getDbConnection();
		PreparedStatement prepareStatement = dbConnection.prepareStatement("registration_insert");
		prepareStatement.setString(1, registrationVo.getUsername());
		prepareStatement.setString(2, registrationVo.getPassword());
		prepareStatement.setString(3, registrationVo.getEmail());
		prepareStatement.setLong(4, registrationVo.getPhoneNumber());
		int value = prepareStatement.executeUpdate();
		return value;
	}

	public Registration fetch_registration(Registration registrationVO) throws SQLException, IOException, ClassNotFoundException {
		Connection dbConnection = new DbConnection().getDbConnection();
		PreparedStatement prepareStatement = dbConnection.prepareStatement(new ConfigLoader().getQueries().getProperty("fetch_registration"));
		prepareStatement.setString(1, registrationVO.getUsername());
		ResultSet resultSet = prepareStatement.executeQuery();
		Registration registration = null;
		if (resultSet.getFetchSize() == 1) {
			registration = new Registration(resultSet.getString(1), resultSet.getString(2),resultSet.getString(3), resultSet.getLong(4));
		}
		return registration;
	}
}
