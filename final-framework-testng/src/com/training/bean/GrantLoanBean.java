package com.training.bean;

public class GrantLoanBean {
	private String memberLogin;
	private String amount;
	private String description;

	public GrantLoanBean() {
	}

	public GrantLoanBean(String memberLogin, String amount, String description) {
		super();
		this.memberLogin = memberLogin;
		this.amount = amount;
		this.description = description;
	}

	public String getMemberLogin() {
		return memberLogin;
	}

	public void setMemberLogin(String memberLogin) {
		this.memberLogin = memberLogin;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "GrantLoanBean [memberLogin=" + memberLogin + ", amount=" + amount + ",description=" + description +"]";
	}

}
