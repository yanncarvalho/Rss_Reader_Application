package br.com.yann.rssreader.auth;

public enum RolesEnum {
  ADMIN("admin"),
	USER("user");

	private String role;

	public String getRole() {
		return this.role;
	}

	RolesEnum(String role) {
		this.role = role;
	}
}