package com.example.data_collection.entity;

import javax.persistence.*;

/**
* Create by Generate...
* Date: Tue Oct 12 16:40:55 CST 2021
* 
* @author fhrdr
*/
@Entity
@Table(name = "t_admin")
public class Admin {

	/**
	 * 管理员ID
	 */
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	/**
	 * 管理员账号
	 */
	@Column(name = "name" )
	private String name;
	/**
	 * 管理员密码
	 */
	@Column(name = "password" )
	private String password;


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

}
