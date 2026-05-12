package com.ga.deliverysystem.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ga.deliverysystem.Model.Enum.UserRoleEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "Users")
public class UserModel {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String userName;

    @Column(unique = true)
    private String emailAddress;

    @Column
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role",nullable = false)
    private UserRoleEnum role;

    @OneToOne(
            cascade = CascadeType.ALL, fetch = FetchType.EAGER
    )
    @JoinColumn(name = "profile_id", referencedColumnName = "id")
    private UserProfileModel userProfileModel;
    public Long getId() {
        return id;
    }
    private  boolean Activated;

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRoleEnum getRole() {
        return role;
    }

    public void setRole(UserRoleEnum role) {
        this.role = role;
    }

    public UserProfileModel getUserProfile() {
        return userProfileModel;
    }

    public void setUserProfile(UserProfileModel userProfileModel) {
        this.userProfileModel = userProfileModel;
    }

    public boolean isActivated() {
        return Activated;
    }

    public void setActivated(boolean activated) {
        Activated = activated;
    }
}
