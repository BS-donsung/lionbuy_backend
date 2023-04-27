package com.ateam.lionbuy.security

import com.ateam.lionbuy.security.filter.JWTAuthenticationFilter
import com.ateam.lionbuy.security.filter.JWTParsingFilter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Autowired
    private lateinit var jwtAuthenticationFilter: JWTAuthenticationFilter

    @Autowired
    private lateinit var jwtTokenFilter: JWTParsingFilter


    @Bean
    fun `config`( http : HttpSecurity ) : SecurityFilterChain =
        with(http) {
            this.csrf().disable()
                .httpBasic().disable()
                .formLogin().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

            this.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)
            this.addFilterBefore(jwtTokenFilter, JWTAuthenticationFilter::class.java)

            this.authorizeHttpRequests {
                it.requestMatchers("login").permitAll()
                it.requestMatchers("/auth/login").permitAll()
                it.requestMatchers("/auth/register").permitAll()
            }

            this.authorizeHttpRequests().anyRequest().permitAll()
//            TestSetting
//            this.authorizeHttpRequests().anyRequest().authenticated()
            this.build()
        }


}