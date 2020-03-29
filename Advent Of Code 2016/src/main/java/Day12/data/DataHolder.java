package Day12.data;

public interface DataHolder {
    default void setValue(Integer value) {
        throw new UnsupportedOperationException();
    }
    Integer getValue();
}