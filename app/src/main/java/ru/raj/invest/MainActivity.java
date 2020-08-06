package ru.raj.invest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText editSum, editProcent, editAddSum;
    SeekBar seekPeriod;
    CheckBox checkAdd;
    RadioGroup radioAddPeriod;
    TextView textResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editSum = findViewById(R.id.editSum);
        editProcent = findViewById(R.id.editProcent);
        editAddSum = findViewById(R.id.editAddSum);
        seekPeriod = findViewById(R.id.seekPeriod);
        checkAdd = findViewById(R.id.checkAdd);
        radioAddPeriod = findViewById(R.id.radioAddPeriod);
        textResult = findViewById(R.id.textResult);
    }

    public void onClick(View view) { // обработчки клика по единственной кнопке
        //int sum = Integer.getInteger(editSum.getText().toString());
        // это выдает ошибку, т.к. getInteger возвращает integer value of the system property
        // (не знаю что это такое)

        double sum = Integer.parseInt(editSum.getText().toString());
        int add = 0;
        if (checkAdd.isChecked())
            add = Integer.parseInt(editAddSum.getText().toString());
        double percent = Double.parseDouble(editProcent.getText().toString());
        int period = seekPeriod.getProgress();

        for (int i = 0; i < period; i++) {
            if (checkAdd.isChecked()) {
                switch (radioAddPeriod.getCheckedRadioButtonId()) {
                    case R.id.radioMonth:
                        sum += add * 12; break;
                    case R.id.radioQuater:
                        sum += add * 4; break;
                    case R.id.radioYear:
                        sum += add;
                    default:
                        break;
                }
            }
            sum *= (1 + percent/100);
        }

        textResult.setText("Итого: " + String.valueOf(sum));
    }
}