package ticketbooking.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.factory.PasswordEncoderFactories;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        //For h2 console.
//        http.headers().frameOptions().disable();
//
//        http.csrf().disable().authorizeRequests()
//                .antMatchers("/swagger-ui.html").authenticated()
//                .antMatchers("/h2-console").hasRole("root")
//                .antMatchers(HttpMethod.PATCH, "/orders/**").hasRole("root")
//                .antMatchers(HttpMethod.DELETE, "/orders/**").hasRole("root")
//                .antMatchers(HttpMethod.PATCH, "/clients/**").hasRole("root")
//                .antMatchers(HttpMethod.DELETE, "/clients/**").hasRole("root")
//                .antMatchers(HttpMethod.POST, "/seats/**").hasRole("root")
//                .antMatchers(HttpMethod.PATCH, "/seats/**").hasRole("root")
//                .antMatchers(HttpMethod.DELETE, "/seats/**").hasRole("root")
//                .antMatchers(HttpMethod.PATCH, "/screenings/**").hasRole("root")
//                .antMatchers(HttpMethod.POST, "/screenings/**").hasRole("root")
//                .antMatchers(HttpMethod.DELETE, "/screenings/**").hasRole("root")
//                .and().httpBasic();
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//
//        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//
//        auth.inMemoryAuthentication()
//                .withUser("root").password(encoder.encode("root")).roles("root");
//    }
//}
