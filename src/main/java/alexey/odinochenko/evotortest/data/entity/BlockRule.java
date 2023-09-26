package alexey.odinochenko.evotortest.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "block_rule")
@IdClass(RuleBlockId.class)
public class BlockRule implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "rule_id", referencedColumnName = "id")
    private Rule rule;

    @Id
    @ManyToOne
    @JoinColumn(name = "block_id", referencedColumnName = "id")
    private Block block;

    private Long rank;
}
