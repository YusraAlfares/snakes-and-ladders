import java.util.*;
class Graph {
    public int Rows;
    public int Columns;
    public ArrayList<ArrayList<Integer>> List;
    
    public Graph(int Rows, int Columns) {
        this.Rows = Rows;
        this.Columns = Columns;
        this.List = new ArrayList<>(Rows * Columns);

        int i = 0;
        while (i < Rows * Columns) {
            List.add(new ArrayList<>());
            i++;  
        }


    }

    public void Connect(int x, int y) {
        List.get(x).add(y);
    }

    public ArrayList<Integer> getList(int x) {
        return List.get(x);
    }
}

