class StackNode<T> {
    private T value;
    private StackNode<T> next;
    
    T getValue() {
        return value;
    }
    
    StackNode<T> getNext() {
        return next;
    }
    
    StackNode(T value, StackNode<T> next) {
        this.value = value;
        this.next = next;
    }
}
