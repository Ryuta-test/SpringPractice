package jp.practice.spring.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.practice.spring.service.MemoService;
import jp.practice.spring.viewmodel.MemoViewModel;
import jp.practice.spring.viewmodel.ReqAjaxViewModel;
import jp.practice.spring.viewmodel.RetAjaxViewModel;

@Controller
public class MemoController {

	@Autowired
	MemoService memoService;

	@RequestMapping(value = "/memo", method = RequestMethod.GET)
	public String index(Model model) {
		// 認証済みユーザーのユーザー名を取得する
		// UserDetailsServiceImplでセットしたIdが取得できる
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String id = auth.getName();

		// ListのViewModelのインスタンスを生成
		List<MemoViewModel> memoViewModel = new ArrayList<MemoViewModel>();

		memoViewModel = memoService.getMemoViewModel(id);

		model.addAttribute("memoViewModel", memoViewModel);

		return "memo";
	}

	/**
	 * メモの追加
	 * 
	 * @param reqModel
	 * @return
	 */
	@RequestMapping(value = "/memo/insert", method = RequestMethod.POST)
	@ResponseBody
	public RetAjaxViewModel insertMemo(@RequestBody ReqAjaxViewModel reqModel) {

		// 認証済みユーザーのユーザー名を取得する
		// UserDetailsServiceImplでセットしたIdが取得できる
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String id = auth.getName();

		RetAjaxViewModel response = new RetAjaxViewModel();
		String title = reqModel.getTitle();
		String input = reqModel.getMemo();

		try {
			memoService.insertMemo(id, title, input);
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(-1);
			response.setMessage("メモを追加できませんでした。");
			return response;

		}

		response.setCode(0);
		response.setMessage("メモを追加しました。");

		// RetAjaxViewModelにgetterがないと、例外をスローする
		return response;
	}

	/**
	 * メモの更新
	 * 
	 * @param reqModel
	 * @return
	 */
	@RequestMapping(value = "/memo/update", method = RequestMethod.POST)
	@ResponseBody
	public RetAjaxViewModel updateMemo(@RequestBody ReqAjaxViewModel reqModel) {
		// 認証済みユーザーのユーザー名を取得する
		// UserDetailsServiceImplでセットしたIdが取得できる
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String id = auth.getName();
		String title = reqModel.getTitle();
		String memo = reqModel.getMemo();

		RetAjaxViewModel response = new RetAjaxViewModel();

		try {
			memoService.updateMemo(id, title, memo);
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(-1);
			response.setMessage("メモを更新できませんでした。");
			return response;

		}

		response.setCode(0);
		response.setMessage("メモを更新しました。");

		// RetAjaxViewModelにgetterがないと、例外をスローする
		return response;
	}

	/**
	 * メモの削除
	 * 
	 * @param reqModel
	 * @return
	 */
	@RequestMapping(value = "/memo/delete", method = RequestMethod.POST)
	@ResponseBody
	public RetAjaxViewModel deleteMemo(@RequestBody ReqAjaxViewModel reqModel) {
		// 認証済みユーザーのユーザー名を取得する
		// UserDetailsServiceImplでセットしたIdが取得できる
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String id = auth.getName();

		String title = reqModel.getTitle();

		RetAjaxViewModel response = new RetAjaxViewModel();

		try {
			memoService.deleteMemo(id, title);
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(-1);
			response.setMessage("メモを削除できませんでした。");
			return response;

		}

		response.setCode(0);
		response.setMessage("メモを削除しました。");

		// RetAjaxViewModelにgetterがないと、例外をスローする
		return response;
	}

}
