package io.pranludi.crossfit.member.repository.dto;

import io.pranludi.crossfit.member.domain.MemberGrade;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import java.time.LocalDateTime;
import java.util.Objects;
import org.springframework.data.domain.Persistable;

@Entity
@Table(name = "member")
public class MemberDTO implements Persistable<String> {

    @Id
    private String id;
    private String password;
    private String branchId;
    private String name;
    private String email;
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private MemberGrade grade;
    private LocalDateTime lastPaidAt;

    @Transient
    private boolean isNew = false;

    public MemberDTO() {
    }

    @Override
    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public MemberGrade getGrade() {
        return grade;
    }

    public void setGrade(MemberGrade grade) {
        this.grade = grade;
    }

    public LocalDateTime getLastPaidAt() {
        return lastPaidAt;
    }

    public void setLastPaidAt(LocalDateTime lastPaidAt) {
        this.lastPaidAt = lastPaidAt;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof MemberDTO memberDTO)) {
            return false;
        }
        return Objects.equals(id, memberDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "MemberDTO{" +
            "id='" + id + '\'' +
            ", password='" + password + '\'' +
            ", branchId='" + branchId + '\'' +
            ", name='" + name + '\'' +
            ", email='" + email + '\'' +
            ", phoneNumber='" + phoneNumber + '\'' +
            ", grade=" + grade +
            ", lastPaidAt=" + lastPaidAt +
            '}';
    }
}
