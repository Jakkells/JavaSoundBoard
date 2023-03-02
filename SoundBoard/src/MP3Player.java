import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.concurrent.atomic.AtomicReference;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import javax.swing.*;


public class MP3Player {

    private static boolean isSoundPlaying = false;
    private static Player player;



        public static void main(String[] args) throws FileNotFoundException, JavaLayerException {
            MP3KeyPlayer player = new MP3KeyPlayer();
            player.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            player.setSize(300, 300);
            player.setVisible(true);
        }

        public static void playSound(String filePath) {
            try {
                FileInputStream fis = new FileInputStream(filePath);
                Player playMP3 = new Player(fis);
                playMP3.play();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

    public static void stopSound() {
        if (isSoundPlaying) {
            player.close();
            isSoundPlaying = true;
        }
    }

    public static class MP3KeyPlayer extends JFrame implements KeyListener {
        private AtomicReference<Thread> playThread = new AtomicReference<>();

        public MP3KeyPlayer() {
            addKeyListener(this);
            setFocusable(true);
        }

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            Thread currentThread = playThread.getAndSet(null);
            if (currentThread != null) {
                currentThread.interrupt();
            }

            Thread playThread = new Thread(() -> playSound(getSoundPath(e.getKeyCode())));
            this.playThread.set(playThread);
            playThread.start();
        }

        private String getSoundPath(int keyCode) {
            switch (keyCode) {
                case KeyEvent.VK_F:
                    return "src/Wet fart noise.mp3";
                case KeyEvent.VK_V:
                    return "src/vineBoom.mp3";
                case KeyEvent.VK_A:
                    return "src/agh.mp3";
                case KeyEvent.VK_S:
                    return "src/sigma.mp3";
                case KeyEvent.VK_I:
                    return "src/imposter.mp3";
                case KeyEvent.VK_1:

                default:
                    return null;
            }
        }

        private void playSound(String filePath) {
            try {
                FileInputStream fis = new FileInputStream(filePath);
                Player playMP3 = new Player(fis);
                playMP3.play();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }

    }
}