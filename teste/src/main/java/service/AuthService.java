package service;

import model.dto.AccessDTO;
import model.dto.AuthenticationDTO;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import security.jwt.JwtUtils;

@Service
public class AuthService {

    private AuthenticationManager authenticationManager;

    private JwtUtils jwtUtils;

    public AccessDTO login(AuthenticationDTO authDto) {

        try {
            //Cria credencial para o Spring
            UsernamePasswordAuthenticationToken userAuth =
                    new UsernamePasswordAuthenticationToken(authDto.getUsername(), authDto.getPassword());

            //Método para autenticação
            Authentication authentication = authenticationManager.authenticate((userAuth));

            //Busca usuário logado
            UserDetailsImpl userAuthenticate = (UserDetailsImpl) authentication.getPrincipal();

            String token = jwtUtils.generateTokenFromUserDetailsImpl(userAuthenticate);

            AccessDTO accessDTO = new AccessDTO(token);
            return accessDTO;

        } catch (BadCredentialsException e) {
            //Todo login ou Senha inválido
        }

        return null;
    }
}
