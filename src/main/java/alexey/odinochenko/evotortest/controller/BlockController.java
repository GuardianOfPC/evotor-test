package alexey.odinochenko.evotortest.controller;

import alexey.odinochenko.evotortest.data.entity.Block;
import alexey.odinochenko.evotortest.data.request.CreateBlockRequest;
import alexey.odinochenko.evotortest.service.block.BlockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/block")
@RequiredArgsConstructor
public class BlockController {
    private final BlockService blockService;

    @GetMapping("")
    public ResponseEntity<List<Block>> getAll() {
        return ResponseEntity.ok(blockService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Block> getById(@PathVariable Long id) {
        return ResponseEntity.ok(blockService.getById(id));
    }

    @PostMapping("")
    public ResponseEntity<Block> create(@RequestBody CreateBlockRequest request) {
        return ResponseEntity.ok(blockService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Block> update(@PathVariable Long id, @RequestBody CreateBlockRequest request) {
        return ResponseEntity.ok(blockService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        blockService.delete(id);
        return ResponseEntity.ok().build();
    }

}
