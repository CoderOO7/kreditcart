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
    private String token;

    private Date expiryTime;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.ORDINAL)
    private SessionStatusEnum sessionStatus;
}
