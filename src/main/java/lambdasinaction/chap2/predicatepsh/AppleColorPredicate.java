package lambdasinaction.chap2.predicatepsh;

public class AppleColorPredicate implements ApplePredicate{
    @Override
    public boolean test(FilteringApplesPSH.Apple apple) {
        return "green".equals(apple.getColor());    }
}
