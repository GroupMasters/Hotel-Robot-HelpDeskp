/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel_helpdesk;
import server.share.Controller;
import server.share.ISubject;
import server.share.IObserver;
/**
 *
 * @author obaro
 */
public class RobotController extends IObserver implements Controller {
    private RobotModel model;
    private RobotView view;
    
    public RobotController(RobotModel amodel, RobotView aview)
    {
        this.model =amodel;
        this.view =aview;
        this.view.attach(this);
        this.model.attach(this);
    }

    @Override
    public void launch() {
      this.view.setAlwaysOnTop(true);
      this.view.pack();
      this.view.setResizable(false);
        this.view.setVisible(true);
    }

    @Override
    public ISubject getModel() {
        return this.model;
    }

    @Override
    public ISubject getView() {
     return this.view;
    }
}
