package vn.quanphan.realestate.service;

import java.util.Collections;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import vn.quanphan.realestate.util.constant.AccountStatus;

@Component("userDetailsService")
public class UserDetailsCustom implements UserDetailsService {

    private final UserService userService;

    public UserDetailsCustom(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        vn.quanphan.realestate.domain.User user = this.userService.handleGetUserByUsername(username);
        if (user == null) {
            throw new BadCredentialsException("Username/password không hợp lệ");
        }
        if (!user.getEmailVerified()) {
            throw new BadCredentialsException("Email chưa được xác thực");
        }
        if (user.getStatus() == AccountStatus.INACTIVE || user.getStatus() == null) {
            throw new BadCredentialsException("Tài khoản đã bị khóa");
        }
        // Lấy tên role từ user
        String role = user.getRole().getName();

        // Kiểm tra và thêm tiền tố "ROLE_" nếu cần
        if (!role.startsWith("ROLE_")) {
            role = "ROLE_" + role;
        }

        // Tạo đối tượng UserDetails với quyền được định nghĩa đúng
        return new User(
                user.getEmail(),
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(role))
        );
    }

}
