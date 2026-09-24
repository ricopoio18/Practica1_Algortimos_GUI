package Juego21;

public class Pila<T> {
    private T[] pila;
    private int tope;

    public Pila(){
        pila = (T[]) new Object[10];
        tope = -1;
    }

    public Pila(int capacidad){
        pila = (T[]) new Object[capacidad];
        tope = -1;

    }

    public boolean pila_llena(){
        if (tope == pila.length){
            return true;
        }
        return false;
    }

    public boolean pila_vacia(){
        return tope == -1 ? true : false;
    }

    public  void push(T elemento){
        if (pila_llena()){
            System.out.println("Desbordamiento");

        }else{
            tope++;
            pila[tope] = elemento;
        }
    }

    public T pop(){
        T dato = null;
        if(pila_vacia()){
            System.out.println("Subdesbordamiento");
        }
        else {
            dato = pila[tope];
            tope--;
        }
        return dato;
    }

}
