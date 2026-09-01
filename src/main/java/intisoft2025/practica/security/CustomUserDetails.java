package intisoft2025.practica.security;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomUserDetails implements UserDetails {
    private String dni;
    private String username;
    private String password;
    private String rol;
    private Long idEmpresa;
    private boolean estado;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // ponytail: mapeo directo y mínimo del rol a la autoridad oficial de Spring Security
        if (rol == null || rol.isBlank()) {
            return List.of();
        }
        return List.of(new SimpleGrantedAuthority("ROLE_" + rol.toUpperCase()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return estado;
    }
}
