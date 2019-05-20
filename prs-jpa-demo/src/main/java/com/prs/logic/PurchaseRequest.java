package com.prs.logic;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class PurchaseRequest {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name = "userID")
	private User user;
	private String description;
	private String justification;
	private LocalDate dateneeded;
	private String deliveryMode;
	private String status = "new";
	private double total;
	private LocalDateTime submittedDate;
	private String reasonForRejection;
	
	public PurchaseRequest() {
		super();
	}

	public PurchaseRequest(int id, User user, String description, String justification, LocalDate dateneeded, String deliveryMode,
			String status, double total, LocalDateTime submittedDate, String reasonForRejection) {
		super();
		this.id = id;
		this.user = user;
		this.description = description;
		this.dateneeded = dateneeded;
		this.deliveryMode = deliveryMode;
		this.status = status;
		this.total = total;
		this.submittedDate = submittedDate;
		this.reasonForRejection = reasonForRejection;
	}

	public PurchaseRequest(User user, String description, String justification, LocalDate dateneeded, String deliveryMode, LocalDateTime submittedDate) {
		super();
		this.user = user;
		this.description = description;
		this.justification = justification;
		this.dateneeded = dateneeded;
		this.deliveryMode = deliveryMode;
		this.total = total;
		this.submittedDate = submittedDate;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getDescriptiton() {
		return description;
	}

	public void setDescriptiton(String description) {
		this.description = description;
	}

	public LocalDate getDate() {
		return dateneeded;
	}

	public void setDate(LocalDate dateneeded) {
		this.dateneeded = dateneeded;
	}

	public String getDeliveryMode() {
		return deliveryMode;
	}

	public void setDeliveryMode(String deliveryMode) {
		this.deliveryMode = deliveryMode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public LocalDateTime getSubmittedDate() {
		return submittedDate;
	}

	public void setSubmittedDate(LocalDateTime submittedDate) {
		this.submittedDate = submittedDate;
	}

	public String getReasonForRejection() {
		return reasonForRejection;
	}

	public void setReasonForRejection(String reasonForRejection) {
		this.reasonForRejection = reasonForRejection;
	}

	@Override
	public String toString() {
		return "\nPurchaseRequest: id=" + id + ", user=" + user.getUserName() + ", description=" + description + ", dateneeded=" + dateneeded
				+ ", deliveryMode=" + deliveryMode + ", status=" + status + ", total=" + total + ", submittedDate="
				+ submittedDate + ", reasonForRejection=" + reasonForRejection;
	}
	
	
}
