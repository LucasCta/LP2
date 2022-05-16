import java.util.LinkedList;

public class LinkedStack implements IStackable{
    private LinkedList<Integer> l = new LinkedList<Integer>();
    public int size (){
        return this.l.size();
    }    
    public void push(int v){
        this.l.push(v);
    }
    public int pop(){
        int temp = this.l.getFirst();
        this.l.pop();
        return temp;
    }
}