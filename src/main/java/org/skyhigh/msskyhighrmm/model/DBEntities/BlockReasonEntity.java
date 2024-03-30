package org.skyhigh.msskyhighrmm.model.DBEntities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.skyhigh.msskyhighrmm.model.SystemObjects.Generators.BlockReason.BlockReasonSequenceIdGenerator;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "block_reasons")
public class BlockReasonEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "block_reason_seq")
    @GenericGenerator(
            name = "block_reason_seq",
            strategy = "org.skyhigh.msskyhighrmm.model.SystemObjects.Generators.BlockReason.BlockReasonSequenceIdGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = BlockReasonSequenceIdGenerator.INCREMENT_PARAM, value = "1"), //было 50
                    @org.hibernate.annotations.Parameter(name = BlockReasonSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d")
            })
    private String id;

    @Column(name = "description", nullable = false)
    private String description;
}
