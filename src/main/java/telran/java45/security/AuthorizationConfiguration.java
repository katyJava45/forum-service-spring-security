package telran.java45.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class AuthorizationConfiguration {

	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		http.httpBasic();
		http.csrf().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); //zapret na peredachu cookies
		//filtres ot chastnux k obshchim (poryadok imeet znachenie):
		http.authorizeRequests(authorize -> authorize
				.mvcMatchers("/account/register", "/forum/posts/**").permitAll()
//				.mvcMatchers("/account/password")
//					.access("@customWebSecurity.checkChangePasswordDate(authentication.name)")		
			
				.mvcMatchers("/account/user/*/role/*").hasRole("ADMINISTRATOR")
//				.mvcMatchers(HttpMethod.PUT, "/account/user/{login}").access("#login == principal.getUsername()")
				.mvcMatchers(HttpMethod.PUT, "/account/user/{login}")
					.access("#login == authentication.name")
				.mvcMatchers(HttpMethod.DELETE, "/account/user/{login}")
					.access("#login == authentication.name or hasRole('ADMINISTRATOR')")				
				.mvcMatchers(HttpMethod.POST, "/forum/post/{author}")
					.access("#author == authentication.name")
				.mvcMatchers(HttpMethod.PUT, "/forum/post/*/comment/{author}")
					.access("#author == authentication.name")
				.mvcMatchers(HttpMethod.PUT, "/forum/post/{id}")
					.access("@customWebSecurity.checkPostAuthor(#id, authentication.name)")
				.mvcMatchers(HttpMethod.DELETE, "/forum/post/{id}")
					.access("@customWebSecurity.checkPostAuthor(#id, authentication.name) or hasRole('MODERATOR')")
				.anyRequest().authenticated()
				);
		return http.build();
	}
}
