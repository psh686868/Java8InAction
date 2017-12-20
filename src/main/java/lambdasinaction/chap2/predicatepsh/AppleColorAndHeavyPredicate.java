package lambdasinaction.chap2.predicatepsh;

public class AppleColorAndHeavyPredicate implements ApplePredicate{
    @Override
    public boolean test(FilteringApplesPSH.Apple apple) {
        return "red".equals(apple.getColor())
                && apple.getWeight() > 150;
    }
}
