package jp.practice.spring.mybatis.mapper;

import java.util.List;
import jp.practice.spring.mybatis.dto.Memo;
import jp.practice.spring.mybatis.dto.MemoExample;
import org.apache.ibatis.annotations.Param;
import jp.practice.spring.mybatis.dto.MemoKey;

public interface MemoMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table develop.memo
	 * @mbg.generated  Fri May 01 19:12:19 JST 2020
	 */
	long countByExample(MemoExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table develop.memo
	 * @mbg.generated  Fri May 01 19:12:19 JST 2020
	 */
	int deleteByExample(MemoExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table develop.memo
	 * @mbg.generated  Fri May 01 19:12:19 JST 2020
	 */
	int deleteByPrimaryKey(MemoKey key);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table develop.memo
	 * @mbg.generated  Fri May 01 19:12:19 JST 2020
	 */
	int insert(Memo record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table develop.memo
	 * @mbg.generated  Fri May 01 19:12:19 JST 2020
	 */
	int insertSelective(Memo record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table develop.memo
	 * @mbg.generated  Fri May 01 19:12:19 JST 2020
	 */
	List<Memo> selectByExample(MemoExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table develop.memo
	 * @mbg.generated  Fri May 01 19:12:19 JST 2020
	 */
	Memo selectByPrimaryKey(MemoKey key);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table develop.memo
	 * @mbg.generated  Fri May 01 19:12:19 JST 2020
	 */
	int updateByExampleSelective(@Param("record") Memo record, @Param("example") MemoExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table develop.memo
	 * @mbg.generated  Fri May 01 19:12:19 JST 2020
	 */
	int updateByExample(@Param("record") Memo record, @Param("example") MemoExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table develop.memo
	 * @mbg.generated  Fri May 01 19:12:19 JST 2020
	 */
	int updateByPrimaryKeySelective(Memo record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table develop.memo
	 * @mbg.generated  Fri May 01 19:12:19 JST 2020
	 */
	int updateByPrimaryKey(Memo record);
}