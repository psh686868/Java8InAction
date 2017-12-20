
package lambdasinaction.chap10.psh;

import lambdasinaction.chap10.Car;
import lambdasinaction.chap10.Insurance;
import lambdasinaction.chap10.Person;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class OptionalMainPSH {

    public String getCarInsuranceName(Optional<Person> person) {


        return person.flatMap(Person::getCar)
                     .flatMap(Car::getInsurance)
                     .map(Insurance::getName)
                     .orElse("Unknown");
    }

   /* public Set<String> getCarInsuranceNames(List<Person> persons) {
        return persons.stream()
                      .map(Person::getCar)
                      .map(optCar -> optCar.flatMap(Car::getInsurance))
                      .map(optInsurance -> optInsurance.map(Insurance::getName))
                      .flatMap(Optional::)
                      .collect(toSet());
    }*/

    public static void main(String[] args) {
        String name = null;
        InsurancePSH insurancePSH = new InsurancePSH();
        if(insurancePSH!=null){
            name = insurancePSH.getName();
        }

        System.out.println(name);
        Optional<InsurancePSH> insurancePSHOptional = Optional.ofNullable(insurancePSH);
        Optional<String> nameOptional = insurancePSHOptional.map(InsurancePSH::getName);
        if(nameOptional.isPresent()){
            String s = nameOptional.get();
            System.out.println(s);
        }

        PersonPsh personPsh = new PersonPsh();

        Optional<PersonPsh> personPsh1 = Optional.of(personPsh);
        String unknown = personPsh1.filter(p -> {
                    if (p.getAge()==null) return Boolean.FALSE;
                  return p.getAge() >= 13;
                 })
                .flatMap(PersonPsh::getCarPSH)
                .flatMap(CarPSH::getInsurancePSH)
                .map(InsurancePSH::getName)
                .orElse("Unknown");
        System.out.println(unknown);
        new CarPSH();

    }
}

