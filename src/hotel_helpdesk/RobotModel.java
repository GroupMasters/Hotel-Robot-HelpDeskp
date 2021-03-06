/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel_helpdesk;

import java.util.StringTokenizer;
import helps.IObserver;
import helps.ISubject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author obaro
 */
class RobotModel extends Object implements ISubject {

    private RobotController controller;
    private Vector<String> conversations;
    private RobotBrain brain;
    final String RobotStart = "<font color='red'><b>Robot: </b></font>:",
            UserStart = "<font color='blue'><b>You: </b></font>";

    RobotModel() {
        conversations = new Vector<String>();
         brain= new RobotBrain(this);
        conversations.add("<i>Connecting to server...</i><br>");
        conversations.add("<i>Connection established successfully.</i><br>");
        conversations.add("<i>Tranfering you to an online supported [Robot] .</i><br>");
     
        this.helpType();
        
    }

    @Override
    public void attach(IObserver observer) {
        controller = (RobotController) observer;
    }

    void processMessage(String text) {
        
        String[] message = text.split("or");
        //re split with and 
        String[] tokensMessages = this.getSplitterMessage(message);
        
        
        StringTokenizer tokenizer1 = new StringTokenizer(text);
        this.brain.analysis(tokenizer1);       
        this.conversations.add("<br>" + UserStart + this.brain.recongnise() );
        
        for(int i=0; i <  tokensMessages.length;i++){
            StringTokenizer tokenizer = new StringTokenizer(tokensMessages[i]);

        // finished processs the message call the controller results to display to the user
        
        this.brain.analysis(tokenizer);       
        String result = this.brain.getAnalysisAnswer();        
        this.conversations.add("<br>" + RobotStart +  result );       
        this.controller.xhsUpdateMessageBoard();       
        
        }
        
      
    }

    public Vector<String> getConversations() {
        if (this.conversations == null) {
            return null;
        }
        return conversations;
    }

    private void helpType() {
       conversations.add(RobotStart + " How may I help you please!\n");
       conversations.add("\n1- I can help on the following information .<br>");
       conversations.add("\n1-The hotel .<br>");
       conversations.add("\n2 - The Available Rooms .<br>");
       conversations.add("\n2 - The booking list .<br>");    
      
    }

    private String[] getSplitterMessage(String[] message) {
     
        //now take each substring and split
        
        List<String> tokensMessage=new ArrayList<>();
        
        for(String str:message)
        {
          String[] arrStr =   str.split("and");
          for( String str2:arrStr)
          {
              tokensMessage.add(str2);
          }
        }
        
       String[] result = new String[tokensMessage.size()];
       Iterator<String> iter = tokensMessage.iterator();
       int counter=0;
       while(iter.hasNext())
       {
           result[counter]=iter.next();
           counter++;
       }
       
       return result;
       
    }

}
