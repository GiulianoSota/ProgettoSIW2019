package it.uniroma3.siw.ProgettoSIW2019.auth;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import javax.sql.DataSource;

/* AuthConfiguration è una Spring Security Configuration.
 * Estende WebSecurityConfigurerAdapter, ovvero fornisce le impostazioni per Web security. */
@Configuration
@EnableWebSecurity
public class AuthConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private Environment environment;

    /* Il datasource dove trovare i Funzionari nella nostra applicazione (è un bean). */
    private DataSource dataSource;

    /* Il metodo di configurazione
     * The configure method is the main method in the AuthConfiguration.
     * it
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // paragfrafo di autorizzazione: definiremo qui CHI può accedere a QUALI pagine
                .authorizeRequests()

                    // solo gli admin possono accedere alle Richieste
                    .antMatchers(HttpMethod.GET, "/richieste").hasAnyAuthority("ADMIN")
                    .antMatchers(HttpMethod.GET, "/richiesta/{id}").hasAnyAuthority("ADMIN")

                    // chiunque (autenticato o non) può accedere a tutte le altre pagine
                    // (che avranno alcune funzioni aggiuntive esclusive degli admin)
                    .anyRequest().permitAll()

                // paragrafo di login: definiremo qui come effettuare il login
                // usa il protocollo formlogin per effettuare il login
                .and().formLogin()
                    // dopo che il login ha avuto successo, ridireziona a '/' (pagina principale)
                    .defaultSuccessUrl("/")
                //NOTE: we are using the default configuration for login,
                // meaning that the /login url is automatically mapped to auto-generated page.
                // for our own page, we would need to use loginPage()
                // and write a method for accessing it with GET method (but Spring would still handle the POST automatically)

                // paragrafo del logout: definiremo qui come effettuare il logout
                .and().logout()
                    // logout è effettuato quando si manda una richiesta GET a "/logout"
                    .logoutUrl("/logout")
                    // dopo che il logout ha avuto successo, ridireziona a '/' (pagina principale)
                    .logoutSuccessUrl("/");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(this.buildDatasource())
                .authoritiesByUsernameQuery("SELECT username, role FROM funzionario WHERE username=?")
                .usersByUsernameQuery("SELECT username, password, 1 as enabled FROM funzionario WHERE username=?");
    }

    @Bean
    DataSource buildDatasource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getProperty("spring.datasource.driver-class-name"));
        dataSource.setUrl(environment.getProperty("spring.datasource.url"));
        dataSource.setUsername(environment.getProperty("spring.datasource.username"));
        dataSource.setPassword(environment.getProperty("spring.datasource.password"));
        return dataSource;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
