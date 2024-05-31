/* Trabajo Práctico No 2
Thread e interrupciones


Teniendo en cuenta el desarrollo implementado en Java sobre utilización de thread e
interrupciones.
1. Desarrolle un código en el lenguaje de programación que desee donde se
utilicen thread y se utilice alguna interrupción sobre los threads utilizados.
2. Explique el funcionamiento del código desarrollado.


Lo que busqué recrear fue una carrera, utilizándolo como clase, conteniendo el método ‘main’ que crea los dos objetos de la clase ‘contadorConThread’ para los atletas, inicializándolos con el método ‘start()’ arranca desde 0 kilómetros(0 segundos) hasta una ‘meta’(10 segundos), donde se interrumpe el thread usando el método atleta(1 o 2).’interrupt()’. La clase ‘contadorConThread’ se extiende de ‘Thread’ y tiene el método ‘run()’ que viene a simular la carrera, utiliza un bucle infinito para incrementar el contador y mostrar el avance del atleta c/segundo hasta que el contador llegue a 10. Ahí es interrumpido el thread y se imprime el mensaje indicando que el atleta ha sido interrumpido.
*/


public class Carrera {
    //Clase principal que contiene el método main
    public static void main(String[] args) {
        //Crear un objeto de la clase contadorConThread para el atleta 1
        contadorConThread atleta1 = new contadorConThread("Atleta 1");
        //Crear un objeto de la clase contadorConThread para el atleta 2
        contadorConThread atleta2 = new contadorConThread("Atleta 2");
       
        //Iniciar el thread del atleta 1
        atleta1.start();
        //Iniciar el thread del atleta 2
        atleta2.start();




        //Esperar 10 segundos antes de interrumpir los threads
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            //Manejar la interrupción del thread
            System.out.println("La carrera ha sido interrumpida.");
        }
       
        //Interrumpir los threads
        atleta1.interrupt();
        atleta2.interrupt();
       
        //Comparar los contadores para determinar el ganador
        if (atleta1.getCounter() > atleta2.getCounter()) {
            //El atleta 1 es el ganador
            System.out.println("El atleta número 1 es el ganador.");
        } else if (atleta2.getCounter() > atleta1.getCounter()) {
            //El atleta 2 es el ganador
            System.out.println("El atleta número 2 es el ganador.");
        } else {
            //Ha sido un empate
            System.out.println("Ha sido empate.");
        }
    }
}


class contadorConThread extends Thread {
   //Atributo privado para el contador
    private int contador;
   
    //Método constructor para inicializar el nombre del thread
    public contadorConThread(String name) {
        super(name);
    }
   
    //Método para obtener el valor del contador
    public int getCounter() {
        return contador;
    }


    //Método para ejecutar el thread
    public void run() {
        try {
            //Bucle infinito para simular la carrera
            while (!Thread.currentThread().isInterrupted() && contador < 10) {
                //Imprime el estado del contador
                System.out.println(getName() + " lleva " + contador + " kilómetros.");
                //Incrementa el contador
                contador++;
                //Espera 1 segundo antes de continuar
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            //Maneja la interrupción del thread
            System.out.println(getName() + " ha sido interrumpido.");
        }
    }
}



