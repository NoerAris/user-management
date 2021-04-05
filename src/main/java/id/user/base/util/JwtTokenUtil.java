package id.user.base.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.stereotype.Component;

import id.user.entity.User;
import id.user.service.UserService;

import java.util.Collection;
import java.util.Map;

@Component
public class JwtTokenUtil extends DefaultAccessTokenConverter {

    @Autowired
    private UserService userService;

    @Override
    public OAuth2Authentication extractAuthentication(Map<String, ?> map) {
        final OAuth2Authentication auth = super.extractAuthentication(map);
        final User user = userService.getByUsername((String) auth.getPrincipal());
        return new OAuth2Authentication(auth.getOAuth2Request(), auth.getUserAuthentication()) {
            @Override
            public Collection<GrantedAuthority> getAuthorities() {
                if (user != null) {
                    return (Collection<GrantedAuthority>) user.getAuthorities();
                }
                return auth.getAuthorities();
            }
        };
    }
}
