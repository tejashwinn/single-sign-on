package com.tejashwin.admin.config;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.tejashwin.admin.constant.AdminConstants.ROLE_PREFIX;
import static com.tejashwin.admin.constant.CustomJWTClaimNames.RESOURCE_ACCESS;
import static com.tejashwin.admin.constant.CustomJWTClaimNames.ROLES;
import static org.springframework.security.oauth2.core.oidc.StandardClaimNames.PREFERRED_USERNAME;

@Configuration
public class JwtAuthConfig implements Converter<Jwt, AbstractAuthenticationToken> {


    private final JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter;

    private final String clientId;

    public JwtAuthConfig(@Value("${admin.service.keycloak.client-id}") String clientId) {
        this.jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        this.clientId = clientId;
    }

    @Override
    public AbstractAuthenticationToken convert(@NonNull Jwt jwt) {
        Collection<GrantedAuthority> authorities = Stream.concat(
                this.jwtGrantedAuthoritiesConverter
                        .convert(jwt)
                        .stream(),
                this.extractRoles(jwt)
                        .stream()).collect(Collectors.toSet());
        return new JwtAuthenticationToken(
                jwt,
                authorities,
                this.getPrincipalClaimName(jwt));
    }

    private String getPrincipalClaimName(Jwt jwt) {
        return jwt.getClaim(PREFERRED_USERNAME);
    }

    private Collection<GrantedAuthority> extractRoles(Jwt jwt) {
        if (!jwt.hasClaim(RESOURCE_ACCESS))
            return Set.of();
        final Map<String, Map<String, Collection<String>>> resourceAccess = jwt.getClaim(RESOURCE_ACCESS);
        if (!resourceAccess.containsKey(this.clientId))
            return Set.of();
        final Map<String, Collection<String>> resource = resourceAccess.get(this.clientId);
        if (!resource.containsKey(ROLES))
            return Set.of();
        return resource.get(ROLES)
                .stream()
                .map(role -> new SimpleGrantedAuthority(String.format(ROLE_PREFIX, role)))
                .collect(Collectors.toSet());
    }
}
