package stack;

import edu.rutgers.cs112.LL.LLNode;

public class PancakeStack {
    private LLNode<String> topPancake;
    private int numPancakes;

    public PancakeStack() {
        topPancake = null;
        numPancakes = 0;
    }

    public void push(String pancakeType) {
        LLNode<String> n = new LLNode<>(pancakeType);
        n.setNext(topPancake);
        topPancake = n;
        numPancakes++;
    }

    public boolean isEmpty() {
        return topPancake == null;
    }

    public String pop() {
        if (topPancake == null)
            return null;
        String d = topPancake.getData();
        topPancake = topPancake.getNext();
        numPancakes--;
        return d;
    }

    public void reverse() {
        PancakeStack t = new PancakeStack();
        PancakeStack t2 = new PancakeStack();
        while (!this.isEmpty())
            t.push(this.pop());
        while (!t.isEmpty())
            t2.push(t.pop());
        while (!t2.isEmpty())
            this.push(t2.pop());
    }

    public LLNode<String> getStack() {
        return topPancake;
    }

    public int getNumPancakes() {
        return numPancakes;
    }
}
