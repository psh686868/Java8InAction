package reactive.function;

@FunctionalInterface
interface DisplayData {
    String write(String item, Object... arguments);
}
