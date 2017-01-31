package code.service.impl;

import code.dao.EmployeeDao;
import code.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Napha on 30.01.2017.
 */
@Service("loginService")
public class LoginServiceImpl implements UserDetailsService {
    @Autowired
    private EmployeeDao employeeDao;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Employee user = employeeDao.findEmployeeByLogin(username);
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
       // authorities.add(new SimpleGrantedAuthority(user.getRole().name()));
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

        return new User(user.getUsername(), user.getPassword(),
                user.isEnabled(), true, true, true, authorities);
    }
}
