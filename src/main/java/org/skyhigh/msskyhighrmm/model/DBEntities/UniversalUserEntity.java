package org.skyhigh.msskyhighrmm.model.DBEntities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.skyhigh.msskyhighrmm.model.BusinessObjects.Users.UserInfo.UserInfo;

import java.util.UUID;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "universal_user")
public class UniversalUserEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "secret_id", nullable = false)
    private int secretId;

    @Column(name = "user_info", columnDefinition = "jsonb")
    @JdbcTypeCode(SqlTypes.JSON)
    private UserInfo user_info;

    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "block_reason_id", length = 10)
    private String block_reason_id;
}
