/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel_helpdesk;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.swing.JOptionPane;

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
    private final static int AVALIABLE_ROOMS = 1;
    private final static int ALLROOMS_LIST = 2;
    private final static int HOTEL_INFO = 3;
    private final static int BOOKING_LIST = 4;
    private final static int ROBOT_INFO = 5;

    //query fields
    private static int question_type = 0;
    private ProcessorXML query;
    private static boolean IS_SOMETHING_OUT = false;

    RobotBrain(Object parent) {
        strResults = "";
        roomSym = new ArrayList<String>();
        bookingSym = new ArrayList<String>();
        userSyms = new ArrayList<String>();
        hotelSymns = new ArrayList<String>();
        AmountsSym = new ArrayList<String>();
        referenquestions = new ArrayList<String>();
        //this will get the keyword word to rearrange the semantic meaning
        QueryTypeList = new ArrayList<String>();
        query = new ProcessorXML(question_type, this);
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
        AmountsSym.add("information");
        AmountsSym.add("informations");
        // keywords towards rooms concepts

        roomSym.add("rooms");
        roomSym.add("room");
        roomSym.add("apartments");
        roomSym.add("aprtment");
        roomSym.add("apartment");
        roomSym.add("accomdations");
        roomSym.add("accomdation");
        roomSym.add("lunch");
        roomSym.add("rom");
        roomSym.add("roms");

        //keyowrds towards booking
        bookingSym.add("book");
        bookingSym.add("bookings");
        bookingSym.add("booking");
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
        CONTEXT_ABOUT_AMOUNT = 0;
        CONTEXT_ABOUT_ROOMS = 0;
        CONTEXT_ABOUT_HOTEL = 0;
        CONTEXT_ABOUT_SUPPORTER = 0;
        CONTEXT_ABOUT_BOOKING = 0;
        CONTEXT_REFERENCE = 0;

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
            //handler and suggest question for customer or guest
            this.detectedQuestionFocus();

        } else if (isValidFocusQuestion(question_focus)) {
            this.detectedQuestionFocus();
        } else {

            this.detectedQuestionFocus();
            RobotBrain.ValidQuestionCount += 1;
        }

    }

    String getAnalysisAnswer() {
        this.strResults = this.query.setQuestionType(question_type);
        return (this.strResults + "[" + question_type + "]").trim();
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

    private void decision() {
        // the subject is about number of items
        if (RobotBrain.CONTEXT_ABOUT_BOOKING == 1
                || (RobotBrain.CONTEXT_ABOUT_BOOKING == 1 && RobotBrain.CONTEXT_ABOUT_ROOMS == 1)) {
            // the subject is amount the number of bookings are avaliable
            question_type = RobotBrain.BOOKING_LIST;
        } else if (RobotBrain.CONTEXT_ABOUT_HOTEL == 1) {
            question_type = RobotBrain.HOTEL_INFO;
        } else if (RobotBrain.CONTEXT_ABOUT_ROOMS == 1) {
            question_type = RobotBrain.AVALIABLE_ROOMS;
        } else if (RobotBrain.CONTEXT_ABOUT_SUPPORTER == 1) {
            question_type = RobotBrain.ROBOT_INFO;
        } else if (RobotBrain.CONTEXT_REFERENCE == 1) {
            question_type = 0;
        } else {
            question_type = -1;
        }
    }

    private void detectedQuestionFocus() {

        if (RobotBrain.CONTEXT_ABOUT_AMOUNT == 1) {
            IS_SOMETHING_OUT = true;
            decision();

        } else {
            IS_SOMETHING_OUT = false;
            decision();
            // the question did not mention mention list or count
        }
    }

    private void displaySystemGuide() {
    }

    /*
     The query class , that will return answer base on the user questions
     like
     1. display all rooms
     2. How many rooms are avaliable
     3. display rooms of that are less than 100 pounds

     */
    private class ProcessorXML {

        private String answer;
        private JAXB_XMLParser xmlhandler; // we need an instance of our parser
        //This is a candidate for a name change
     
        private HotelInfo hotel;
       //create individual object list
        private List<Room> rooms = new ArrayList<Room>();
        private List<BookingInfo> bookings = new ArrayList<BookingInfo>();
        private List<Supporter> supporters = new ArrayList<Supporter>();
        private RobotBrain brain;

        public ProcessorXML(int task, RobotBrain abrain) {
            answer = "";
            brain = abrain;         
            hotel = new HotelInfo();
            try
            {
              xmlhandler = new JAXB_XMLParser("C:\\Users\\Obaro I. Johnson\\Desktop\\Assignment\\Hotel-Robot-HelpDeskp\\src\\hotel_helpdesk\\database.xml",ObjectFactory.class);
              hotel=(HotelInfo) xmlhandler.unMarshaller();
               
              setQuestionType(task);// Set the question task  and return the question focus directions
            }
            catch(Exception err)
            {
                JOptionPane.showMessageDialog(null,err.getMessage());
            }
            

        }

        private void analysis(int task) {
            switch (task) {
                case RobotBrain.AVALIABLE_ROOMS:
                    getAvaliableRooms();
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
        private void getAvaliableRooms() {

            this.rooms = this.hotel.getRoom();
            this.answer = "Below is the list of rooms requested\n <table width='100%' > <tr><th>Room Number</th><th>Type</th> <th>Amount</th>"
                    + "<th>Description</th> <th>Status</th> </tr>";

            Iterator<Room> iter = this.rooms.iterator();
             
            while (iter.hasNext()) {
                //get all the rooms in the databases
                Room temRoom = (Room)iter.next();
                this.answer += "<tr><td>"+temRoom.getRoomNumber()+"</td><td>"+temRoom.getType()+"</td> <th>"+temRoom.getAmount()+"</td><td>"+temRoom.getDescriptions()+"</td> <td>"+temRoom.isStatus()+"</td> </tr>";
                
               // JOptionPane.showMessageDialog(null,"testing");
            }

            //the last room
            this.answer += "</table>";

        }

        private void getHotelDetails() {

            this.answer = "Answer found is  \n <center><font color='blue' size='14' > The Hotel Details</font></center>\n";
            this.answer+=" Hotel Name : "+hotel.getName()+"<br> ";
            this.answer+=" Description: "+hotel.getDescriptions() +"<br>";
            this.answer+=" Number of Rooms: "+hotel.getNumberOfRooms()+"<br>";
        }

        private void getBookings() {
            this.bookings = this.hotel.getBookingInfo();
            this.answer = " Hi below is the listd of all the bookings \n<center><font color='blue' size='14' > Booking Informations</font></center>\n";
            this.answer += "<table> <tr><th>Booking ID</th><th>Room Number</th> <th>Customer ID</th>"
                    + "<th>Description</th> <th>Status</th><th>Booked Date</th> </tr>";

            Iterator<BookingInfo> iter = this.bookings.iterator();

            while (iter.hasNext()) {
                //get all the rooms in the databases
            }

            //the last room
            this.answer += "<tr><th>Booking ID</th><th>Room Number</th> <th>Customer ID</th>"
                    + "<th>Description</th> <th>Status</th><th>Booked Date</th> </tr>";
            this.answer += " </table>";
        }

        private void getRobotInformation() {

            this.supporters = hotel.get_0020Supporter();
            this.answer += "Well! I am a robot and I am here to help you!\n I'm not allow to tell you any thing more  than this!\n";
        }

        private void error() {

            this.answer = "I dont understand that please can your enter your question again!\n";
        }

        private String setQuestionType(int question_type) {

            this.analysis(question_type);
            return this.answer;

        }

    }
}
