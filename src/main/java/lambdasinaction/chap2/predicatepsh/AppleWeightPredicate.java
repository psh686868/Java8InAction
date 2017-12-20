package lambdasinaction.chap2.predicatepsh;

public class AppleWeightPredicate implements ApplePredicate{
    @Override
    public boolean test(FilteringApplesPSH.Apple apple) {
        return apple.getWeight() > 150;
    }
}
