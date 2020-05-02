package jp.practice.spring.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import jp.practice.spring.mybatis.dto.Login;
import jp.practice.spring.mybatis.mapper.LoginMapper;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private LoginMapper loginMapper;

	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {

		// SQLのselectを実行する
		Login loginUser = loginMapper.selectByPrimaryKey(id);

		if (loginUser == null) {
			// ログインIDがDBに存在しなかったときの例外処理
			throw new UsernameNotFoundException("User:" + loginUser + "was not found in the database");
		}

		// 権限のリスト
		// AdminやUserなどが存在するが、今回は利用しないのでUSERのみを仮で設定
		// 権限を利用する場合は、DB上で権限テーブル、ユーザ権限テーブルを作成し管理が必要
		List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
		GrantedAuthority authority = new SimpleGrantedAuthority("USER");
		grantList.add(authority);

		// DBのパスワードを暗号化して、クライアントからsubmitされたPWと比較する
		// クライアントからsubmitされたPWはWebSecurityConfigで指定したように暗号化される
		BCryptPasswordEncoder pwEncoder = new BCryptPasswordEncoder();

		// UserDetailsはインタフェースなのでUserクラスのコンストラクタで生成したユーザオブジェクトをキャスト
		UserDetails userDetails = (UserDetails) new User(loginUser.getId(), pwEncoder.encode(loginUser.getPw()),
				grantList);

		return userDetails;
	}

}