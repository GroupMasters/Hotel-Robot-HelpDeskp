package hotel_helpdesk;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Obaro I. Johnson
 */
 class ProcessorXML {

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
                JOptionPane.showMessageDialog(null,err.getMessage(),"Error Message",JOptionPane.ERROR_MESSAGE);
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
            this.answer = "Below is the list of rooms requested<br><hr>";

            Iterator<Room> iter = this.rooms.iterator();
             
            while (iter.hasNext()) {
                //get all the rooms in the databases
                Room temRoom = iter.next();
                this.answer += "<hr>Room Number : "+temRoom.getRoomNumber();
                this.answer+="<br>Room Type: "+temRoom.getType();
                this.answer+="<br>Cost :"+temRoom.getAmount();
                this.answer+="Description "+temRoom.getDescriptions();
                this.answer+="Status "+temRoom.isStatus()+"<hr>";
                
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
            this.answer = " Below is the search found on the booking information requested\n<center><font color='blue' size='13' > Booking Informations</font></center>\n";
            
            Iterator<BookingInfo> iter;
            iter = this.bookings.iterator();
               while(iter.hasNext())
            {
              BookingInfo booking = iter.next();
              this.answer+="<hr><br>Booking Number: "+booking.getBookNo();
              this.answer+="<br>Room Number: "+booking.getRoomNumber();
              this.answer+="<br> Customer Id :"+booking.getCustomerID();
              this.answer +="<br>Description :"+booking.getDescriptions();
              this.answer+="<br>Avaliable : "+booking.isStatus()+"<br><hr>"  ;
            }
            
          
        }

        private void getRobotInformation() {

            this.supporters = hotel.get_0020Supporter();
            this.answer += "Well! I am a robot and I am here to help you!\n I'm not allow to tell you any thing more  than this!\n";
        }

        private void error() {

            this.answer = "I dont understand that please can your enter your question again!\n";
        }

       String setQuestionType(int question_type) {

            this.analysis(question_type);
            return this.answer;

        }

    }