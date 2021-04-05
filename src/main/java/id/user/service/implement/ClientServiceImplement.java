package id.user.service.implement;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

@Service
@EnableCaching
public class ClientServiceImplement extends BaseServiceImplement implements ClientDetailsService {

    @Override
    @Cacheable("jwtToken")
    public ClientDetails loadClientByClientId(String s) throws ClientRegistrationException {
        return jwtTokenRepository.loadClientByClientId(s);
    }
}
