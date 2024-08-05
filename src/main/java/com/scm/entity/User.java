package com.scm.entity;

import java.io.Serializable;
import java.util.*;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "user")
@Table(name = "users")
public class User implements Serializable {

    @Id
    private String userid;

    @Column(name = "user_name", nullable = false)
    private String name;

    private String password;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(length = 1024)
    private String about;

    @Column(length = 1024)
    private String profilePic;

    private String phoneNumber;

    // Information
    // @Getter(value = AccessLevel.NONE)
    // @Setter
    private boolean isEnabled = false;

    private boolean emailVerified = false;
    private boolean phoneVerified = false;

    // SELF, GOOGLE GITHUB
    @Enumerated(value = jakarta.persistence.EnumType.STRING)
    private Providers provider = Providers.SELF;

    private String providerUserId;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Contact> contacts = new ArrayList<>();
}
