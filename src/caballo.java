import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class caballo {
    int n;
    public caballo(int n){
       this.n=n;
    }
    public class Posicion {
        int x, y;

        public Posicion(int i, int j) {
            this.x = i;
            this.y = j;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public boolean equals(Object o) {
            return (o instanceof Posicion) && ((Posicion) o).x == x && ((Posicion) o).y == y;
        }

        public int hashCode() {
            return x * 7 + y * 17;
        }

        public String toString() {
            return "(" + x + "," + y + ")";
        }
    }
    public List<Posicion> BackTracking(){
        List<Posicion> lista = new ArrayList<Posicion>();
        lista.add(new Posicion(0,0));
        boolean sol = BackTracking(lista,1);
        return sol?lista:null;
    }

    public boolean BackTracking(List<Posicion> lista, int etapa){
        boolean haySol=false;
        if(etapa==n*n){
            return true;
        }else{
            int i=0;
            while (!haySol&&i<8){
                Posicion pos = mover(lista.get(lista.size()-1),i);//que se mueva pues
                if(estaTablero(pos) && !lista.contains(pos)) {
                    lista.add(pos);
                    haySol=BackTracking(lista,etapa+1);
                    if(!haySol){
                        lista.remove(lista.size()-1);
                    }
                }
                i++;
            }
            return haySol;
        }
    }
    public Posicion mover(Posicion actual, int i){
        Posicion nuevo;
        switch (i){
            case 0:
                nuevo = new Posicion(actual.x+1, actual.y+2);
                break;
            case 1:
                nuevo = new Posicion(actual.x+1, actual.y-2);
                break;
            case 2:
                nuevo = new Posicion(actual.x-1, actual.y+2);
                break;
            case 3:
                nuevo = new Posicion(actual.x-1, actual.y-2);
                break;
            case 4:
                nuevo = new Posicion(actual.x+2, actual.y+1);
                break;
            case 5:
                nuevo = new Posicion(actual.x+2, actual.y-1);
                break;
            case 6:
                nuevo = new Posicion(actual.x-2, actual.y+1);
                break;
            default:
                nuevo = new Posicion(actual.x-2, actual.y-1);
                break;
        }
        return nuevo;
    }

    public boolean estaTablero(Posicion posicion){
        return (posicion.x>=0&&posicion.x<n)&&(posicion.y>=0&&posicion.y<n);
    }

    public static void main(String [] args){
        caballo caballito = new caballo(8);
        List<Posicion> solucion = caballito.BackTracking();

        if (solucion != null) {
            System.out.println("Solución encontrada. Casillas recorridas: " + solucion.size());
        } else {
            System.out.println("No se encontró solución.");
        }
    }
}
