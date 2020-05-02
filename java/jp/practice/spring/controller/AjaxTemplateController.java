package jp.practice.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.practice.spring.viewmodel.ReqAjaxViewModel;
import jp.practice.spring.viewmodel.RetAjaxViewModel;

@Controller
public class AjaxTemplateController {

	@RequestMapping(value = { "/", "/ajaxTemplate" }, method = RequestMethod.GET)
	public String index(Model model) {

		return "ajaxTemplate";
	}

	@RequestMapping(value = "/ajaxGet", method = RequestMethod.GET)
	@ResponseBody
	public RetAjaxViewModel getAjax(@ModelAttribute("requestModel") ReqAjaxViewModel reqModel) {

		RetAjaxViewModel response = new RetAjaxViewModel();

		response.setCode(0);
		response.setMessage(reqModel.getTest() + " was success");

		// RetAjaxViewModelにgetterがないと、例外をスローする
		return response;
	}

	@RequestMapping(value = "/ajaxPost", method = RequestMethod.POST)
	@ResponseBody
	public RetAjaxViewModel postAjax(@RequestBody ReqAjaxViewModel reqModel) {

		RetAjaxViewModel response = new RetAjaxViewModel();

		response.setCode(0);
		response.setMessage(reqModel.getTest() + " was success");

		// RetAjaxViewModelにgetterがないと、例外をスローする
		return response;
	}

}
