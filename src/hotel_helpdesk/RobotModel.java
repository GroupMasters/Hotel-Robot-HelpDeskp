/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel_helpdesk;
import server.share.IObserver;
import server.share.ISubject;
/**
 *
 * @author obaro
 */
class RobotModel implements ISubject {

    private RobotController controller;
    @Override
    public void attach(IObserver observer) {
       controller = (RobotController) observer;
    }
    
}
