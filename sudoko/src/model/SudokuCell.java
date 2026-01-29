package model;

public class SudokuCell {

    private  int value;
    private final boolean fixed;

    public SudokuCell(int value) {
        this.value = value;
        this.fixed = value != 0;
    }

    public int getValue() {
        return value;
    }

    public boolean isEmpty() {
        return value == 0;
    }

    public boolean isFixed() {
        return fixed;
    }

    public void setValue(int value) {
        if (!fixed) {
            this.value = value;
        }
    }
}
