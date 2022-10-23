package Model;

@FunctionalInterface
public interface CallableLogic {
    public boolean call(boolean... vs);
}
