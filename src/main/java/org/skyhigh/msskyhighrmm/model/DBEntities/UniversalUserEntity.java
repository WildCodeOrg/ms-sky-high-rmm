package org.skyhigh.msskyhighrmm.model.DBEntities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;

import org.hibernate.type.SqlTypes;
import org.hibernate.validator.constraints.Length;

import org.skyhigh.msskyhighrmm.model.BusinessObjects.Users.UserInfo.UserInfo;


import java.util.UUID;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "universal_user")
public class UniversalUserEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "login", nullable = false)
    @Length(min = 6, max = 20)
    private String login;

    @Column(name = "password", nullable = false)
    @Length(min = 8, max = 20)
    private String password;

    @Column(name = "user_info", columnDefinition = "jsonb")
    @JdbcTypeCode(SqlTypes.JSON)
    private UserInfo user_info;

    @Column(name = "block_reason_id", length = 10)
    private String block_reason_id;
}
