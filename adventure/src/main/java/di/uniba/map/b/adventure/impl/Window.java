/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package di.uniba.map.b.adventure.impl;
import di.uniba.map.b.adventure.GameDescription;
import di.uniba.map.b.adventure.parser.Parser;
import di.uniba.map.b.adventure.parser.ParserOutput;
import di.uniba.map.b.adventure.type.CommandType;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class Window extends JFrame
{
    JPanel panel;
    JLabel currentBackground;
    JLabel labelNavicella;
    JLabel timePlay;
    JTextArea leaderBoard;
    JTextArea roomNameTextArea;
    JTextArea roomDescriptionTextArea;
    JTextArea startDescriptionTextArea;
    JTextArea messageTextArea;
    JTextField testo;
    JTextField testo2;
    JTextField testo3;
    JScrollPane scrollPane;
    AePlayWave sottofondo;
    RESTClient client;
    JButton pauseButton;
    JButton loadGameButton;
    JButton newGameButton;
    JButton exitAndSaveButton;
    JButton exitWithoutSaveButton;
    JButton showLeaderBoardButton;
    
    Image image;
    Image resizedImage;
    private boolean isPaused = false;
    private int elapsedSeconds = 0;
    private String timeString;
    private String insertText;
    private final ImageIcon resizedPortale;
    private final ImageIcon resizedCorridoio;
    private final ImageIcon resizedSalaComandi;
    private final ImageIcon resizedLaboratorio;
    private final ImageIcon resizedMotore;
    private final ImageIcon resizedArmeria;
    private final ImageIcon resizedAlieno;
    private final ImageIcon resizedArchivio;
    private final ImageIcon resizedPortaleAcceso;
    
    
    public Window(GameDescription game, Parser parser)
    {          
        setSize(600,600);  
        setResizable(false);
        setLayout(null);
        
        client = new RESTClient();
        sottofondo= new AePlayWave("./resources/audio/amongus.wav");
        
//Resize di tutte le immagini-------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
// navicella
        ImageIcon originalNavicella = new ImageIcon("./resources/immagini/navicella.jpg");
        image = originalNavicella.getImage(); // Trasforma in un oggetto Image
        resizedImage = image.getScaledInstance(600, 600,  java.awt.Image.SCALE_SMOOTH); // Ridimensiona l'immagine
        ImageIcon resizedNavicella = new ImageIcon(resizedImage);
// portale
        ImageIcon originalPortale = new ImageIcon("./resources/immagini/portale.jpeg");
        image = originalPortale.getImage();
        resizedImage = image.getScaledInstance(600, 600,  java.awt.Image.SCALE_SMOOTH); 
        resizedPortale = new ImageIcon(resizedImage);
//corridoio
        ImageIcon originalCorridoio = new ImageIcon("./resources/immagini/corridoio.jpeg");
        image = originalCorridoio.getImage();
        resizedImage = image.getScaledInstance(600, 600,  java.awt.Image.SCALE_SMOOTH); 
        resizedCorridoio = new ImageIcon(resizedImage);
//sala comandi
        ImageIcon originalSalaComandi = new ImageIcon("./resources/immagini/sala_comandi.jpeg");
        image = originalSalaComandi.getImage();
        resizedImage = image.getScaledInstance(600, 600,  java.awt.Image.SCALE_SMOOTH); 
        resizedSalaComandi = new ImageIcon(resizedImage);
//laboratorio
        ImageIcon originalLaboratorio = new ImageIcon("./resources/immagini/laboratorio.jpeg");
        image = originalLaboratorio.getImage();
        resizedImage = image.getScaledInstance(600, 600,  java.awt.Image.SCALE_SMOOTH); 
        resizedLaboratorio = new ImageIcon(resizedImage);
//motore
        ImageIcon originalMotore = new ImageIcon("./resources/immagini/motore.jpeg");
        image = originalMotore.getImage();
        resizedImage = image.getScaledInstance(600, 600,  java.awt.Image.SCALE_SMOOTH); 
        resizedMotore = new ImageIcon(resizedImage);
//armeria
        ImageIcon originalArmeria = new ImageIcon("./resources/immagini/armeria.jpeg");
        image = originalArmeria.getImage();
        resizedImage = image.getScaledInstance(600, 600,  java.awt.Image.SCALE_SMOOTH); 
        resizedArmeria = new ImageIcon(resizedImage);
//alieno
        ImageIcon originalAlieno = new ImageIcon("./resources/immagini/alieno.jpeg");
        image = originalAlieno.getImage();
        resizedImage = image.getScaledInstance(600, 600,  java.awt.Image.SCALE_SMOOTH); 
        resizedAlieno = new ImageIcon(resizedImage);
//archivio
        ImageIcon originalArchivio = new ImageIcon("./resources/immagini/archivio.jpeg");
        image = originalArchivio.getImage();
        resizedImage = image.getScaledInstance(600, 600,  java.awt.Image.SCALE_SMOOTH); 
        resizedArchivio = new ImageIcon(resizedImage);
//portale acceso
        ImageIcon originalPortaleAcceso = new ImageIcon("./resources/immagini/portale_acceso.jpeg");
        image = originalPortaleAcceso.getImage();
        resizedImage = image.getScaledInstance(600, 600,  java.awt.Image.SCALE_SMOOTH); 
        resizedPortaleAcceso = new ImageIcon(resizedImage);
//\Resize di tutte le immagini-------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    startAudio(sottofondo);
    
//JLabel panel-------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        panel = new JPanel();
        panel.setSize(600, 600);
        panel.setLocation(1,1);
        panel.setVisible(true);
        panel.setLayout(null);
        add(panel);
//\JLabel panel-------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------



//JTextArea message-------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        messageTextArea=new JTextArea();
        messageTextArea.setOpaque(true);
        messageTextArea.setEditable(false); // Rende la JTextArea non modificabile
        messageTextArea.setBorder(BorderFactory.createEmptyBorder()); // Rimuove i bordi
        messageTextArea.setForeground(Color.WHITE);
        messageTextArea.setBackground(Color.BLACK); 
        messageTextArea.setVisible(false);
//\JTextArea message-------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------



//JLabel scrollpane-------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        scrollPane=new JScrollPane();
        scrollPane.setVisible(false);
        scrollPane.setSize(480, 51);
        scrollPane.setLocation(50, 415);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setViewportView(messageTextArea);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        panel.add(scrollPane);
//\JLabel scrollpanel-------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------



//JLabel time-------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        timePlay = new JLabel("00:00:00");
        timePlay.setForeground(Color.WHITE);
        timePlay.setBackground(Color.BLACK);
        timePlay.setHorizontalAlignment(SwingConstants.CENTER);
        timePlay.setOpaque(true);
        timePlay.setSize(55, 17);
        timePlay.setLocation(470, 338);
        timePlay.setVisible(false);
        timePlay.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        panel.add(timePlay);
        panel.setComponentZOrder(timePlay, 0);
        
        // Creare un contatore per i secondi
        // Sposta qui la definizione di elapsedSeconds

        // Modifica del timer per aggiornare elapsedSeconds
        Timer timer = new Timer(1000, (ActionEvent e) -> {
        elapsedSeconds++;
        int hours = elapsedSeconds / 3600;
        int minutes = (elapsedSeconds % 3600) / 60;
        int seconds = elapsedSeconds % 60;
        timeString = String.format("%02d:%02d:%02d", hours, minutes, seconds);
        timePlay.setText(timeString);
    });
//\JLabeltime-------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------


//JButton newGameButton-------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        newGameButton = new JButton("Nuova Partita");
        newGameButton.setSize(100, 30);
        newGameButton.setLocation(400, 450);
        newGameButton.setForeground(Color.WHITE);
        newGameButton.setBackground(Color.BLACK);
        newGameButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        newGameButton.setVisible(false);
        panel.add(newGameButton);
        panel.setComponentZOrder(newGameButton, 0);
        // Gestione azione di nuova partita
        newGameButton.addActionListener((ActionEvent e) -> {
            labelNavicella.setVisible(false);
            startDescriptionTextArea.setVisible(false);
            currentBackground.setVisible(true); // Visualizza solo la prima immagine
            roomNameTextArea.setVisible(true);
            roomDescriptionTextArea.setVisible(true);
            testo.setVisible(true);
            testo.setEditable(true);
            messageTextArea.setVisible(true);
            scrollPane.setVisible(true);
            timePlay.setVisible(true);
            pauseButton.setVisible(true);
            newGameButton.setVisible(false);
            loadGameButton.setVisible(false);
            showLeaderBoardButton.setVisible(false);
            timer.start();
        });
//\JButton newGameButton-------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------



//JButton loadGameButton-------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        loadGameButton = new JButton("Carica Partita");
        loadGameButton.setSize(100, 30);
        loadGameButton.setLocation(100, 450);
        loadGameButton.setForeground(Color.WHITE);
        loadGameButton.setBackground(Color.BLACK);
        loadGameButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        loadGameButton.setVisible(false);
        panel.add(loadGameButton);
        panel.setComponentZOrder(loadGameButton, 0);

        loadGameButton.addActionListener((ActionEvent e) -> {
            String gameName = JOptionPane.showInputDialog("Inserisci il nome della partita da caricare:");
            if (gameName != null && !gameName.trim().isEmpty()) {
                Map<String, Object> loadedGame = SaveGame.load(gameName.trim());
                if (!loadedGame.isEmpty()) {
                    // Recupera i dati della partita caricata
                    elapsedSeconds = (int) loadedGame.get("ElapsedSeconds");
                    int currentRoomId = (int) loadedGame.get("CurrentRoom");
                    List<Integer> inventoryIds = (List<Integer>) loadedGame.get("Inventory");

                    // Aggiorna l'oggetto game con i dati caricati
                    game.setCurrentRoomById(currentRoomId); // Assicurati che ci sia un metodo per impostare la stanza corrente tramite ID
                    game.setInventoryByIds(inventoryIds); // Assicurati che ci sia un metodo per impostare l'inventario tramite una lista di ID

                    // Aggiorna l'interfaccia utente
                    String currentRoomName = game.getCurrentRoom().getName(); // Assumendo che tu abbia un metodo per ottenere il nome della stanza corrente
                    showRoomName(currentRoomName);

                    labelNavicella.setVisible(false);
                    startDescriptionTextArea.setVisible(false);
                    currentBackground.setVisible(true); // Visualizza solo la prima immagine
                    roomNameTextArea.setVisible(true);
                    roomDescriptionTextArea.setVisible(true);
                    showRoomDescription(game.getCurrentRoom().getDescription());
                    testo.setVisible(true);
                    messageTextArea.setVisible(true);
                    scrollPane.setVisible(true);
                    timePlay.setVisible(true);
                    pauseButton.setVisible(true);
                    newGameButton.setVisible(false);
                    loadGameButton.setVisible(false);
                    timer.start();

                    JOptionPane.showMessageDialog(null, "Partita caricata con successo!");
                } else {
                    JOptionPane.showMessageDialog(null, "Partita non trovata!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Nome della partita non valido!");
            }
        });

//\JButton loadGameButton-------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------


//\JButton exitAndSaveButton-------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        exitAndSaveButton = new JButton("Esci e salva");
        exitAndSaveButton.setSize(100, 30);
        exitAndSaveButton.setLocation(270, 250);
        exitAndSaveButton.setForeground(Color.WHITE);
        exitAndSaveButton.setBackground(Color.BLACK);
        exitAndSaveButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        exitAndSaveButton.setVisible(false);
        panel.add(exitAndSaveButton);
        panel.setComponentZOrder(exitAndSaveButton, 0);
        exitAndSaveButton.addActionListener((ActionEvent e) -> {
            while (true) {
                String gameName = JOptionPane.showInputDialog("Inserisci il nome della partita da salvare:");
                if (gameName == null) {
                    JOptionPane.showMessageDialog(null, "Operazione di salvataggio annullata.");
                    break; // Esci dal ciclo se l'utente preme "Cancel"
                } else if (!gameName.trim().isEmpty()) {
                    if (!SaveGame.gameExists(gameName.trim())) {
                        SaveGame.save(game.getCurrentRoom(), game.getInventory(), gameName.trim(), elapsedSeconds);
                        JOptionPane.showMessageDialog(null, "Partita salvata con successo!");
                        System.exit(0);
                        break; // Esci dal ciclo dopo un salvataggio riuscito
                    } else {
                        JOptionPane.showMessageDialog(null, "Una partita con questo nome esiste già. Inserisci un altro nome.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Nome della partita non valido! Inserisci un nome valido.");
                }
            }
        });
//\JButton exitAndSaveButton-------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------



//\JButton exitWithoutSaveButton-------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        exitWithoutSaveButton = new JButton("Esci senza salvare");
        exitWithoutSaveButton.setSize(130, 30);
        exitWithoutSaveButton.setLocation(120, 250);
        exitWithoutSaveButton.setForeground(Color.WHITE);
        exitWithoutSaveButton.setBackground(Color.BLACK);
        exitWithoutSaveButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        exitWithoutSaveButton.setVisible(false);
        panel.add(exitWithoutSaveButton);
        panel.setComponentZOrder(exitWithoutSaveButton, 0);

        exitWithoutSaveButton.addActionListener((ActionEvent e) -> {
            int response = JOptionPane.showConfirmDialog(
                null,
                "Sei sicuro di voler uscire senza salvare?",
                "Conferma uscita",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.WARNING_MESSAGE
            );

            if (response == JOptionPane.OK_OPTION) {
                System.exit(0);
            }
        });
//\JButton exitWithoutSaveButton-------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------




//JButton Pausebutton-------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        pauseButton = new JButton("Pausa");
        pauseButton.setSize(100, 30);
        pauseButton.setLocation(450, 50);
        pauseButton.setForeground(Color.WHITE);
        pauseButton.setBackground(Color.BLACK);
        pauseButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        pauseButton.setOpaque(true);
        pauseButton.setVisible(false);
        panel.add(pauseButton);
        panel.setComponentZOrder(pauseButton, 0);

        // Gestione azione di pausa/riprendi
        pauseButton.addActionListener((ActionEvent e) -> {
            if (isPaused) {
                // Se il gioco è in pausa, riprendi
                isPaused = false;
                pauseButton.setText("Pausa");
                timer.start(); // Riprendi il timer
                testo.setEditable(true);
                exitAndSaveButton.setVisible(false);
                exitWithoutSaveButton.setVisible(false);
            } else {
                // Se il gioco non è in pausa, metti in pausa
                isPaused = true;
                pauseButton.setText("Riprendi");
                timer.stop(); // Ferma il timer
                testo.setEditable(false);
                exitAndSaveButton.setVisible(true);
                exitWithoutSaveButton.setVisible(true);
            }
        });

//\JButton Pausebutton-------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------



//JButton showLeaderBoardButton-------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        showLeaderBoardButton = new JButton("Mostra Classifica");
        showLeaderBoardButton.setSize(120, 30);
        showLeaderBoardButton.setLocation(450, 50);
        showLeaderBoardButton.setForeground(Color.WHITE);
        showLeaderBoardButton.setBackground(Color.BLACK);
        showLeaderBoardButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        showLeaderBoardButton.setVisible(false);
        panel.add(showLeaderBoardButton);
        panel.setComponentZOrder(showLeaderBoardButton, 0);
        showLeaderBoardButton.addActionListener((ActionEvent e) -> {
            if (showLeaderBoardButton.getText().equals("Mostra Classifica")){
                showLeaderBoardButton.setSize(60, 30);
                showLeaderBoardButton.setText("X");
                leaderBoard.setVisible(true);
            }
            else if(showLeaderBoardButton.getText().equals("X")){
                showLeaderBoardButton.setSize(120, 30);
                showLeaderBoardButton.setText("Mostra Classifica");
                leaderBoard.setVisible(false);
            } 
        });
//\JButton showLeaderBoardButton-------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------




//JLabel Navicella-------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        labelNavicella = new JLabel(resizedNavicella);
        labelNavicella.setSize(600, 600);
        labelNavicella.setLocation(0, 0);
        panel.add(labelNavicella);

        labelNavicella.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                //labelNavicella.setVisible(true);
                startDescriptionTextArea.setVisible(false);
                currentBackground.setVisible(false); // Visualizza solo la prima immagine
                roomNameTextArea.setVisible(false);
                roomDescriptionTextArea.setVisible(false);
                testo.setVisible(false);
                messageTextArea.setVisible(false);
                scrollPane.setVisible(false);
                timePlay.setVisible(false);
                pauseButton.setVisible(false);
                newGameButton.setVisible(true);
                loadGameButton.setVisible(true);
                exitAndSaveButton.setVisible(false);
                exitWithoutSaveButton.setVisible(false);
                showLeaderBoardButton.setVisible(true);
                showLeaderBoard();
                showLeaderBoardButton.setLocation(250, 450);
            }
        });

        labelNavicella.setFocusable(true);
        labelNavicella.requestFocusInWindow();
        labelNavicella.setVisible(true);

        currentBackground = new JLabel(resizedPortale); // Prima immagine di sfondo
        currentBackground.setSize(600, 600);
        currentBackground.setLocation(0, 0);
        panel.add(currentBackground);
        currentBackground.setVisible(false);
//\JLabel portalRoomImage-------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------



//JTextArea startDescription-------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        startDescriptionTextArea = new JTextArea();
        startDescriptionTextArea.setEditable(false); // Rende la JTextArea non modificabile
        startDescriptionTextArea.setOpaque(true);
        startDescriptionTextArea.setForeground(Color.WHITE);
        startDescriptionTextArea.setBackground(Color.BLACK);
        startDescriptionTextArea.setSize(420,70);
        startDescriptionTextArea.setLocation(98,430);
        startDescriptionTextArea.setVisible(true);
        panel.add(startDescriptionTextArea);
        panel.setComponentZOrder(startDescriptionTextArea, 0);
//\JTextArea startDescription-------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------



//JTextArea roomName-------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        roomNameTextArea=new JTextArea();
        roomNameTextArea.setOpaque(true);
        roomNameTextArea.setFont(new Font("Serif", Font.BOLD, 12));
        roomNameTextArea.setSize(200, 20);
        roomNameTextArea.setLocation(50, 338);
        roomNameTextArea.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        roomNameTextArea.setForeground(Color.WHITE);
        roomNameTextArea.setBackground(Color.BLACK);
        roomNameTextArea.setVisible(false);
        roomNameTextArea.setMargin(new Insets(0, 20, 0, 0));
        panel.add(roomNameTextArea);
        panel.setComponentZOrder(roomNameTextArea, 0);
//\JTextArea RoomName-------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------   



//JTextArea roomDescription-------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        roomDescriptionTextArea=new JTextArea();
        roomDescriptionTextArea.setOpaque(true);
        roomDescriptionTextArea.setSize(480, 51);
        roomDescriptionTextArea.setLocation(50, 360);
        roomDescriptionTextArea.setEditable(false); // Rende la JTextArea non modificabile
        roomDescriptionTextArea.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        roomDescriptionTextArea.setForeground(Color.WHITE);
        roomDescriptionTextArea.setBackground(Color.BLACK); 
        roomDescriptionTextArea.setVisible(false);
        panel.add(roomDescriptionTextArea);
        panel.setComponentZOrder(roomDescriptionTextArea, 0);
        
//\JTextArea roomDescription-------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------



//JTextArea leaderBoard-------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        leaderBoard=new JTextArea();
        leaderBoard.setOpaque(true);
        leaderBoard.setSize(320, 170);
        leaderBoard.setLocation(50, 50);
        leaderBoard.setEditable(false); // Rende la JTextArea non modificabile
        leaderBoard.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        leaderBoard.setForeground(Color.WHITE);
        leaderBoard.setBackground(Color.BLACK); 
        leaderBoard.setVisible(false);
        panel.add(leaderBoard);
        panel.setComponentZOrder(leaderBoard, 0);
        
//\JTextArea leaderBoard-------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------



//JTextField-------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        //testo comandi
        testo = new JTextField(20);
        testo.setSize(200,20);
        testo.setLocation(200,470);
        testo.setForeground(Color.WHITE);
        testo.setBackground(Color.BLACK); 
        testo.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        testo.setVisible(false);
        panel.add(testo);
        panel.setComponentZOrder(testo, 0);
        
        
        testo.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                testo2.setVisible(false);
                testo3.setVisible(false);
                // Controlla se il tasto premuto è "Invio"
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    // Ottieni il testo dal JTextField e stampalo
                    insertText = testo.getText();                    
                    testo.setText("");
                    Window.this.showMessage("");
                    ParserOutput p = parser.parse(insertText, game.getCommands(), game.getCurrentRoom().getObjects(), game.getInventory());
                    if (p == null || p.getCommand() == null) {
                        Window.this.showMessage("Non capisco quello che mi vuoi dire.");
                    } else if (p.getCommand() != null && p.getCommand().getType() == CommandType.END) {
                        Window.this.showMessage("Sei un fifone, addio!");
                        testo.setVisible(false);
                        testo2.setVisible(false);
                        testo3.setVisible(false);
                        pauseButton.setVisible(false);
                        roomDescriptionTextArea.setVisible(false);
                        roomNameTextArea.setVisible(false);
                        timer.stop();
                        
                    } else {
                        game.nextMove(p, System.out, Window.this);
                        if (game.getCurrentRoom() == null) {
                            Window.this.showMessage("La tua avventura termina qui! Complimenti!");
                            testo.setVisible(false);
                            testo2.setVisible(false);
                            testo3.setVisible(false);
                            pauseButton.setVisible(false);
                            roomDescriptionTextArea.setVisible(false);
                            roomNameTextArea.setVisible(false);
                            System.exit(0);
                        }
                    }                    
                }
            }
        });
        
        
        //testo coordinate
        testo2 = new JTextField(20);
        testo2.setSize(200,20);
        testo2.setLocation(200,490);
        testo2.setForeground(Color.WHITE);
        testo2.setBackground(Color.BLACK);
        testo2.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        testo2.setVisible(false);
        panel.add(testo2);
        panel.setComponentZOrder(testo2, 0);
        
        testo2.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                // Controlla se il tasto premuto è "Invio"
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    // Ottieni il testo dal JTextField e stampalo
                    insertText = testo2.getText().replace(" ", "");
                    if (PushObserver.verify(insertText)) {
                        Window.this.showMessage("Hai inserito correttamente le coordinate spaziali \nSei tornato a casa sano e salvo.\nLa tua avventura termina qui! Complimenti!");
                        testo2.setVisible(false);
                        testo.setVisible(false);
                        testo3.setVisible(false);
                        roomDescriptionTextArea.setVisible(false);
                        roomNameTextArea.setVisible(false);
                        showLeaderBoardButton.setLocation(450, 50);
                        showLeaderBoardButton.setVisible(true);
                        game.setCurrentRoom(null);
                        currentBackground.setIcon(resizedPortaleAcceso);
                        pauseButton.setVisible(false);
                        timer.stop();
        
                        String name;
                        do {
                            name = JOptionPane.showInputDialog(Window.this, "Inserisci il tuo nome (max 10 caratteri):", "Nome Giocatore", JOptionPane.PLAIN_MESSAGE);
                            if (name == null) {
                                Window.this.showMessage("Inserimento annullato.");
                                return; // Esce dal metodo se l'utente annulla l'inserimento
                            }
                            if (name.length() > 10) {
                                Window.this.showMessage("Nome troppo lungo. Reinserisci un nome con massimo 10 caratteri.");
                            }
                        } while (name.length() > 10 || name.trim().isEmpty());

                        if (!name.trim().isEmpty()) {
                            // Ottieni la data corrente
                            String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                            String time = timeString;  // Assumendo che timeString sia già definito da qualche parte nel codice
                            PlayerData player = new PlayerData(name, date, time);
                            client.addPlayer(player);
                            showLeaderBoard();
                            Window.this.showMessage("Hai inserito correttamente le coordinate spaziali \nSei tornato a casa sano e salvo.\nLa tua avventura termina qui! Complimenti!");
                        } else {
                            Window.this.showMessage("Nome non valido. Inserimento annullato.");
                        }

                    } else {
                        Window.this.showMessage("Coordinate errate. Riprova.");
                    }
                }
            }
        });
        
        
        //testo codice
        testo3 = new JTextField(20);
        testo3.setSize(200,20);
        testo3.setLocation(200,490);
        testo3.setForeground(Color.WHITE);
        testo3.setBackground(Color.BLACK);
        testo3.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        testo3.setVisible(false);
        panel.add(testo3);
        panel.setComponentZOrder(testo3, 0);

        testo3.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                // Controlla se il tasto premuto è "Invio"
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    // Ottieni il testo dal JTextField e stampalo
                    insertText = testo3.getText();
                    if (insertText.equals("07738")) {
                        Window.this.showMessage("Hai inserito correttamente il codice di accesso \nOra inserisci le coordinate.");
                        testo3.setVisible(false);
                        testo.setVisible(true);
                        testo2.setVisible(true);
                    } else {
                        Window.this.showMessage("Codice errato. Riprova.");
                    }
                }
            }
        });

//\JTextField-------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        


        // Aggiungi un WindowListener per gestire l'evento di chiusura della finestra
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                stopAudio(sottofondo); // Chiamata al metodo per fermare il thread
                dispose(); // Chiudi la finestra in modo appropriato
                System.exit(0);
            }
        });

        setVisible(true);
    }
    
    public void showStartDescription(String startDescription){
        
        startDescriptionTextArea.setText(startDescription);
    }
    
    public void showRoomName(String roomName){
             
        roomNameTextArea.setText("Ti trovi qui: " + roomName);
        
        if(roomName.equals("Sala del portale"))    currentBackground.setIcon(resizedPortale);
        if(roomName.equals("Corridoio"))    currentBackground.setIcon(resizedCorridoio);
        if(roomName.equals("Sala di controllo"))    currentBackground.setIcon(resizedSalaComandi);
        if(roomName.equals("Laboratorio"))    currentBackground.setIcon(resizedLaboratorio);
        if(roomName.equals("Sala motori"))    currentBackground.setIcon(resizedMotore);
        if(roomName.equals("Armeria"))    currentBackground.setIcon(resizedArmeria);
        if(roomName.equals("Anticamera"))    currentBackground.setIcon(resizedAlieno);
        if(roomName.equals("Archivio"))    currentBackground.setIcon(resizedArchivio);
            }
    
    public void showRoomDescription(String roomDescription){
        roomDescriptionTextArea.setText(roomDescription);
    }
    
    public void showMessage(String message){
        messageTextArea.setText(message);
    }
    
    public void showLeaderBoard(){
        String leaderboard = client.getLeaderboard();
        leaderBoard.setText(leaderboard);
    }    
    
    public String getInsertText(){
        return insertText;
    }
    
    private void startAudio(AePlayWave sound){
        sound.startSound();
    }
    
    private void stopAudio(AePlayWave sound){
        sound.stopSound();
    }
   
}