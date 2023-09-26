package alexey.odinochenko.evotortest.data.entity;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class RuleBlockId implements Serializable {
    private Long rule;
    private Long block;
}
