package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import repository.BeneficiarioRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

@Autowired
private BeneficiarioRepository beneficiarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        BeneficiarioRepository beneficiario = (BeneficiarioRepository) beneficiarioRepository.findByLogin(username).get();
        return UserDetailsImpl.build(beneficiario);
    }
}
