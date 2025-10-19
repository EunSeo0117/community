package ktb.week4.Login;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ktb.week4.user.User;
import ktb.week4.user.UserRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final CookieUtil cookieUtil;

    public JwtFilter(JwtUtil jwtUtil, UserRepository userRepository, CookieUtil cookieUtil) {
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
        this.cookieUtil = cookieUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String accessToken = this.cookieUtil.findCookie("accessToken", request.getCookies());

        if (accessToken == null) {
            System.out.println("token null");
            filterChain.doFilter(request, response);
            return;
        }


        if (jwtUtil.isExpired(accessToken)) {
            System.out.println("token expired");
            filterChain.doFilter(request, response);
            return;
        }

        String email = jwtUtil.getEmail(accessToken);

        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("존재하지 않는 사용자입니다.");
        }

        CustomUserDetails customUserDetails = new CustomUserDetails(user);

        Authentication authToken = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authToken);

        filterChain.doFilter(request, response);
    }










}
