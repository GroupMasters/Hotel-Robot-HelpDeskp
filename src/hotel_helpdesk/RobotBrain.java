/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel_helpdesk;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 *
 * @author obaro
 */
class RobotBrain {

    private ArrayList<String> roomSym;
    private ArrayList<String> bookingSym;
    private ArrayList<String> userSyms;
    private ArrayList<String> hotelSymns;
    private ArrayList<String> referenquestions;
    private ArrayList<String> AmountsSym;
    private String strResults;
    private String message;
    private static int CONTEXT_ABOUT_AMOUNT = 0;
    private static int CONTEXT_ABOUT_ROOMS = 0;
    private static int CONTEXT_ABOUT_HOTEL = 0;
    private static int CONTEXT_ABOUT_SUPPORTER = 0;
    private static int CONTEXT_ABOUT_BOOKING = 0;
    private static int CONTEXT_REFERENCE = 0;
    private static int ValidQuestionCount = 0;
    
    //query variable
   private final static  int AVALIABLE_ROOMS=1;
   private final static int ALLROOMS_LIST=2;
   private final static int HOTEL_INFO=3;
   private final static int BOOKING_LIST=4;
   private final static int ROBOT_INFO=5;
   
    
    RobotBrain(Object parent) {
        strResults = "no answer";
        roomSym = new ArrayList<String>();
        bookingSym = new ArrayList<String>();
        userSyms = new ArrayList<String>();
        hotelSymns = new ArrayList<String>();
        AmountsSym = new ArrayList<String>();
        referenquestions = new ArrayList<String>();
        //this will get the keyword word to rearrange the semantic meaning
        QueryTypeList = new ArrayList<String>();
        this.init();
    }

    private void init() {
        //The symnome should appear here
        AmountsSym.add("count");
        AmountsSym.add("amount");
        AmountsSym.add("list");
        AmountsSym.add("total");
        AmountsSym.add("vacancy");
        AmountsSym.add("available");
        AmountsSym.add("unavailable");
        AmountsSym.add("many");
        AmountsSym.add("cost");
        AmountsSym.add("price");
         AmountsSym.add("costs");
        AmountsSym.add("prices");
        // keywords towards rooms concepts

        roomSym.add("rooms");
        roomSym.add("room");
        roomSym.add("apartments");
        roomSym.add("aprtment");
        roomSym.add("apartment");
        roomSym.add("accomdations");
        roomSym.add("accomdation");
        roomSym.add("lunch");

        //keyowrds towards booking
        bookingSym.add("book");
        bookingSym.add("bookings");
        bookingSym.add("reservations");
        bookingSym.add("reserve");
        bookingSym.add("reserves");
        //taking about the supporter
        userSyms.add("you");
        userSyms.add("who");
        userSyms.add("robot");
        userSyms.add("name");
        //Taking about the hotel
        hotelSymns.add("hotel");
        hotelSymns.add("locations");
        hotelSymns.add("place");
        // Reference sym
        referenquestions.add("that");

    }

    void analysis(StringTokenizer tokenizer) {
        this.message = "";
        QueryTypeList.clear();
        //initialise then again
        CONTEXT_ABOUT_AMOUNT     = 0;
        CONTEXT_ABOUT_ROOMS      = 0;
        CONTEXT_ABOUT_HOTEL      = 0;
        CONTEXT_ABOUT_SUPPORTER  = 0;
        CONTEXT_ABOUT_BOOKING    = 0;
        CONTEXT_REFERENCE        = 0;
    
    
        while (tokenizer.hasMoreTokens()) {
            String word = tokenizer.nextToken().toLowerCase();
           
            if (isTakingAboutAmount(word)) {
                CONTEXT_ABOUT_AMOUNT = 1;
                QueryTypeList.add(word);
                this.message += " <b>" + word + "</b> ";
            } else if (isTakingAboutRoom(word)) {

                CONTEXT_ABOUT_ROOMS = 1;
                QueryTypeList.add(word);
                this.message += " <b>" + word + "</b> ";
            } else if (isTakingAboutBooking(word)) {

                CONTEXT_ABOUT_BOOKING = 1;
                QueryTypeList.add(word);
                this.message += " <b>" + word + "</b> ";
            } else if (isReferenceToPreviousQuerySubject(word)) {

                CONTEXT_REFERENCE = 1;
                QueryTypeList.add(word);
                this.message += " <b>" + word + "</b> ";
            } else if (isTakingAboutSupporter(word)) {
                CONTEXT_ABOUT_SUPPORTER = 1;
                QueryTypeList.add(word);
                this.message += " <b>" + word + "</b> ";
            } else if (isAskingAboutHotelDetails(word)) {
                CONTEXT_ABOUT_HOTEL = 1;
                QueryTypeList.add(word);
                this.message += "<b>" + word + "</b> ";

            } else {
                this.message += word + " ";
            }

        }

        // Find the semantic meaning of the user setences      
        this.analysis();

    }

    private void analysis() {

        int question_focus = 0;
        if (CONTEXT_ABOUT_AMOUNT == 1) {
            // the user is taking amount number of something
            question_focus++;
        }
        if (RobotBrain.CONTEXT_ABOUT_BOOKING == 1) {
            question_focus++;
        }
        if (RobotBrain.CONTEXT_ABOUT_HOTEL == 1) {
            question_focus++;
        }
        if (RobotBrain.CONTEXT_ABOUT_ROOMS == 1) {
            question_focus++;
        }
        if (RobotBrain.CONTEXT_ABOUT_SUPPORTER == 1) {
            question_focus++;
        }
        if (RobotBrain.CONTEXT_REFERENCE == 1) {
            question_focus++;
        }

        boolean isAmbiguous = this.isAmbiguousQuestion(question_focus);
        if (isAmbiguous) {
            strResults = "I dont understand your question is too ambiguous , please be specific!!" + question_focus;
            //add suggestion teams
        } else if (isValidFocusQuestion(question_focus)) {
            strResults = " I will be able to answer you question please! wait..." + question_focus;
        } else {
            strResults = "Sorry! I dont understand what your are saying.\n Please say again!" + question_focus;
            RobotBrain.ValidQuestionCount += 1;
        }

    }

    String getAnalysisAnswer() {

        return this.strResults;
    }

    private static final String Context_subject = null;
    private static ArrayList<String> QueryTypeList;

    //This method will
    private boolean isTakingAboutAmount(String word) {
        boolean isOkay = false;

        Iterator<String> iter = AmountsSym.iterator();

        while (iter.hasNext()) {
            if (iter.next().equalsIgnoreCase(word)) {
                isOkay = true;
                break;
            }
        }

        return isOkay;
    }

    private boolean isTakingAboutRoom(String word) {
        boolean isOkay = false;

        Iterator<String> iter = roomSym.iterator();

        while (iter.hasNext()) {
            if (iter.next().equalsIgnoreCase(word)) {
                isOkay = true;
                break;
            }
        }

        return isOkay;
    }

    private boolean isTakingAboutBooking(String word) {
        boolean isOkay = false;

        Iterator<String> iter = this.bookingSym.iterator();

        while (iter.hasNext()) {
            if (iter.next().equalsIgnoreCase(word)) {
                isOkay = true;
                break;
            }
        }

        return isOkay;
    }

    private boolean isReferenceToPreviousQuerySubject(String word) {
        boolean isOkay = false;

        Iterator<String> iter = this.referenquestions.iterator();

        while (iter.hasNext()) {
            if (iter.next().equalsIgnoreCase(word)) {
                isOkay = true;
                break;
            }
        }

        return isOkay;
    }

    private boolean isTakingAboutSupporter(String word) {
        boolean isOkay = false;

        Iterator<String> iter = this.userSyms.iterator();

        while (iter.hasNext()) {
            if (iter.next().equalsIgnoreCase(word)) {
                isOkay = true;
                break;
            }
        }

        return isOkay;
    }

    private boolean isAskingAboutHotelDetails(String word) {
        boolean isOkay = false;

        Iterator<String> iter = this.hotelSymns.iterator();

        while (iter.hasNext()) {
            if (iter.next().equalsIgnoreCase(word)) {
                isOkay = true;
                break;
            }
        }

        return isOkay;
    }

    private boolean isAmbiguousQuestion(int question_focus) {
        boolean isOkay = false;

        if (question_focus > 3) {
            isOkay = true;

        }

        return isOkay;
    }

    private boolean isValidFocusQuestion(int question_focus) {
        boolean isOkay = false;

        if (question_focus >= 1 && question_focus <= 3) {
            isOkay = true;
        }

        return isOkay;
    }

    String recongnise() {

        return this.message.trim();
    }

    /*
     The query class , that will return answer base on the user questions
     like 
     1. display all rooms 
     2. How many rooms are avaliable
     3. display rooms of that are less than 100 pounds
    
     */
    private class QueryClass {
        private String answer;
       public QueryClass(int task)
        {
            this.analysis(task);
        }
       
       public String getAnswer()
       {
           return answer;
       }

        private void analysis(int task) {
            switch(task)
            {
                case RobotBrain.AVALIABLE_ROOMS:
                  getAvaliableRooms();
                    break;
                case RobotBrain.ALLROOMS_LIST:
                    getAllRoomsDetails();
                    break;
                case RobotBrain.HOTEL_INFO:
                    getHotelDetails();
                    break;
                case RobotBrain.BOOKING_LIST:
                    getBookings();
                    break;
                case RobotBrain.ROBOT_INFO:
                    getRobotInformation();
                    break;
                    default:
                        error();
                        break;                 
                 
            }
       }
        
        //The Xml file questies here
        
        private void getAvaliableRooms()
        {
            
        }
        private void getAllRoomsDetails()
        {
           
        }
        private void getHotelDetails()
        {
        
        }
        
        private void getBookings()
        {
            
        }
        private void getRobotInformation()
        {
            
        }
        private void error()
        {
            
        }
        
    }
}
