package SpringBoot.App.sequrity;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true)
public class WebSecurityConfig {
    private final NamedParameterJdbcTemplate jdbcTemplate;

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            http.csrf().disable()
                    .authorizeHttpRequests()
//                    .antMatchers("/login/**").permitAll()
//                    .antMatchers("/registration").permitAll()
//                    .antMatchers("/security/**").permitAll()
                    .anyRequest()
                    .authenticated()
                    .and()
                    .formLogin()
                    .permitAll()
                    .loginPage("/login")
                    .loginProcessingUrl("/login")
                    .defaultSuccessUrl("/homepage", true)
                    .failureUrl("/registration")
                    //.failureHandler(authenticationFailureHandler())
                    .and()
                    .logout()
                    .permitAll()
                    .logoutUrl("/logout")
                    .deleteCookies("JSESSIONID");
            return http.build();
        }

    @Bean
    public UserDetailsService userDetailsService() {
        return new MyUserJdbcDetailsService(jdbcTemplate);
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new MyAuthenticationFailureHandler();
    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails user =
//                User.withDefaultPasswordEncoder()
//                        .username("user")
//                        .password("user")
//                        .roles("USER")
//                        .build();
//        UserDetails admin =
//                User.withDefaultPasswordEncoder()
//                        .username("admin")
//                        .password("admin")
//                        .roles("ADMIN")
//                        .build();
//        return new InMemoryUserDetailsManager(user, admin);
//    }

}
