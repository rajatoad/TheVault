package com.revature.thevault.repository.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Table(name = "login_credential_table")
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginCredentialEntity {
   {
		// TODO Auto-generated constructor stub
	}
	@Id
    @Column(name = "pk_user_id")
    @GeneratedValue(generator = "login_credential_table_pk_user_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(allocationSize = 1, name = "login_credential_table_pk_user_id_seq", sequenceName = "login_credential_table_pk_user_id_seq")
    int pk_user_id;
    @Column(name = "username")
    String username;
    @Column(name = "password")
    String password;
    
	public LoginCredentialEntity(int pk_user_id, String username, String password) {
		super();
		this.pk_user_id = pk_user_id;
		this.username = username;
		this.password = password;
	}
	public int getPk_user_id() {
		return pk_user_id;
	}
	public void setPk_user_id(int pk_user_id) {
		this.pk_user_id = pk_user_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
    
}
