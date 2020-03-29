package Day12.data;

public class Number implements DataHolder {

    private Integer value;

    public Number(Integer value) {
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return this.value;
    }
}