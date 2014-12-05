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
        conversations.add(RobotStart + " How may I help you please!");
    }

    @Override
    public void attach(IObserver observer) {
        controller = (RobotController) observer;
    }

    void processMessage(String text) {
        StringTokenizer tokenizer = new StringTokenizer(text);

        // finished processs the message call the controller results to display to the user
        
        this.brain.analysis(tokenizer);
        this.conversations.add("<br>" + UserStart + this.brain.recongnise() );
        String result = this.brain.getAnalysisAnswer();        
        this.conversations.add("<br>" + RobotStart +  result );       
        this.controller.xhsUpdateMessageBoard();
        
      
    }

    public Vector<String> getConversations() {
        if (this.conversations == null) {
            return null;
        }
        return conversations;
    }

}
