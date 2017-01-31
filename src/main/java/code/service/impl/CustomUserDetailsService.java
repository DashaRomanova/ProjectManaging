package code.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Napha on 28.01.2017.
 */
@Service
@Transactional(readOnly=true)
public class CustomUserDetailsService implements UserDetailsService{
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }

//    public Collection getAuthorities(Integer role) {
//        List authList = getGrantedAuthorities(getRoles(role));
//        return authList;
//    }
//
//    public List getRoles(Integer role) {
//        List roles = new ArrayList();
//        if (role.intValue() == 1) {
//            roles.add("ROLE_MODERATOR");
//            roles.add("ROLE_ADMIN");
//        } else if (role.intValue() == 2) {
//            roles.add("ROLE_MODERATOR");
//        }
//        return roles;
//    }
//
//    public static List getGrantedAuthorities(List roles) {
//        List authorities = new ArrayList();
//        for (String role : roles) {
//            authorities.add(new SimpleGrantedAuthority(role));
//        }
//        return authorities;
//    }



}
