package andry.hais.appointment_api.vo.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class PricesForm {

    @NotEmpty
    private String subject;

    @NotNull
    private Integer fifteenMinPrice;

    @NotNull
    private Integer halfAnHourPrice;

    @NotNull
    private Integer hourPrice;
}
