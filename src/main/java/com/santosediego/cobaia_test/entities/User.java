package com.santosediego.cobaia_test.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.santosediego.cobaia_test.entities.enums.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(schema = "auth", name = "tb_user")
public class User implements Serializable, UserDetails {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, length = 120)
	private String name;
	@Column(unique = true, nullable = false, length = 120)
	private String username;
	@Column(unique = true, length = 120)
	private String email;
	@Column(nullable = false)
	private String password;
	@Column(nullable = false)
	private Boolean active = true;
	@Column(nullable = false)
	private String role;
	@Column(name = "created_at", columnDefinition = "TIMESTAMP WITHOUT TIME ZONE", updatable = false)
	private Instant createdAt;
	@Column(name = "updated_at", columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private Instant updatedAt;

	public User() {
	}

	public User(Long id, String name, String username, String email, String password, Boolean active, String role,
			Instant createdAt, Instant updatedAt) {
		this.id = id;
		this.name = name;
		this.username = username;
		this.email = email;
		this.password = password;
		this.active = active;
		this.role = role;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public static User of(String name, String username, String email, String password, Role role) {
		User u = new User();
		u.name = name;
		u.username = username;
		u.email = email;
		u.password = password;
		u.role = (role == null ? Role.USER : role).getId();
		u.active = true;
		return u;
	}

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

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = (active != null) ? active : true;
	}

	public Role getRole() {
		return Role.fromId(role);
	}

	public void setRole(Role role) {
		this.role = role.getId();
	}

	public Instant getCreatedAt() {
		return createdAt;
	}

	public Instant getUpdatedAt() {
		return updatedAt;
	}

	@PrePersist
	public void prePersist() {
		createdAt = Instant.now();
	}

	@PreUpdate
	public void preUpdate() {
		updatedAt = Instant.now();
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Role roleEnum = Role.fromId(this.role);

	    if (roleEnum == Role.ADMIN) {
	        return List.of(
	            new SimpleGrantedAuthority("ROLE_ADMIN"),
	            new SimpleGrantedAuthority("ROLE_USER")
	        );
	    }

	    return List.of(new SimpleGrantedAuthority("ROLE_USER"));
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return active;
	}
}
