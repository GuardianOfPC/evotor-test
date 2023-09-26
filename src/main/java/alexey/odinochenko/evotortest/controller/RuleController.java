package alexey.odinochenko.evotortest.controller;

import alexey.odinochenko.evotortest.data.entity.BlockRule;
import alexey.odinochenko.evotortest.data.entity.Rule;
import alexey.odinochenko.evotortest.data.request.AddBlockToRuleRequest;
import alexey.odinochenko.evotortest.data.request.CreateRuleRequest;
import alexey.odinochenko.evotortest.data.response.RuleDtoResponse;
import alexey.odinochenko.evotortest.service.rule.RuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rule")
@RequiredArgsConstructor
public class RuleController {
    private final RuleService ruleService;

    @GetMapping("/{id}")
    public ResponseEntity<RuleDtoResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(ruleService.getById(id));
    }

    @GetMapping("")
    public ResponseEntity<List<RuleDtoResponse>> getAll() {
        return ResponseEntity.ok(ruleService.getAll());
    }

    @PostMapping("")
    public ResponseEntity<Rule> create(@RequestBody CreateRuleRequest request) {
        return ResponseEntity.ok(ruleService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RuleDtoResponse> update(@PathVariable Long id, @RequestBody CreateRuleRequest request) {
        return ResponseEntity.ok(ruleService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        ruleService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/group-id")
    public ResponseEntity<List<RuleDtoResponse>> getByGroupId(@RequestHeader(value="Group-Id") Long groupId) {
        return ResponseEntity.ok(ruleService.getByGroupId(groupId));
    }

    @PostMapping("/{id}")
    public ResponseEntity<BlockRule> addBlock(@PathVariable Long id, @RequestBody AddBlockToRuleRequest request) {
        return ResponseEntity.ok(ruleService.addBlock(id, request));
    }
}
