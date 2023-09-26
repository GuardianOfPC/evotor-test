package alexey.odinochenko.evotortest.service.rule;

import alexey.odinochenko.evotortest.data.entity.BlockRule;
import alexey.odinochenko.evotortest.data.entity.Rule;
import alexey.odinochenko.evotortest.data.request.AddBlockToRuleRequest;
import alexey.odinochenko.evotortest.data.request.CreateRuleRequest;
import alexey.odinochenko.evotortest.data.response.RuleDtoResponse;

import java.util.List;

public interface RuleService {
    RuleDtoResponse getById(Long id);

    List<RuleDtoResponse> getAll();

    Rule create(CreateRuleRequest request);

    RuleDtoResponse update(Long id, CreateRuleRequest request);

    void delete(Long id);

    List<RuleDtoResponse> getByGroupId(Long groupId);

    BlockRule addBlock(Long ruleId, AddBlockToRuleRequest request);
}
