package jp.practice.spring.mybatis.dto;

import java.util.Date;

public class Login {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column develop.login.id
	 * @mbg.generated  Fri May 01 19:12:19 JST 2020
	 */
	private String id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column develop.login.pw
	 * @mbg.generated  Fri May 01 19:12:19 JST 2020
	 */
	private String pw;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column develop.login.update_user
	 * @mbg.generated  Fri May 01 19:12:19 JST 2020
	 */
	private String updateUser;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column develop.login.update_date
	 * @mbg.generated  Fri May 01 19:12:19 JST 2020
	 */
	private Date updateDate;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column develop.login.id
	 * @return  the value of develop.login.id
	 * @mbg.generated  Fri May 01 19:12:19 JST 2020
	 */
	public String getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column develop.login.id
	 * @param id  the value for develop.login.id
	 * @mbg.generated  Fri May 01 19:12:19 JST 2020
	 */
	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column develop.login.pw
	 * @return  the value of develop.login.pw
	 * @mbg.generated  Fri May 01 19:12:19 JST 2020
	 */
	public String getPw() {
		return pw;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column develop.login.pw
	 * @param pw  the value for develop.login.pw
	 * @mbg.generated  Fri May 01 19:12:19 JST 2020
	 */
	public void setPw(String pw) {
		this.pw = pw == null ? null : pw.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column develop.login.update_user
	 * @return  the value of develop.login.update_user
	 * @mbg.generated  Fri May 01 19:12:19 JST 2020
	 */
	public String getUpdateUser() {
		return updateUser;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column develop.login.update_user
	 * @param updateUser  the value for develop.login.update_user
	 * @mbg.generated  Fri May 01 19:12:19 JST 2020
	 */
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser == null ? null : updateUser.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column develop.login.update_date
	 * @return  the value of develop.login.update_date
	 * @mbg.generated  Fri May 01 19:12:19 JST 2020
	 */
	public Date getUpdateDate() {
		return updateDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column develop.login.update_date
	 * @param updateDate  the value for develop.login.update_date
	 * @mbg.generated  Fri May 01 19:12:19 JST 2020
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
}