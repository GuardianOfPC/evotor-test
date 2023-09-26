package alexey.odinochenko.evotortest.service.block;

import alexey.odinochenko.evotortest.data.entity.Block;
import alexey.odinochenko.evotortest.data.request.CreateBlockRequest;

import java.util.List;

public interface BlockService {
    Block getById(Long id);

    List<Block> getAll();

    Block create(CreateBlockRequest request);

    Block update(Long id, CreateBlockRequest request);

    void delete(Long id);
}
