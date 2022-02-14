package br.com.yann.rssreader.entity;

public enum UserRole {

    ADMIN("ADMIN"),
    BASIC("BASIC");

    private String role;

    public String getRole() {
      return this.role;
    }

    UserRole(String role) {
      this.role = role;
    }

}
