package hellotvxlet;

import java.awt.Color;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.event.ActionEvent;
import javax.tv.xlet.Xlet;
import javax.tv.xlet.XletContext;
import javax.tv.xlet.XletStateChangeException;
import org.havi.ui.HScene;
import org.havi.ui.HSceneFactory;
import org.havi.ui.HStaticText;
import org.havi.ui.HTextButton;
import org.havi.ui.HVisible;

public class HelloTVXlet implements Xlet {

    public void destroyXlet(boolean unconditional) throws XletStateChangeException {
    }

    public void initXlet(XletContext ctx) throws XletStateChangeException {
        HScene scene = HSceneFactory.getInstance().getDefaultHScene();
        // SCHERM = 720 x 576

        Vraag v1 = new Vraag("Welke kleur heeft het bloed van een inktvis", "Rood", "Blauw", "Zwart", "Groen", 2);
        Vraag v2 = new Vraag("Hoeveel magen heeft een vogel?", "1", "2", "3", "4", 2);
        Vraag v3 = new Vraag("Van welke plant kunnen koala's leven?", "Eikels", "Beukennootjes", "Eucalyptus", "Elsjes", 3);
        Vraag v4 = new Vraag("Wat is giftig voor honden?", "Ham", "Chips", "Chocolade", "Kaas", 3);
        Vraag v5 = new Vraag("Wat is een koudbloedig dier?", "Slang", "Zwaan", "Hond", "Kat", 1);
        Vraag v6 = new Vraag("Wat doen katten als ze tevreden zijn?", "Miauwen", "Spinnen", "Piepen", "Grommen", 2);
        Vraag v7 = new Vraag("Wat voor dieren zijn varkens?", "Omnivoor", "Carnivoor", "Herbivoor", "Veganist", 1);
        Vraag v8 = new Vraag("Hoe heet de gevaarlijkste haai?", "Stierhaai", "IJshaai", "Walvishaai", "Witte haai", 4);
        Vraag v9 = new Vraag("Hoeveel poten heeft een spin?", "2", "4", "6", "8", 4);
        Vraag v10 = new Vraag("Hoeveel magen heeft een koe?", "1", "2", "4", "8", 3);


        HStaticText correctness = new HStaticText("CORRECT", 290, 500, 125, 50); // tekst,x,y,w,h

        HStaticText hst = new HStaticText(v1.getVraag(), 20, 200, 680, 100); // tekst,x,y,w,h


        Image bmap;

        bmap = scene.getToolkit().getImage("dieren.jpg");

        MediaTracker mtrack = new MediaTracker(scene);

        mtrack.addImage(bmap, 1);
        try {
            mtrack.waitForAll();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        scene.setBackgroundImage(bmap);

        scene.setRenderMode(scene.IMAGE_CENTER);



        hst.setBackgroundMode(HVisible.BACKGROUND_FILL);
        hst.setBackground(new Color(0, 0, 150));
        scene.add(hst);

        correctness.setBackgroundMode(HVisible.BACKGROUND_FILL);
        correctness.setBackground(Color.GREEN);
        scene.add(correctness);
        HTextButton button1 = new HTextButton("Bill Clinton", 20, 330, 320, 50); // tekst,x,y,w,h

        button1.setBackgroundMode(HVisible.BACKGROUND_FILL);
        button1.setBackground(new Color(0, 0, 100));
        scene.add(button1);

        HTextButton button2 = new HTextButton("George Washington", 20, 420, 320, 50); // tekst,x,y,w,h

        button2.setBackgroundMode(HVisible.BACKGROUND_FILL);
        button2.setBackground(new Color(0, 0, 100));
        scene.add(button2);

        HTextButton button3 = new HTextButton("Donald Trump", 370, 330, 320, 50); // tekst,x,y,w,h

        button3.setBackgroundMode(HVisible.BACKGROUND_FILL);
        button3.setBackground(new Color(0, 0, 100));
        scene.add(button3);

        HTextButton button4 = new HTextButton("Ronald Reagan", 370, 420, 320, 50); // tekst,x,y,w,h

        button4.setBackgroundMode(HVisible.BACKGROUND_FILL);
        button4.setBackground(new Color(0, 0, 100));
        scene.add(button4);


        button1.setFocusTraversal(null, button2, null, button3);
        button2.setFocusTraversal(button1, null, null, button4);
        button3.setFocusTraversal(null, button4, button1, null);
        button4.setFocusTraversal(button3, null, button2, null);

        button1.requestFocus();

        quizGame();
        scene.validate();
        scene.setVisible(true);
    }

    public void quizGame() {
        
        
        
    }
        public void nextQuestion(ActionEvent arg0) {
            
        }


    public void pauseXlet() {
    }

    public void startXlet() throws XletStateChangeException {
    }
}