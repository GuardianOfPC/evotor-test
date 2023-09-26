package alexey.odinochenko.evotortest.repository;

import alexey.odinochenko.evotortest.data.entity.BlockRule;
import alexey.odinochenko.evotortest.data.entity.Rule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlockRuleRepository extends JpaRepository<BlockRule, Long> {
    List<BlockRule> findAllByRule(Rule rule);
}
