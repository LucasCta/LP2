import java.util.ArrayList;

public class ArrayStack implements IStackable{
    private ArrayList<Integer> a = new ArrayList<Integer>();
    public int size (){
        return this.a.size();
    }    
    public void push(int v){
        this.a.add(v);
    }
    public int pop(){
        int temp = this.a.get(a.size()-1);
        this.a.remove(a.size()-1); 
        return temp;
    }
}