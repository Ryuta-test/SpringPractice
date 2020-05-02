package jp.practice.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import jp.practice.spring.service.UserDetailsServiceImpl;

/**
 * Spring Securityによるログイン認証設定
 * 
 *
 */

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	// クライアントからsubmitされたPWを暗号化するために利用する
	// この定義がないと、認証時にSpring Securityで例外がスローされる
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}

	/**
	 * 静的ファイルは認証対象から除外
	 * 
	 * @param web
	 * @throws Exception
	 */
	@Override
	public void configure(WebSecurity web) throws Exception {
		// **はそれ以下の階層すべて
		web.ignoring().antMatchers("/img/**", "/css/**", "/js/**");
	}

	/**
	 * クライアントからログインのリクエストを受け取ったときの認証処理設定
	 * 
	 * 
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// http.authorizeRequests().anyRequest().authenticated();

		http.authorizeRequests().antMatchers("/favicon.ico", "/css/**", "/js/**", "/login.html", "/login").permitAll()
				.anyRequest().authenticated();

		http.formLogin()
				// ログインページを指定
				.loginPage("/login")
				// フォームのsubmitURL（ログインボタン押下時のsubmitURL）
				.loginProcessingUrl("/login_check")
				// リクエストパラメータのname属性を明示
				.usernameParameter("username")
				// リクエストパラメータのname属性を明示
				.passwordParameter("password")
				// 認証成功時にリダイレクトするURL
				.defaultSuccessUrl("/index", true)
				// 認証失敗時の遷移先
				.failureUrl("/login?error").permitAll();

		http.logout()
				// ログアウトのパス
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				// ログアウト成功時の遷移先
				.logoutSuccessUrl("/login").permitAll()
				// ログアウト後のセッションを削除
				.invalidateHttpSession(true);
//				// ログアウト後のCookieのJSESSIONIDを削除
//				.deleteCookies("JSESSIONID");

		// ログアウトしたら、セッションを無効にする
		http.sessionManagement().invalidSessionUrl("/login");
	}

	/**
	 * ログインを許可するユーザー・パスワードを設定(直接指定)
	 * 
	 */
//	@Autowired
//	public void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication().withUser("user").password("{noop}password").roles("USER");
//	}

	/**
	 * ログインを許可するユーザー・パスワードを設定(DBから検索)
	 * 
	 */
	@Autowired
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}

}
