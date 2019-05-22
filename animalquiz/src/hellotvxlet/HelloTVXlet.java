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
    public HStaticText questionText;
    public HTextButton button1;
    public HTextButton button2;
    public HTextButton button3;
    public HTextButton button4;
    int currentQuestion = 0;
    Question questions[] = new Question[10];
    HStaticText correctness;
    public int score;

    public void initXlet(XletContext ctx) throws XletStateChangeException {
        scene = HSceneFactory.getInstance().getDefaultHScene(); // SCHERM = 720 x 576

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

        questionText = new HStaticText(questions[currentQuestion].getQuestion(), 20, 200, 680, 100); // tekst,x,y,w,h

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
        scene.setRenderMode(HScene.IMAGE_CENTER);

        questionText.setBackgroundMode(HVisible.BACKGROUND_FILL);
        questionText.setBackground(new Color(0, 0, 150));
        scene.add(questionText);

        button1 = new HTextButton(questions[0].getChoices()[0], 20, 330, 320, 50);

        button1.setBackgroundMode(HVisible.BACKGROUND_FILL);
        button1.setBackground(new Color(0, 0, 100));
        scene.add(button1);

        button2 = new HTextButton(questions[0].getChoices()[1], 20, 420, 320, 50);

        button2.setBackgroundMode(HVisible.BACKGROUND_FILL);
        button2.setBackground(new Color(0, 0, 100));
        scene.add(button2);

        button3 = new HTextButton(questions[0].getChoices()[2], 370, 330, 320, 50);

        button3.setBackgroundMode(HVisible.BACKGROUND_FILL);
        button3.setBackground(new Color(0, 0, 100));
        scene.add(button3);

        button4 = new HTextButton(questions[0].getChoices()[3], 370, 420, 320, 50);

        button4.setBackgroundMode(HVisible.BACKGROUND_FILL);
        button4.setBackground(new Color(0, 0, 100));
        scene.add(button4);

        buttonActions();
        addFocusAndListeners();

        scene.validate();
        scene.setVisible(true);
    }

    public void buttonActions() {
        button1.setActionCommand("1");
        button2.setActionCommand("2");
        button3.setActionCommand("3");
        button4.setActionCommand("4");
    }

    public void addFocusAndListeners() {
        button1.requestFocus();
        button1.setFocusTraversal(null, button2, null, button3);
        button1.addHActionListener(this);
        button2.setFocusTraversal(button1, null, button3, button4);
        button2.addHActionListener(this);
        button3.setFocusTraversal(null, button4, button1, button2);
        button3.addHActionListener(this);
        button4.setFocusTraversal(button3, null, button2, null);
        button4.addHActionListener(this);
    }

    public void removeFocusAndListeners() {
        button1.setFocusTraversal(null, null, null, null);
        button1.removeHActionListener(this);
        button2.setFocusTraversal(null, null, null, null);
        button2.removeHActionListener(this);
        button3.setFocusTraversal(null, null, null, null);
        button3.removeHActionListener(this);
        button4.setFocusTraversal(null, null, null, null);
        button4.removeHActionListener(this);
    }

    public void score() {
        HStaticText scoreText = new HStaticText("Je hebt " + score + " van de " + questions.length + " vragen goed beantwoord!\n" + (int) (((double) score / questions.length) * 100) + "%", 80, 250, 560, 90);
        scoreText.setBackgroundMode(HVisible.BACKGROUND_FILL);
        scoreText.setBackground(new Color(0, 0, 150));
        scene.add(scoreText);
    }

    public void nextQuestion() {
        currentQuestion++;
        System.out.println("CURRENTT question: " + currentQuestion);
        questionText.setTextContent(questions[currentQuestion].getQuestion(), HVisible.NORMAL_STATE);
        button1.setTextContent(questions[currentQuestion].getChoices()[0], HVisible.NORMAL_STATE);
        button2.setTextContent(questions[currentQuestion].getChoices()[1], HVisible.NORMAL_STATE);
        button3.setTextContent(questions[currentQuestion].getChoices()[2], HVisible.NORMAL_STATE);
        button4.setTextContent(questions[currentQuestion].getChoices()[3], HVisible.NORMAL_STATE);
    }

    public void actionPerformed(ActionEvent arg0) {

        if (arg0.getActionCommand().equals(questions[currentQuestion].getAnswer())) {
            correctness = new HStaticText("JUIST", 290, 500, 125, 50);
            correctness.setBackgroundMode(HVisible.BACKGROUND_FILL);
            correctness.setBackground(new Color(0, 100, 0));
            score++;
        } else {
            correctness = new HStaticText("FOUT", 290, 500, 125, 50);
            correctness.setBackgroundMode(HVisible.BACKGROUND_FILL);
            correctness.setBackground(new Color(255, 0, 0));
        }

        changeButtonColors(questions[currentQuestion].getAnswer(), false);
        scene.add(correctness);
        scene.repaint();
        removeFocusAndListeners();

        new java.util.Timer().schedule(
                new java.util.TimerTask() {

                    public void run() {
                        if (currentQuestion == questions.length - 1) {
                            scene.remove(questionText);
                            scene.remove(button1);
                            scene.remove(button2);
                            scene.remove(button3);
                            scene.remove(button4);
                            score();
                        } else {
                            nextQuestion();
                            changeButtonColors(null, true);
                            addFocusAndListeners();
                        }
                        scene.remove(correctness);
                        scene.repaint();
                    }
                },
                1850);

    }

    public void changeButtonColors(String correctAnswer, boolean reset) {
        if (reset) {
            button1.setBackground(new Color(0, 0, 100));
            button2.setBackground(new Color(0, 0, 100));
            button3.setBackground(new Color(0, 0, 100));
            button4.setBackground(new Color(0, 0, 100));
        } else {
            button1.setBackground(new Color(255, 0, 0));
            button2.setBackground(new Color(255, 0, 0));
            button3.setBackground(new Color(255, 0, 0));
            button4.setBackground(new Color(255, 0, 0));
            switch (Integer.parseInt(correctAnswer)) {
                case 1:
                    button1.setBackground(new Color(0, 100, 0));
                    break;
                case 2:
                    button2.setBackground(new Color(0, 100, 0));
                    break;
                case 3:
                    button3.setBackground(new Color(0, 100, 0));
                    break;
                case 4:
                    button4.setBackground(new Color(0, 100, 0));
                    break;
            }
        }
    }

    public void startXlet() throws XletStateChangeException {
    }

    public void pauseXlet() {
    }

    public void destroyXlet(boolean unconditional) throws XletStateChangeException {
    }
}