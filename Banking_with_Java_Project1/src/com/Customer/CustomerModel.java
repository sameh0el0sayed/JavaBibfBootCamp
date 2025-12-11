package com.Customer;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.mindrot.jbcrypt.BCrypt;

import java.util.Objects;
import java.util.Optional;

public final  class CustomerModel {

    private final String id;
    private final String firstName;
    private final String middleName;   // stored raw, exposed as Optional
    private final String lastName;
    private final String UserName;
    private final String passwordHash;  // store only the hashed password

    /**
     * Jackson-compatible constructor.
     */
    @JsonCreator
    public CustomerModel(
            @JsonProperty("id") String id,
            @JsonProperty("firstName") String firstName,
            @JsonProperty("middleName") String middleName,
            @JsonProperty("lastName") String lastName,
            @JsonProperty("userName") String UserName,
            @JsonProperty("passwordHash") String passwordHash) {

        this.id = Objects.requireNonNull(id, "id");
        this.firstName = Objects.requireNonNull(firstName, "firstName");
        this.middleName = Objects.requireNonNull(lastName, "middleName"); // may be null
        this.lastName = Objects.requireNonNull(lastName, "lastName");
        this.UserName = Objects.requireNonNull(UserName, "userName");
        this.passwordHash =  Objects.requireNonNull(passwordHash, "passwordHash");
    }


    // ─────────────── Getters ───────────────

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUserName() {
        return UserName;
    }

    public String getPasswordHash() {
        return passwordHash;
    }



}

