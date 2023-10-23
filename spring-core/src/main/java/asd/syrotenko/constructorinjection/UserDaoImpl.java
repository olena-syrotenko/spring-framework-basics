package asd.syrotenko.constructorinjection;

public class UserDaoImpl implements UserDao {

	@Override
	public String getUsernameById(Integer id) {
		return "username-" + id;
	}
}
