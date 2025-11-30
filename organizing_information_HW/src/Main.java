//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {

    System.out.println(removeDuplicates(Arrays.asList(1,2,3,2,1,4)));

}

public static List<Integer> removeDuplicates(List<Integer> nums) {
    List<Integer> result = new ArrayList<>();
    for (Integer n : nums) {
        if (!result.contains(n)) result.add(n);
    }
    return result;
}
