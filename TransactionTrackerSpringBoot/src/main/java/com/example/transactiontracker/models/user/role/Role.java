package com.example.transactiontracker.models.user.role;

import com.example.transactiontracker.models.base.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Data
@Entity
@Table(name = "roles")
@NoArgsConstructor
public class Role extends BaseEntity implements GrantedAuthority {

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private RoleType name;

    @Override
    public String getAuthority() {
        return name.toString();
    }
}