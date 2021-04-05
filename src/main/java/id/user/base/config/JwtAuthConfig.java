package id.user.base.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import id.user.base.util.AuthInterceptorUtil;
import id.user.base.util.Md5PassUtil;
import id.user.service.implement.ClientServiceImplement;
import id.user.service.implement.UserServiceImplement;

@Configuration
@EnableAuthorizationServer
@EnableWebSecurity
public class JwtAuthConfig extends AuthorizationServerConfigurerAdapter {

    @Value("${security.jwt.secret:secret}")
    private String secret;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private AuthInterceptorUtil authInterceptor;

    @Autowired
    private UserServiceImplement userService;

    @Autowired
    private ClientServiceImplement clientService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new Md5PassUtil();
    }

    @Bean
    public JwtAccessTokenConverter aksesToken() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(secret);
        return converter;
    }

    @Bean
    public TokenStore token() {
        return new JwtTokenStore(aksesToken());
    }

    @Bean
    @Primary
    public DefaultTokenServices defaultToken() {
        DefaultTokenServices def = new DefaultTokenServices();
        def.setTokenStore(token());
        def.setSupportRefreshToken(true);
        return def;
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoint) {
        endpoint.authenticationManager(authManager)
        	.tokenStore(token())
        	.accessTokenConverter(aksesToken())
        	.userDetailsService(userService)
        	.addInterceptor(authInterceptor);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security.passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clientConfig) throws Exception {
        clientConfig.withClientDetails(clientService);
    }
}
