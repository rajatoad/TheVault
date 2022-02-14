package com.revature.thevault.repository.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.sql.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

//@Data
////@NoArgsConstructor
////@AllArgsConstructor
////Requires variables to use LOMBOK, ONCE IMLEMENTED REMOVE THIS COMMENT AND INCLUDE LOMBOK
//@FieldDefaults(level = AccessLevel.PRIVATE)
//
@Entity
@Table(name = "withdraw_table")

public class WithdrawEntity {
	@Id
	@Column(name="unique_id")
    @GeneratedValue(generator = "withdraw_id_seq", strategy = GenerationType.AUTO)
	@SequenceGenerator(allocationSize = 1, name = "withdraw_id_seq", sequenceName = "withdraw_unique_id_seq")
	 private int withdraw_id;
	
	
	  @JoinColumn(name = "account_id")
	    private AccountEntity account;
	@ManyToOne(fetch = FetchType.EAGER)
	 @JoinColumn(name = "request_type_id")
    private RequestTypeEntity requestType  ;
@ManyToOne(fetch = FetchType.EAGER)
@JoinColumn(name = "request_status_id")
private RequestStatusEntity requestStatus;
@ManyToOne(fetch = FetchType.EAGER)
	
	
	
	  @Column(name = "amount")
	    private double amount;
	  @Column(name = "reference")
	    private String reference;
	  @Column(name = "date_withdraw")
	    private Date date_withdraw;
	
	public WithdrawEntity(int withdraw_id,AccountEntity account, RequestTypeEntity requestType, RequestStatusEntity requestStatus, String reference, Date date_withdraw, double amount  ) {
		this.withdraw_id = withdraw_id;
		this.account = account;
		this.requestType = requestType;
		this.requestStatus = requestStatus;
		this.reference = reference;
		this.date_withdraw = date_withdraw;
		this.amount = amount;
			
	}
	
	public AccountEntity getAccount() {
		return account;
	}
	
	  public void setAccount(AccountEntity account) {
	        this.account = account;
	    }
	  
	  public RequestTypeEntity getRequestType() {
		  return requestType;
	  }
	  
	  public void setRequestType(RequestTypeEntity requestType) {
		  this.requestType = requestType;
	  }
	  
	  public RequestStatusEntity getStatus() {
		return requestStatus;  
	  }
	  
	  public void setStatus(RequestStatusEntity requestStatus) {
		  this.requestStatus = requestStatus;
	  }
	  
	  
	  
	  public int getWithdrawId() {
		  return withdraw_id;
	  }
	  
	  public void setWithdrawId(int withdraw_id) {
		  this.withdraw_id = withdraw_id;
	  }
	  
	  public String getReference() {
		  return reference;
	  }
	  
	  public void setReference(String reference) {
		  this.reference = reference;
	  }
	  
	  public Date getDate() {
		  return date_withdraw;
	  }
	  
	  public void setDate(Date date_withdraw) {
		  this.date_withdraw = date_withdraw;
	  }
	  
	  public double getAmount() {
		  return amount;
	  }
	  
	  public void setAmount(double amount) {
		  this.amount = amount;
	  }
	  
	  @Override
	    public int hashCode() {
	        return Objects.hash(account, amount, requestStatus, requestType, date_withdraw, withdraw_id, reference);
	    }

	    @Override
	    public String toString() {
	        return "{\"CompletedRequestEntity\":{"
	                + "\"withdraw_id\":" + withdraw_id
	                + ", \"account\":" + account
	                + ", \"requestType\":" + requestType
	                + ", \"requestStatus\":\"" + requestStatus + "\""
	                + ", \"amount\":\"" + amount + "\""
	                + ", \"date_withdraw\":" + date_withdraw
	                + ", \"reference\":\"" + reference + "\""
	                + "}}";
	    }
	
	
	
}
	
	
