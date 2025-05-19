package littleprince.config.security.model;

import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Builder
@Getter
public class CustomUserDetail implements UserDetails {
    private final Long memberId;
    private final String email;
    private final String password;
    private final List<GrantedAuthority> authorities;

    public CustomUserDetail(Long memberId, String email, String password, List<GrantedAuthority> authorities) {
        this.memberId = memberId;
        this.email = email;
        this.password = password;
        this.authorities = authorities != null ? authorities : List.of();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }
}
