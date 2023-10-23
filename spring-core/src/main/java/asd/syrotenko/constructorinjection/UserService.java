package asd.syrotenko.constructorinjection;

public class UserService {
	private UserDao userDao;

	public UserService(UserDao userDao) {
		this.userDao = userDao;
	}

	public String getUserById(Integer id) {
		return userDao.getUsernameById(id);
	}
}
