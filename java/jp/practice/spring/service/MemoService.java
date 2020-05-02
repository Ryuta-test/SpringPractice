package jp.practice.spring.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.practice.spring.mybatis.dto.Memo;
import jp.practice.spring.mybatis.dto.MemoExample;
import jp.practice.spring.mybatis.mapper.MemoMapper;
import jp.practice.spring.viewmodel.MemoViewModel;

@Service
public class MemoService {

	@Autowired
	private MemoMapper memoMapper;

	/**
	 * メモ情報取得処理
	 */
	public List<MemoViewModel> getMemoViewModel(String key) {
		// ListのViewModelのインスタンスを生成
		List<MemoViewModel> retViewModel = new ArrayList<MemoViewModel>();

		// SQLのwhere句等をセットするためのインスタンス
		MemoExample example = new MemoExample();

		// where句を指定する
		example.createCriteria().andIdEqualTo(key);

		// SQLのselectを実行する
		List<Memo> memoList = memoMapper.selectByExample(example);

		// マッパーを生成する
		ModelMapper modelMapper = new ModelMapper();

		for (int i = 0; i < memoList.size(); i++) {
			// 1レコード取得
			Memo getMemo = memoList.get(i);

			MemoViewModel memoView = new MemoViewModel();
			// ViewModelにマッピング
			memoView = modelMapper.map(getMemo, MemoViewModel.class);

			// 1レコードをリストに追加
			retViewModel.add(memoView);
		}

		return retViewModel;
	}

	/**
	 * 新規メモ作成
	 */
	public int insertMemo(String key, String title, String input) {

		Memo record = new Memo();
		record.setId(key);
		record.setMemo(input);
		record.setTitle(title);

		int memoList = memoMapper.insert(record);

		return memoList;
	}

	/**
	 * メモ更新
	 */
	public int updateMemo(String key, String title, String memo) {

		Memo record = new Memo();
		record.setId(key);
		record.setTitle(title);
		record.setMemo(memo);

		// SQLのwhere句等をセットするためのインスタンス
		MemoExample example = new MemoExample();

		// where句を指定する
		example.createCriteria().andIdEqualTo(key).andTitleEqualTo(title);

		int memoList = memoMapper.updateByExample(record, example);
		return memoList;
	}

	/**
	 * メモ削除
	 */
	public int deleteMemo(String key, String title) {
		// SQLのwhere句等をセットするためのインスタンス
		MemoExample example = new MemoExample();

		// where句を指定する
		example.createCriteria().andIdEqualTo(key).andTitleEqualTo(title);

		int memoList = memoMapper.deleteByExample(example);

		return memoList;
	}

}
