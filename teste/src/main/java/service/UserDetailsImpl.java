package service;

import model.entity.Beneficiario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import repository.BeneficiarioRepository;

import java.util.ArrayList;
import java.util.Collection;

public class UserDetailsImpl  implements UserDetails {

    private Long id;
    private String name;
    private String username;
    private String email;
    private  String password;

    public UserDetailsImpl(Long id, String name, String username, String email, String password, Collection<? extends GrantedAuthority> authorities) {
        super();
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.authorities = authorities;
    }

    public UserDetailsImpl(Long id, String nome, String string, CharSequence senha, String string1) {
    }


    public static UserDetailsImpl build(Beneficiario newbeneficiario){
        return new UserDetailsImpl(
                newbeneficiario.getId(),
                newbeneficiario.getNome(),
                newbeneficiario.getLogin().toString(),
                newbeneficiario.getSenha(),
                newbeneficiario.getEmail().toString());
    }

    private Collection<? extends GrantedAuthority> authorities;

    public static UserDetails build(BeneficiarioRepository beneficiario) {
        return null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
