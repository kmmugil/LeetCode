import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class prob_341 {
    public static void main(String[] args) {
        List<NestedInteger> nestedList = new ArrayList<>();
        NestedInteger n1 = new TestNestedInteger(1);
        NestedInteger n2 = new TestNestedInteger(2);
        List<NestedInteger> l1 = new ArrayList<>();
        l1.add(n1); l1.add(n1);
        nestedList.add(new TestNestedInteger(l1));
        nestedList.add(n2);
        nestedList.add(new TestNestedInteger(l1));
        List<NestedInteger> nestedList1 = new ArrayList<>();
        NestedInteger n3 = new TestNestedInteger(new ArrayList<>());
        List<NestedInteger> l2 = new ArrayList<>();
        l2.add(n3);
        NestedInteger n4 = new TestNestedInteger(l2);
        List<NestedInteger> l3 = new ArrayList<>();
        l3.add(n4);
        NestedInteger n5 = new TestNestedInteger(l3);
        nestedList1.add(n5);
        nestedList1.add(n3);
        List<Integer> flattenedList = new ArrayList<>();
        System.out.println(nestedList);
        NestedIterator i = new NestedIterator(nestedList);
        System.out.println(i.hasNext());
        while(i.hasNext()) {
            flattenedList.add(i.next());
            System.out.println(flattenedList);
        }
        System.out.println(flattenedList);
    }
}

class NestedIterator implements Iterator<Integer> {

    private final List<NestedInteger> nestedList;
    private final Stack<NestedInteger> nestedIntegers = new Stack<>();
    private final Stack<Integer> indices = new Stack<>();
    private int listIndex = -1;

    public NestedIterator(List<NestedInteger> nestedList) {
        this.nestedList = nestedList;
        this.shiftRight();
    }

    @Override
    public Integer next() {
        int next = nestedIntegers.pop().getInteger();
        this.shiftRight();
        return next;
    }

    @Override
    public boolean hasNext() {
        return !nestedIntegers.isEmpty();
    }

    private void shiftRight() {
        while(!nestedIntegers.isEmpty() && nestedIntegers.peek().getList().size() <= indices.peek()+1) {
            nestedIntegers.pop();
            indices.pop();
        }
        if(!nestedIntegers.empty()) {
            int index = indices.pop()+1;
            indices.push(index);
            NestedInteger nestedInteger = nestedIntegers.peek().getList().get(indices.peek());
            if (!nestedInteger.isInteger()) {
                indices.push(-1);
                nestedIntegers.push(nestedInteger);
                this.shiftRight();
            } else {
                nestedIntegers.push(nestedIntegers.peek().getList().get(indices.peek()));
            }
        } else if(listIndex+1 < nestedList.size()) {
            listIndex++;
            indices.clear();
            indices.push(-1);
            NestedInteger nestedInteger = nestedList.get(listIndex);
            if (!nestedInteger.isInteger()) {
                indices.push(-1);
                nestedIntegers.push(nestedInteger);
                this.shiftRight();
            } else {
                nestedIntegers.push(nestedList.get(listIndex));
            }
        }
    }
}

interface NestedInteger {
    // @return true if this NestedInteger holds a single integer, rather than a nested list.
    boolean isInteger();

    // @return the single integer that this NestedInteger holds, if it holds a single integer
    // Return null if this NestedInteger holds a nested list
    Integer getInteger();

    // @return the nested list that this NestedInteger holds, if it holds a nested list
    // Return empty list if this NestedInteger holds a single integer
    List<NestedInteger> getList();
}

class TestNestedInteger implements NestedInteger {
    private Integer integer = null;
    private List<NestedInteger> list = null;

    public TestNestedInteger(Integer integer) {
        this.integer = integer;
    }

    public TestNestedInteger(List<NestedInteger> list) {
        this.list = list;
    }

    @Override
    public boolean isInteger() {
        return integer != null;
    }

    @Override
    public Integer getInteger() {
        return integer;
    }

    public void setInteger(Integer integer) {
        this.integer = integer;
    }

    @Override
    public List<NestedInteger> getList() {
        return list;
    }

    public void setList(List<NestedInteger> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return isInteger() ? integer.toString() : list.toString();
    }
}
