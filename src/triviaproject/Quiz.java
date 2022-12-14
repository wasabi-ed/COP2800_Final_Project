/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package triviaproject;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;



/**
 *
 * @author wasabi
 */
public class Quiz implements ActionListener{
    
    String[] questions;
    String[][] options;
    char[] answers;
    
    //char guess;
    char answer;
    int index;
    int correct_guesses = 0;
    int total_questions;
    int result;
    //int seconds = 10;
    
    JFrame frame = new JFrame();
    JTextField textField = new JTextField();
    JTextArea textArea = new JTextArea();
    JButton buttonA = new JButton();
    JButton buttonB = new JButton();
    JButton buttonC = new JButton();
    JButton buttonD = new JButton();
    JLabel answer_labelA = new JLabel();
    JLabel answer_labelB = new JLabel();
    JLabel answer_labelC = new JLabel();
    JLabel answer_labelD = new JLabel();
    //JLabel time_label = new JLabel();
    //JLabel seconds_left = new JLabel();
    JTextField number_right = new JTextField();
    JTextField percentage = new JTextField();
    
    
    public Quiz(String[] questions, String[][] options, char[] answers){
        this.questions = questions;
        this.options = options;
        this.answers = answers;
        this.total_questions = questions.length;
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(650, 650);
        frame.getContentPane().setBackground(new Color(50, 50, 50));
        frame.setResizable(false);
        frame.setLayout(null);
        
        textField.setBounds(0,0,650,50);
        textField.setBackground(new Color(25, 25, 25));
        textField.setForeground(new Color(204,0,0));
        textField.setFont(new Font("Helvetica", Font.ITALIC, 30));
        textField.setBorder(BorderFactory.createBevelBorder(1));
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setEditable(false);
        textField.setText("testing testing");
        
        textArea.setBounds(0,50,650,50);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setBackground(new Color(25, 25, 25));
        textArea.setForeground(new Color(204,0,0));
        textArea.setFont(new Font("Helvetica", Font.ITALIC, 20));
        textArea.setBorder(BorderFactory.createBevelBorder(1));
        textArea.setEditable(false);
        textArea.setText("testing testing");
        
        buttonA.setBounds(0,100,100,100);
        buttonA.setFont(new Font("Helvetica", Font.BOLD, 35));
        buttonA.setFocusable(false);
        buttonA.addActionListener(this);
        buttonA.setText("A");
        
        buttonB.setBounds(0,200,100,100);
        buttonB.setFont(new Font("Helvetica", Font.BOLD, 35));
        buttonB.setFocusable(false);
        buttonB.addActionListener(this);
        buttonB.setText("B");
        
        buttonC.setBounds(0,300,100,100);
        buttonC.setFont(new Font("Helvetica", Font.BOLD, 35));
        buttonC.setFocusable(false);
        buttonC.addActionListener(this);
        buttonC.setText("C");
        
        buttonD.setBounds(0,400,100,100);
        buttonD.setFont(new Font("Helvetica", Font.BOLD, 35));
        buttonD.setFocusable(false);
        buttonD.addActionListener(this);
        buttonD.setText("D");
        
        answer_labelA.setBounds(125, 100, 500, 100);
        answer_labelA.setBackground(new Color(50, 50, 50));
        answer_labelA.setForeground(new Color(51, 204, 255));
        answer_labelA.setFont(new Font("Helvetica", Font.PLAIN, 25));
        answer_labelA.setText("Testing Label 1");
        
        answer_labelB.setBounds(125, 200, 500, 100);
        answer_labelB.setBackground(new Color(50, 50, 50));
        answer_labelB.setForeground(new Color(51, 204, 255));
        answer_labelB.setFont(new Font("Helvetica", Font.PLAIN, 25));
        answer_labelB.setText("Testing Label 2");
        
        answer_labelC.setBounds(125, 300, 500, 100);
        answer_labelC.setBackground(new Color(50, 50, 50));
        answer_labelC.setForeground(new Color(51, 204, 255));
        answer_labelC.setFont(new Font("Helvetica", Font.PLAIN, 25));
        answer_labelC.setText("Testing Label 3");
        
        answer_labelD.setBounds(125, 400, 500, 100);
        answer_labelD.setBackground(new Color(50, 50, 50));
        answer_labelD.setForeground(new Color(51, 204, 255));
        answer_labelD.setFont(new Font("Helvetica", Font.PLAIN, 25));
        answer_labelD.setText("Testing Label 4");
        
        number_right.setBounds(225, 225, 200, 100);
        number_right.setBackground(new Color(25, 25, 25));
        number_right.setForeground(new Color(25, 255, 0));
        number_right.setFont(new Font("Ink Free", Font.BOLD, 50));
        number_right.setBorder(BorderFactory.createBevelBorder(1));
        number_right.setHorizontalAlignment(JTextField.CENTER);
        number_right.setEditable(false);
                
        percentage.setBounds(225, 325, 200, 100);
        percentage.setBackground(new Color(25, 25, 25));
        percentage.setForeground(new Color(25, 255, 0));
        percentage.setFont(new Font("Ink Free", Font.BOLD, 50));
        percentage.setBorder(BorderFactory.createBevelBorder(1));
        percentage.setHorizontalAlignment(JTextField.CENTER);
        percentage.setEditable(false);
        
        frame.add(answer_labelA);
        frame.add(answer_labelB);
        frame.add(answer_labelC);
        frame.add(answer_labelD);
        frame.add(buttonA);
        frame.add(buttonB);
        frame.add(buttonC);
        frame.add(buttonD);
        frame.add(textField);
        frame.add(textArea);
        frame.setVisible(true);
        
        nextQuestion();
        
    }
    public void nextQuestion(){
        if(index >= total_questions){
            results();
        }
        else{
            textField.setText("Question: " +(index + 1));
            textArea.setText(questions[index]);
            answer_labelA.setText(options[index][0]);
            answer_labelB.setText(options[index][1]);
            answer_labelC.setText(options[index][2]);
            answer_labelD.setText(options[index][3]);
        }
    }
    @Override
    public void actionPerformed(ActionEvent e){
        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);
        
        if(e.getSource() == buttonA){
            answer = 'A';
            if(answer == answers[index]){
                correct_guesses++;
            }
        }
        if(e.getSource() == buttonB){
            answer = 'B';
            if(answer == answers[index]){
                correct_guesses++;
            }
        }
        if(e.getSource() == buttonC){
            answer = 'C';
            if(answer == answers[index]){
                correct_guesses++;
            }
        }
        if(e.getSource() == buttonD){
            answer = 'D';
            if(answer == answers[index]){
                correct_guesses++;
            }
        }
        displayAnswer();
    }
    public void displayAnswer(){
        
        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);
        
        if(answers[index] != 'A')
            answer_labelA.setForeground(new Color(255,0,0));
        if(answers[index] != 'B')
            answer_labelB.setForeground(new Color(255,0,0));        
        if(answers[index] != 'C')
            answer_labelC.setForeground(new Color(255,0,0));        
        if(answers[index] != 'D')
            answer_labelD.setForeground(new Color(255,0,0));
        
        Timer pause = new Timer(2000, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                
                answer_labelA.setForeground(new Color(51, 204, 255));
                answer_labelB.setForeground(new Color(51, 204, 255));
                answer_labelC.setForeground(new Color(51, 204, 255));
                answer_labelD.setForeground(new Color(51, 204, 255));
                
                answer = ' ';
                buttonA.setEnabled(true);
                buttonB.setEnabled(true);
                buttonC.setEnabled(true);
                buttonD.setEnabled(true);
                index++;
                nextQuestion();
            }
        });
        pause.setRepeats(false);
        pause.start();
    }
    public void results(){
        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);

        result = (int) ((correct_guesses / (double) total_questions) * 100);

        textField.setText("RESULTS!");
        textArea.setText("");
        answer_labelA.setText("");
        answer_labelB.setText("");
        answer_labelC.setText("");
        answer_labelD.setText("");

        number_right.setText("(" + correct_guesses + "/" + total_questions + ")");
        percentage.setText(result + "%");

        frame.add(number_right);
        frame.add(percentage);
    }
    
    public static void main(String[] args) throws MalformedURLException, IOException, ParseException {
        // TODO code application logic here
        String[] questions;
        String[][] options;
        char[] answers;
        
        Random rng = new Random();
        
        String url = "https://opentdb.com/api.php?amount=10&type=multiple";
        BufferedReader in = new BufferedReader(new InputStreamReader(new URL(url).openStream()));
        
        JSONObject obj = (JSONObject)(new JSONParser().parse(in));
        JSONArray results = (JSONArray) obj.get("results");
        
        questions = new String[results.size()];
        options = new String[results.size()][];
        answers = new char[results.size()];
        for(int i = 0; i < results.size(); i++) {
            JSONObject result = (JSONObject) results.get(i);
            String question = (String) result.get("question");
            String correct_answer = (String) result.get("correct_answer");
            JSONArray incorrect_answers = (JSONArray) result.get("incorrect_answers");
            
            questions[i] = question;
            options[i] = new String[4];
            options[i][0] = correct_answer;
            options[i][1] = (String) incorrect_answers.get(0);
            options[i][2] = (String) incorrect_answers.get(1);
            options[i][3] = (String) incorrect_answers.get(2);
            
            int choice = rng.nextInt(4);
            String tmp = options[i][0];
            options[i][0] = options[i][choice];
            options[i][choice] = tmp;
            
            answers[i] = (char)('A' + choice);
        }
        
        Quiz quiz = new Quiz(questions, options, answers);
    }
}
