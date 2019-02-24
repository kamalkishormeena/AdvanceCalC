package com.app.kk.advancecalc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.kk.advancecalc.Database.DBHelper;
import com.app.kk.advancecalc.Supports.Checker;
import com.app.kk.advancecalc.Supports.Postfix;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    private android.support.v7.widget.Toolbar toolbar;
    private TextView expression;
    private TextView result;
    private TextView one;
    private TextView two;
    private TextView three;
    private TextView four;
    private TextView five;
    private TextView six;
    private TextView seven;
    private TextView eight;
    private TextView nine;
    private TextView zero;
    private TextView plus;
    private TextView minus;
    private TextView multiply;
    private TextView divide;
    private TextView pow;
    private TextView square_root;
    private TextView factorial;
    private TextView dot;
    private TextView left_parentheses;
    private TextView right_parentheses;
    private TextView backspace;
    private TextView erase;
    private TextView equals;
    private TextView answer;
    private TextView change_sign;
    private TextView shift;
    private TextView sin;
    private TextView cos;
    private TextView tan;
    private TextView log;
    private TextView pie;
    private TextView square;
    private TextView mode;
    private TextView modeDisplay;
    private int angleMode=1;

    private RelativeLayout relativeLayout;
    private Postfix postfix;
    private String ans;
    private LinkedList<String> input;
    private DBHelper dbHelper;
    private double PI=3.141592653589793;
    private boolean shiftPressed = false;
    private int SETTINGS_ACTION = 1;
    SharedPref sharedpref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        sharedpref = new SharedPref(MainActivity.this);

        if(sharedpref.loadNightModeState()==true) {
            setTheme(R.style.DarkTheme);
        }
        else {
            setTheme(R.style.AppTheme);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.appbar);
        toolbar.setTitle("");

        if(sharedpref.loadNightModeState()==true){
            toolbar.setPopupTheme(R.style.AppTheme_Popupoverlay_Dark);
        } else {
            toolbar.setPopupTheme(R.style.AppTheme_Popupoverlay);
        }

        setSupportActionBar(toolbar);

        postfix = new Postfix();
        input = new LinkedList<String>();

        expression = (TextView) findViewById(R.id.expression);
        result = (TextView) findViewById(R.id.result);
        one = (TextView) findViewById(R.id.one);
        two = (TextView) findViewById(R.id.two);
        three = (TextView) findViewById(R.id.three);
        four = (TextView) findViewById(R.id.four);
        five = (TextView) findViewById(R.id.five);
        six = (TextView) findViewById(R.id.six);
        seven = (TextView) findViewById(R.id.seven);
        eight = (TextView) findViewById(R.id.eight);
        nine = (TextView) findViewById(R.id.nine);
        zero = (TextView) findViewById(R.id.zero);
        plus = (TextView) findViewById(R.id.plus);
        minus = (TextView) findViewById(R.id.subtract);
        multiply = (TextView) findViewById(R.id.multiply);
        divide = (TextView) findViewById(R.id.divide);
        pow = (TextView) findViewById(R.id.pow);
        square_root = (TextView) findViewById(R.id.square_root);
        factorial = (TextView) findViewById(R.id.factorial);
        dot = (TextView) findViewById(R.id.dot);
        backspace = (TextView) findViewById(R.id.backspace);
        erase = (TextView) findViewById(R.id.erase);
        equals = (TextView) findViewById(R.id.equals);
        change_sign = (TextView) findViewById(R.id.change_sign);
        answer = (TextView) findViewById(R.id.answer);
        shift = (TextView) findViewById(R.id.shift);
        sin = (TextView) findViewById(R.id.sin);
        cos = (TextView) findViewById(R.id.cos);
        tan = (TextView) findViewById(R.id.tan);
        log = (TextView) findViewById(R.id.log);
        left_parentheses = (TextView) findViewById(R.id.left_parentheses);
        right_parentheses = (TextView) findViewById(R.id.right_parentheses);

        mode = (TextView) findViewById(R.id.mode);
        pie = (TextView) findViewById(R.id.pie);
        square = (TextView) findViewById(R.id.square);
        modeDisplay = (TextView) findViewById(R.id.modeDisplay);

        dbHelper=new DBHelper(this);

        mode.setTag(1);

        modeDisplay.setText(R.string.mode1);
        modeDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                angleMode=((int)mode.getTag());
                if(angleMode == 1) {
                    mode.setTag(2);
                    modeDisplay.setText(R.string.mode2);
                    mode.setText(R.string.mode1);
                } else {
                    mode.setTag(1);
                    modeDisplay.setText(R.string.mode1);
                    mode.setText(R.string.mode2);

                }
            }
        });

        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addElement("1");
                check();
            }
        });

        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addElement("2");
                check();
            }
        });

        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addElement("3");
                check();
            }
        });

        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addElement("4");
                check();
            }
        });

        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addElement("5");
                check();
            }
        });

        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addElement("6");
                check();
            }
        });

        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addElement("7");
                check();
            }
        });

        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addElement("8");
                check();
            }
        });

        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addElement("9");
                check();
            }
        });

        zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addElement("0");
                check();
            }
        });

        dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!input.isEmpty()) {
                    if (!input.peekLast().contains(".")){
                        addElement(".");
                    }
                } else {
                    addElement(".");
                }
            }
        });

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addElement("+");
            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addElement("-");
            }
        });

        multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addElement("*");
            }
        });

        divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addElement("/");
            }
        });

        pow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addElement("^");
            }
        });

        square.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addElement("^2");
            }
        });

        square_root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFunctionHelper("sqrt");
            }
        });

        factorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addElement("!");
            }
        });

        left_parentheses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addElement("(");
            }
        });

        right_parentheses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addElement(")");
            }
        });

        erase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input.clear();
                expression.setText("");
                result.setText("");
            }
        });

        backspace.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                expression.setText("");
                input.clear();
                result.setText("");
                return false;
            }
        });

        backspace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!input.isEmpty()) {
                    if (Checker.isNumeric(input.peekLast())) {
                        String temp = input.removeLast();
                        temp = temp.substring(0, temp.length() - 1);
                        if (!temp.isEmpty()) {
                            input.addLast(temp);
                        }
                    } else {
                        input.removeLast();
                    }
                    StringBuilder tempText = new StringBuilder();
                    for (String s : input) {
                        tempText.append(s);
                    }
                    expression.setText(tempText.toString());
                }
            }
        });

        answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ans != null) {
                    if (!input.isEmpty()) {
                        if (!Checker.isNumeric(input.peekLast())) {
                            input.addLast(ans);
                            setExpressionText();
                        }
                    } else {
                        input.addLast(ans);
                        setExpressionText();
                    }
                }
            }
        });

        change_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!input.isEmpty() && Checker.isNumeric(input.peekLast())) {
                    Double temp = Double.parseDouble(input.removeLast());
                    temp *= -1;
                    input.addLast(temp.toString());
                    setExpressionText();
                }
            }
        });

        shift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (shiftPressed == false) {
                    shiftPressed = true;

                    sin.setText(R.string.sin2);
                    cos.setText(R.string.cos2);
                    tan.setText(R.string.tan2);
                    log.setText(R.string.log2);

                } else {
                    shiftPressed = false;

                    sin.setText(R.string.sin1);
                    cos.setText(R.string.cos1);
                    tan.setText(R.string.tan1);
                    log.setText(R.string.log1);
                }
            }
        });

        mode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                angleMode=((int)mode.getTag());
                if(angleMode==1)
                {
                    mode.setTag(2);
                    mode.setText(R.string.mode1);
                    modeDisplay.setText(R.string.mode2);
                }
                else
                {
                    mode.setTag(1);
                    mode.setText(R.string.mode2);
                    modeDisplay.setText(R.string.mode1);
                }
            }
        });

        sin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                angleMode=((int)mode.getTag());
                if (shiftPressed) {
                    if (angleMode == 2){
                        addFunctionHelper("asind");
                    } else {
                        addFunctionHelper("asin");
                    }
                } else {
                    if(angleMode == 2){
                        addFunctionHelper("sind");
                    } else {
                        addFunctionHelper("sin");
                    }
                }
            }
        });

        cos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                angleMode=((int)mode.getTag());
                if (shiftPressed) {
                    if (angleMode == 2){
                        addFunctionHelper("acosd");
                    }else{
                        addFunctionHelper("acos");
                    }
                } else {
                    if (angleMode == 2) {
                        addFunctionHelper("cosd");
                    } else {
                        addFunctionHelper("cos");
                    }
                }
            }
        });

        tan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                angleMode=((int)mode.getTag());
                if (shiftPressed) {
                    if (angleMode == 2){
                        addFunctionHelper("atand");
                    } else {
                        addFunctionHelper("atan");
                    }
                }  else {
                    if(angleMode == 2){
                        addFunctionHelper("tand");
                    }  else {
                        addFunctionHelper("tan");
                    }
                }
            }
        });

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (shiftPressed) {
                    addFunctionHelper("log2");
                } else {
                    addFunctionHelper("log");
                }
            }
        });

        pie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addElement("π");
                //expression.setText("3.141592653589793");
                //input.contains("3.141592653589793");
            }
        });

        equals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                resfinal(input);

              }
        });


    }

    private void check(){
        if((input.contains("+"))||(input.contains("-"))||(input.contains("*"))||(input.contains("/"))
                ||(input.contains(")"))){
            res(input);
        }
    }

    private void res(LinkedList<String> view){
        if (!input.isEmpty()) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    final String tempResult = postfix.evaluate(input);
                    if (!tempResult.equals("ERROR") && !tempResult.equals("NaN")) {
                        ans = tempResult.toString();
                    }

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            result.setText(tempResult);
                            // expression.setText(tempResult);
                        }
                    });
                }
            });
            t.start();
        }
    }


    private void resfinal(LinkedList<String> view){
        if (!input.isEmpty()) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    final String tempResult = postfix.evaluate(input);
                    if (!tempResult.equals("ERROR") && !tempResult.equals("NaN")) {
                        ans = tempResult.toString();
                    }

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            dbHelper.insert("SCIENTIFIC", input + " = " + tempResult);
                            result.setText("");
                            if(String.valueOf(tempResult).equals("1.633123935319537E16")) {
                                expression.setText("∞");
                            } else {
                                expression.setText(tempResult);
                            }
                        }
                    });
                }
            });
            t.start();
        }
    }

    private void addFunctionHelper(String element){
        if (!input.isEmpty()){
            if (Checker.isNumeric(input.peekLast()) || input.peekLast().equals(")")){
                addElement("*");
                addElement(element);
                addElement("(");
            } else {
                addElement(element);
                addElement("(");
            }
        } else {
            addElement(element);
            addElement("(");
        }
    }

    private void addElement(final String element) {
        if (!input.isEmpty()) {
            if ((Checker.isNumeric(element) || element.equals(".")) && (Checker.isNumeric(input.peekLast()) || input.peekLast().equals("."))) {
                String token = input.removeLast();
                token += element;
                input.addLast(token);
            } else if (Checker.isOperator(element) && Checker.isOperator(input.peekLast())) {
                input.removeLast();
                input.addLast(element);
            } else {
                input.addLast(element);
            }
        } else {
            input.addLast(element);
        }

        setExpressionText();
    }

    private void setExpressionText() {
        StringBuilder text = new StringBuilder();
        for (String s : input) {
            text.append(s);
        }
        expression.setText(text.toString());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this,SettingsActivity2.class);
            startActivity(intent);
            //startActivity(new Intent(MainActivity.this, SettingsActivity.class));
            return true;
        }
        if (id == R.id.action_history){
            Intent i = new Intent(this, HistoryActivity.class);
            i.putExtra("calcName", "SCIENTIFIC");
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SETTINGS_ACTION) {
            if (resultCode == SettingsFragment.RESULT_CODE_THEME_UPDATED) {
                finish();
                startActivity(getIntent());
                return;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
