package alexey.odinochenko.evotortest.data.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddBlockToRuleRequest {
    private Long blockId;
    private Long rank;
}
