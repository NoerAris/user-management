package id.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Repository;

import id.user.entity.JwtToken;

@Repository
public interface JwtTokenRepository extends JpaRepository<JwtToken, Long>, ClientDetailsService {

    @Override
    @Query("select x from JwtToken x where x.clientId = ?1")
    ClientDetails loadClientByClientId(String s) throws ClientRegistrationException;
}
