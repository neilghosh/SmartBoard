import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.lang.*;
import java.awt.Color;
import java.awt.*;
import javax.swing.event.MouseInputAdapter;

/*
 * Master.java
 *
 * Created on 16 March 2006, 22:15
 */

/**
 *
 * @author  neil
 */
public class Master extends javax.swing.JFrame implements Runnable,NeuralReportable {
     /*WHITE BOARD VARIABLES*/
    private java.util.ArrayList pts;
    public java.util.ArrayList objects;
    public java.util.ArrayList pages;
    int ms_x,ms_y;
    int oldx,oldy;
    boolean viz_flag;
    private boolean up;
    char rec_letter;
    public static boolean rec_flag;
    int pagecount;   
    boolean current_page_is_saved;
  
    /**
   * The downsample width for the application.
   */
  static final int DOWNSAMPLE_WIDTH = 9;
  /**
   * The down sample height for the application.
   */
  static final int DOWNSAMPLE_HEIGHT = 14;
  /**
   * The entry component for the user to draw into.
   */
  Entry entry;
  /**
   * The down sample component to display the drawing
   * downsampled.
   */
  Sample sample;
  /**
   * The letters that have been defined.
   */
  DefaultListModel letterListModel = new DefaultListModel();

  /**
   * The neural network.
   */
  KohonenNetwork net;

  /**
   * The background thread used for training.
   */
  Thread trainThread = null;
  
  
  //{{DECLARE_CONTROLS
  javax.swing.JLabel JLabel1 = new javax.swing.JLabel();
  javax.swing.JLabel JLabel2 = new javax.swing.JLabel();
  javax.swing.JScrollPane JScrollPane1 = new javax.swing.JScrollPane();

  /**
   * The letters list box
   */
  javax.swing.JList letters = new javax.swing.JList();

  /**
   * The delete button
   */
 
  javax.swing.JLabel JLabel3 = new javax.swing.JLabel();
  javax.swing.JLabel JLabel4 = new javax.swing.JLabel();

  /**
   * How many tries
   */
  javax.swing.JLabel tries = new javax.swing.JLabel();

  /**
   * The last error
   */
  javax.swing.JLabel lastError = new javax.swing.JLabel();

  /**
   * The best error
   */
  javax.swing.JLabel bestError = new javax.swing.JLabel();
  javax.swing.JLabel JLabel8 = new javax.swing.JLabel();
  javax.swing.JLabel JLabel5 = new javax.swing.JLabel();
  //}}
  //{{DECLARE_MENUS
  //}}

    /** Creates new form Master */
    public Master() {

    initComponents();

    
        up=true;
        pts      =  new java.util.ArrayList();
        objects  =  new java.util.ArrayList();
        pages    =  new java.util.ArrayList();
        pagecount = 1;

    getContentPane().setLayout(null);
    setBounds(0,0,800,600);
    entry = new Entry();
    entry.reshape(12,24,200,128);
    getContentPane().add(entry);

    sample = new Sample(DOWNSAMPLE_WIDTH,DOWNSAMPLE_HEIGHT);
    sample.reshape(180,210,65,70);
    entry.setSample(sample);
    getContentPane().add(sample);

    //{{INIT_CONTROLS
    //setTitle("Java Neural Network");
    getContentPane().setLayout(null);
    //setSize(405,382);
    setVisible(false);
    JLabel1.setText("Letters Known");
    getContentPane().add(JLabel1);
    JLabel1.setBounds(12,158,100,12);
    JLabel2.setText("Tries:");
    getContentPane().add(JLabel2);
    JLabel2.setBounds(12,294,72,24);
  
    JScrollPane1.setVerticalScrollBarPolicy(
      javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    JScrollPane1.setOpaque(true);
    getContentPane().add(JScrollPane1);
    JScrollPane1.setBounds(12,170,144,132);
    JScrollPane1.getViewport().add(letters);
    letters.setBounds(0,0,126,129);

    JLabel3.setText("Last Error:");
    getContentPane().add(JLabel3);
    JLabel3.setBounds(12,318,72,24);
    JLabel4.setText("Best Error:");
    getContentPane().add(JLabel4);
    JLabel4.setBounds(12,332,72,24);
    tries.setText("0");
    getContentPane().add(tries);
    tries.setBounds(96,294,72,24);
    lastError.setText("0");
    getContentPane().add(lastError);
    lastError.setBounds(96,318,72,24);
    bestError.setText("0");
    getContentPane().add(bestError);
    bestError.setBounds(96,332,72,24);
    JLabel8.setHorizontalTextPosition(
      javax.swing.SwingConstants.CENTER);
    JLabel8.setHorizontalAlignment(
      javax.swing.SwingConstants.CENTER);
    JLabel8.setText("Training Results");
    getContentPane().add(JLabel8);
    JLabel8.setFont(new Font("Dialog", Font.BOLD, 14));
    JLabel8.setBounds(12,240,120,24);
    JLabel5.setText("Draw Letters Here");
    getContentPane().add(JLabel5);
    JLabel5.setBounds(12,12,144,12);
    //}}

    //{{REGISTER_LISTENERS
   
    
    SymListSelection lSymListSelection = new SymListSelection();
    letters.addListSelectionListener(lSymListSelection);
  
    //}}
    letters.setModel(letterListModel);
    //{{INIT_MENUS
    //}}
        
     }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        jPanel1 = new javax.swing.JPanel();
        pageNumber = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        ADD = new javax.swing.JMenuItem();
        CLEAR = new javax.swing.JMenuItem();
        RECOGNIZE = new javax.swing.JMenuItem();
        DOWNSAMPLE = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        LOAD = new javax.swing.JMenuItem();
        TRAIN = new javax.swing.JMenuItem();
        SAVE = new javax.swing.JMenuItem();
        DELETE = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        OPEN = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();

        getContentPane().setLayout(null);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MASTER");
        setBackground(new java.awt.Color(255, 255, 255));
        setLocationByPlatform(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(new javax.swing.border.MatteBorder(null));
        jPanel1.setPreferredSize(new java.awt.Dimension(500, 400));
        jPanel1.setAutoscrolls(true);
        jPanel1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel1MouseDragged(evt);
            }
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jPanel1MouseMoved(evt);
            }
        });
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel1MouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jPanel1MouseReleased(evt);
            }
        });

        getContentPane().add(jPanel1);
        jPanel1.setBounds(270, 30, 500, 400);

        pageNumber.setText("PAGE NUMBER # 1");
        getContentPane().add(pageNumber);
        pageNumber.setBounds(390, 10, 210, 15);

        jMenu1.setText("FILE");
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });

        jMenuItem1.setText("OPEN");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });

        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("save");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });

        jMenu1.add(jMenuItem2);

        jMenuItem3.setText("clear");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });

        jMenu1.add(jMenuItem3);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("TEST");
        ADD.setText("ADD");
        ADD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ADDActionPerformed(evt);
            }
        });

        jMenu2.add(ADD);

        CLEAR.setText("clear");
        CLEAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CLEARActionPerformed(evt);
            }
        });

        jMenu2.add(CLEAR);

        RECOGNIZE.setText("recognize");
        RECOGNIZE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RECOGNIZEActionPerformed(evt);
            }
        });

        jMenu2.add(RECOGNIZE);

        DOWNSAMPLE.setText("downsample");
        DOWNSAMPLE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DOWNSAMPLEActionPerformed(evt);
            }
        });

        jMenu2.add(DOWNSAMPLE);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("LEARN");
        LOAD.setText("load");
        LOAD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LOADActionPerformed(evt);
            }
        });

        jMenu3.add(LOAD);

        TRAIN.setText("train");
        TRAIN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TRAINActionPerformed(evt);
            }
        });

        jMenu3.add(TRAIN);

        SAVE.setText("save");
        SAVE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SAVEActionPerformed(evt);
            }
        });

        jMenu3.add(SAVE);

        DELETE.setText("delete");
        DELETE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DELETEActionPerformed(evt);
            }
        });

        jMenu3.add(DELETE);

        jMenuBar1.add(jMenu3);

        jMenu4.setText("WHITE BOARD");
        OPEN.setText("Item");
        jMenu4.add(OPEN);

        jMenuBar1.add(jMenu4);

        jMenu5.setText("PAGE");
        jMenu5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu5ActionPerformed(evt);
            }
        });

        jMenuItem4.setText("NEW PAGE");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });

        jMenu5.add(jMenuItem4);

        jMenuItem5.setText("PREV");
        jMenuItem5.setEnabled(false);
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });

        jMenu5.add(jMenuItem5);

        jMenuItem6.setText("NEXT");
        jMenuItem6.setEnabled(false);
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });

        jMenu5.add(jMenuItem6);

        jMenuBar1.add(jMenu5);

        setJMenuBar(jMenuBar1);

        pack();
    }//GEN-END:initComponents

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
                nextpage();        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenu5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu5ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        prevPage();        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
            newpage();        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu1ActionPerformed

    private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseClicked
       //  System.out.print(evt.getButton());
        
       if(evt.getButton()==1)
       {
            if(rec_flag)
            {
       
                Morph objMorph = new Morph();
                Shape shape = objMorph.drawLetter(rec_letter,evt.getX(),evt.getY());
                rec_flag=false;
                recognize();
                //System.out.print(rec_letter);
                objects.add(objMorph.drawLetter(rec_letter,evt.getX(),evt.getY()));
                new Recreate().drawImage(objects);  
                rec_flag = false;
                
            }
       }
       
       
       else if(evt.getButton()==3)
       {
         undo();  
       }
       else if(evt.getButton()==2)
       {
             String text = JOptionPane.showInputDialog("Enter TEXT");
             jPanel1.getGraphics().drawString(text,evt.getX(),evt.getY());
             
           
       }
  
                               
            
    }//GEN-LAST:event_jPanel1MouseClicked

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
                 pages=Sedeserial.deserialize("./shapes.xml");    
                 Recreate.drawImage((java.util.ArrayList)pages.get(0));
                 pagecount = 1;
                 this.pageNumber.setText("Page Number # " + pagecount); 
                 jMenuItem5.setEnabled(false);
                 if(pages.size()>1)
                     jMenuItem6.setEnabled(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        pages.add(objects);
        current_page_is_saved=true;
        Sedeserial.serialize(pages);        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
         jPanel1.getGraphics().clearRect(0,0, 600, 400);
        objects.clear();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jPanel1MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseMoved
         this.setTitle("CROSSNOTE ***"+" x="+evt.getX()+"    y="+evt.getY());        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel1MouseMoved

    private void jPanel1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseReleased
            up=true;
        if(pts.size()==0)
            System.out.println("no points:-)");
        else {
            ConvexHull objConvexHull = new ConvexHull();
            // System.out.println("obj no "+ objects.size());
            Point2D points_hull[] = objConvexHull.createConvexHull(pts);
            Recognize objRecognize = new Recognize();
            Point2D point_array_old[] = new Point2D[pts.size()];//backup
            
            for(int j=0;j<pts.size();j++)
                
            {
               
                point_array_old[j]=(Point2D)(pts.get(j));
                
            }
            Shape objShape = objRecognize.recognizeShape(point_array_old, points_hull);
            objects.add(objShape);
            
            
            // System.out.println(objects.size());
            pts.clear();
           // Graphics g = jPanel1.getGraphics();
            
            jPanel1.getGraphics().clearRect(0,0, 600,400);
            //Recreate.drawShape(objShape);
            
            new Recreate().drawImage(objects);  
        }// TODO add your handling code here:
    }//GEN-LAST:event_jPanel1MouseReleased

    private void jPanel1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseDragged
        
        
        
        ms_x=evt.getX();
        ms_y=evt.getY();
        if(!up) {
           // jPanel2.getGraphics().drawLine(20,20,200,200);
           Graphics g1 = jPanel1.getGraphics();
            g1.setColor(Color.lightGray);
            g1.drawLine(oldx,oldy,ms_x,ms_y);
            g1.dispose();
        }
        else {
            up=false;
        }
        java.awt.Point a = new java.awt.Point(ms_x,ms_y);
        
        this.setTitle("CROSSNOTE "+" x="+a.x+"    y="+a.y);
        pts.add(a);
        oldx=ms_x;
        oldy=ms_y;
    }//GEN-LAST:event_jPanel1MouseDragged

    private void SAVEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SAVEActionPerformed
    try {
      OutputStream os;// the actual file stream
      PrintStream ps;// used to read the file line by line

      os = new FileOutputStream( "./sample1.dat",false );
      ps = new PrintStream(os);

      for ( int i=0;i<letterListModel.size();i++ ) {
        SampleData ds = (SampleData)letterListModel.elementAt(i);
        ps.print( ds.getLetter() + ":" );
        for ( int y=0;y<ds.getHeight();y++ ) {
          for ( int x=0;x<ds.getWidth();x++ ) {
            ps.print( ds.getData(x,y)?"1":"0" );
          }
        }
        ps.println("");
      }

      ps.close();
      os.close();
     CLEARActionPerformed(null);
      JOptionPane.showMessageDialog(this,
                                    "Saved to 'sample.dat'.",
                                    "Training",
                                    JOptionPane.PLAIN_MESSAGE);

    } catch ( Exception e ) {
      JOptionPane.showMessageDialog(this,"Error: " +
                                    e,"Training",
                                    JOptionPane.ERROR_MESSAGE);
    }        // TODO add your handling code here:
    }//GEN-LAST:event_SAVEActionPerformed

    private void TRAINActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TRAINActionPerformed
    train();
    }//GEN-LAST:event_TRAINActionPerformed

    private void LOADActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LOADActionPerformed
           load();
    }//GEN-LAST:event_LOADActionPerformed

    private void DOWNSAMPLEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DOWNSAMPLEActionPerformed
    entry.downSample();        // TODO add your handling code here:
    }//GEN-LAST:event_DOWNSAMPLEActionPerformed

    private void RECOGNIZEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RECOGNIZEActionPerformed
        recognize();
    }//GEN-LAST:event_RECOGNIZEActionPerformed

    private void CLEARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CLEARActionPerformed
    entry.clear();
    sample.getData().clear();
    sample.repaint();
        // TODO add your handling code here:
    }//GEN-LAST:event_CLEARActionPerformed

    private void DELETEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DELETEActionPerformed
int i = letters.getSelectedIndex();

    if ( i==-1 ) {
      JOptionPane.showMessageDialog(this,
                                    "Please select a letter to delete.","Error",
                                    JOptionPane.ERROR_MESSAGE);
      return;
    }

    letterListModel.remove(i);        // TODO add your handling code here:
    }//GEN-LAST:event_DELETEActionPerformed

    private void ADDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ADDActionPerformed
       int i;

    String letter = JOptionPane.showInputDialog(
      "Please enter a letter you would like to assign this sample to.");
    if ( letter==null )
      return;

    if ( letter.length()>1 ) {
      JOptionPane.showMessageDialog(this,
                                    "Please enter only a single letter.","Error",
                                    JOptionPane.ERROR_MESSAGE);
      return;
    }

    entry.downSample();
    SampleData sampleData = (SampleData)sample.getData().clone();
    sampleData.setLetter(letter.charAt(0));

    for ( i=0;i<letterListModel.size();i++ ) {
      Comparable str = (Comparable)letterListModel.getElementAt(i);
      if ( str.equals(letter) ) {
        JOptionPane.showMessageDialog(this,
                                      "That letter is already defined, delete it first!","Error",
                                      JOptionPane.ERROR_MESSAGE);
        return;
      }

      if ( str.compareTo(sampleData)>0 ) {
        letterListModel.add(i,sampleData);
        return;
      }
    }
    letterListModel.add(letterListModel.size(),sampleData);
    letters.setSelectedIndex(i);
    entry.clear();
    sample.repaint();
    }//GEN-LAST:event_ADDActionPerformed
    
    
    
    
    
    /** Exit the Application */
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        System.exit(0);
    }//GEN-LAST:event_exitForm
    

  /**
   * Run method for the background training thread.
   */
  public void run()
  {
    try {
      int inputNeuron = this.DOWNSAMPLE_HEIGHT * this.DOWNSAMPLE_WIDTH;
      int outputNeuron = letterListModel.size();

      TrainingSet set = new TrainingSet(inputNeuron,outputNeuron);
      set.setTrainingSetCount(letterListModel.size());

      for ( int t=0;t<letterListModel.size();t++ ) {
        int idx=0;
        SampleData ds = (SampleData)letterListModel.getElementAt(t);
        for ( int y=0;y<ds.getHeight();y++ ) {
          for ( int x=0;x<ds.getWidth();x++ ) {
            set.setInput(t,idx++,ds.getData(x,y)?.5:-.5);
          }
        }
      }

      net = new KohonenNetwork(inputNeuron,outputNeuron,this);
      net.setTrainingSet(set);
      net.learn();
    } catch ( Exception e ) {
      JOptionPane.showMessageDialog(this,"Error: " + e,
                                    "Training",
                                    JOptionPane.ERROR_MESSAGE);
    }

  }

  /**
   * Called to update the stats, from the neural network.
   *
   * @param trial How many tries.
   * @param error The current error.
   * @param best The best error.
   */
  public void update(int retry,double totalError,double bestError)
  {
    if ( (((retry%100)!=0) || (retry==10)) && !net.halt )
      return;

    if ( net.halt ) {
      trainThread = null;
      TRAIN.setText("Begin Training");
      /*JOptionPane.showMessageDialog(this,
                                    "Training has completed.","Training",
                                    JOptionPane.PLAIN_MESSAGE);*/
    }
    UpdateStats stats = new UpdateStats();
    stats._tries = retry;
    stats._lastError=totalError;
    stats._bestError=bestError;
    try {
      SwingUtilities.invokeAndWait(stats);
    } catch ( Exception e ) {
      JOptionPane.showMessageDialog(this,"Error: " + e,"Training",
                                    JOptionPane.ERROR_MESSAGE);
    }
  }

 
  char []mapNeurons()
  {
    char map[] = new char[letterListModel.size()];
    double normfac[] = new double[1];
    double synth[] = new double[1];

    for ( int i=0;i<map.length;i++ )
      map[i]='?';
    for ( int i=0;i<letterListModel.size();i++ ) {
      double input[] = new double[DOWNSAMPLE_WIDTH*DOWNSAMPLE_HEIGHT];
      int idx=0;
      SampleData ds = (SampleData)letterListModel.getElementAt(i);
      for ( int y=0;y<ds.getHeight();y++ ) {
        for ( int x=0;x<ds.getWidth();x++ ) {
          input[idx++] = ds.getData(x,y)?.5:-.5;
        }
      }

      int best = net.winner ( input , normfac , synth ) ;
      map[best] = ds.getLetter();
    }
    return map;
  }


  public class UpdateStats implements Runnable {
    long _tries;
    double _lastError;
    double _bestError;

    public void run()
    {
      tries.setText(""+_tries);
      lastError.setText(""+_lastError);
      bestError.setText(""+_bestError);
    }
  } 
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        SplashScreen splash = new SplashScreen(0);
        splash.showSplash();
        Master m = new Master();
        
        m.show();
        m.load();
        m.train();
                
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem ADD;
    private javax.swing.JMenuItem CLEAR;
    private javax.swing.JMenuItem DELETE;
    private javax.swing.JMenuItem DOWNSAMPLE;
    private javax.swing.JMenuItem LOAD;
    private javax.swing.JMenuItem OPEN;
    private javax.swing.JMenuItem RECOGNIZE;
    private javax.swing.JMenuItem SAVE;
    private javax.swing.JMenuItem TRAIN;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private static javax.swing.JPanel jPanel1;
    private javax.swing.JLabel pageNumber;
    // End of variables declaration//GEN-END:variables
 
    


    class SymListSelection implements javax.swing.event.ListSelectionListener {
    public void valueChanged(javax.swing.event.ListSelectionEvent event)
    {
      Object object = event.getSource();
      if ( object == letters )
        letters_valueChanged(event);
    }
  }

  /**
   * Called when a letter is selected from the list box.
   *
   * @param event The event
   */
  void letters_valueChanged(javax.swing.event.ListSelectionEvent event)
  {
    if ( letters.getSelectedIndex()==-1 )
      return;
    SampleData selected =
      (SampleData)letterListModel.getElementAt(letters.getSelectedIndex());
    sample.setData((SampleData)selected.clone());
    sample.repaint();
    entry.clear();
    
    

  }
  
  
   public void recognize()
   {
       if ( net==null ) {
      JOptionPane.showMessageDialog(this,
                                    "I need to be trained first!","Error",
                                    JOptionPane.ERROR_MESSAGE);
      return;
    }
    entry.downSample();

    double input[] = new double[DOWNSAMPLE_WIDTH*DOWNSAMPLE_HEIGHT];
    int idx=0;
    SampleData ds = sample.getData();
    for ( int y=0;y<ds.getHeight();y++ ) {
      for ( int x=0;x<ds.getWidth();x++ ) {
        input[idx++] = ds.getData(x,y)?.5:-.5;
      }
    }

    double normfac[] = new double[1];
    double synth[] = new double[1];

    int best = net.winner ( input , normfac , synth ) ;
    char map[] = mapNeurons();
 /*   JOptionPane.showMessageDialog(this,
                                  "  " + map[best] + "   (Neuron #"
                                  + best + " fired)","That Letter Is",
                                  JOptionPane.PLAIN_MESSAGE);
  */
    rec_letter = map[best];
    
    CLEARActionPerformed(null);        // TODO add your handling code here:
   }
   public void load()
   {
     
       
        try {
      FileReader f;// the actual file stream
      BufferedReader r;// used to read the file line by line

      f = new FileReader( new File("./sample1.dat") );
      r = new BufferedReader(f);
      String line;
      int i=0;

      letterListModel.clear();

      while ( (line=r.readLine()) !=null ) {
        SampleData ds =
          new SampleData(line.charAt(0),this.DOWNSAMPLE_WIDTH,this.DOWNSAMPLE_HEIGHT);
        letterListModel.add(i++,ds);
        int idx=2;
        for ( int y=0;y<ds.getHeight();y++ ) {
          for ( int x=0;x<ds.getWidth();x++ ) {
            ds.setData(x,y,line.charAt(idx++)=='1');
          }
        }
      }

      r.close();
      f.close();
      CLEARActionPerformed(null);
    /*  JOptionPane.showMessageDialog(this,
                                    "Loaded from 'sample.dat'.","Training",
                                    JOptionPane.PLAIN_MESSAGE);
                                    */
    } catch ( Exception e ) {
      /*JOptionPane.showMessageDialog(this,
                                    "Error: " + e,"Training",
                                    JOptionPane.ERROR_MESSAGE);*/
    }
        // TODO add your handling code here:
       
 
   }
   public void train()
   {
        if ( trainThread==null )
        {
      TRAIN.setText("Stop Training");
      TRAIN.repaint();
      trainThread = new Thread(this);
      trainThread.start();
    } else {
      net.halt=true;
    }        // TODO add your handling code here:
    
       
   }
   public void undo()
   {
       if(objects.size()>0)
       {
           objects.remove(objects.size()-1);
       }
       Recreate.drawImage(objects);
       
   }
   public void newpage()
   {
       
      try{
          pages.set(pagecount-1,objects);
          pagecount = pages.size()+1;
         }
       catch(java.lang.IndexOutOfBoundsException exp)
       {
           pages.add(objects);
           pagecount++;
       }
       
       
       jMenuItem5.setEnabled(true);
       
       this.pageNumber.setText("Page Number # " + pagecount);
       objects = new ArrayList();
           
       Recreate.drawImage(objects);
       
       
       
       
   }
   
   public static Graphics getGraphicsFromMaster() {
      return(jPanel1.getGraphics());
      
  }  
  
   public void prevPage() {
       
       try{
           pages.set(pagecount-1,objects);
           }
       catch(java.lang.IndexOutOfBoundsException exp)
       {
           pages.add(objects);
       }
       
       current_page_is_saved = true ;
         if(pagecount-2==0)
         {
             jMenuItem5.setEnabled(false);
         }
            
         
             jMenuItem6.setEnabled(true);
         
         
        java.util.ArrayList prev_page = new ArrayList();
        
        prev_page = (ArrayList)pages.get(pagecount-2);
        Recreate.drawImage(prev_page);
        
        objects = new ArrayList();
        objects=(ArrayList)prev_page.clone();
        pagecount--;
        this.pageNumber.setText("Page Number # " + pagecount);
        //System.out.println("----"+prev_page.size());
       
      
       
       
  }  
  
   public void nextpage() {
         pages.set(pagecount-1,objects);
     
         
        if(pagecount+1 >= pages.size())
     {
         jMenuItem6.setEnabled(false);
     }
         
          jMenuItem5.setEnabled(true);
         
        
        java.util.ArrayList next_page = new ArrayList();
        
        next_page = (ArrayList)pages.get(pagecount);
        Recreate.drawImage(next_page);
        
        objects = new ArrayList();
        objects=(ArrayList)next_page.clone();
        pagecount++;
        this.pageNumber.setText("Page Number # " + pagecount); 
         
       
   }   
  
  
}
