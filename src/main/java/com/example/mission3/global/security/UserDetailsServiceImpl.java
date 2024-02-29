package com.example.mission3.global.security;

import com.example.mission3.domain.admin.entity.Admin;
import com.example.mission3.domain.admin.repository.AdminRepository;
import com.example.mission3.global.handler.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static com.example.mission3.global.handler.exception.ErrorCode.ADMIN_ACCOUNT_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = adminRepository.findByEmail(username).orElseThrow(() ->
                new UsernameNotFoundException(ADMIN_ACCOUNT_NOT_FOUND.getMessage())
        );

        return new UserDetailsImpl(admin);
    }
}
