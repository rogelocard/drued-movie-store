package com.ssc.druedmoviestore.security;


//@SuppressWarnings("deprecation")
//@Configuration
//@EnableWebSecurity
//public class BasicConfiguration extends WebSecurityConfigurerAdapter {
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http
//				.authorizeRequests().antMatchers("/", "index", "/css/*", "/js/*", "/img/*", "registrar", "login").permitAll()
//				.anyRequest().authenticated().and().formLogin()// .loginPage("/login.html").permitAll()
//				.defaultSuccessUrl("/movie.html", true).and().csrf().disable();
//	}
//}