/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package di.uniba.map.b.adventure.impl;
import di.uniba.map.b.adventure.GameDescription;
import di.uniba.map.b.adventure.parser.Parser;
import di.uniba.map.b.adventure.parser.ParserOutput;
import di.uniba.map.b.adventure.type.CommandType;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Window extends JFrame
{
    JPanel panel;
    JLabel currentBackground;
    JLabel labelNavicella;
    JLabel timePlay;
    JTextArea roomNameTextArea;
    JTextArea roomDescriptionTextArea;
    JTextArea startDescriptionTextArea;
    JTextArea messageTextArea;
    JTextField testo;
    JTextField testo2;
    JTextField testo3;
    JScrollPane scrollPane;
    AePlayWave sottofondo;
    
    Image image;
    Image resizedImage;
    
    
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
        panel=new JPanel();
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
        //Creare una JLabel per visualizzare il tempo
        timePlay = new JLabel("00:00:00");
        timePlay.setForeground(Color.WHITE);
        timePlay.setBackground(Color.BLACK);
        timePlay.setHorizontalAlignment(SwingConstants.CENTER);
        timePlay.setOpaque(true);
        timePlay.setSize(55, 17);
        timePlay.setLocation(470, 338);
        timePlay.setVisible(false);
        timePlay.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        
        // Aggiungere JLabel al frame
        panel.add(timePlay);
        
        // Creare un contatore per i secondi
        final int[] elapsedSeconds = {0};
        
        // Timer per aggiornare l'etichetta ogni secondo
        Timer timer = new Timer(1000, (ActionEvent e) -> {
            // Incrementare il contatore dei secondi
            elapsedSeconds[0]++;
            // Convertire il tempo in ore, minuti e secondi
            int hours = elapsedSeconds[0] / 3600;
            int minutes = (elapsedSeconds[0] % 3600) / 60;
            int seconds = elapsedSeconds[0] % 60;
            // Formattare il tempo come stringa
            String timeString = String.format("%02d:%02d:%02d", hours, minutes, seconds);
            // Impostare il tempo formattato sull'etichetta
            timePlay.setText(timeString);
        });        
//\JLabeltime-------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------



//JLabel Navicella-------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        labelNavicella=new JLabel(resizedNavicella);
        labelNavicella.setSize(600,600);
        labelNavicella.setLocation(0,0);
        panel.add(labelNavicella);
        labelNavicella.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                labelNavicella.setVisible(false);
                startDescriptionTextArea.setVisible(false);
                roomNameTextArea.setVisible(true); 
                roomDescriptionTextArea.setVisible(true);
                testo.setVisible(true);
                currentBackground.setVisible(true);
                scrollPane.setVisible(true);
                messageTextArea.setVisible(true);
                timePlay.setVisible(true);
                timer.start();
            }
        });
        
        labelNavicella.setFocusable(true);
        labelNavicella.requestFocusInWindow();
        labelNavicella.setVisible(true);
//\JLabel Navicella-------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------



//JLabel currentBackground-------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------  
        currentBackground=new JLabel(resizedPortale);
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
        startDescriptionTextArea.setBackground(panel.getBackground()); // Rende lo sfondo uguale a quello del frame
        //startDescriptionTextArea.setBorder(BorderFactory.createEmptyBorder()); // Rimuove i bordi
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
        roomNameTextArea.setEditable(false); // Rende la JTextArea non modificabile
        roomNameTextArea.setBackground(panel.getBackground()); // Rende lo sfondo uguale a quello del frame
        //roomNameTextArea.setBorder(BorderFactory.createEmptyBorder()); // Rimuove i bordi
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
        roomDescriptionTextArea.setBackground(panel.getBackground()); // Rende lo sfondo uguale a quello del frame
        //roomDescriptionTextArea.setBorder(BorderFactory.createEmptyBorder()); // Rimuove i bordi
        roomDescriptionTextArea.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        roomDescriptionTextArea.setForeground(Color.WHITE);
        roomDescriptionTextArea.setBackground(Color.BLACK); 
        roomDescriptionTextArea.setVisible(false);
        panel.add(roomDescriptionTextArea);
        panel.setComponentZOrder(roomDescriptionTextArea, 0);
        
//\JTextArea RoomDescription-------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------



//JTextField-------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        testo = new JTextField(20);
        testo.setSize(200,20);
        testo.setLocation(200,470);
        testo.setForeground(Color.WHITE);
        testo.setBackground(Color.BLACK); 
        testo.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        testo.setVisible(false);
        panel.add(testo);
        
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
                            roomDescriptionTextArea.setVisible(false);
                            roomNameTextArea.setVisible(false);
                            System.exit(0);
                        }
                    }                    
                }
            }
        });

        testo2 = new JTextField(20);
        testo2.setSize(200,20);
        testo2.setLocation(200,490);
        testo2.setForeground(Color.WHITE);
        testo2.setBackground(Color.BLACK);
        testo2.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        testo2.setVisible(false);
        panel.add(testo2);
        
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
                        game.setCurrentRoom(null);
                        currentBackground.setIcon(resizedPortaleAcceso);
                        timer.stop();
                    } else {
                        Window.this.showMessage("Coordinate errate. Riprova.");
                    }
                }
            }
        });

        testo3 = new JTextField(20);
        testo3.setSize(200,20);
        testo3.setLocation(200,490);
        testo3.setForeground(Color.WHITE);
        testo3.setBackground(Color.BLACK);
        testo3.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        testo3.setVisible(false);
        panel.add(testo3);

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
