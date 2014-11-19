/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel_helpdesk;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import server.share.IObserver;
import server.share.View;
import server.share.ISubject;
import javax.swing.JTextPane;
import javax.swing.JToolBar;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.StyledDocument;
/**
 *
 * @author obaro
 */
class RobotView extends View implements ISubject{
    private static String CMD_SEND_MESSAGE="Send";
    private static String CMD_CLEAR_SCREEN ="Clear";
    private static String CMD_DISCONNECT_APP= "Disconnect";
    private static String CMD_CLOSE_APPLICATION="Exit Application";
  private RobotController controller ;
  private JTextPane jtplMessages;
  private GridBagLayout jgb ;
  private JScrollPane jsrpl;
  private JPanel jpnlCommands;
  private JPanel jpnlContent;
  private JTextField jtxtMessage;
  private JButton jbtnSendMessage;
  private JButton btnSettings;
  private JButton jbtnDisconnect;
  private JButton jbtnCloseApp;
  
  private GridBagLayout g;
  private Font inputMessageFont ;
  private StyledDocument document;
  private JToolBar toolbar;
  
  //private statics
  private static int APP_WIDTH =400;
  private static int APP_HEIGHT =400;
  private static  final int   LEFT =4;
  private static  final int  RIGHT =4;
  private static  final int   TOP =4;
  private static  final int   BOTTOM =4;
  
  
  public RobotView(String title)
  {
      
      this.addWindowListener(new EventHandler(this));
      this.setTitle(title);
      this.initGui();
  }
  
  
  private void initGui()
  {
      inputMessageFont= new Font(Font.SERIF,Font.PLAIN,15);
      jtplMessages = new JTextPane();
       jtplMessages.setPreferredSize(new Dimension(APP_WIDTH,APP_HEIGHT));
      document = new DefaultStyledDocument();      
      jtplMessages.setDocument(document);           
      this.jpnlContent = new JPanel(new GridBagLayout());
      this.jsrpl = new JScrollPane(jtplMessages);
      this.toolbar= new JToolBar();
      jpnlCommands= new JPanel(new FlowLayout());
      this.jbtnSendMessage = new JButton(RobotView.CMD_SEND_MESSAGE);
      this.btnSettings = new JButton(RobotView.CMD_CLEAR_SCREEN);
      this.jbtnCloseApp= new JButton(RobotView.CMD_CLOSE_APPLICATION);
      this.jbtnDisconnect = new JButton(RobotView.CMD_DISCONNECT_APP);
      this.jtxtMessage= new JTextField();
      this.jtxtMessage.setFont(inputMessageFont);
      
      //add the buttons to the button panel
      
     
      
      
      
      this.jgb= new GridBagLayout();
      GridBagConstraints gc= new GridBagConstraints();
      gc.fill = GridBagConstraints.HORIZONTAL;
      gc.gridx = 0;
      gc.gridy=0;
      gc.gridheight = 1;
      gc.gridwidth =3;
      gc.insets= new Insets(LEFT,RIGHT,TOP,BOTTOM);
      this.jpnlContent.add(this.toolbar,gc);
      //add the textpane
      gc.fill = GridBagConstraints.HORIZONTAL;
      gc.gridx = 0;
      gc.gridy=1;
      gc.gridheight = 1;
      gc.gridwidth =2;
      gc.insets= new Insets(LEFT,RIGHT,TOP,BOTTOM);
      this.jpnlContent.add(this.jsrpl,gc);
      
      //add the right handside command buttons
      
      gc.fill = GridBagConstraints.HORIZONTAL;
      gc.gridx = 3;
      gc.gridy=1;
      gc.gridheight = 1;
      gc.gridwidth =1;
      gc.insets= new Insets(LEFT,RIGHT,TOP,BOTTOM);
      this.jpnlContent.add(this.jpnlCommands,gc);
      
      
      //add the text message field
        gc.fill = GridBagConstraints.HORIZONTAL;
      gc.gridx = 0;
      gc.gridy=2;
      gc.gridheight = 1;
      gc.gridwidth =2;
      gc.insets= new Insets(LEFT,RIGHT,TOP,BOTTOM);
      this.jpnlContent.add(this.jtxtMessage,gc);
      
      //add the button
       //add the text message field
        gc.fill = GridBagConstraints.HORIZONTAL;
      gc.gridx = 2;
      gc.gridy=2;
      gc.gridheight = 1;
      gc.gridwidth =1;
      gc.insets= new Insets(LEFT,RIGHT,TOP,BOTTOM);
      this.jpnlContent.add(this.jbtnSendMessage,gc);
      
      
      //add the main panle to the form
      this.getContentPane().add(jpnlContent);
  }
  
    @Override
    public void attach(IObserver observer) {
        controller = (RobotController) observer;
    }
    
    
    
    private class EventHandler implements WindowListener,ActionListener
    {
        RobotView parent;
       
        EventHandler(RobotView view)
        {
            this.parent=view;
        }
        @Override
        public void windowOpened(WindowEvent e) {
                }

        @Override
        public void windowClosing(WindowEvent e) {
            this.parent.controller.xhsCloseHelpWindow();
             }

        @Override
        public void windowClosed(WindowEvent e) {
               }

        @Override
        public void windowIconified(WindowEvent e) {
         }

        @Override
        public void windowDeiconified(WindowEvent e) {
            }

        @Override
        public void windowActivated(WindowEvent e) {
           }

        @Override
        public void windowDeactivated(WindowEvent e) {
            }

        @Override
        public void actionPerformed(ActionEvent e) {
            if(RobotView.CMD_SEND_MESSAGE.equals(e.getActionCommand()))
            {
                this.parent.controller.xhsSendMessage(this.parent.jtxtMessage.getText());
            }
            
              }
        
    }
    
}
