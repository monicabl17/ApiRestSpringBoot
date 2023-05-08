package com.example.demo.security;

import java.security.interfaces.RSAKey;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import static org.springframework.security.config.Customizer.withDefaults;
import com.example.demo.jose.Jwks;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

@Configuration
@EnableWebMvc
public class SecurityConfig {

  private com.nimbusds.jose.jwk.RSAKey rsaKey;

  //Supuestamente conecta con los usuarios de la BBDD. Se pueden hacer pruebas con usuarios en memoria 
  //utilizando InMemoryUserDetailsManager () (esto último funciona.)

  @Bean
  UserDetailsService userDetailsService(DataSource dataSource) {

    return new JdbcUserDetailsManager(dataSource);

  }

  @Bean
  InMemoryUserDetailsManager user() {

    /*
     * Esto es para pruebas. Crear un usuario por defecto.
     */

     return new InMemoryUserDetailsManager(
                User.withUsername("usuario").password(passwordEncoder().encode("password")).authorities("USER").build()
        );
  }

  //Esto encripta la contraseña.

  @Bean
  PasswordEncoder passwordEncoder() {

    return new BCryptPasswordEncoder();
  }
  
  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    /*
     * Configuramos los endPoints, desactivamos las sesiones y configuramos la
     * autenticación con JWT
     */

    return http.csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> auth.requestMatchers("/token").permitAll()
            .anyRequest().authenticated())
        .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .httpBasic(withDefaults()).build();

  }

  @Bean
  public JWKSource<SecurityContext> jwkSource() {
    
    /*
     * Configuramos el origen del RSAkey
     */

    rsaKey = Jwks.generateRsa();
    JWKSet jwkSet = new JWKSet(rsaKey);
    return (jwkSelector, securityContext) -> jwkSelector.select(jwkSet);
  }

  @Bean
  public JwtEncoder jwtEncoder(JWKSource<SecurityContext> jwks) {
    /*
     * Codificamos la Json Web Keyset
     */
    return new NimbusJwtEncoder(jwks);
  }

  @Bean
  public JwtDecoder jwtDecoder() throws JOSEException {
    /*
     * Decodificamos la Json Web Keyset
     */
    return NimbusJwtDecoder.withPublicKey(rsaKey.toRSAPublicKey()).build();
  }

}
