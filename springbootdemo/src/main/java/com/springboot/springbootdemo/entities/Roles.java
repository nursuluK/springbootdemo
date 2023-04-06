package com.springboot.springbootdemo.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name="t_roles")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Roles implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="role")
    private String role;

    @Override
    public String getAuthority() {
        return this.role;
    }
}
