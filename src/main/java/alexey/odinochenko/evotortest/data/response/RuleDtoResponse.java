package alexey.odinochenko.evotortest.data.response;

import alexey.odinochenko.evotortest.data.entity.Block;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RuleDtoResponse {
    private Long id;
    private String name;
    private List<Block> blocks;
}
