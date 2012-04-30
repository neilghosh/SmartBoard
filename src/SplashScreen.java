import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;

public class SplashScreen extends JWindow {
  private int duration;

  public SplashScreen(int d) {
    duration = d;
  }

  // A simple little method to show a title screen in the center
  // of the screen for the amount of time given in the constructor
  public void showSplash() {
      
    final SwingProgressBarExample it = new SwingProgressBarExample();  
    JPanel content = (JPanel) getContentPane();
    content.setBackground(Color.cyan);

    // Set the window's bounds, centering the window
    int width = 450;
    int height = 115;
    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (screen.width - width) / 2;
    int y = (screen.height - height) / 2;
    setBounds(x, y, width, height);

    // Build the splash screen
    JLabel label = new JLabel(new ImageIcon("/home/neil/bluewin/java/neil1.jpg"));
    JLabel label1 = new JLabel("TURNING ON THE MODULES..... BLUEWIN Version 0.0406");
    JLabel copyrt = new JLabel("Copyright 2006, Neil Ghosh email: neil.ghosh@gmail.com",
        JLabel.CENTER);
    copyrt.setFont(new Font("Sans-Serif", Font.BOLD, 12));
    
    content.add(label1, BorderLayout.NORTH);
    
    content.add(it, BorderLayout.CENTER);
    content.add(copyrt, BorderLayout.SOUTH);
    Color oraRed = new Color(156, 20, 20, 255);
    content.setBorder(BorderFactory.createLineBorder(Color.GRAY, 10));

    // Display it
    setVisible(true);
    it.begin();

    // Wait a little while, maybe while loading resources
    try {
      Thread.sleep(duration);
    } catch (Exception e) {
    }

    setVisible(false);
  }

  public void showSplashAndExit() {
    showSplash();
    
    System.exit(0);
  }

  /*public static void main(String[] args) {
    // Throw a nice little title page up on the screen first
    SplashScreen splash = new SplashScreen(10000);
    // Normally, we'd call splash.showSplash() and get on with the program.
    // But, since this is only a test...
    splash.showSplashAndExit();
  }*/
}

