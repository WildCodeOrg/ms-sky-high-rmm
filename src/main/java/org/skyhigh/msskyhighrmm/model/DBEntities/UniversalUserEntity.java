package org.skyhigh.msskyhighrmm.model.DBEntities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
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
    @Length(min = 8, max = 120)
    private String password;

    @Column(name = "user_info", columnDefinition = "jsonb")
    @JdbcTypeCode(SqlTypes.JSON)
    private UserInfo user_info;

    @Column(name = "block_reason_id", length = 10)
    private String block_reason_id;

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj) || (
                (id == null ? ((UniversalUserEntity) obj).getId() == null : id.equals(((UniversalUserEntity) obj).getId()))
                || (login == null ? ((UniversalUserEntity) obj).getLogin() == null : login.equals(((UniversalUserEntity) obj).getLogin()))
                || (password == null ? ((UniversalUserEntity) obj).getPassword() == null : password.equals(((UniversalUserEntity) obj).getPassword()))
                || (user_info == null ? ((UniversalUserEntity) obj).getUser_info() == null : user_info.equals(((UniversalUserEntity) obj).getUser_info()))
                || (block_reason_id == null ? ((UniversalUserEntity) obj).getBlock_reason_id() == null : block_reason_id.equals(((UniversalUserEntity) obj).getBlock_reason_id()))
        );
    }
}
