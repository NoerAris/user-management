package id.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "mst_jwt_token")
public class JwtToken implements ClientDetails {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "client_id")
    private String clientId;

    @Column(name = "client_secret")
    private String secret;

    @Column(name = "has_secret")
    private boolean hasSecret;
    
    @Column(name = "has_scope")
    private boolean hasScope;

    private String scope;

    @Column(name = "grant_types")
    private String grantTypes;

    private String authorities;

    @Column(name = "redirect_uris")
    private String redirectUris;

    private boolean approved;

    @JsonIgnore
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @JsonIgnore
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;

    @Column(name = "expired")
    private Long tokenExpired;

    public Long getId() {
        return id;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public boolean isHasSecret() {
        return hasSecret;
    }

    public void setHasSecret(boolean hasSecret) {
        this.hasSecret = hasSecret;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public boolean isHasScope() {
        return hasScope;
    }

    public void setHasScope(boolean hasScope) {
        this.hasScope = hasScope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getGrantTypes() {
        return grantTypes;
    }

    public void setGrantTypes(String grantTypes) {
        this.grantTypes = grantTypes;
    }

    public void setAuthorities(String authorities) {
        this.authorities = authorities;
    }

    public String getRedirectUris() {
        return redirectUris;
    }

    public void setRedirectUris(String redirectUris) {
        this.redirectUris = redirectUris;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public Long getTokenExpired() {
        return tokenExpired;
    }

    public void setTokenExpired(Long tokenExpired) {
        this.tokenExpired = tokenExpired;
    }

    @Override
    public String getClientId() {
        return clientId;
    }

    @Override
    public Set<String> getResourceIds() {
        Set<String> resources = new HashSet<>();
        resources.add("oauth2-resource");
        return resources;
    }

    @Override
    public boolean isSecretRequired() {
        return hasSecret;
    }

    @Override
    public String getClientSecret() {
        return secret;
    }

    @Override
    public boolean isScoped() {
        return hasScope;
    }

    @Override
    public Set<String> getScope() {
        if (null != scope) {
            return new HashSet<>(Arrays.asList(scope.split(",")));
        }
        return null;
    }

    @Override
    public Set<String> getAuthorizedGrantTypes() {
        if (null != grantTypes) {
            return new HashSet<>(Arrays.asList(grantTypes.split(",")));
        }
        return null;
    }

    @Override
    public Set<String> getRegisteredRedirectUri() {
        if (null != redirectUris) {
            return new HashSet<>(Arrays.asList(redirectUris.split(",")));
        }
        return null;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> grants = new HashSet<>();
        if (null != authorities) {
            String[] s = authorities.split(",");
            for (String authority : s) {
                grants.add(new SimpleGrantedAuthority(authority));
            }
        }
        return grants;
    }

    @Override
    public Integer getAccessTokenValiditySeconds() {
        return tokenExpired.intValue();
    }

    @Override
    public Integer getRefreshTokenValiditySeconds() {
        return tokenExpired.intValue() * 2;
    }

    @Override
    public boolean isAutoApprove(String s) {
        return approved;
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        return null;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}
