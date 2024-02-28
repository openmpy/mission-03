package com.example.mission3.global.security;

import com.example.mission3.domain.admin.entity.Admin;
import com.example.mission3.domain.admin.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = adminRepository.findByEmail(username).orElseThrow(() ->
                new UsernameNotFoundException("찾을 수 없는 관리자 이메일입니다. email: %s".formatted(username))
        );

        return new UserDetailsImpl(admin);
    }
}
