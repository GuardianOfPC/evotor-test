package alexey.odinochenko.evotortest.service.block;

import alexey.odinochenko.evotortest.data.entity.Block;
import alexey.odinochenko.evotortest.data.exception.ResourceNotFound;
import alexey.odinochenko.evotortest.data.request.CreateBlockRequest;
import alexey.odinochenko.evotortest.repository.BlockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlockServiceImpl implements BlockService {
    private final BlockRepository blockRepository;
    @Override public Block getById(Long id) {
        return blockRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Block not found with id " + id));
    }

    @Override public List<Block> getAll() {
        return (List<Block>) blockRepository.findAll();
    }

    @Override public Block create(CreateBlockRequest request) {
        var block = Block.builder()
                .name(request.getName())
                .build();
        return blockRepository.save(block);
    }

    @Override public Block update(Long id, CreateBlockRequest request) {
        Block block = blockRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Block not found with id " + id));
        block.setName(request.getName());
        return blockRepository.save(block);
    }

    @Override public void delete(Long id) {
        blockRepository.deleteById(id);
    }
}
