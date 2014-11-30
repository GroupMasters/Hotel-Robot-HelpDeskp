/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel_helpdesk;
import java.util.Vector;
import server.share.Controller;
import server.share.ISubject;
import server.share.IObserver;
/**
 *
 * @author obaro
 */
public class RobotController extends IObserver implements Controller {
    private final RobotModel model;
    private final RobotView view;
    
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
      Vector<String> con= (Vector<String>)this.model.getConversations();
      
     view.updateConversations(con);
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

    void xhsCloseHelpWindow() {
        this.view.dispose();
       System.exit(0);
    }

    void xhsSendMessage(String text) {
        if(!text.isEmpty())
        {
            this.view.clearConversation();
           //process the message in its own thread 
            Thread thread = new Thread(){
             public void run()
             {
                 model.processMessage(text);
               
             }
            };
            thread.start();
        }
        
     }

  synchronized  void  xhsResultMessgae(int i)
    {//return the result conversations
        view.updateConversations(model.getConversations());
    }
}
