package com.core.security.auth;

import com.core.security.exception.*;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * BearerTokenAuthenticationFilter
 *             ↓
 * JwtDecoder
 *             ↓
 * Jwt
 *             ↓
 * JwtAuthenticationConverter
 *             ↓
 * Authentication
 *             ↓
 * SecurityContextHolder
 *             ↓
 * Controller
 * Converter được gọi ở đâu?
 * SecurityFilterChain securityFilterChain(
 *         HttpSecurity http,
 *         JwtAuthenticationConverter converter
 * ) throws Exception {
 *
 *     http
 *         .oauth2ResourceServer(oauth2 ->
 *                 oauth2.jwt(jwt ->
 *                         jwt.jwtAuthenticationConverter(converter)
 *                 )
 *         );
 *
 *     return http.build();
 * }
 */
public class JwtAuthenticationConverter
        implements Converter<Jwt, AbstractAuthenticationToken> {

    @Override
    public AbstractAuthenticationToken convert(Jwt jwt) {

        String subject = jwt.getSubject();

        if (subject == null || subject.isBlank()) {
            throw new ApiException(CoreErrorCode.TOKEN_ERROR);
        }

        UUID userId;

        try {
            userId = UUID.fromString(subject);
        } catch (Exception ex) {
            throw new ApiException(CoreErrorCode.TOKEN_ERROR);
        }

        String username = jwt.getClaimAsString("username");

        if (username == null || username.isBlank()) {
            throw new ApiException(CoreErrorCode.TOKEN_ERROR);
        }

        List<String> roles = jwt.getClaimAsStringList("roles");

        Collection<GrantedAuthority> authorities =
                roles == null
                        ? List.of()
                        : roles.stream()
                        .map(role ->
                                role.startsWith("ROLE_")
                                        ? role
                                        : "ROLE_" + role)
                        .map(SimpleGrantedAuthority::new)
                        .map(GrantedAuthority.class::cast)
                        .toList();

        AppUserPrincipal principal =
                new AppUserPrincipal(
                        userId,
                        username,
                        roles == null
                                ? Set.of()
                                : Set.copyOf(roles)
                );

        return new UsernamePasswordAuthenticationToken(
                principal,
                null,
                authorities
        );
    }
}