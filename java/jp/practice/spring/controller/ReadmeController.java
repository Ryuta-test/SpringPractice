package jp.practice.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ReadmeController {

	@RequestMapping(value = "/Readme")
	public String index(Model model) {

		return "Readme";
	}

}