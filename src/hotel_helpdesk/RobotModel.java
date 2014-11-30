/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel_helpdesk;
import java.util.StringTokenizer;
import server.share.IObserver;
import server.share.ISubject;
import java.util.Vector;
import javax.swing.text.Style;
/**
 *
 * @author obaro
 */
class RobotModel implements ISubject {

    private RobotController controller;
    private Vector<String> conversations;
     RobotModel()
     {
         conversations= new Vector<String>(); 
         conversations.add("Support: How may I help you?");
     }
    @Override
    public void attach(IObserver observer) {
       controller = (RobotController) observer;         
    }

    void processMessage(String text) {        
        StringTokenizer tokenizer = new StringTokenizer(text);         
       while(tokenizer.hasMoreTokens())
       {
           //process the language (NLP)
           
       }
    // finished processs the message call the controller results to display to the user
       this.controller.xhsResultMessgae(1);
       
    }
   public Vector<String> getConversations()
    {
      if(this.conversations==null)
        return null;
      return conversations;
    }
    
}
