package com.egroup.login.dynamoDB.dao;

import com.egroup.login.dynamoDB.entity.Login;
import com.egroup.login.dynamoDB.entity.LoginToken;

public interface LoginLoadDAO {
	public LoginToken loginToken(LoginToken loginToken);
	public Login login(Login login);
}
