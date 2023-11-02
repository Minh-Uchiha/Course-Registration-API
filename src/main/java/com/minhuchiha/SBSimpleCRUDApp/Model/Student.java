package com.minhuchiha.SBSimpleCRUDApp.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.minhuchiha.SBSimpleCRUDApp.Enum.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonFormat(with = JsonFormat.Feature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
public class Student implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID studentId;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @Min(0)
    @Max(4)
    @Column(name = "gpa", nullable = false, columnDefinition = "DECIMAL(3, 2) DEFAULT '4.0'")
    private double gpa;

    @Past
    @JsonFormat(pattern = "MM/dd/yyyy")
    private LocalDate dob;

    @NotNull(message = "Email is mandatory")
    @Pattern(regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$", flags = Pattern.Flag.CASE_INSENSITIVE, message = "Invalid email")
    @Column(unique = true)
    private String email;

    @NotNull(message = "Password is mandatory")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$", message = "A valid password must contain at least 1 digit, 1 lowercase character, 1 uppercase character, 1 special character and have length between 8 and 32")
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CourseParticipation> registrations;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
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
        return true;
    }
}


