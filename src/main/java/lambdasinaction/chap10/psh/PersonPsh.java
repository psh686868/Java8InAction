package lambdasinaction.chap10.psh;

import java.util.Optional;

/**
 *
 */
public class PersonPsh {
    private Optional<CarPSH> carPSH;
    private Integer age;

    public Optional<CarPSH> getCarPSH() {
        return carPSH;
    }

    public void setCarPSH(Optional<CarPSH> carPSH) {
        this.carPSH = carPSH;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
