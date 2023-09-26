package alexey.odinochenko.evotortest.repository;

import alexey.odinochenko.evotortest.data.entity.Block;
import alexey.odinochenko.evotortest.data.entity.Rule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BlockRepository  extends JpaRepository<Block, Long> {
    @Query("SELECT b FROM Block b JOIN b.rule r WHERE r.rule = :rule")
    List<Block> findAllById(@Param("rule") Rule rule);
}
