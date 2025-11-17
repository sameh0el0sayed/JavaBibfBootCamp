//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {
    //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
    // to see how IntelliJ IDEA suggests fixing it.
 ArrayList<String> fav_tings=new ArrayList<>();
    fav_tings.add("keys");
    fav_tings.add("bag");
    fav_tings.add("laptop");

    ArrayList<String> other_tings=new ArrayList<>();
    other_tings.add("food");
    fav_tings.addAll(0,other_tings);
    //System.out.println(fav_tings.size());
    //PrintData(fav_tings);

    // Home work of the Collections and LAB
// Home work of the Collections and LAB
// Home work of the Collections and LAB


    LinkedList<String> names = new LinkedList<>();

// Add elements
    names.add("Ali");
    names.add("Sara");
    names.add("Hassan");

// Add at first and last
    names.addFirst("First");
    names.addLast("Last");

// Access elements
    System.out.println("First element: " + names.getFirst());
    System.out.println("Last element: " + names.getLast());

    // Remove elements
    names.removeFirst();
    names.removeLast();
    names.remove("Sara");

// Loop
    for (String name : names) {
        System.out.println(name);
    }


}

 private  void PrintData(ArrayList<String> mythings){
     System.out.println("Hi");
     System.out.println("Size is "+mythings.size());
    for(int i=0;i<=mythings.size()-1 ;i++){
        System.out.println("index "+i+":"+mythings.get(i));
    }
 }



