package com.imarques.abeaudit_aws_lambda.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PaymentAuthorization {
	public enum AuthorizationStatus {
		SUCCESS, 
		ERROR
	}
	private Long id;
	private Transaction transaction;
	private AuthorizationStatus status;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime date;
	public Transaction getTransaction() {
		return transaction;
	}
	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}
	public AuthorizationStatus getStatus() {
		return status;
	}
	public void setStatus(AuthorizationStatus status) {
		this.status = status;
	}
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
}
