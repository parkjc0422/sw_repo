package com.stu.sample.user.CHH;

import android.widget.Toast;

import java.util.Stack;
import java.util.StringTokenizer;

public class MyOperator {

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
    public boolean OperatorCheckVail(String temp, String sPre) {

        String sPreEnd = "";
        if(sPre.length() > 1) {
            sPreEnd = sPre.substring(sPre.length()-1,sPre.length());
        }
        String sOperator = "+-/*";
        if(sPre.length() == 0) {
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

    // 피연산자 벨리데이션
    public boolean CheckNumVail(String chk, String sText) {
        if (sText.length() == 1 && sText.equals("0")) {
            return false;
        }
        else if(sText.length() > 1) {
            if(sText.substring(sText.length()-1, sText.length()).equals("/") && chk.equals("0")) {
                return true;
            }
        }

        return true;
    }
}


