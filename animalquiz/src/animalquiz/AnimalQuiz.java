package animalquiz;

import java.awt.Color;
import javax.tv.xlet.*;
import org.havi.ui.HScene;
import org.havi.ui.HSceneFactory;
import org.havi.ui.HStaticText;
import org.havi.ui.HVisible;


public class AnimalQuiz implements Xlet {

  
    public AnimalQuiz() {
        
    }

    public void initXlet(XletContext context) {
      HScene scene=HSceneFactory.getInstance().getDefaultHScene();
      
      Vraag v1 = new Vraag("wie?", "ik", "dd", "zd", "scc", 1);
      
      HStaticText hst=new HStaticText("Hel " + v1.getVraag() + "e World" ,100,100,200,200);
      hst.setBackgroundMode(HVisible.BACKGROUND_FILL);
      hst.setBackground(Color.BLUE);
      
     
      scene.add(hst);
      scene.setVisible(true);
         
      scene.validate();
 
      
     
    }

    public void startXlet() {
    
    }

    public void pauseXlet() {
     
    }

    public void destroyXlet(boolean unconditional) {
     
    }
}
