package hellotvxlet;

public class Question {

    public String question;
    public String[] choices = new String[4];
    public String answer;

    public Question(String question, String choice1, String choice2,
            String choice3, String choice4, String answer) {
        setQuestion(question);
        setChoices(new String[] {choice1, choice2, choice3, choice4});
        setAnswer(answer);
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String[] getChoices() {
        return choices;
    }

    public void setChoices(String[] choices) {
        this.choices = choices;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}


