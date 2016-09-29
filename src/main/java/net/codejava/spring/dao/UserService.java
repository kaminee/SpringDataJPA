package net.codejava.spring.dao;

import java.util.List;

import net.codejava.spring.model.Role;
import net.codejava.spring.model.User;

public interface UserService {
	public User create(User shop);
    public User delete(int id);
    public List<User> findAll();
    public User update(User shop);
    public User findById(int id);
    public User findByUsername(String name);
    public List<Role> findRolesByUsername(String name);


}
