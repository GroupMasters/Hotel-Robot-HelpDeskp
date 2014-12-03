/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel_helpdesk;

import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author obaro
 */
class RobotBrain {
    
    private ArrayList<String> roomSym;
    private ArrayList<String> bookingSym;
    private ArrayList<String> userSyms;
    private ArrayList<String> hotelSymns;
    private ArrayList<String> reservationSym;
    private String strResults;
    
    RobotBrain(Object parent)
    {
        strResults="no answer";
        roomSym         = new ArrayList<String>(); 
        bookingSym      = new ArrayList<String>(); 
        userSyms        = new ArrayList<String>(); 
        hotelSymns      = new ArrayList<String>(); 
        reservationSym  = new ArrayList<String>(); 
        this.init();
    }
  
    private void init()
    {
        //The symnome should appear here
        
    }
    void analysis(StringTokenizer tokenizer) {
        
    }

    String getAnalysisAnswer()
    {
        return this.strResults;
    }
}
