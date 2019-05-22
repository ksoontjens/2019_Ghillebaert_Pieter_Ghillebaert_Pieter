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
import org.havi.ui.event.HActionListener;

public class HelloTVXlet implements Xlet, HActionListener {

    public HScene scene;
    public HStaticText hst;
    public HTextButton button1;
    public HTextButton button2;
    public HTextButton button3;
    public HTextButton button4;
    int currentQuestion = 0;
    Question questions[] = new Question[10];
    HStaticText correctness;

    public void initXlet(XletContext ctx) throws XletStateChangeException {
        scene = HSceneFactory.getInstance().getDefaultHScene();
        // SCHERM = 720 x 576

        questions[0] = new Question("Welke kleur heeft het bloed van een inktvis?", "Rood", "Blauw", "Zwart", "Groen", "2");
        questions[1] = new Question("Hoeveel magen heeft een vogel?", "1", "2", "3", "4", "2");
        questions[2] = new Question("Van welke plant kunnen koala's leven?", "Eikels", "Beukennootjes", "Eucalyptus", "Elsjes", "3");
        questions[3] = new Question("Wat is giftig voor honden?", "Ham", "Chips", "Chocolade", "Kaas", "3");
        questions[4] = new Question("Wat is een koudbloedig dier?", "Slang", "Zwaan", "Hond", "Kat", "1");
        questions[5] = new Question("Wat doen katten als ze tevreden zijn?", "Miauwen", "Spinnen", "Piepen", "Grommen", "2");
        questions[6] = new Question("Wat voor dieren zijn varkens?", "Omnivoor", "Carnivoor", "Herbivoor", "Veganist", "1");
        questions[7] = new Question("Hoe heet de gevaarlijkste haai?", "Stierhaai", "IJshaai", "Walvishaai", "Witte haai", "4");
        questions[8] = new Question("Hoeveel poten heeft een spin?", "2", "4", "6", "8", "4");
        questions[9] = new Question("Hoeveel magen heeft een koe?", "1", "2", "4", "8", "3");

        hst = new HStaticText(questions[currentQuestion].getQuestion(), 20, 200, 680, 100); // tekst,x,y,w,h


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


        button1 = new HTextButton(questions[0].getChoices()[0], 20, 330, 320, 50); // tekst,x,y,w,h

        button1.setBackgroundMode(HVisible.BACKGROUND_FILL);
        button1.setBackground(new Color(0, 0, 100));
        scene.add(button1);

        button2 = new HTextButton(questions[0].getChoices()[1], 20, 420, 320, 50); // tekst,x,y,w,h

        button2.setBackgroundMode(HVisible.BACKGROUND_FILL);
        button2.setBackground(new Color(0, 0, 100));
        scene.add(button2);

        button3 = new HTextButton(questions[0].getChoices()[2], 370, 330, 320, 50); // tekst,x,y,w,h

        button3.setBackgroundMode(HVisible.BACKGROUND_FILL);
        button3.setBackground(new Color(0, 0, 100));
        scene.add(button3);

        button4 = new HTextButton(questions[0].getChoices()[3], 370, 420, 320, 50); // tekst,x,y,w,h

        button4.setBackgroundMode(HVisible.BACKGROUND_FILL);
        button4.setBackground(new Color(0, 0, 100));
        scene.add(button4);

        buttonActions();

        button1.requestFocus();

        quizGame();
        scene.validate();
        scene.setVisible(true);
    }

    public void buttonActions() {
        button1.setFocusTraversal(null, button2, null, button3);
        button2.setFocusTraversal(button1, null, button3, button4);
        button3.setFocusTraversal(null, button4, button1, button2);
        button4.setFocusTraversal(button3, null, button2, null);

        button1.setActionCommand("1");
        button1.addHActionListener(this);
        button2.setActionCommand("2");
        button2.addHActionListener(this);
        button3.setActionCommand("3");
        button3.addHActionListener(this);
        button4.setActionCommand("4");
        button4.addHActionListener(this);



    }

    public void quizGame() {
    }

    public void nextQuestion() {
        currentQuestion++;
        System.out.println("CURRENTT: " + currentQuestion);
        hst.setTextContent(questions[currentQuestion].getQuestion(), HVisible.NORMAL_STATE);
        button1.setTextContent(questions[currentQuestion].getChoices()[0], HVisible.NORMAL_STATE);
        button2.setTextContent(questions[currentQuestion].getChoices()[1], HVisible.NORMAL_STATE);
        button3.setTextContent(questions[currentQuestion].getChoices()[2], HVisible.NORMAL_STATE);
        button4.setTextContent(questions[currentQuestion].getChoices()[3], HVisible.NORMAL_STATE);
    }

    public void pauseXlet() {
    }

    public void startXlet() throws XletStateChangeException {
    }

    public void destroyXlet(boolean unconditional) throws XletStateChangeException {
    }

    public void actionPerformed(ActionEvent arg0) {

        if (arg0.getActionCommand().equals(questions[currentQuestion].getAnswer())) {
            correctness = new HStaticText("CORRECT", 290, 500, 125, 50);
            correctness.setBackgroundMode(HVisible.BACKGROUND_FILL);
            correctness.setBackground(Color.GREEN);
            System.out.println("CORRRREECT");

        } else {
            correctness = new HStaticText("FALSE", 290, 500, 125, 50);
            correctness.setBackgroundMode(HVisible.BACKGROUND_FILL);
            correctness.setBackground(Color.RED);
            System.out.println("falssssee");
        }
        scene.add(correctness);
       // scene.remove(correctness);
        scene.repaint();
        //scene.add(correctness);
        nextQuestion();



    }
}