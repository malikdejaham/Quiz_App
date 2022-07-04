package com.example.tawhidquiz.controler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tawhidquiz.R;
import com.example.tawhidquiz.model.QuestionModel;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    public static final String EXTRA_SCORE = "com.example.tawhidquiz.controler.EXTRA_SCORE";
    public static final String EXTRA_TOTALQUESTIONS = "com.example.tawhidquiz.controler.EXTRA_TOTALQUESTIONS";

    private TextView tvQuestion, tvQuestionNo;
    private RadioGroup radioGroup;
    private RadioButton rb1, rb2, rb3;
    private Button btnNext;

    int totalQuestions;
    int qCounter;
    int score;
    int finalScore;

    ColorStateList dfRbColor;
    boolean answered;

    private QuestionModel currentQuestion;

    private List<QuestionModel> questionsList;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        getSupportActionBar().hide();

        questionsList = new ArrayList<>();
        tvQuestion = findViewById(R.id.textQuestion);
        tvQuestionNo = findViewById(R.id.textQuestionNo);

        radioGroup = findViewById(R.id.radioGroup);
        rb1 = findViewById(R.id.rb1);
        rb2 = findViewById(R.id.rb2);
        rb3 = findViewById(R.id.rb3);
        btnNext = findViewById(R.id.btnNext);

        dfRbColor = rb1.getTextColors();

        addQuestions();
        totalQuestions = questionsList.size();
        showNextQuestion();

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(answered == false) {
                    if(rb1.isChecked() || rb2.isChecked() || rb3.isChecked()) {
                        checkAnswer();

                    } else {
                        Toast.makeText(QuizActivity.this, "Choisir une réponse", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    showNextQuestion();
                }
            }
        });

    }

    private void checkAnswer() {
        answered = true;
        RadioButton rbSelected = findViewById(radioGroup.getCheckedRadioButtonId());
        int answerNo = radioGroup.indexOfChild(rbSelected) +1;
        if(answerNo == currentQuestion.getCorrectAnsNo()){
            score++;
        }
        rb1.setTextColor(Color.RED);
        rb2.setTextColor(Color.RED);
        rb3.setTextColor(Color.RED);
        switch (currentQuestion.getCorrectAnsNo()) {
            case 1:
                rb1.setTextColor(Color.GREEN);
                break;
            case 2:
                rb2.setTextColor(Color.GREEN);
                break;
            case 3:
                rb3.setTextColor(Color.GREEN);
                break;
        }
        if (qCounter < totalQuestions){
            btnNext.setText("Question Suivante");
        } else {
            btnNext.setText("Afficher mon score");
        }
    }

    public void showNextQuestion() {

        radioGroup.clearCheck();
        rb1.setTextColor(dfRbColor);
        rb2.setTextColor(dfRbColor);
        rb3.setTextColor(dfRbColor);

        if (qCounter < totalQuestions) {

            currentQuestion = questionsList.get(qCounter);
            tvQuestion.setText(currentQuestion.getQuestion());
            rb1.setText(currentQuestion.getOption1());
            rb2.setText(currentQuestion.getOption2());
            rb3.setText(currentQuestion.getOption3());

            qCounter++;
            btnNext.setText("Valider la réponse");
            tvQuestionNo.setText("Question: "+qCounter+"/"+totalQuestions);
            answered = false;

        } else {
            finalScore = score;
            showFinalScore();

        }
    }


    // Afficher nouvelle activité avec le score final au clic sur le bouton

    public void showFinalScore() {
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QuizActivity.this, ScoreActivity.class);
                intent.putExtra(EXTRA_SCORE, score);
                intent.putExtra(EXTRA_TOTALQUESTIONS, totalQuestions);
                startActivity(intent);
            }
        });
    }

    private void addQuestions() {
        questionsList.add(new QuestionModel("En combien de parties se divise le tawhid ?", "Deux", "Trois", "Cinq", 2));
        questionsList.add(new QuestionModel("Qu'est ce que le tawhid ar-rububiyya ?", "L'unicité dans la seigneurie", "L'unicité dans l'adoration", "L'unicité dans les noms et attributs", 1));
        questionsList.add(new QuestionModel("Qu'est ce que le tawhid al uluhiyya ?", "L'unicité dans la seigneurie", "L'unicité dans l'adoration", "L'unicité dans les noms et attributs", 2));
        questionsList.add(new QuestionModel("Qu'est ce que le tawhid al asma wa sifat ?", "L'unicité dans la seigneurie", "L'unicité dans l'adoration", "L'unicité dans les noms et attributs", 3));
        questionsList.add(new QuestionModel("Que signifie le tawhid al uluhiyya ?", "Qu'Allah est le Créateur, le Pourvoyeur, l'Organisateur", "Qu'Allah est le Créateur et que Lui Seul mérite l'adoration", "Qu'Allah seul mérite l'adoration", 3));
        questionsList.add(new QuestionModel("Que signifie le tawhid ar-rububiyya ?", "Qu'Allah est le Créateur, le Pourvoyeur, l'Organisateur", "Qu'Allah est le Créateur et que Lui Seul mérite l'adoration", "Qu'Allah seul mérite l'adoration", 1));
        questionsList.add(new QuestionModel("Quel est le sens de \"Lâ ilâha illa Allah\"", "Il n'y a de divinité qu'Allah", "Il n'y a nulle divinité digne d'être adorée si ce n'est Allah", "Il n'y a de divinité en dehors d'Allah", 2));
        questionsList.add(new QuestionModel("Quelles sont les conditions de l'attestation qu'il n'y a de divinité digne d'adoration qu'Allah ?", "Connaissance, certitude, acceptation, soumission, véracité, sincérité, amour et mécroire au taghout", "Connaissance, prière, zakat, jeûne, foi, excellence, amour et destin", "Croire aux anges, aux messagers, au livre, certitude, véracité, jugement et n'adorer qu'Allah", 1));
        questionsList.add(new QuestionModel("Combien y a-t-il de sourates dans le Coran ?", "126", "143", "114", 3));
        questionsList.add(new QuestionModel("Quels sont les 3 degrés de la religion ?", "Al-Islam, Al-Iman, Al-Ihsan", "Tawhid, Aqida, Barzakh", "Ar rububiyaa, Al uluhiyya, Al asma wa sifat",1));
        questionsList.add(new QuestionModel("Ou est Allah ?", "Il est partout", "Il s'est elevé sur son trône", "Il n'est nulle part", 2));
        questionsList.add(new QuestionModel("Les actes annulant l'Islam sont au nombre de...", "10", "Cinq", "Vingt", 1));
        questionsList.add(new QuestionModel("Quelle est la premiere chose qu'il est obligatoire d'apprendre ?", "La Prière", "Le Tawhid", "L'Arabe", 2));
        questionsList.add(new QuestionModel("{C’est Allah qui a en Sa possession les Cieux et la Terre. Il crée ce qu’Il veut. Il fait don de filles à qui Il veut, et don de garçons à qui Il veut} [Ash-Shoûrâ : 49]. Ce verset prouve :", "le Tawhid de la Seigneurie", "le Tawhid de l'adoration", "le Tawhid des Noms & Attributs", 1));
        questionsList.add(new QuestionModel("Les Attributs divins sont exempts de toute ressemblance avec ceux de la création. Quelle en est la preuve ?", "{alors qu’ils ne Le cernent pas de par leur science} [Tâhâ : 110]", "{Allah est, en vérité, Celui qui entend et qui voit parfaitement tout} [An-Nissâ° : 58]", "{Rien ne Lui est semblable. Et Il est Celui qui voit et entend parfaitement tout} [Ash-Shoûrâ : 11]", 3));
        questionsList.add(new QuestionModel("Quelle est la preuve que l'on doit renoncer au [Comment] des attributs d'Allah", "{Rien ne Lui est semblable. Et Il est Celui qui voit et entend parfaitement tout} [Ash-Shoûrâ : 11]", "{alors qu’ils ne Le cernent pas de par leur science} [Tâhâ : 110]", "{Allah est, en vérité, Celui qui entend et qui voit parfaitement tout} [An-Nissâ° : 58]", 2));
        questionsList.add(new QuestionModel("Les anges peuvent désobéir à Allah", "Vrai", "Faux", "Je ne sais pas", 2));
        questionsList.add(new QuestionModel("Est-il permis d'invoquer les anges ?", "Oui", "Non", "Je ne sais pas", 2));
        questionsList.add(new QuestionModel("De quoi les anges ont-ils été crées", "D'argile", "D'eau", "De lumière", 3));
        questionsList.add(new QuestionModel("Les anges peuvent prendre l'apparence humaine", "Vrai", "Faux", "Je ne sais pas", 1));
        /*questionsList.add(new QuestionModel("", "", "", "", 1));
        questionsList.add(new QuestionModel("", "", "", "", 1));
        questionsList.add(new QuestionModel("", "", "", "", 1));
        questionsList.add(new QuestionModel("", "", "", "", 1));
        questionsList.add(new QuestionModel("", "", "", "", 1));
        questionsList.add(new QuestionModel("", "", "", "", 1));
        questionsList.add(new QuestionModel("", "", "", "", 1));
        questionsList.add(new QuestionModel("", "", "", "", 1));
        questionsList.add(new QuestionModel("", "", "", "", 1));
        questionsList.add(new QuestionModel("", "", "", "", 1));
        */

    }
}