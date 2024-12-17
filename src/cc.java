import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;



    public class cc {
        int n;

        public cc(int n) {
            this.n = n;
        }

        public class Posicion {
            int x, y;

            public Posicion(int i, int j) {
                this.x = i;
                this.y = j;
            }

            public boolean equals(Object o) {
                return (o instanceof Posicion) && ((Posicion) o).x == x && ((Posicion) o).y == y;
            }

            public String toString() {
                return "(" + x + "," + y + ")";
            }
        }

        public List<Posicion> BackTracking() {
            List<Posicion> lista = new ArrayList<>();
            lista.add(new Posicion(0, 0));
            boolean sol = BackTracking(lista, 1);
            return sol ? lista : null;
        }

        public boolean BackTracking(List<Posicion> lista, int etapa) {
            if (etapa == n * n) return true;

            List<Posicion> posiblesMovimientos = getMovimientos(lista.get(lista.size() - 1), lista);

            // Ordena movimientos por la heurística de Warnsdorff
            posiblesMovimientos.sort(Comparator.comparingInt(p -> getMovimientos(p, lista).size()));
            for (Posicion pos : posiblesMovimientos) {
                lista.add(pos);
                if (BackTracking(lista, etapa + 1)) return true;
                lista.remove(lista.size() - 1); // Retrocede si no hay solución
            }

            return false;
        }

        public List<Posicion> getMovimientos(Posicion actual, List<Posicion> lista) {
            int[] dx = {1, 1, -1, -1, 2, 2, -2, -2};
            int[] dy = {2, -2, 2, -2, 1, -1, 1, -1};
            List<Posicion> movimientos = new ArrayList<>();

            for (int i = 0; i < 8; i++) {
                Posicion nuevo = new Posicion(actual.x + dx[i], actual.y + dy[i]);
                if (estaTablero(nuevo) && !lista.contains(nuevo)) movimientos.add(nuevo);
            }

            return movimientos;
        }

        public boolean estaTablero(Posicion posicion) {
            return (posicion.x >= 0 && posicion.x < n) && (posicion.y >= 0 && posicion.y < n);
        }

        public static void main(String[] args) {
            cc caballito = new cc(8);
            List<Posicion> solucion = caballito.BackTracking();
            if (solucion != null) {
                System.out.println("El caballo recorrió todas las casillas:");
                solucion.forEach(System.out::println);
                System.out.println(solucion.size());
            } else {
                System.out.println("No hay solución.");
            }
        }
    }

