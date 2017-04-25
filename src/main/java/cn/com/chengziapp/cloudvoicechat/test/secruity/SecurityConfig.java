package cn.com.chengziapp.cloudvoicechat.test.secruity;


//@Configuration
//@EnableWebSecurity
public class SecurityConfig {//extends WebSecurityConfigurerAdapter{

	/*@Autowired
	private ReaderRepository readerRepository;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.userDetailsService(new UserDetailsService() {
			
			@Override
			public UserDetails loadUserByUsername(String username)
					throws UsernameNotFoundException {
				
				return readerRepository.findOne(username);
			}
		});
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {		
		http.authorizeRequests()			
				.antMatchers("/", "/home").permitAll()
                .anyRequest().authenticated()
                .and()
			.formLogin()
				.loginPage("/login")
				.permitAll()
				.and()
			.logout()
				.permitAll();
	}
	
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
                .withUser("admin").password("123456").roles("USER");
    }*/

}
