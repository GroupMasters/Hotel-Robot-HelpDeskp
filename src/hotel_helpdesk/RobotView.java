/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel_helpdesk;
import java.awt.Component;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import server.share.IObserver;
import server.share.View;
import server.share.ISubject;
import javax.swing.JTextPane;
/**
 *
 * @author obaro
 */
class RobotView extends View implements ISubject{
  private RobotController controller ;
  private JTextPane jtplMessages;
  private GridBagLayout jgb ;
  private JScrollPane jsrpl;
  private JPanel jpnlCommands;
  private JPanel jpnlContent;
  private JTextField jtxtMessage;
  private JButton jbtnSendMessage;
  private JButton jbtnClear;
  private JButton jbtnDisconnect;
  private JButton jbtnCloseApp;
  
  
  
  public RobotView(String title)
  {
      this.setTitle(title);
      this.initGui();
  }
  
  
  private void initGui()
  {
      jtplMessages = new JTextPane();
      this.jpnlContent = new JPanel(new GridBagLayout());
      this.jsrpl = new JScrollPane(jtplMessages);
      
      
      //create/initialised the grid layout
      //mistakesn
      //add the main panle to the form
      this.getContentPane().add(jpnlContent);
  }
  
    @Override
    public void attach(IObserver observer) {
        controller = (RobotController) observer;
    }
    
}
