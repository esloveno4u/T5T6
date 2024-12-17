import java.util.ArrayList;
import java.util.List;

public class Reinas {
    public static List<Integer> reinas(int n){
        List<Integer> lista = new ArrayList<Integer>();
        boolean sol = reinas(n,lista);
        return sol?lista:null;
    }
    public static boolean reinas(int n, List<Integer> lista){
        boolean haySolucion = false;
        if (lista.size()==n) {
            haySolucion = true;
        }
        int i = 0;
        while (!haySolucion && i < n){
            if(!lista.contains(i) && noDiagonal(i,lista)){
                lista.add(i);
                haySolucion = reinas(n,lista);
                if (!haySolucion) lista.remove(lista.size() -1);
            }
            i++;
        }
        return haySolucion;
    }
    public static boolean noDiagonal(int i, List<Integer> lista){
        boolean noCome = true;
        int j = lista.size();
        int k = 0;
        while (k < lista.size() && noCome){
            noCome = Math.abs(j-k) != Math.abs(i-lista.get(k));
            k++;
        }
        return noCome;
    }
    public static int Cuentareinas(int n){
        List<Integer> lista = new ArrayList<Integer>();
        return Cuentareinas(n,lista);
    }

    public static int Cuentareinas(int n, List<Integer> lista){

        if (lista.size()==n) {
            return 1;
        }
        int soluciones=0;
        int i = 0;
        while ( i < n){
            if(!lista.contains(i) && noDiagonal(i,lista)){
                lista.add(i);
                int sol1 = Cuentareinas(n,lista);
                soluciones+=sol1;
                lista.remove(lista.size() -1);
            }
            i++;
        }
        return soluciones;
    }
    public static void main(String [] args){

        System.out.println(Cuentareinas(10));
    }

}
