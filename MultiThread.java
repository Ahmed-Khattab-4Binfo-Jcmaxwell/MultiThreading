package Esercizio1;

import java.util.concurrent.TimeUnit;
/**
 *
 * @author Matteo Palitto
 */
public class Esercizio1 {

    /**
     * @param args the command line arguments
     */
    // "main" e' il THREAD principale da cui vengono creati e avviati tutti gli altri THREADs
    // i vari THREADs poi evolvono indipendentemente dal "main" che puo' eventualmente terminare prima degli altri
    public static void main(String[] args) {
        System.out.println("Main Thread iniziata...");
        long start = System.currentTimeMillis();
        
        // Posso creare un THREAD e avviarlo immediatamente
        Thread tic = new Thread (new TicTacToe("TIC"));
        tic.start();
        
        // Posso creare un 2ndo THREAD e farlo iniziare qualche tempo dopo...
        Thread tac = new Thread(new TicTacToe("TAC"));
        tac.start();
        
        Thread toe = new Thread (new TicTacToe("TOE"));
        toe.start();
       
        
        long end = System.currentTimeMillis();
        System.out.println("Main Thread completata! tempo di esecuzione: " + (end - start) + "ms");
    }
    
}

// Ci sono vari (troppi) metodi per creare un THREAD in Java questo e' il mio preferito per i vantaggi che offre
// +1 si puo estendere da un altra classe
// +1 si possono passare parametri (usando il Costruttore)
// +1 si puo' controllare quando un THREAD inizia indipendentemente da quando e' stato creato
class TicTacToe implements Runnable {
    
    // non essesndo "static" c'e' una copia delle seguenti variabili per ogni THREAD 
    private String t;
    private static String msg;
    private static int contatore = 0; //Cont conta quante volte il thread TOE viene dopo TAC
    public static boolean c = false; //se il thread è TAC diventa true mentre se è altro è False
    // Costruttore, possiamo usare il costruttore per passare dei parametri al THREAD
    public TicTacToe (String s) {
        this.t = s;
    }
    
    @Override // Annotazione per il compilatore
    // se facessimo un overloading invece di un override il copilatore ci segnalerebbe l'errore
    // per approfondimenti http://lancill.blogspot.it/2012/11/annotations-override.html

    public void run() 
    {
        for (int i = 10; i > 0; i--) 
        {           
            if("TAC".equals(t))
                c = true;
                
            msg = "<" + t + "> ";
            int casuale=100+(int)(Math.random()*300); //Tempo casuale tra 100 e 300 millisecondi
             try {
                TimeUnit.MILLISECONDS.sleep(casuale);
            } catch (InterruptedException e) {}
            if("TOE".equals(t) && c == true)
                contatore++;
            else
                c = false;
            msg += t + ": " + i;
            
            System.out.println(msg);
        }
    System.out.println(contatore);
    }
}


