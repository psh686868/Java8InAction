package lambdasinaction.chap10.psh;


import lambdasinaction.chap10.Insurance;


public class OperationsWithOptional {
    public static final String Unknown = "Unknown";

    public static void main(String[] args) {

    }

    public String getCarInsuranceName(PersonWithoutOption person){
        if(person==null){
            return OperationsWithOptional.Unknown;
        }
        CarWithOutOption car = person.getCar();
        if(car==null){
            return OperationsWithOptional.Unknown;
        }
        Insurance insurance = car.getInsurance();
        if(insurance==null){
            return OperationsWithOptional.Unknown;
        }
        return insurance.getName();
    }
}
