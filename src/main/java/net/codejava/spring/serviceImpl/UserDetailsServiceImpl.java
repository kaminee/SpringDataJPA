package net.codejava.spring.serviceImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.codejava.spring.dao.UserService;
import net.codejava.spring.model.Role;
import net.codejava.spring.model.User;
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserService userService;
	
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		
		System.out.println("\n\t username--->"+username);

		User user = userService.findByUsername(username); //our own User model class
		
		List<User> userList=userService.findAll();
		System.out.println("\n\t getPassword--->"+user.getPassword());

		if(user!=null){
			String password = user.getPassword();
			System.out.println("\n\t sword--->"+password);
			//additional information on the security object
			boolean enabled = true;//user.getStatus().equals(UserStatus.ACTIVE);
			System.out.println("\n\t enabled--->"+enabled);

			boolean accountNonExpired = true;//user.getStatus().equals(UserStatus.ACTIVE);
			System.out.println("\n\t accountNonExpired--->"+accountNonExpired);

			boolean credentialsNonExpired = true;//user.getStatus().equals(UserStatus.ACTIVE);
			boolean accountNonLocked = true;//user.getStatus().equals(UserStatus.ACTIVE);
			
			//Let's populate user roles
			List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>(user.getRoles().size());
			Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			for(Role role : user.getRoles()){
				authList.add(new SimpleGrantedAuthority(role.getRoleName()));
				System.out.println("\n\t role.getRoleName()==>"+role.getRoleName());
				authorities.addAll(authList);
			}
			
			//Now let's create Spring Security User object
			org.springframework.security.core.userdetails.User securityUser = new 
					org.springframework.security.core.userdetails.User(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
System.out.println(securityUser.getUsername()+"\t "+securityUser.getAuthorities());
			return securityUser;
		}else{
			throw new UsernameNotFoundException("User Not Found!!!");
		}
	}

}
