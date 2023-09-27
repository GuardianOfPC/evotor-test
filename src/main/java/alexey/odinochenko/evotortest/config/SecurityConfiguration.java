package alexey.odinochenko.evotortest.config;

import alexey.odinochenko.evotortest.data.entity.Role;
import alexey.odinochenko.evotortest.service.auth.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/v1/auth/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/block/**").hasAnyRole(Role.ADMIN.name(), Role.USER.name())
                        .requestMatchers(HttpMethod.POST, "/api/v1/block/**").hasAnyRole(Role.ADMIN.name())
                        .requestMatchers(HttpMethod.PUT, "/api/v1/block/**").hasAnyRole(Role.ADMIN.name())
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/block/**").hasAnyRole(Role.ADMIN.name())
                        .requestMatchers(HttpMethod.GET, "/api/v1/rule/**").hasAnyRole(Role.ADMIN.name(), Role.USER.name())
                        .requestMatchers(HttpMethod.POST, "/api/v1/rule/**").hasAnyRole(Role.ADMIN.name())
                        .requestMatchers(HttpMethod.PUT, "/api/v1/rule/**").hasAnyRole(Role.ADMIN.name())
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/rule/**").hasAnyRole(Role.ADMIN.name())
                        .anyRequest().authenticated()
                )
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return  http.build();
    }
}
