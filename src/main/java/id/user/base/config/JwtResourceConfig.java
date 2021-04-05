package id.user.base.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableResourceServer
public class JwtResourceConfig extends ResourceServerConfigurerAdapter {

    @Value("${security.jwt.secret:secret}")
    private String secret;

    @Bean
    public JwtAccessTokenConverter accessToken() {
        JwtAccessTokenConverter convert = new JwtAccessTokenConverter();
        convert.setSigningKey(secret);
        return convert;
    }

    @Bean
    public TokenStore token() {
        return new JwtTokenStore(accessToken());
    }

    @Bean
    @Primary
    public DefaultTokenServices defaultToken() {
        DefaultTokenServices def = new DefaultTokenServices();
        def.setTokenStore(token());
        return def;
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer config) {
        config.tokenServices(defaultToken());
    }

    @Override
    public void configure(HttpSecurity security) throws Exception {
        security.cors().and().csrf().disable().authorizeRequests().anyRequest().permitAll();
    }
}
