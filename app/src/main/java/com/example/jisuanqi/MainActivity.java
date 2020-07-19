package com.example.jisuanqi;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //   数字按钮声明
    private Button num0;
    private Button num1;
    private Button num2;
    private Button num3;
    private Button num4;
    private Button num5;
    private Button num6;
    private Button num7;
    private Button num8;
    private Button num9;
    //    运算符按钮声明
    private Button btn_plus;
    private Button btn_subtract;
    private Button btn_multiply;
    private Button btn_divider;
    private Button btn_equal;
    //    其他按钮声明
    private Button btn_point;
    private Button btn_cancel;
    //    运行结果
    private TextView mResultText;
    //    已输入的字符
    private String existedText = "";
    //    是否计算过
    private boolean isCounted = false;
    //    以负号开头，且运算符不是减号
    private boolean startWithOperator = false;
    //    以负号开头，且运算符是减号
    private boolean startWithSubtract = false;
    //    不以负号开头，且包含运算符
    private boolean noStartWithOperator = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initEvent();
    }
    //    初始化空间
    private void initView() {

//        数字按钮控件
        num0 = (Button) findViewById(R.id.btn_zero);
        num1 = (Button) findViewById(R.id.btn_one);
        num2 = (Button) findViewById(R.id.btn_two);
        num3 = (Button) findViewById(R.id.btn_three);
        num4 = (Button) findViewById(R.id.btn_four);
        num5 = (Button) findViewById(R.id.btn_five);
        num6 = (Button) findViewById(R.id.btn_six);
        num7 = (Button) findViewById(R.id.btn_seven);
        num8 = (Button) findViewById(R.id.btn_eight);
        num9 = (Button) findViewById(R.id.btn_nine);

//        运算符控件
        btn_plus = (Button) findViewById(R.id.btn_plus);
        btn_subtract = (Button) findViewById(R.id.btn_subtract);
        btn_multiply = (Button) findViewById(R.id.btn_multiply);
        btn_divider = (Button) findViewById(R.id.btn_divider);
        btn_equal = (Button) findViewById(R.id.btn_equal);

        btn_point = (Button) findViewById(R.id.btn_point);
        btn_cancel = (Button) findViewById(R.id.btn_cancel);

        mResultText = (TextView) findViewById(R.id.result_text);

        existedText = mResultText.getText().toString();
    }

    //    初始化事件
    private void initEvent() {
        num0.setOnClickListener(this);
        num1.setOnClickListener(this);
        num2.setOnClickListener(this);
        num3.setOnClickListener(this);
        num4.setOnClickListener(this);
        num5.setOnClickListener(this);
        num6.setOnClickListener(this);
        num7.setOnClickListener(this);
        num8.setOnClickListener(this);
        num9.setOnClickListener(this);
        btn_plus.setOnClickListener(this);
        btn_subtract.setOnClickListener(this);
        btn_multiply.setOnClickListener(this);
        btn_divider.setOnClickListener(this);
        btn_equal.setOnClickListener(this);
        btn_point.setOnClickListener(this);
        btn_cancel.setOnClickListener(this);
    }

    //    点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btn_zero:
                existedText = isOverRange(existedText,"0");
                break;
            case R.id.btn_one:
                existedText = isOverRange(existedText,"1");
                break;
            case R.id.btn_two:
                existedText = isOverRange(existedText,"2");
                break;
            case R.id.btn_three:
                existedText = isOverRange(existedText,"3");
                break;
            case R.id.btn_four:
                existedText = isOverRange(existedText,"4");
                break;
            case R.id.btn_five:
                existedText = isOverRange(existedText,"5");
                break;
            case R.id.btn_six:
                existedText = isOverRange(existedText,"6");
                break;
            case R.id.btn_seven:
                existedText = isOverRange(existedText,"7");
                break;
            case R.id.btn_eight:
                existedText = isOverRange(existedText,"8");
                break;
            case R.id.btn_nine:
                existedText = isOverRange(existedText,"9");
                break;

            case R.id.btn_plus:
                /**
                 * 判断已有的字符是否是科学计数
                 * 是 置零
                 * 否 进行下一步
                 *
                 * 判断表达式是否可以进行计算
                 * 是 先计算再添加字符
                 * 否 添加字符
                 *
                 * 判断计算后的字符是否是 error
                 * 是 置零
                 * 否 添加运算符
                 */
                if (!existedText.contains("e")) {
                    if (judgeExpression()) {
                        existedText = getResult();
                        if (existedText.equals("error")){
                        } else {
                            existedText += "+";
                        }
                    } else {
                        if (isCounted) {
                            isCounted = false;
                        }
                        if ((existedText.substring(existedText.length() - 1)).equals("-")) {
                            existedText = existedText.replace("-", "+");
                        } else if ((existedText.substring(existedText.length() - 1)).equals("×")) {
                            existedText = existedText.replace("×", "+");
                        } else if ((existedText.substring(existedText.length() - 1)).equals("÷")) {
                            existedText = existedText.replace("÷", "+");
                        } else if (!(existedText.substring(existedText.length() - 1)).equals("+")) {
                            existedText += "+";
                        }
                    }
                } else {
                    existedText = "0";
                }
                break;
            case R.id.btn_subtract:
                if (!existedText.contains("e")) {
                    if (judgeExpression()) {
                        existedText = getResult();
                        if (existedText.equals("error")){
                        } else {
                            existedText += "-";
                        }
                    } else {
                        if (isCounted) {
                            isCounted = false;
                        }
                        if ((existedText.substring(existedText.length() - 1)).equals("+")) {
//                    Log.d("Anonymous", "onClick: " + "进入减法方法");
                            existedText = existedText.replace("+", "-");
                        } else if ((existedText.substring(existedText.length() - 1)).equals("×")) {
                            existedText = existedText.replace("×", "-");
                        } else if ((existedText.substring(existedText.length() - 1)).equals("÷")) {
                            existedText = existedText.replace("÷", "-");
                        } else if (!(existedText.substring(existedText.length() - 1)).equals("-")) {
                            existedText += "-";
                        }
                    }
                } else {
                    existedText = "0";
                }
                break;
            case R.id.btn_multiply:
                if (!existedText.contains("e")) {
                    if (judgeExpression()) {
                        existedText = getResult();
                        if (existedText.equals("error")){
                        } else {
                            existedText += "×";
                        }
                    } else {

                        if (isCounted) {
                            isCounted = false;
                        }

                        if ((existedText.substring(existedText.length() - 1)).equals("+")) {
                            existedText = existedText.replace("+", "×");
                        } else if ((existedText.substring(existedText.length() - 1)).equals("-")) {
                            existedText = existedText.replace("-", "×");
                        } else if ((existedText.substring(existedText.length() - 1)).equals("÷")) {
                            existedText = existedText.replace("÷", "×");
                        } else if (!(existedText.substring(existedText.length() - 1)).equals("×")) {
                            existedText += "×";
                        }
                    }
                } else {
                    existedText = "0";
                }
                break;
            case R.id.btn_divider:

                if (!existedText.contains("e")) {
                    if (judgeExpression()) {
                        existedText = getResult();
                        if (existedText.equals("error")){

                        } else {
                            existedText += "÷";
                        }

                    } else {

                        if (isCounted) {
                            isCounted = false;
                        }

                        if ((existedText.substring(existedText.length() - 1)).equals("+")) {
                            existedText = existedText.replace("+", "÷");
                        } else if ((existedText.substring(existedText.length() - 1)).equals("-")) {
                            existedText = existedText.replace("-", "÷");
                        } else if ((existedText.substring(existedText.length() - 1)).equals("×")) {
                            existedText = existedText.replace("×", "÷");
                        } else if (!(existedText.substring(existedText.length() - 1)).equals("÷")) {
                            existedText += "÷";
                        }
                    }
                } else {
                    existedText = "0";
                }
                break;
            case R.id.btn_equal:
                existedText = getResult();
                isCounted = true;
                break;

            case R.id.btn_point:
                /**
                 * 判断是否运算过
                 * 否
                 *   判断是否有运算符，有 判断运算符之后的数字，无 判断整个数字
                 *   判断数字是否过长，是则不能添加小数点，否则可以添加
                 *   判断已经存在的数字里是否有小数点
                 * 是
                 *   字符串置为 0.
                 */
                if (!isCounted){

                    if (existedText.contains("+") || existedText.contains("-") ||
                            existedText.contains("×") || existedText.contains("÷") ){

                        String param1 = null;
                        String param2 = null;

                        if (existedText.contains("+")) {
                            param1 = existedText.substring(0, existedText.indexOf("+"));
                            param2 = existedText.substring(existedText.indexOf("+") + 1);
                        } else if (existedText.contains("-")) {
                            param1 = existedText.substring(0, existedText.indexOf("-"));
                            param2 = existedText.substring(existedText.indexOf("-") + 1);
                        } else if (existedText.contains("×")) {
                            param1 = existedText.substring(0, existedText.indexOf("×"));
                            param2 = existedText.substring(existedText.indexOf("×") + 1);
                        } else if (existedText.contains("÷")) {
                            param1 = existedText.substring(0, existedText.indexOf("÷"));
                            param2 = existedText.substring(existedText.indexOf("÷") + 1);
                        }
                        Log.d("Anonymous param1",param1);
                        Log.d("Anonymous param2",param2);

                        boolean isContainedDot = param2.contains(".");
                        if (param2.length() >= 9){

                        } else if (!isContainedDot){
                            if (param2.equals("")){
                                existedText += "0.";
                            } else {
                                existedText += ".";
                            }
                        } else {
                            return;
                        }
                    } else {
                        boolean isContainedDot = existedText.contains(".");
                        if (existedText.length() >= 9){

                        } else if (!isContainedDot){
                            existedText += ".";
                        } else {
                            return;
                        }
                    }
                    isCounted = false;

                } else {
                    existedText = "0.";
                    isCounted = false;
                }
                break;

            case R.id.btn_cancel:
                existedText = "0";
                break;
        }
//        设置显示
        mResultText.setText(existedText);
    }

    //    进行运算，得到结果
//    @return 返回结果
    private String getResult() {

        String tempResult = null;

        String param1 = null;
        String param2 = null;

        double arg1 = 0;
        double arg2 = 0;
        double result = 0;

        getCondition();

//        如果有运算符，则进行运算
//        没有运算符，则把已经存在的数据再传出去

        if ( startWithOperator || noStartWithOperator || startWithSubtract) {

            if (existedText.contains("+")) {
//                先获取两个参数
                param1 = existedText.substring(0, existedText.indexOf("+"));
                param2 = existedText.substring(existedText.indexOf("+") + 1);
//                如果第二个参数为空，则还是显示当前字符
                if (param2.equals("")){
                    tempResult = existedText;
                } else {
//                    转换String为Double,计算后再转换成String类型,进行正则表达式处理
                    arg1 = Double.parseDouble(param1);
                    arg2 = Double.parseDouble(param2);
                    result = arg1 + arg2;
                    tempResult = String.format("%f", result);
                    tempResult = subZeroAndDot(tempResult);
                }
            } else if (existedText.contains("×")) {

                param1 = existedText.substring(0, existedText.indexOf("×"));
                param2 = existedText.substring(existedText.indexOf("×") + 1);

                if (param2.equals("")){
                    tempResult = existedText;
                } else {
                    arg1 = Double.parseDouble(param1);
                    arg2 = Double.parseDouble(param2);
                    result = arg1 * arg2;
                    tempResult = String.format("%f", result);
                    tempResult = subZeroAndDot(tempResult);
                }

            } else if (existedText.contains("÷")) {

                param1 = existedText.substring(0, existedText.indexOf("÷"));
                param2 = existedText.substring(existedText.indexOf("÷") + 1);

                if (param2.equals("0")){
                    tempResult = "error";
                } else if (param2.equals("")){
                    tempResult = existedText;
                } else {
                    arg1 = Double.parseDouble(param1);
                    arg2 = Double.parseDouble(param2);
                    result = arg1 / arg2;
                    tempResult = String.format("%f", result);
                    tempResult = subZeroAndDot(tempResult);
                }

            } else if (existedText.contains("-")) {

                param1 = existedText.substring(0, existedText.lastIndexOf("-"));
                param2 = existedText.substring(existedText.lastIndexOf("-") + 1);

                if (param2.equals("")){
                    tempResult = existedText;
                } else {
                    arg1 = Double.parseDouble(param1);
                    arg2 = Double.parseDouble(param2);
                    result = arg1 - arg2;
                    tempResult = String.format("%f", result);
                    tempResult = subZeroAndDot(tempResult);
                }

            }
//            如果数据长度大于等于10位，进行科学计数
//            如果有小数点，再判断小数点前是否有十个数字，有则进行科学计数

            if (tempResult.length() >= 10) {
                tempResult = String.format("%e", Double.parseDouble(tempResult));
            } else if (tempResult.contains(".")) {
                if (tempResult.substring(0, tempResult.indexOf(".")).length() >= 10) {
                    tempResult = String.format("%e", Double.parseDouble(tempResult));
                }
            }
        } else {
            tempResult = existedText;
        }

        return tempResult;
    }


    /**
     * 先判断是否按过等于号
     * 是 按数字则显示当前数字
     * 否 在已有的表达式后添加字符
     *
     * 判断数字是否就是一个 0
     * 是 把字符串设置为空字符串。
     *   1、打开界面没有运算过的时候，AC键归零或删除完归零的时候，会显示一个 0
     *   2、当数字是 0 的时候，设置成空字符串，再按 0 ，数字会还是 0，不然可以按出 000 这种数字
     * 否 添加按下的键的字符
     *
     * 判断数字是否包含小数点
     * 是 数字不能超过十位
     * 否 数字不能超过九位
     *
     * 进行上面的判断后，再判断数字是否超过长度限制
     * 超过不做任何操作
     * 没超过可以再添数字
     */
    private String isOverRange(String existedText, String s) {

        if (!isCounted){
//            判断是否是科学计数(是:文本置零)
            if (existedText.contains("e")){
                existedText = "0";
            }
//            判断是否只有一个 0,是:文本清空

            if (existedText.equals("0")){
                existedText = "";
            }
//            判断是否有运算符(是:判断第二个数字;否:判断整个字符串)

            if (existedText.contains("+") || existedText.contains("-") ||
                    existedText.contains("×") || existedText.contains("÷")){
//                包括运算符时 两个数字 判断第二个数字
                String param2 = null;
                if (existedText.contains("+")){
                    param2 = existedText.substring(existedText.indexOf("+")+1);
                } else if (existedText.contains("-")){
                    param2 = existedText.substring(existedText.indexOf("-")+1);
                } else if (existedText.contains("×")){
                    param2 = existedText.substring(existedText.indexOf("×")+1);
                } else if (existedText.contains("÷")){
                    param2 = existedText.substring(existedText.indexOf("÷")+1);
                }

//            Log.d("Anonymous param1",param1);
//            Log.d("Anonymous param2",param2);
                if (existedText.substring(existedText.length()-1).equals("+") ||
                        existedText.substring(existedText.length()-1).equals("-") ||
                        existedText.substring(existedText.length()-1).equals("×") ||
                        existedText.substring(existedText.length()-1).equals("÷")){
                    existedText += s;
                } else {
                    if (param2.contains(".")){
                        if (param2.length() >= 10){

                        } else {
                            existedText += s;
                        }
                    } else {
                        if (param2.length() >= 9){

                        } else {
                            existedText += s;
                        }
                    }
                }
            } else {
//                不包括运算符时 一个数字
                if (existedText.contains(".")){
                    if (existedText.length() >= 10){

                    } else {
                        existedText += s;
                    }
                } else {
                    if (existedText.length() >= 9){

                    } else {
                        existedText += s;
                    }
                }
            }

            isCounted = false;

        } else {

            existedText = s;
            isCounted = false;

        }


        return existedText;
    }
//    使用java正则表达式去掉多余的.与0
//    @param s 传入的字符串
//    @return 修改之后的字符串

    public static String subZeroAndDot(String s){
        if(s.indexOf(".") > 0){
            s = s.replaceAll("0+?$", "");//去掉多余的0
            s = s.replaceAll("[.]$", "");//如最后一位是.则去掉
        }
        return s;
    }

//    判断表达式
//    为了按等号是否进行运算
//    以及出现两个运算符（第一个参数如果为负数的符号不计）先进行运算再添加运算符

    private boolean judgeExpression() {

        getCondition();

        String tempParam2 = null;

        if ( startWithOperator || noStartWithOperator || startWithSubtract) {

            if (existedText.contains("+")) {
//                先获取第二个参数
                tempParam2 = existedText.substring(existedText.indexOf("+") + 1);
//                如果第二个参数为空，表达式不成立
                if (tempParam2.equals("")) {
                    return false;
                } else {
                    return true;
                }
            } else if (existedText.contains("×")) {

                tempParam2 = existedText.substring(existedText.indexOf("×") + 1);

                if (tempParam2.equals("")) {
                    return false;
                } else {
                    return true;
                }

            } else if (existedText.contains("÷")) {

                tempParam2 = existedText.substring(existedText.indexOf("÷") + 1);

                if (tempParam2.equals("")) {
                    return false;
                } else {
                    return true;
                }
            } else if (existedText.contains("-")) {
//                这里是以最后一个 - 号为分隔去取出两个参数
//                进到这个方法，必须满足有运算公式
//                而又避免了第一个参数是负数的情况

                tempParam2 = existedText.substring(existedText.lastIndexOf("-") + 1);

                if (tempParam2.equals("")) {
                    return false;
                } else {
                    return true;
                }

            }
        }
        return false;
    }

    //    取得判断条件
    private void getCondition() {

        startWithOperator = existedText.startsWith("-") && ( existedText.contains("+") ||
                existedText.contains("×") || existedText.contains("÷") );

        startWithSubtract = existedText.startsWith("-") && ( existedText.lastIndexOf("-") != 0 );

        noStartWithOperator = !existedText.startsWith("-") && ( existedText.contains("+") ||
                existedText.contains("-") || existedText.contains("×") || existedText.contains("÷"));
    }

}
