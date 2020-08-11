package andry.hais.appointment_api.service.impl;

import andry.hais.appointment_api.enums.ResultEnum;
import andry.hais.appointment_api.exception.MyException;
import andry.hais.appointment_api.models.User;
import andry.hais.appointment_api.repository.UserRepository;
import andry.hais.appointment_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@DependsOn("passwordEncoder")
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Could not find that email: " + email);
        }
        List<GrantedAuthority> listRoles = Collections.singletonList(new SimpleGrantedAuthority(user.getRole().toString()));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), listRoles);
    }

    @Override
    public User findOne(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    @Transactional
    public User save(User user) {
        //register
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        try {
            User savedUser = userRepository.save(user);

            savedUser.getProfile().setUser(savedUser);

            return userRepository.save(savedUser);

        } catch (Exception e) {
            throw new MyException(ResultEnum.VALID_ERROR);
        }
    }

    @Override
    @Transactional
    public User update(User user) {
        User oldUser = userRepository.findByEmail(user.getEmail());
        oldUser.setPassword(passwordEncoder.encode(user.getPassword()));
        oldUser.setName(user.getName());
        oldUser.setPhone(user.getPhone());
        return userRepository.save(oldUser);
    }

}
