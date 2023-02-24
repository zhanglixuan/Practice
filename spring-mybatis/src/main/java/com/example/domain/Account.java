package com.example.domain;

/**
 * @author 张丽璇
 * @date 2023/2/21
 */
public class Account {
	private Integer id;
	private String account;
	private Double money;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	@Override
	public String toString() {
		return "Account{" +
				"id=" + id +
				", account='" + account + '\'' +
				", money=" + money +
				'}';
	}
}
