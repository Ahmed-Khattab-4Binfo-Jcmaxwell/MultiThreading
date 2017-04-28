package Esercizio1;

import java.util.concurrent.TimeUnit; //importo libreria tempo

import static multithread.TicTacToe.contatore;// importo libreria

public class Esercizio1
{
    public static void main(String[] args)     // "main" e' il THREAD principale da cui vengono creati e avviati tutti gli altri THREADs
    // i vari THREADs poi evolvono indipendentemente dal "main" che puo' eventualmente terminare prima degli altri
    {
        System.out.println("Main Thread iniziata...");
        long start = System.currentTimeMillis();
        
        Thread tic = new Thread (new TicTacToe("TIC")); //Thread Tic
        tic.start(); //Fa partire il Thread da 10 effettuando un countdown secondo un tempo random compreso tra 100 e 300 millisecondi
        
        Thread tac = new Thread(new TicTacToe("TAC")); //Thread Tac
        tac.start();        
        Thread toe = new Thread(new TicTacToe("TOE")); //Thread Toe
        toe.start();
        
        long end = System.currentTimeMillis();
        System.out.println("Main Thread completata! tempo di esecuzione: " + (end - start) + "ms");
        
        try //qui all'interno vengono gestite le eccezioni
        {
            tic.join();
        }     
        catch (InterruptedException e) 
        {}
    
        try 
        {
            tac.join();
        } 
        catch (InterruptedException e) 
        {}
        
        try 
        {
            toe.join();
        } 
        catch (InterruptedException e) 
        {}
        System.out.println();
        System.out.println("punteggio: " + contatore); //stampo il punteggio ovvero la variabile dentro conteggio
    }
}
// Ci sono vari (troppi) metodi per creare un THREAD in Java questo e' il mio preferito per i vantaggi che offre
// +1 si puo estendere da un altra classe
// +1 si possono passare parametri (usando il Costruttore)
// +1 si puo' controllare quando un THREAD inizia indipendentemente da quando e' stato creato
class TicTacToe implements Runnable 
{    
    private String t; // non essesndo "static" c'e' una copia delle seguenti variabili per ogni THREAD
    private String msg; //essendo static non copierò i valori sottostanti negli altri Thread
    public static int contatore = 0; //contatore conta quante volte il thread TOE viene dopo TAC, sarà il punteggio finale
    public static boolean c = false;//se il thread è TAC diventa true mentre se è altro è False

    public TicTacToe (String s) //uso il costruttore per passare in una variabile tutti i paramentri del Thread
    {
        this.t = s;
    }
    
    @Override // Annotazione per il compilatore
    // se facessimo un overloading invece di un override il copilatore ci segnalerebbe l'errore

    public void run() 
    {
        for (int i = 10; i > 0; i--) 
        {           
            if("TAC".equals(t))
                c = true;
                
            msg = "<" + t + "> ";
            int casuale=100+(int)(Math.random()*300); //genero numero casuale tra 100 e 300 memorizzato in 'casuale'
             try {
                TimeUnit.MILLISECONDS.sleep(casuale); //casuale ora diventa un numero rappresentante il tempo il MILLISECONDI
            } catch (InterruptedException e) {} //Richiamo eccezione
            if("TOE".equals(t) && c == true)
                contatore++;// aumento il contatore di 1
            else
                c = false;
            msg += t + ": " + i;
            
            System.out.println(msg);
        } 
    }
}
