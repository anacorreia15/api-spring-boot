package med.voll.api.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import med.voll.api.domain.user.User;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class TokenService {

    public String generateToken(User user) {
        try {
            var algorithm = Algorithm.HMAC256("secret");
            return JWT.create()
                    .withIssuer("API Voll.med") // Identificação da aplicação emissora
                    .withSubject(user.getLogin())
                    .withExpiresAt(ExpirationDate())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException("Could not generate token", exception);
        }
    }

    private Instant ExpirationDate() {
        return LocalDateTime.now()
                .atZone(ZoneId.of("Europe/Lisbon"))
                .plusHours(2)
                .toInstant();
    }
}
