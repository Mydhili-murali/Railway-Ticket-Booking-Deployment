package mydhili.RailwayTicketBooking.Security;

import mydhili.RailwayTicketBooking.Service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetailsService userDetailsService;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/addTrain","/viewUsersAccount","/deleteAccount/*","/adminViewTrainSchedule/*","/updateTrainSchedule/*","/addTrainDetails","/addTrainSchedule","/adminViewTrains").hasRole("ADMIN")
                .antMatchers("/viewProfile","/updateProfile","/seatMap/*","/bookedStatus/*","/myBookings","/cancelBookings/*","/trainSchedule/*","/viewTrains").hasRole("USER")
                .antMatchers("/").permitAll()
//                .antMatchers("/*").hasRole("USER")
                .and().formLogin().loginPage("/login").defaultSuccessUrl("/success");
        //       .authorizeRequests()
//                .antMatchers("/", "/home","/registration").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .permitAll()
//                .and()
//                .logout()
//                .permitAll();
    }
    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

}
