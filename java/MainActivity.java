package com.SmartphoneCoder.talkingcalculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.SmartphoneCoder.talkingcalculator.R;

import android.widget.Toast;
import android.util.DisplayMetrics;
import android.content.SharedPreferences.Editor;
import android.speech.tts.TextToSpeech;
import android.widget.ImageButton;
import android.view.ViewGroup;
import android.content.res.Configuration;
import android.widget.ScrollView;

import java.util.Locale;  // for text to speech


import android.util.Log;
import android.view.Menu;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.io.File;
import java.io.IOException;




public class MainActivity extends Activity {

TextToSpeech tts;
ImageButton ImgButton;
TextView question_layout, answer_layout;
ScrollView scroll1, scroll2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
       
                     
        
       
      // ############ 
       text_size();
      // ############
       
      question_layout = (TextView)findViewById(R.id.queston_textview);
      
      answer_layout = (TextView)findViewById(R.id.answer_textview); 
       
       
       tts=new TextToSpeech(MainActivity.this, new TextToSpeech.OnInitListener() {

            @Override
            public void onInit(int status) {
                // TODO Auto-generated method stub
                if(status == TextToSpeech.SUCCESS){
                    //int result=tts.setLanguage(new )Locale("hi"));
                    int result = tts.setLanguage(Locale.UK);
                    if(result==TextToSpeech.LANG_MISSING_DATA ||
                            result==TextToSpeech.LANG_NOT_SUPPORTED){
                        Log.e("error", "This Language is not supported");
                    }
                  
                }
                else
                    Log.e("error", "Initilization Failed!");
            }
        });


    }

    /*@Override
    protected void onPause() {
        // TODO Auto-generated method stub

        if(tts != null){

            tts.stop();
            tts.shutdown();
        }
        super.onPause();
    }*/
    
    
    String question = "";
    String q_to_set = "";
    boolean play_sound = true;
    int brac_op_or_clo = 0;
    int length = 0;
    boolean can_type_point = true;
    
    char last_char_1; // last char of question
    char last_char_2; // second last char of question
    
    char num_0 = '0';
    char num_1 = '1';
    char num_2 = '2';
    char num_3 = '3';
    char num_4 = '4';
    char num_5 = '5';
    char num_6 = '6';
    char num_7 = '7';
    char num_8 = '8';
    char num_9 = '9';
    char power = '^';
    char divide = '/';
    char mult = '*';
    char subtr = '-';
    char addition = '+';
    char un_ro = 't';
    char dot = '.';
    char open_b = '(';
    char close_b = ')';
   
  
    
    public void scroll_position(){
      scroll1 = (ScrollView)findViewById(R.id.scroll1);
      scroll2 = (ScrollView)findViewById(R.id.scroll2);
      
      scroll1.post(new Runnable() {            
        @Override
        public void run() {
           scroll1.fullScroll(View.FOCUS_DOWN);              
        }
      });
      
      scroll2.post(new Runnable() {            
        @Override
        public void run() {
           scroll2.fullScroll(View.FOCUS_DOWN);              
        }
      });

    }
    
    
    
    
    
    public void text_size(){
    
    
      DisplayMetrics displayMetrics = new DisplayMetrics();
getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
    
    
      TextView first_textview = (TextView)findViewById(R.id.queston_textview);
       first_textview.setTextSize((((float)2.5)/100*height));
       
       
       TextView second_textview = (TextView)findViewById(R.id.answer_textview);
       second_textview.setTextSize((((float)2)/100*height));
       
       
       
       // First row of buttons
       Button btnC = (Button)findViewById(R.id.btnC);
       Button btnB = (Button)findViewById(R.id.btnB);
       Button btnP = (Button)findViewById(R.id.btnP);
       Button btnDel = (Button)findViewById(R.id.btnDel);
       
       // Second row of buttons
       Button btn7 = (Button)findViewById(R.id.btn7);
       Button btn8 = (Button)findViewById(R.id.btn8);
       Button btn9 = (Button)findViewById(R.id.btn9);
       Button btnD = (Button)findViewById(R.id.btnD);
        
       // Third row of buttons
       Button btn4 = (Button)findViewById(R.id.btn4);
       Button btn5 = (Button)findViewById(R.id.btn5);
       Button btn6 = (Button)findViewById(R.id.btn6);
       Button btnM = (Button)findViewById(R.id.btnM);
       
       // Fourth row of buttons 
       Button btn1 = (Button)findViewById(R.id.btn1);
       Button btn2 = (Button)findViewById(R.id.btn2);
       Button btn3 = (Button)findViewById(R.id.btn3);
       Button btnS = (Button)findViewById(R.id.btnS);
       
       // Fifth row of buttons
       Button btn0 = (Button)findViewById(R.id.btn0);
       Button btnDot = (Button)findViewById(R.id.btnDot);
       Button btnUR = (Button)findViewById(R.id.btnUR);
       Button btnA = (Button)findViewById(R.id.btnA);
       
       // Sixth row of buttons
       Button btnE = (Button)findViewById(R.id.btnE);
   
       
       /* ########################################## */
       
       // First row of buttons
       btnC.setTextSize((((float)3)/100*height));
       btnB.setTextSize((((float)2.5)/100*height));
       btnP.setTextSize((((float)2.5)/100*height));
       btnDel.setTextSize((((float)2.5)/100*height));
       
       // Second row of buttons
       btn7.setTextSize((((float)3)/100*height));
       btn8.setTextSize((((float)2.5)/100*height));
       btn9.setTextSize((((float)2.5)/100*height));
       btnD.setTextSize((((float)3)/100*height));
       
       // Third row of buttons
       btn4.setTextSize((((float)3)/100*height));
       btn5.setTextSize((((float)2.5)/100*height));
       btn6.setTextSize((((float)2.5)/100*height));
       btnM.setTextSize((((float)3)/100*height));
       
       // Fourth row of buttons 
       btn1.setTextSize((((float)3)/100*height));
       btn2.setTextSize((((float)2.5)/100*height));
       btn3.setTextSize((((float)2.5)/100*height));
       btnS.setTextSize((((float)2.5)/100*height));
       
       // Fifth row of buttons
       btn0.setTextSize((((float)3)/100*height));
       btnDot.setTextSize((((float)2.5)/100*height));
       btnUR.setTextSize((((float)2)/100*height));
       btnA.setTextSize((((float)2.5)/100*height));
       
       // Sixth row of buttons
       btnE.setTextSize((((float)3)/100*height));
       
    }
    
    
    
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
      super.onConfigurationChanged(newConfig);
      text_size();
    }
    
    
    

            
    public double eval(String equation){
      Expression e = new ExpressionBuilder(equation)
        .variables("x", "y")
        .build()
        .setVariable("x", 2.3)
        .setVariable("y", 3.14);
      double result = e.evaluate();
      return result;
    }

    
    
 public String remove_0(String equation){
  
  double to_double = Double.parseDouble(equation);
  
  if(to_double > 0){
  
  String ulta_equ = "";
    for (int ab = 1; ab <= equation.length(); ab++){
      ulta_equ += String.valueOf(equation.charAt(equation.length()-ab));
    }
    
    int pos = 0;
    for (int ac = 0; ac <= ulta_equ.length()-1; ac++){
      if (ulta_equ.charAt(ac) != '0' && ulta_equ.charAt(ac) != '.'){
        pos *= 0;
        pos += ac;
        break;
        
    }
  }
    
    String before_f_result = "";
    for (int ad = pos; ad <= ulta_equ.length()-1; ad++){
      before_f_result += String.valueOf(ulta_equ.charAt(ad));
    }
    
    String result = "";
    for (int ae = 1; ae <= before_f_result.length(); ae++){
      result += String.valueOf(before_f_result.charAt(before_f_result.length()-ae));
    }
  return result;
  }
  else {
    return ".0";
  }
}
    
    
    
    public void set_answer(){
      for (int loop1=1;loop1<=1;loop1++){
          try{
            
            
           String format_answer = String.format("%.50f",eval(question)); 
           
          
           int indexOfDecimal = format_answer.indexOf(".");
    String int_part = "";
    for (int af=0;af<=indexOfDecimal-1;af++){
      int_part += String.valueOf(format_answer.charAt(af));
      
    }
    
    String dec_part = "";
    for (int ag=indexOfDecimal;ag<=(format_answer.length()-int_part.length());ag++){
      dec_part += String.valueOf(format_answer.charAt(ag));
    }
    
    

   
    if (((int_part+remove_0(dec_part)).length())<=17){
      answer_layout.setText(int_part+remove_0(dec_part));
    }else{ answer_layout.setText(""); }
           
           
           
          }
          catch(Exception e){
            answer_layout.setText("");
          }
        }
    }  
    
    
    
    
    
    
    
    
    
    
    
    public void clear(View view){
      question = "";
      q_to_set = "";
      brac_op_or_clo *= 0;
      length *= 0;
      can_type_point = true;
      
      question_layout.setText(question);
      
      answer_layout.setText("");
      if (play_sound==true){
          tts.speak("clear", TextToSpeech.QUEUE_FLUSH, null);
      }
    
       
    }
    
    
    
    public void bracket(View view){
    last_char_1 = '#';
    if (question != ""){
      last_char_1 = question.charAt(question.length()-1);
    }
    //Toast.makeText(getApplicationContext(),String.valueOf(last_char_1),Toast.LENGTH_SHORT).show();

        
        if (question == "" || last_char_1 == '^' || last_char_1 == '+' || last_char_1 == '-' || last_char_1 == '*' || last_char_1 == '/' || last_char_1 == 't'){
          q_to_set += "(";
          question_layout.setText(q_to_set);
          question += "(";
          brac_op_or_clo++;
          length *= 0;
          can_type_point = true; 
          if (play_sound==true){
          tts.speak("open bracket", TextToSpeech.QUEUE_FLUSH, null);  }
          
        }
        
        
        else if (question != "" ){
       
        
        if (brac_op_or_clo == 0){

        
          if (last_char_1 == '1' || last_char_1 == '2' || last_char_1 == '3' || last_char_1 == '4' || last_char_1 == '5' || last_char_1 == '6' || last_char_1 == '7' || last_char_1 == '8' || last_char_1 == '9' || last_char_1 == '0' || last_char_1 == '.' || last_char_1 == ')' ){
          
          q_to_set += "×(";
          question_layout.setText(q_to_set);
          question += "*(";
          brac_op_or_clo++;
          length *= 0;
          can_type_point = true; 
          if (play_sound==true){
            tts.speak("multiply"+"open bracket", TextToSpeech.QUEUE_FLUSH, null);  }
            
          }
          
          } 
          
          else if (brac_op_or_clo > 0 && last_char_1 != '('){
            
            q_to_set += ")";
            question_layout.setText(q_to_set);
            question += ")";
            brac_op_or_clo--;
            length *= 0;
            can_type_point = true; 
            if (play_sound==true){
         	 tts.speak("close bracket", TextToSpeech.QUEUE_FLUSH, null);
            }
          }
           
        }        
        set_answer();
        scroll_position();        
    }
    
    
    public void power(View view){
    
      last_char_1 = '#';
      if (question != ""){
        last_char_1 = question.charAt(question.length()-1);
      }
      
      if (question != ""){
        if (last_char_1 != '^' && last_char_1 != 't' && last_char_1 != '(' && last_char_1 != '+' && last_char_1 != '-' && last_char_1 != '*' && last_char_1 != '/'){
          q_to_set += "^";
          question_layout.setText(q_to_set);
          question += "^";
          length *= 0;
          can_type_point = true; 
          if (play_sound==true){
            tts.speak("power", TextToSpeech.QUEUE_FLUSH, null);}
        
        }
      }
        
        set_answer();
        scroll_position();    
        
    }
    
    
    
    
    
    public void delete(View view){
    
      last_char_1 = '#';
      if (question != ""){
        last_char_1 = question.charAt(question.length()-1);
      }
      
      
      if(question != ""){
        if (last_char_1 == 't'){
          String delete_last_1 = "";
          for (int an = 0; an <= q_to_set.length()-2; an++){
            delete_last_1 += String.valueOf(q_to_set.charAt(an));
          }
          String delete_last_2 = "";
          for (int ao = 0; ao <= question.length()-5; ao++){
            delete_last_2 += String.valueOf(question.charAt(ao));
          }
          q_to_set = delete_last_1;
          question_layout.setText(q_to_set);
          question = delete_last_2;
        }
        else if (last_char_1 == '(' || last_char_1 == ')'){
          String delete_last_3 = "";
          for (int ap = 0; ap <= q_to_set.length()-2; ap++){
            delete_last_3 += String.valueOf(q_to_set.charAt(ap));
          }
          String delete_last_4 = "";
          for (int aq = 0; aq <= question.length()-2; aq++){
            delete_last_4 += String.valueOf(question.charAt(aq));
          }
          q_to_set = delete_last_3;
          question_layout.setText(q_to_set);
          question = delete_last_4;
          if (last_char_1 == '('){
            brac_op_or_clo--;
          }else if (last_char_1 == ')'){
            brac_op_or_clo++;
          }
        }
        else{
          String delete_last_5 = "";
          for (int ar = 0; ar <= q_to_set.length()-2; ar++){
            delete_last_5 += String.valueOf(q_to_set.charAt(ar));
          }
          String delete_last_6 = "";
          for (int as = 0; as <= question.length()-2; as++){
            delete_last_6 += String.valueOf(question.charAt(as));
          }
          q_to_set = delete_last_5;
          question_layout.setText(q_to_set);
          question = delete_last_6;
        }
        
        if (play_sound==true){
          tts.speak("back space", TextToSpeech.QUEUE_FLUSH, null);}
        
        
        
        
        
  try{
    char last_2 = question.charAt(question.length()-1);
    if (last_2 != '+' || last_2 != '-' || last_2 != '*' || last_2 != '/' || last_2 != '(' || last_2 != ')' || last_2 != '^' || last_2 != 't' ){
      length *= 0;  
	   String ulta_2 = "";
      for (int at = 1; at <= question.length(); at++){
        ulta_2 += String.valueOf(question.charAt(question.length()-at));
      }
      
      String num_bef_any_sim = "";
      for (int au = 0; au <= ulta_2.length()-1; au++){
        char current = ulta_2.charAt(au);
        num_bef_any_sim += String.valueOf(ulta_2.charAt(au));
        
        if (current == '+' || current == '-' || current == '*' || current == '/' || current == '(' || current == ')' || current == 't' || current == '^' || String.valueOf(current) == ""){
          break;
        }else {
          length++; 
          //num_bef_any_sim += String.valueOf(ulta_2.charAt(au));
        }
      }
     if ((num_bef_any_sim.indexOf(".")) > -1){  can_type_point = false;      
	 }else{ can_type_point = true; } 
    }else {
	  length *= 0;
	}}
	catch(Exception e){
      length *= 0;
    }      
      }
             
      set_answer();
      scroll_position();   
    }
    
    
    public void press_btn7(View view){
    
    
        
    last_char_1 = '#';
    if (question != ""){
      last_char_1 = question.charAt(question.length()-1);
    }
    
     if (length <= 14){   
        if (question != ""){
        if (last_char_1 == close_b){
          q_to_set += "×7";
          question_layout.setText(q_to_set);
          question += "*7";
          if (play_sound==true){
          tts.speak("multiplied by 7", TextToSpeech.QUEUE_FLUSH, null);  }
        }
        
        else if (last_char_1 != close_b){
          q_to_set += "7";
          question_layout.setText(q_to_set);
          question += "7";
          if (play_sound==true){
          tts.speak("7", TextToSpeech.QUEUE_FLUSH, null);  }
        }  }
        
        else if (question == ""){
          q_to_set += "7";
          question_layout.setText(q_to_set);
          question += "7";
          if (play_sound==true){
          tts.speak("7", TextToSpeech.QUEUE_FLUSH, null); }
        }        
        set_answer();
        scroll_position();
        length++;
        }else{
          Toast.makeText(getApplicationContext(),"Max length of digits (14) reached",Toast.LENGTH_SHORT).show();
          if (play_sound==true){ tts.speak("Max length of digits 14 reached", TextToSpeech.QUEUE_FLUSH, null); }
        }
    }
    
    
    public void press_btn8(View view){
        
     last_char_1 = '#';
    if (question != ""){
     last_char_1 = question.charAt(question.length()-1);
    
    }
    
      if (length <= 14){
        if (question != ""){
        if (last_char_1 == close_b){
          q_to_set += "×8";
          question_layout.setText(q_to_set);
          question += "*8";
          if (play_sound==true){
          tts.speak("multiplied by 8", TextToSpeech.QUEUE_FLUSH, null);  }
        }
        
        else if (last_char_1 != close_b){
          q_to_set += "8";
          question_layout.setText(q_to_set);
          question += "8";
          if (play_sound==true){
          tts.speak("8", TextToSpeech.QUEUE_FLUSH, null);  }
        }  }
        
        else if (question == ""){
          q_to_set += "8";
          question_layout.setText(q_to_set);
          question += "8";
          if (play_sound==true){
          tts.speak("8", TextToSpeech.QUEUE_FLUSH, null); }
        }
        set_answer();
        scroll_position();
        length++;
        }else{
          Toast.makeText(getApplicationContext(),"Max length of digits (14) reached",Toast.LENGTH_SHORT).show();
          if (play_sound==true){ tts.speak("Max length of digits 14 reached", TextToSpeech.QUEUE_FLUSH, null); }
        }
    }
    
    
    public void press_btn9(View view){
        
     last_char_1 = '#';
    if (question != ""){
     last_char_1 = question.charAt(question.length()-1);
    
    }
    
     if (length <= 14){   
        if (question != ""){
        if (last_char_1 == close_b){
          q_to_set += "×9";
          question_layout.setText(q_to_set);
          question += "*9";
          if (play_sound==true){
          tts.speak("multiplied by 9", TextToSpeech.QUEUE_FLUSH, null);  }
        }
        
        else if (last_char_1 != close_b){
          q_to_set += "9";
          question_layout.setText(q_to_set);
          question += "9";
          if (play_sound==true){
          tts.speak("9", TextToSpeech.QUEUE_FLUSH, null);  }
        }  }
        
        else if (question == ""){
          q_to_set += "9";
          question_layout.setText(q_to_set);
          question += "9";
          if (play_sound==true){
          tts.speak("9", TextToSpeech.QUEUE_FLUSH, null); }
        }        
        set_answer();
        scroll_position();
        length++;
        }else{
          Toast.makeText(getApplicationContext(),"Max length of digits (14) reached",Toast.LENGTH_SHORT).show();
          if (play_sound==true){ tts.speak("Max length of digits 14 reached", TextToSpeech.QUEUE_FLUSH, null); }
        }
    }
    
    public void divide(View view){
      
      last_char_2 = '#';
      last_char_1 = '#';
      if (question != ""){
        last_char_1 = question.charAt(question.length()-1);
      }
        
        for(int loop2 = 1; loop2 <= 1; loop2++){
          try{
            last_char_2 = question.charAt(question.length()-2);
          }
          catch(Exception e){
            continue;
          }
        }
      
       
    
      if (question != ""){
        if (last_char_1 != '^' && last_char_1 != 't' && last_char_1 != '(' && last_char_1 != '+' && last_char_1 != '-' && last_char_1 != '*' && last_char_1 != '/'){
          q_to_set += "÷";
          question_layout.setText(q_to_set);
          question += "/";
          length *= 0;
          can_type_point = true; 
          if (play_sound==true){
            tts.speak("divided by", TextToSpeech.QUEUE_FLUSH, null);}
        }
        else if (last_char_1 == '+' || last_char_1 == '-' || last_char_1 == '*'){
        if (last_char_2 != '^' && last_char_2 != 't' && last_char_2 != '(' && last_char_2 != '#'){
          String change_sign_a1 = "";
          for (int ah = 0; ah <= question.length()-2; ah++){
            change_sign_a1 += String.valueOf(question.charAt(ah));
          }
          String change_sign_a2 = "";
          for (int ai = 0; ai <= q_to_set.length()-2; ai++){
            change_sign_a2 += String.valueOf(q_to_set.charAt(ai));
          }
          q_to_set = change_sign_a2+"÷";
          question_layout.setText(q_to_set);
          question = change_sign_a1+"/";
          length *= 0;
          can_type_point = true; 
          if (play_sound==true){
            tts.speak("divided by", TextToSpeech.QUEUE_FLUSH, null);}
          
        }}
      }
    
    
      set_answer();
      scroll_position();  
        

    }
    
    
    
    
    public void press_btn4(View view){
        
     last_char_1 = '#';
    if (question != ""){
     last_char_1 = question.charAt(question.length()-1);
    
    }
    
     if (length <= 14){   
        if (question != ""){
        if (last_char_1 == close_b){
          q_to_set += "×4";
          question_layout.setText(q_to_set);
          question += "*4";
          if (play_sound==true){
          tts.speak("multiplied by 4", TextToSpeech.QUEUE_FLUSH, null);  }
        }
        
        else if (last_char_1 != close_b){
          q_to_set += "4";
          question_layout.setText(q_to_set);
          question += "4";
          if (play_sound==true){
          tts.speak("4", TextToSpeech.QUEUE_FLUSH, null);  }
        }  }
        
        else if (question == ""){
          q_to_set += "4";
          question_layout.setText(q_to_set);
          question += "4";
          if (play_sound==true){
          tts.speak("4", TextToSpeech.QUEUE_FLUSH, null); }
        }       
        set_answer();
        scroll_position();
        length++;
        }else{
          Toast.makeText(getApplicationContext(),"Max length of digits (14) reached",Toast.LENGTH_SHORT).show();
          if (play_sound==true){ tts.speak("Max length of digits 14 reached", TextToSpeech.QUEUE_FLUSH, null); }
        }
    }
    
    
    public void press_btn5(View view){
        
     last_char_1 = '#';
    if (question != ""){
     last_char_1 = question.charAt(question.length()-1);
    
    }
    
    if (length <= 14){    
        if (question != ""){
        if (last_char_1 == close_b){
          q_to_set += "×5";
          question_layout.setText(q_to_set);
          question += "*5";
          if (play_sound==true){
          tts.speak("multiplied by 5", TextToSpeech.QUEUE_FLUSH, null);  }
        }
        
        else if (last_char_1 != close_b){
          q_to_set += "5";
          question_layout.setText(q_to_set);
          question += "5";
          if (play_sound==true){
          tts.speak("5", TextToSpeech.QUEUE_FLUSH, null);  }
        }  }
        
        else if (question == ""){
          q_to_set += "5";
          question_layout.setText(q_to_set);
          question += "5";
          if (play_sound==true){
          tts.speak("5", TextToSpeech.QUEUE_FLUSH, null); }
        }
        
        set_answer();
        scroll_position(); 
        length++;
        }else{
          Toast.makeText(getApplicationContext(),"Max length of digits (14) reached",Toast.LENGTH_SHORT).show();
          if (play_sound==true){ tts.speak("Max length of digits 14 reached", TextToSpeech.QUEUE_FLUSH, null); }
        }
    }
    
    
    public void press_btn6(View view){
        
     last_char_1 = '#';
    if (question != ""){
     last_char_1 = question.charAt(question.length()-1);
    
    }
    
     if (length <= 14){   
        if (question != ""){
        if (last_char_1 == close_b){
          q_to_set += "×6";
          question_layout.setText(q_to_set);
          question += "*6";
          if (play_sound==true){
          tts.speak("multiplied by 6", TextToSpeech.QUEUE_FLUSH, null);  }
        }
        
        else if (last_char_1 != close_b){
          q_to_set += "6";
          question_layout.setText(q_to_set);
          question += "6";
          if (play_sound==true){
          tts.speak("6", TextToSpeech.QUEUE_FLUSH, null);  }
        }  }
        
        else if (question == ""){
          q_to_set += "6";
          question_layout.setText(q_to_set);
          question += "6";
          if (play_sound==true){
          tts.speak("6", TextToSpeech.QUEUE_FLUSH, null); }
        }
        
        set_answer();
        scroll_position(); 
        length++;
        }else{
          Toast.makeText(getApplicationContext(),"Max length of digits (14) reached",Toast.LENGTH_SHORT).show();
          if (play_sound==true){ tts.speak("Max length of digits 14 reached", TextToSpeech.QUEUE_FLUSH, null); }
        }
    }
    
    public void multiply(View view){
        
       
      last_char_2 = '#';
      last_char_1 = '#';
      if (question != ""){
        last_char_1 = question.charAt(question.length()-1);
      }
        
        for(int loop2 = 1; loop2 <= 1; loop2++){
          try{
            last_char_2 = question.charAt(question.length()-2);
          }
          catch(Exception e){
            continue;
          }
        }
        
    
      if (question != ""){
        if (last_char_1 != '^' && last_char_1 != 't' && last_char_1 != '(' && last_char_1 != '+' && last_char_1 != '-' && last_char_1 != '*' && last_char_1 != '/'){
          q_to_set += "×";
          question_layout.setText(q_to_set);
          question += "*";
          length *= 0;
          can_type_point = true; 
          if (play_sound==true){
          tts.speak("times", TextToSpeech.QUEUE_FLUSH, null);}
        }
        
        else if (last_char_1 == '+' || last_char_1 == '-' || last_char_1 == '/'){
        if (last_char_2 != '^' && last_char_2 != 't' && last_char_2 != '(' && last_char_2 != '#'){
          String change_sign_b1 = "";
          for (int aj = 0; aj <= question.length()-2; aj++){
            change_sign_b1 += String.valueOf(question.charAt(aj));
          }
          String change_sign_b2 = "";
          for (int ak = 0; ak <= q_to_set.length()-2; ak++){
            change_sign_b2 += String.valueOf(q_to_set.charAt(ak));
          }
          q_to_set = change_sign_b2+"×";
          question_layout.setText(q_to_set);
          question = change_sign_b1+"*";
          length *= 0;
          can_type_point = true; 
          if (play_sound==true){
            tts.speak("times", TextToSpeech.QUEUE_FLUSH, null);}
        }}
        
      }
    
    
      set_answer();
      scroll_position();  
    }
    
    
    
    
    
    public void press_btn1(View view){
    
    last_char_1 = '#';
    if (question != ""){
     last_char_1 = question.charAt(question.length()-1);
    
    }
    
     if (length <= 14){   
        if (question != ""){
        if (last_char_1 == close_b){
          q_to_set += "×1";
          question_layout.setText(q_to_set);
          question += "*1";
          if (play_sound==true){
          tts.speak("multiplied by 1", TextToSpeech.QUEUE_FLUSH, null);  }
        }
        
        else if (last_char_1 != close_b){
          q_to_set += "1";
          question_layout.setText(q_to_set);
          question += "1";
          if (play_sound==true){
          tts.speak("1", TextToSpeech.QUEUE_FLUSH, null);  }
        }  }
        
        else if (question == ""){
          q_to_set += "1";
          question_layout.setText(q_to_set);
          question += "1";
          if (play_sound==true){
          tts.speak("1", TextToSpeech.QUEUE_FLUSH, null); }
        }
    
        set_answer();
        scroll_position();  
        length++;
        }else{
          Toast.makeText(getApplicationContext(),"Max length of digits (14) reached",Toast.LENGTH_SHORT).show();
          if (play_sound==true){ tts.speak("Max length of digits 14 reached", TextToSpeech.QUEUE_FLUSH, null); }
        }
    }
    
    
    public void press_btn2(View view){
        
     last_char_1 = '#';
    if (question != ""){
     last_char_1 = question.charAt(question.length()-1);
    
    }
    
      if (length <= 14){
        if (question != ""){
        if (last_char_1 == close_b){
          q_to_set += "×2";
          question_layout.setText(q_to_set);
          question += "*2";
          if (play_sound==true){
          tts.speak("multiplied by 2", TextToSpeech.QUEUE_FLUSH, null);  }
        }
        
        else if (last_char_1 != close_b){
          q_to_set += "2";
          question_layout.setText(q_to_set);
          question += "2";
          if (play_sound==true){
          tts.speak("2", TextToSpeech.QUEUE_FLUSH, null);  }
        }  }
        
        else if (question == ""){
          q_to_set += "2";
          question_layout.setText(q_to_set);
          question += "2";
          if (play_sound==true){
          tts.speak("2", TextToSpeech.QUEUE_FLUSH, null); }
        }
        
         set_answer();
         scroll_position(); 
         length++;
        }else{
          Toast.makeText(getApplicationContext(),"Max length of digits (14) reached",Toast.LENGTH_SHORT).show();
          if (play_sound==true){ tts.speak("Max length of digits 14 reached", TextToSpeech.QUEUE_FLUSH, null); }
        }
    }
    
    
    public void press_btn3(View view){
        
     last_char_1 = '#';
    if (question != ""){
     last_char_1 = question.charAt(question.length()-1);
    
    }
    
      if (length <= 14){
        if (question != ""){
        if (last_char_1 == close_b){
          q_to_set += "×3";
          question_layout.setText(q_to_set);
          question += "*3";
          if (play_sound==true){
          tts.speak("multiplied by 3", TextToSpeech.QUEUE_FLUSH, null);  }
        }
        
        else if (last_char_1 != close_b){
          q_to_set += "3";
          question_layout.setText(q_to_set);
          question += "3";
          if (play_sound==true){
          tts.speak("3", TextToSpeech.QUEUE_FLUSH, null);  }
        }  }
        
        else if (question == ""){
          q_to_set += "3";
          question_layout.setText(q_to_set);
          question += "3";
          if (play_sound==true){
          tts.speak("3", TextToSpeech.QUEUE_FLUSH, null); }
        }
        
        set_answer();
        scroll_position();  
        length++;
        }else{
          Toast.makeText(getApplicationContext(),"Max length of digits (14) reached",Toast.LENGTH_SHORT).show();
          if (play_sound==true){ tts.speak("Max length of digits 14 reached", TextToSpeech.QUEUE_FLUSH, null); }
        }
    }
    
    public void subtract(View view){
    
      last_char_1 = '#';
      if (question != ""){
        last_char_1 = question.charAt(question.length()-1);
      }
      
      
     if (last_char_1 != '-'){ 
      if (last_char_1 == '*' || last_char_1 == '+' || last_char_1 == '/'){
        
          String change_sign_d1 = "";
          for (int an = 0; an <= question.length()-2; an++){
            change_sign_d1 += String.valueOf(question.charAt(an));
          }
          String change_sign_d2 = "";
          for (int ao = 0; ao <= q_to_set.length()-2; ao++){
            change_sign_d2 += String.valueOf(q_to_set.charAt(ao));
          }
          q_to_set = change_sign_d2+"-";
          question_layout.setText(q_to_set);
          question = change_sign_d1+"-";
          length *= 0;
          can_type_point = true; 
          if (play_sound==true){
            tts.speak("minus", TextToSpeech.QUEUE_FLUSH, null);}
            
      }
        
      else{
          q_to_set += "-";
          question_layout.setText(q_to_set);
          question += "-"; 
          length *= 0;
          can_type_point = true; 
          if (play_sound==true){
          tts.speak("minus", TextToSpeech.QUEUE_FLUSH, null);}          
      }
    }
    
      set_answer();
      scroll_position();  
    
               
    }
    
    
    
    
    
    public void press_btn0(View view){
        
        last_char_1 = '#';
    if (question != ""){
     last_char_1 = question.charAt(question.length()-1);
    
    }
    
      if (length <= 14) { 
        if (question != ""){
        if (last_char_1 == close_b){
          q_to_set += "×0";
          question_layout.setText(q_to_set);
          question += "*0";
          if (play_sound==true){
          tts.speak("multiplied by 0", TextToSpeech.QUEUE_FLUSH, null);  }
        }
        
        else if (last_char_1 != close_b){
          q_to_set += "0";
          question_layout.setText(q_to_set);
          question += "0";
          if (play_sound==true){
          tts.speak("0", TextToSpeech.QUEUE_FLUSH, null);  }
        }  }
        
        else if (question == ""){
          q_to_set += "0";
          question_layout.setText(q_to_set);
          question += "0";
          if (play_sound==true){
          tts.speak("0", TextToSpeech.QUEUE_FLUSH, null); }
        }
        
        set_answer();
        scroll_position();  
        length++;
        }else{
          Toast.makeText(getApplicationContext(),"Max length of digits (14) reached",Toast.LENGTH_SHORT).show();
          if (play_sound==true){ tts.speak("Max length of digits 14 reached", TextToSpeech.QUEUE_FLUSH, null); }
        }
    }
    
    
    public void point(View view){
        
    last_char_1 = '#';
    if (question != ""){
     last_char_1 = question.charAt(question.length()-1);
    
    }
    
     if (length <= 14) {
      if (can_type_point == true){
        if (question != ""){
        if (last_char_1 == close_b){
          q_to_set += "×0.";
          question_layout.setText(q_to_set);
          question += "*0.";
          if (play_sound==true){
          tts.speak("multiplied by 0 point", TextToSpeech.QUEUE_FLUSH, null);  }
        }
        
        else if (last_char_1 != close_b){
          if (last_char_1 == addition || last_char_1 == subtr || last_char_1 == mult || last_char_1 == divide || last_char_1 == un_ro || last_char_1 == open_b || last_char_1 == power){
          q_to_set += "0.";
          question_layout.setText(q_to_set);
          question += "0.";
          if (play_sound==true){
          tts.speak("0 point", TextToSpeech.QUEUE_FLUSH, null);}
          
        }
        else{
          q_to_set += ".";
          question_layout.setText(q_to_set);
          question += ".";
          if (play_sound==true){
          tts.speak("point", TextToSpeech.QUEUE_FLUSH, null);}
        }  
          
        }  }
        
        else if (question == ""){
          q_to_set += "0.";
          question_layout.setText(q_to_set);
          question += "0.";
          if (play_sound==true){
          tts.speak("0 point", TextToSpeech.QUEUE_FLUSH, null); }
        }
        
        set_answer();
        scroll_position();  
        length++;
        can_type_point = false;
        }else{
  	    Toast.makeText(getApplicationContext(),"Invalid format",Toast.LENGTH_SHORT).show();
          if (play_sound==true){ tts.speak("Invalid format", TextToSpeech.QUEUE_FLUSH, null); }
		}
        }else{
          Toast.makeText(getApplicationContext(),"Max length of digits (14) reached",Toast.LENGTH_SHORT).show();
          if (play_sound==true){ tts.speak("Max length of digits 14 reached", TextToSpeech.QUEUE_FLUSH, null); }
        }
    }
    
    
    public void under_root(View view){
    
      last_char_1 = '#';
      if (question != ""){
        last_char_1 = question.charAt(question.length()-1);
      }
        
        
      if (question != ""){
        if (last_char_1 != '+' && last_char_1 != '-' && last_char_1 != '*' && last_char_1 != '/' && last_char_1 != '^' && last_char_1 != '(' && last_char_1 != 't'){
          q_to_set += "×√";
          question_layout.setText(q_to_set);
          question += "*sqrt";
          length *= 0;
          can_type_point = true; 
          if (play_sound==true){
            tts.speak("multiply"+"under root", TextToSpeech.QUEUE_FLUSH, null);}
        }
        else {
          q_to_set += "√";
          question_layout.setText(q_to_set);
          question += "sqrt";
          length *= 0;
          can_type_point = true; 
          if (play_sound==true){
            tts.speak("under root", TextToSpeech.QUEUE_FLUSH, null);}
        }
      }
      else if (question == ""){
          q_to_set += "√";
          question_layout.setText(q_to_set);
          question += "sqrt";
          length *= 0;
          can_type_point = true; 
          if (play_sound==true){
            tts.speak("under root", TextToSpeech.QUEUE_FLUSH, null);}
      }
      
      set_answer();
      scroll_position();
        
    }
    
    public void add(View view){
        
        last_char_2 = '#';
      last_char_1 = '#';
      if (question != ""){
        last_char_1 = question.charAt(question.length()-1);
      }
        
        for(int loop2 = 1; loop2 <= 1; loop2++){
          try{
            last_char_2 = question.charAt(question.length()-2);
          }
          catch(Exception e){
            continue;
          }
        }
        
    
      if (question != ""){
        if (last_char_1 != '^' && last_char_1 != 't' && last_char_1 != '(' && last_char_1 != '+' && last_char_1 != '-' && last_char_1 != '*' && last_char_1 != '/'){
          q_to_set += "+";
          question_layout.setText(q_to_set);
          question += "+";
          length *= 0;
          can_type_point = true; 
          if (play_sound==true){
          tts.speak("plus", TextToSpeech.QUEUE_FLUSH, null);}
        }
        
        else if (last_char_1 == '*' || last_char_1 == '-' || last_char_1 == '/'){
        if (last_char_2 != '^' && last_char_2 != 't' && last_char_2 != '(' && last_char_2 != '#'){
          String change_sign_c1 = "";
          for (int al = 0; al <= question.length()-2; al++){
            change_sign_c1 += String.valueOf(question.charAt(al));
          }
          String change_sign_c2 = "";
          for (int am = 0; am <= q_to_set.length()-2; am++){
            change_sign_c2 += String.valueOf(q_to_set.charAt(am));
          }
          q_to_set = change_sign_c2+"+";
          question_layout.setText(q_to_set);
          question = change_sign_c1+"+";
          length *= 0;
          can_type_point = true; 
          if (play_sound==true){
            tts.speak("plus", TextToSpeech.QUEUE_FLUSH, null);}
        }}
        
      }
    
    
      set_answer();
      scroll_position();  
    }
    
    
    
    
    public void equal(View view){
     
     
        if (!answer_layout.getText().toString().isEmpty()){
            q_to_set = answer_layout.getText().toString(); 
            question_layout.setText(q_to_set);
            answer_layout.setText("");
            question = q_to_set;
            length = question.length();
            can_type_point = false; 
            //Toast.makeText(getApplicationContext(),question,Toast.LENGTH_SHORT).show();
            if (play_sound==true){
              tts.speak("is equals to"+question, TextToSpeech.QUEUE_FLUSH, null);}
            
          }
      
        
        
        else if (answer_layout.getText().toString().isEmpty()){
          if (play_sound==true){
            tts.speak("empty",TextToSpeech.QUEUE_FLUSH, null);    
          }
        }
    } 
    
    
    
    
    
    
    
    
    
    
    public void sound_control(View view){
        ImgButton = (ImageButton)findViewById(R.id.sound);
        
        if (play_sound==true){
          tts.speak("sound off", TextToSpeech.QUEUE_FLUSH, null);
         play_sound = false;
          ImgButton.setImageResource(R.drawable.mute_speaker_img);
        }
        else if (play_sound == false){
          tts.speak("sound on", TextToSpeech.QUEUE_FLUSH, null);
          play_sound = true;
          ImgButton.setImageResource(R.drawable.speaker_img);
        }
    }

}
