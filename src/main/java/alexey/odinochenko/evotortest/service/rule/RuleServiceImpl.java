package alexey.odinochenko.evotortest.service.rule;

import alexey.odinochenko.evotortest.data.entity.Block;
import alexey.odinochenko.evotortest.data.entity.BlockRule;
import alexey.odinochenko.evotortest.data.entity.Rule;
import alexey.odinochenko.evotortest.data.exception.ResourceNotFound;
import alexey.odinochenko.evotortest.data.request.AddBlockToRuleRequest;
import alexey.odinochenko.evotortest.data.request.CreateRuleRequest;
import alexey.odinochenko.evotortest.data.response.RuleDtoResponse;
import alexey.odinochenko.evotortest.repository.BlockRepository;
import alexey.odinochenko.evotortest.repository.BlockRuleRepository;
import alexey.odinochenko.evotortest.repository.RuleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RuleServiceImpl implements RuleService {
    private final RuleRepository ruleRepository;
    private final BlockRepository blockRepository;
    private final BlockRuleRepository blockRuleRepository;
    @Override public RuleDtoResponse getById(Long id) {

        return mapToRuleDto(id);
    }

    @Override public List<RuleDtoResponse> getAll() {
        return ruleRepository.findAll().stream().map((e) -> mapToRuleDto(e.getId())).toList();
    }

    @Override public Rule create(CreateRuleRequest request) {
        var rule = Rule.builder()
                .name(request.getName())
                .groupId(request.getGroupId())
                .build();

        return ruleRepository.save(rule);
    }

    @Override public RuleDtoResponse update(Long id, CreateRuleRequest request) {
        Rule rule = ruleRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Rule not found with id " + id));
        rule.setName(request.getName());
        rule.setGroupId(request.getGroupId());
        rule = ruleRepository.save(rule);
        return mapToRuleDto(rule.getId());
    }

    @Override public void delete(Long id) {
        ruleRepository.deleteById(id);
    }

    @Override public List<RuleDtoResponse> getByGroupId(Long groupId) {
        return ruleRepository.findAllByGroupId(groupId).stream().map((e) -> mapToRuleDto(e.getId())).toList();
    }

    @Override public BlockRule addBlock(Long ruleId, AddBlockToRuleRequest request) {
        Rule rule = ruleRepository.findById(ruleId).orElseThrow(() -> new ResourceNotFound("Rule not found with id " + ruleId));
        Block block = blockRepository.findById(request.getBlockId()).orElseThrow(() -> new ResourceNotFound("Block not found with id " + request.getBlockId()));
        return blockRuleRepository.save(BlockRule.builder().rule(rule).block(block).rank(request.getRank()).build());
    }

    private RuleDtoResponse mapToRuleDto(Long id) {
        Rule rule = ruleRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Rule not found with id " + id));
        List<BlockRule> blockRules = blockRuleRepository.findAllByRule(rule).stream().sorted(Comparator.comparing(BlockRule::getRank)).toList();
        List<Block> blocks = blockRepository.findAllById(rule);

        List<Block> finalBlocks = new ArrayList<>(blocks.size());

        blockRules.forEach((e) -> {
            finalBlocks.add(blocks.stream().filter(block -> Objects.equals(block.getId(), e.getBlock().getId())).findAny().orElse(null));
        });
        return RuleDtoResponse.builder()
                .id(rule.getId())
                .name(rule.getName())
                .blocks(finalBlocks)
                .build();
    }
}
