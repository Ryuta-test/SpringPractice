package jp.practice.spring.mybatis.dto;

public class MemoKey {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column develop.memo.id
	 * @mbg.generated  Fri May 01 19:12:19 JST 2020
	 */
	private String id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column develop.memo.title
	 * @mbg.generated  Fri May 01 19:12:19 JST 2020
	 */
	private String title;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column develop.memo.id
	 * @return  the value of develop.memo.id
	 * @mbg.generated  Fri May 01 19:12:19 JST 2020
	 */
	public String getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column develop.memo.id
	 * @param id  the value for develop.memo.id
	 * @mbg.generated  Fri May 01 19:12:19 JST 2020
	 */
	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column develop.memo.title
	 * @return  the value of develop.memo.title
	 * @mbg.generated  Fri May 01 19:12:19 JST 2020
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column develop.memo.title
	 * @param title  the value for develop.memo.title
	 * @mbg.generated  Fri May 01 19:12:19 JST 2020
	 */
	public void setTitle(String title) {
		this.title = title == null ? null : title.trim();
	}
}