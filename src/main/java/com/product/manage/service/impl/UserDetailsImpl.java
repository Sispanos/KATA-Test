package com.product.manage.service.impl;

import com.product.manage.repository.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class UserDetailsImpl implements UserDetails {

    private final User user;

    public UserDetailsImpl(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Ici, vous pouvez ajouter des rôles ou des autorisations pour l'utilisateur
        // Par exemple, si l'utilisateur est un admin, vous pouvez retourner une autorisation "ROLE_ADMIN"
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail(); // Utilisez l'email comme identifiant
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Le compte n'est jamais expiré
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Le compte n'est jamais verrouillé
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Les informations d'identification ne sont jamais expirées
    }

    @Override
    public boolean isEnabled() {
        return true; // Le compte est toujours activé
    }
}