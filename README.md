# SPACE ESCAPE
Progetto per l'esame 'Metodi avanzati di programmazione' per l'anno accademico 2023/24.  
Nome gruppo: I SanGiovannesi.  
Autori: Scarale Francescopio, Russo Nicola, Squarcella-Gorgoglione Francesco.

## INDICE

1. [Introduzione](#introduzione)
   - [Mappa di gioco](#mappa-di-gioco)
2. [Utilizzo](#utilizzo)
   - [Comandi principali](#comandi-principali)
3. [Diagrammi UML](#diagrammi-uml)
4. [Specifica algebrica](#specifica-algebrica)
   - [Specifica sintattica](#specifica-sintattica)
   - [Specifica semantica](#specifica-semantica)
   - [Specifica di restrizione](#specifica-di-restrizione)
   
## Introduzione
**SPACE ESCAPE** è un'avventura testuale interattiva progettata per l'esame di 'Metodi avanzati di programmazione'. Il gioco si svolge attraverso un'interfaccia grafica (**GUI**), che consente agli utenti di immergersi nell'avventura di una navicella spaziale intrappolata nello spazio.

### Descrizione del gioco
L'obiettivo del gioco è attivare la navicella spaziale e tornare sani e salvi sulla Terra. Per raggiungere questo obiettivo, i giocatori devono esplorare la mappa di gioco, raccogliere oggetti e codici, e risolvere enigmi. Il gioco inizia con una schermata introduttiva che spiega il contesto e le prime istruzioni per iniziare.

### Come si gioca
Per muoversi all'interno della mappa, il giocatore deve utilizzare i comandi direzionali **nord**, **sud**, **est** e **ovest**. È possibile esplorare le stanze e i corridoi usando il comando **osserva**, che fornisce una descrizione dettagliata dell'ambiente circostante. Inoltre, il giocatore può interagire con oggetti e porte tramite comandi come **utilizza**, **prendi**, **apri**, e altri.

### Mappa di gioco
![Mappa di gioco](mappa_gioco.png)  
La mappa di gioco mostra tutte le stanze e i corridoi esplorabili. Le freccette nere indicano i possibili movimenti tra le diverse aree. Gli oggetti e gli ostacoli rappresentati nella mappa sono quelli che i giocatori incontreranno durante l'avventura.

## Utilizzo
In **SPACE ESCAPE**, il giocatore assume il ruolo di un astronauta intrappolato su una navicella spaziale alla deriva nello spazio. L'obiettivo è esplorare l'ambiente, risolvere enigmi e raccogliere oggetti necessari per attivare la navicella e tornare sulla Terra.

### Comandi Principali
Per interagire con il gioco, utilizza i seguenti comandi:
<br>
nord: Spostati a nord.<br>
sud: Spostati a sud.<br>
est: Spostati a est.<br>
ovest: Spostati a ovest.<br>
osserva: Osserva la stanza attuale per una descrizione dettagliata.<br>
prendi : Prendi un oggetto.<br>
usa : Usa un oggetto nell'inventario.<br>
apri : Apri una un oggetto, se possibile.<br>
ammazza : Ammazza qualsiasi mostro/alieno esistente.<br>
inventario : Stampa tutti gli oggetti che il giocatore ha raccolto durante la partita.<br>
abbandona: Termina il gioco.<br>
attiva: Attiva i vari pannelli e portali.<br>
leggi: Legge le varie mappe/cartine.<br>
Man mano che esplori la navicella, troverai vari oggetti che potrebbero essere utili per risolvere enigmi e superare ostacoli. Usa i comandi sopra elencati per interagire con l'ambiente e avanzare nel gioco.


## Specifica algebrica
### Specifica sintattica
sorts:Dizionario, chiave, valore, Boolean<br><br>

operations:<br>
- newDizionario: () -> Dizionario
- put: Dizionario, chiave, valore -> Dizionario
- get: Dizionario, chiave -> valore
- remove: Dizionario, chiave -> Dizionario
- isEmpty: Dizionario -> Boolean
- containsKey: Dizionario, chiave -> Boolean
- containsValue: Dizionario, valore -> Boolean
- size: Dizionario -> Integer
- keySet: Dizionario -> List<chiave>
- values: Dizionario -> List<valore>
- clear: Dizionario -> Dizionario


### Specifica semantica
declase:d:dizionario, k:chiave, v:valore.<br><br>
- isEmpty(newDizionario()) = true
- isEmpty(put(d, k, v)) = false
- get(put(d, k, v), k) = v
- get(d, k) = undefined se containsKey(d, k) = false
- remove(d, k) = d se containsKey(d, k) = false
- containsKey(remove(d, k), k) = false
- containsKey(put(d, k, v), k) = true
- containsValue(d, v) = (∃k | get(d, k) = v)
- size(newDizionario()) = 0
- size(put(d, k, v)) = size(d) + 1 se containsKey(d, k) = false
- size(put(d, k, v)) = size(d) se containsKey(d, k) = true
- size(remove(d, k)) = size(d) - 1 se containsKey(d, k) = true
- size(remove(d, k)) = size(d) se containsKey(d, k) = false
- keySet(d) = { k | containsKey(d, k) }
- values(d) = [ get(d, k) | k ∈ keySet(d) ]
- isEmpty(clear(d)) = true



### Specifica di restrizione
restrictions:
∀ d:Dizionario, k: chiave, v: valore.<br><br>
- ∀k' | (k' ≠ k implica get(put(d, k, v), k') = get(d, k'))        
- ∀k' | (k' ≠ k implica get(remove(d, k), k') = get(d, k'))
- keySet(d).contains(k) = containsKey(d, k)
- values(d).contains(v) = containsValue(d, v)        
- ∀k . containsKey(clear(d), k) = false
