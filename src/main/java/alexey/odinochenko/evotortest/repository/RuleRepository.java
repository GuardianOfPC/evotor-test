package alexey.odinochenko.evotortest.repository;

import alexey.odinochenko.evotortest.data.entity.Rule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RuleRepository extends JpaRepository<Rule, Long> {
    List<Rule> findAllByGroupId(Long groupId);
}
