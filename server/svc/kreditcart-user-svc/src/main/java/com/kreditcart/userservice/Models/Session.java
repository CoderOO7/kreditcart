package com.kreditcart.userservice.Models;

import com.kreditcart.userservice.Enums.SessionStatusEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name="user_sessions")
public class Session extends  BaseModel {
    @Column(nullable = false)
    private String token;

    @Column(nullable = false)
    private Date expiryTime;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SessionStatusEnum sessionStatus = SessionStatusEnum.ACTIVE;
}
