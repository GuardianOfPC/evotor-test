package alexey.odinochenko.evotortest.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "rules")
public class Rule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Long groupId;

    /*@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "block_rule",
            joinColumns=  @JoinColumn(name="rule_id", referencedColumnName="id"),
            inverseJoinColumns= @JoinColumn(name="block_id", referencedColumnName="id"))*/
    @OneToMany(mappedBy = "rule")
    @JsonIgnore
    private List<BlockRule> blocks;
}