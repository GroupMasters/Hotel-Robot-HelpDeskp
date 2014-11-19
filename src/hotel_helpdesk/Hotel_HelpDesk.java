/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel_helpdesk;

/**
 *
 * @author obaro
 */
public class Hotel_HelpDesk {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        RobotController controller = new  RobotController (new RobotModel(), new RobotView("Hotel Help Desk"));
        controller.launch();
        
    }
    
}
