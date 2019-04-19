class Stack<T> {
    private StackNode<T> curr;
    
    Stack() {
        this.curr = null;
    }
    
    void push(T value) {
        curr = new StackNode(value, curr);
    }
    
    T peek() {
        return isEmpty() ? null : curr.getValue();
    }
    
    T pop() {
        T value = peek();
        curr = curr.getNext();
        return value;
    }
    
    boolean isEmpty() {
        return curr == null;
    }
}
