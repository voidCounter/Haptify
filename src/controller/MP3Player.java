package controller;
import javazoom.jl.player.Player;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.Scanner;


//public class Mp3Player{
//    public static void main(String[] args) {
//
//    	// Absolute path of the music file
//        String filename = "/home/rohit/eclipse-workspace/haptify/src/resources/Of Monsters And Men - Dirty Paws.mp3";
//        // A new mp3Player object passing the file to be played
//       
//        Scanner sc = new Scanner(System.in);
//
//        System.out.println("Write stop to stop the music: ");
//
//        if (sc.nextLine().equalsIgnoreCase("stop")) {
//            mp3Player.close();
//        }
//
//    }
//}

public class MP3Player {
	AppController appController;
	// file can't be changed in runtime
    private final String mp3FileToPlay;
    private Player jlPlayer;

    public MP3Player(AppController appController, String mp3FileToPlay) {
    	this.appController = appController;
        this.mp3FileToPlay = mp3FileToPlay;
    }

    public void play() throws InterruptedException {
        try {
        	// taking input of the file data
            FileInputStream fileInputStream = new FileInputStream(mp3FileToPlay);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            // jlPlayer will decode and play the buffered stream data
            jlPlayer = new Player(bufferedInputStream);
        } catch (Exception e) {
            System.out.println("Problem playing mp3 file " + mp3FileToPlay);
            System.out.println(e.getMessage());
        }

        // Thread is used 
        Thread currThread = new Thread() {
            public void run() {
                try {
                    jlPlayer.play();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
        }
};
      currThread.start();

        
//        currThread.join();
                
    }
    
    public void close() {
        if (jlPlayer != null) jlPlayer.close();
    }
}