package com.stu.sample.user.CHH;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.stu.sample.R;

import java.util.Stack;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    EditText resultText;

    Button div, mul, sub, add, equal;
    Button clear, delete;

    String sResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_Chh);

        //EditText
        editText = (EditText)findViewById(R.id.txtEdit);
        resultText = (EditText)findViewById(R.id.txtResult);

        //Button
        div = (Button)findViewById(R.id.btnDiv);
        mul = (Button)findViewById(R.id.btnMul);
        sub = (Button)findViewById(R.id.btnSub);
        add = (Button)findViewById(R.id.btnAdd);
        equal = div = (Button)findViewById(R.id.btnEqual);

        clear = (Button)findViewById(R.id.btnClear);
        delete = (Button)findViewById(R.id.btnDel);

        clear.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                editText.setText("");
                resultText.setText("");
            }
        });
    }
    // 연산자 입력
    public void cOper(View v) {
        switch (v.getId()) {
            case  R.id.btnAdd :
                if(OperatorCheckVail("+")){
                    editText.setText(editText.getText().toString() + "+");
                    resultText.setText("");
                }
                break;
            case  R.id.btnSub :
                if(OperatorCheckVail("-")){
                    editText.setText(editText.getText().toString() + "-");
                    resultText.setText("");
                }
                break;
            case  R.id.btnDiv :
                if(OperatorCheckVail("/")){
                    editText.setText(editText.getText().toString() + "/");
                    resultText.setText("");
                }
                break;
            case  R.id.btnMul :
                if(OperatorCheckVail("*")){
                    editText.setText(editText.getText().toString() + "*");
                    resultText.setText("");
                }
                break;
            case  R.id.btnDel :
                if(editText.getText().toString().length() != 0) {
                    editText.setText(editText.getText().toString().substring(0, editText.getText().toString().length() - 1));
                }
                break;
            case  R.id.btnEqual :
                if(OperatorCheckVail("=")){
                    try {
                        sResult = Calc(editText.getText().toString());
                        resultText.setText(sResult);
                    }catch(Exception e) {

                    }
                }
                break;
        }
        editText.setSelection(editText.length());
    }
    // 계산
    public String Calc(String str) {
        Double cnt = 0.0;
        Stack<Double> stk_Num = new Stack<Double>();
        StringTokenizer st_Num = new StringTokenizer(str,"+-/*");
        StringTokenizer st_Oper = new StringTokenizer(str,"1234567890 ");

        stk_Num.push(Double.parseDouble(st_Num.nextToken()));
        while(st_Num.hasMoreTokens()) {
            char oper = st_Oper.nextToken().charAt(0);
            String num = st_Num.nextToken();

            Double dNum;

            if(oper == '*') {
                dNum = stk_Num.pop();
                dNum *= Double.parseDouble(num);
                stk_Num.push(dNum);
            }
            else if(oper == '/') {
                dNum = stk_Num.pop();
                dNum /= Double.parseDouble(num);
                stk_Num.push(dNum);
            }
            else if(oper == '+'){
                stk_Num.push(Double.parseDouble(num));
            }
            else if(oper == '-'){
                stk_Num.push(-1 * Double.parseDouble(num));
            }
        }
        while(!stk_Num.isEmpty()) {
            cnt += stk_Num.pop();
        }
        return Double.toString(cnt);
    }
    //연산자 벨리데이션
    public boolean OperatorCheckVail(String temp) {

        String sPre = editText.getText().toString();
        String sPreEnd = "";
        if(sPre.length() > 1) {
            sPreEnd = sPre.substring(sPre.length()-1,sPre.length());
        }
        String sOperator = "+-/*";
        if(sPre.length() == 0) {
            Toast.makeText(MainActivity.this, "숫자를 입력하세요", Toast.LENGTH_SHORT).show();
            return false;
        }
        //연산자가 중복입력 되지 않도록
        else if(sPre.length() > 0  && sPre.length() != 1) {
            if(sOperator.indexOf(sPreEnd) != -1 || sPreEnd.equals(temp)) {
                return false;
            }
        }
        if(temp.equals("=")) {
            if(sPre.length() == 0 || sOperator.indexOf(sPreEnd) != -1) {
                return false;
            }
        }
        return true;
    }
    // 피연산자 입력
    public void num(View v) {
        switch (v.getId()) {
            case R.id.btn0 :
                CheckNumVail("0");
                editText.setText(editText.getText().toString() + 0);
                break;
            case R.id.btn1 :
                CheckNumVail("1");
                editText.setText(editText.getText().toString() + 1);
                break;
            case R.id.btn2 :
                CheckNumVail("2");
                editText.setText(editText.getText().toString() + 2);
                break;
            case R.id.btn3 :
                CheckNumVail("3");
                editText.setText(editText.getText().toString() + 3);
                break;
            case R.id.btn4 :
                CheckNumVail("4");
                editText.setText(editText.getText().toString() + 4);
                break;
            case R.id.btn5 :
                CheckNumVail("5");
                editText.setText(editText.getText().toString() + 5);
                break;
            case R.id.btn6 :
                CheckNumVail("6");
                editText.setText(editText.getText().toString() + 6);
                break;
            case R.id.btn7 :
                CheckNumVail("7");
                editText.setText(editText.getText().toString() + 7);
                break;
            case R.id.btn8 :
                CheckNumVail("8");
                editText.setText(editText.getText().toString() + 8);
                break;
            case R.id.btn9 :
                CheckNumVail("9");
                editText.setText(editText.getText().toString() + 9);
                break;
        }
        editText.setSelection(editText.length());
    }
    // 피연산자 벨리데이션
    public void CheckNumVail(String chk) {
        String sText = editText.getText().toString();
        if (editText.getText().toString().length() == 1 && editText.getText().toString().equals("0")) {
            editText.setText("");
        }
        else if(sText.length() > 1) {
            if(sText.substring(sText.length()-1, sText.length()).equals("/") && chk.equals("0")) {
                Toast.makeText(MainActivity.this, "0으로 나눌 수 없습니다.", Toast.LENGTH_SHORT).show();
                return;
            }
        }
    }
}
