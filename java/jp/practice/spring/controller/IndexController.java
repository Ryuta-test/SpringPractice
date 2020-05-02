package jp.practice.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.practice.spring.viewmodel.LoginViewModel;

@Controller
@PropertySource(value = "classpath:messages_ja.properties")
public class IndexController {

	@Autowired
	MessageSource messageSource;

	@RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
	public String index(Model model) {

		// 認証済みユーザーのユーザー名を取得する
		// UserDetailsServiceImplでセットしたIdが取得できる
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String id = auth.getName();

		LoginViewModel loginViewModel = new LoginViewModel();
		loginViewModel.setId(id);
		model.addAttribute("loginViewModel", loginViewModel);

		return "index";
	}

}
