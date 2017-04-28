In questo progetto abbiamo avviato **TIC TAC TOE** che sono 3 Threads:

Ogni Threads è indipendente dall altro, TUTTI partono da 10 ed effettuano un countdown tramite ciclo for fino a 0, il tempo del countdown viene gestito da un tempo causale compreso tra 100 e 300 millisecondi, nel caso vi siano delle eccezioni esse vengono gestite dal Try Catch

Viene inserita una variabile che parte da **0** e si aggiorna ogni qualvolta il thread **TOE appare dopo il TAC**

Alla fine dei Thread viene restituita la variabile dentro il contatore che viene considerata come un punteggio che indica quante volte il **TOE appare DOPO il TAC**
