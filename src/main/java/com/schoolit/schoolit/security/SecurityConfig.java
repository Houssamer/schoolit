package com.schoolit.schoolit.security;

import com.schoolit.schoolit.filter.AuthenticationFilter;
import com.schoolit.schoolit.filter.AuthorizationFilter;
import com.schoolit.schoolit.models.Role;
import com.schoolit.schoolit.services.utilisateur.UtilisateurService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UtilisateurService utilisateurService;

    public SecurityConfig(BCryptPasswordEncoder bCryptPasswordEncoder,
                          UtilisateurService utilisateurService) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.utilisateurService = utilisateurService;
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(utilisateurService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // login and sign up also refresh token
        http
                .authorizeRequests()
                    .antMatchers("/api/login",
                            "/api/token/refresh",
                            "/api/apprenant/add",
                            "/api/formateur/add").permitAll();

        // acces aux cours
        http
                .authorizeRequests()
                        .antMatchers("/api/cours/*").permitAll();
        // acces aux formations
        http
                .authorizeRequests()
                        .antMatchers("/api/formation/*").permitAll();

        // acces aux Ressources
        http
                .authorizeRequests()
                        .antMatchers("/api/ressource/*").permitAll();
        // apprenant
        http
                .authorizeRequests()
                        .antMatchers("/api/apprenant/{id}",
                                "/api/apprenant/update")
                        .hasAnyAuthority(Role.Apprenant.name());

        // formateur
        http
                .authorizeRequests()
                        .antMatchers("/api/formateur/{id}",
                                "/api/formateur/update",
                                "/api/cours/add",
                                "/api/cours/update",
                                "/api/cours/delete/*",
                                "/api/formation/add",
                                "/api/formation/update",
                                "/api/formation/delete/*",
                                "/api/ressource/add/*",
                                "/api/ressource/update/*",
                                "/api/ressource/delete/*")
                .hasAnyAuthority(Role.Formatteur.name());

        // admin
        http
                .authorizeRequests()
                        .antMatchers("/api/apprenant/all",
                                "/api/apprenant/find",
                                "/api/formateur/all",
                                "/api/formateur/disabled",
                                "/api/formateur/enable/{id}",
                                "/api/formateur/find",
                                "/api/formateur/delete/*").hasAnyAuthority(Role.Admin.name());

        http.addFilter(new AuthenticationFilter(authenticationManagerBean()));
        http.addFilterBefore(new AuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
