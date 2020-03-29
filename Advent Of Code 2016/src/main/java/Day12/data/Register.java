package Day12.data;

public class Register implements DataHolder {

    private Integer value=0;
    private String name;

    public Register(String name){
        this.name = name;
    }

    @Override
    public void setValue(Integer value) {
        this.value = value;
    }

    public void increaseValue() {
        this.value++;
    }

    public void decreaseValue() {
        this.value--;
    }

    @Override
    public Integer getValue() {
        return this.value;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + "[" + value + "]";
    }
}