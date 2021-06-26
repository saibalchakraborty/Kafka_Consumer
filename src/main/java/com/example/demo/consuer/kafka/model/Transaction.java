package com.example.demo.consuer.kafka.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;
	@Column(name = "TRANSACTION_ID")
	private String transactionId;
	@Column(name = "SUCCESS")
	private boolean success;
	@Column(name = "MESSAGE")
	private String msg;
	
	public Transaction() {
	}

	public Transaction(String transactionId, boolean success, String msg) {
		super();
		this.transactionId = transactionId;
		this.success = success;
		this.msg = msg;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
