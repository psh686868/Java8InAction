package lambdasinaction.chap10.psh;

import java.util.Optional;

/**
 *
 */
public class CarPSH {
    private Optional<InsurancePSH> insurancePSH;

    public Optional<InsurancePSH> getInsurancePSH() {
        return insurancePSH;
    }

    public void setInsurancePSH(Optional<InsurancePSH> insurancePSH) {
        this.insurancePSH = insurancePSH;
    }
}
