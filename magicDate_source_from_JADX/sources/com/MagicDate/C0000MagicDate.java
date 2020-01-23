package com.MagicDate;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Stack;

/* renamed from: com.MagicDate.MagicDate */
public class C0000MagicDate extends Activity implements OnClickListener {
    /* access modifiers changed from: private */
    public EditText anzahl;
    private ArrayList<Integer> anzahlArray = new ArrayList<>();
    private DatePicker datePicker;
    private OnDateChangedListener datePickerListener = new OnDateChangedListener() {
        public void onDateChanged(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {
            C0000MagicDate.this.intTag = selectedDay;
            C0000MagicDate.this.intMonat = selectedMonth;
            C0000MagicDate.this.intJahr = selectedYear;
            C0000MagicDate.this.calc(Integer.parseInt(C0000MagicDate.this.anzahl.getText().toString()));
        }
    };
    private TextView datum;
    /* access modifiers changed from: private */
    public int intJahr = this.now.get(1);
    /* access modifiers changed from: private */
    public int intMonat = this.now.get(2);
    /* access modifiers changed from: private */
    public int intTag = this.now.get(5);
    private Calendar now = Calendar.getInstance();
    private SeekBar seekBar;
    private Stack<Integer> stack = new Stack<>();
    private Calendar tmpDate = Calendar.getInstance();
    private ArrayList<Integer> typArray = new ArrayList<>();

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(C0004R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case C0004R.C0005id.help /*2131099655*/:
                alertMessage(getString(C0004R.string.txt_hilfetext), false);
                break;
        }
        return true;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0004R.layout.main);
        this.datum = (TextView) findViewById(C0004R.C0005id.datum);
        this.anzahl = (EditText) findViewById(C0004R.C0005id.anzahl);
        this.datePicker = (DatePicker) findViewById(C0004R.C0005id.datepicker);
        this.datePicker.init(this.intJahr, this.intMonat, this.intTag, this.datePickerListener);
        this.seekBar = (SeekBar) findViewById(C0004R.C0005id.typ);
        final TextView seekBarValue = (TextView) findViewById(C0004R.C0005id.typ_text);
        this.seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                String var = "";
                switch (progress) {
                    case 0:
                        var = C0000MagicDate.this.getApplicationContext().getString(C0004R.string.txt_sekunden);
                        break;
                    case 1:
                        var = C0000MagicDate.this.getApplicationContext().getString(C0004R.string.txt_minuten);
                        break;
                    case 2:
                        var = C0000MagicDate.this.getApplicationContext().getString(C0004R.string.txt_stunden);
                        break;
                    case 3:
                        var = C0000MagicDate.this.getApplicationContext().getString(C0004R.string.txt_tage);
                        break;
                    case 4:
                        var = C0000MagicDate.this.getApplicationContext().getString(C0004R.string.txt_wochen);
                        break;
                    case 5:
                        var = C0000MagicDate.this.getApplicationContext().getString(C0004R.string.txt_monate);
                        break;
                    case 6:
                        var = C0000MagicDate.this.getApplicationContext().getString(C0004R.string.txt_jahre);
                        break;
                }
                seekBarValue.setText(var);
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        getRandom();
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case C0004R.C0005id.berechnen /*2131099652*/:
                String tmpAnzahl = this.anzahl.getText().toString();
                if (tmpAnzahl.length() == 0) {
                    Toast.makeText(getApplicationContext(), getString(C0004R.string.msg_wert), 0).show();
                    return;
                } else {
                    calc(Integer.parseInt(tmpAnzahl));
                    return;
                }
            case C0004R.C0005id.zufall /*2131099653*/:
                getRandom();
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: private */
    public void calc(int anzahl2) {
        this.tmpDate.clear();
        this.tmpDate.set(this.intJahr, this.intMonat, this.intTag);
        Log.v("", this.tmpDate.toString());
        switch (this.seekBar.getProgress()) {
            case 0:
                this.tmpDate.add(13, anzahl2);
                break;
            case 1:
                this.tmpDate.add(12, anzahl2);
                break;
            case 2:
                this.tmpDate.add(10, anzahl2);
                break;
            case 3:
                this.tmpDate.add(6, anzahl2);
                break;
            case 4:
                this.tmpDate.add(3, anzahl2);
                break;
            case 5:
                this.tmpDate.add(2, anzahl2);
                break;
            case 6:
                this.tmpDate.add(1, anzahl2);
                break;
        }
        Log.v("", this.tmpDate.toString());
        String tmp = getString(C0004R.string.txt_magicdate) + "\n";
        if (this.tmpDate.get(5) < 10) {
            tmp = new StringBuilder(String.valueOf(tmp)).append("0").toString();
        }
        String tmp2 = new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(tmp)).append(String.valueOf(this.tmpDate.get(5))).toString())).append(".").toString();
        if (this.tmpDate.get(2) + 1 < 10) {
            tmp2 = new StringBuilder(String.valueOf(tmp2)).append("0").toString();
        }
        this.datum.setText(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(tmp2)).append(String.valueOf(this.tmpDate.get(2) + 1)).toString())).append(".").toString())).append(String.valueOf(this.tmpDate.get(1))).toString());
    }

    private void getRandom() {
        this.anzahlArray.add(Integer.valueOf(10000000));
        this.typArray.add(Integer.valueOf(0));
        this.anzahlArray.add(Integer.valueOf(11223344));
        this.typArray.add(Integer.valueOf(0));
        this.anzahlArray.add(Integer.valueOf(12121212));
        this.typArray.add(Integer.valueOf(0));
        this.anzahlArray.add(Integer.valueOf(12345678));
        this.typArray.add(Integer.valueOf(0));
        this.anzahlArray.add(Integer.valueOf(22446688));
        this.typArray.add(Integer.valueOf(0));
        this.anzahlArray.add(Integer.valueOf(25000000));
        this.typArray.add(Integer.valueOf(0));
        this.anzahlArray.add(Integer.valueOf(31121999));
        this.typArray.add(Integer.valueOf(0));
        this.anzahlArray.add(Integer.valueOf(33557799));
        this.typArray.add(Integer.valueOf(0));
        this.anzahlArray.add(Integer.valueOf(50000000));
        this.typArray.add(Integer.valueOf(0));
        this.anzahlArray.add(Integer.valueOf(75000000));
        this.typArray.add(Integer.valueOf(0));
        this.anzahlArray.add(Integer.valueOf(87654321));
        this.typArray.add(Integer.valueOf(0));
        this.anzahlArray.add(Integer.valueOf(100000000));
        this.typArray.add(Integer.valueOf(0));
        this.anzahlArray.add(Integer.valueOf(123456789));
        this.typArray.add(Integer.valueOf(0));
        this.anzahlArray.add(Integer.valueOf(250000000));
        this.typArray.add(Integer.valueOf(0));
        this.anzahlArray.add(Integer.valueOf(500000000));
        this.typArray.add(Integer.valueOf(0));
        this.anzahlArray.add(Integer.valueOf(750000000));
        this.typArray.add(Integer.valueOf(0));
        this.anzahlArray.add(Integer.valueOf(987654321));
        this.typArray.add(Integer.valueOf(0));
        this.anzahlArray.add(Integer.valueOf(1000000000));
        this.typArray.add(Integer.valueOf(0));
        this.anzahlArray.add(Integer.valueOf(250000));
        this.typArray.add(Integer.valueOf(1));
        this.anzahlArray.add(Integer.valueOf(500000));
        this.typArray.add(Integer.valueOf(1));
        this.anzahlArray.add(Integer.valueOf(1000000));
        this.typArray.add(Integer.valueOf(1));
        this.anzahlArray.add(Integer.valueOf(2500000));
        this.typArray.add(Integer.valueOf(1));
        this.anzahlArray.add(Integer.valueOf(3333333));
        this.typArray.add(Integer.valueOf(1));
        this.anzahlArray.add(Integer.valueOf(5000000));
        this.typArray.add(Integer.valueOf(1));
        this.anzahlArray.add(Integer.valueOf(6666666));
        this.typArray.add(Integer.valueOf(1));
        this.anzahlArray.add(Integer.valueOf(7500000));
        this.typArray.add(Integer.valueOf(1));
        this.anzahlArray.add(Integer.valueOf(10000000));
        this.typArray.add(Integer.valueOf(1));
        this.anzahlArray.add(Integer.valueOf(11223344));
        this.typArray.add(Integer.valueOf(1));
        this.anzahlArray.add(Integer.valueOf(12121212));
        this.typArray.add(Integer.valueOf(1));
        this.anzahlArray.add(Integer.valueOf(12345678));
        this.typArray.add(Integer.valueOf(1));
        this.anzahlArray.add(Integer.valueOf(22446688));
        this.typArray.add(Integer.valueOf(1));
        this.anzahlArray.add(Integer.valueOf(25000000));
        this.typArray.add(Integer.valueOf(1));
        this.anzahlArray.add(Integer.valueOf(9999));
        this.typArray.add(Integer.valueOf(2));
        this.anzahlArray.add(Integer.valueOf(10000));
        this.typArray.add(Integer.valueOf(2));
        this.anzahlArray.add(Integer.valueOf(11111));
        this.typArray.add(Integer.valueOf(2));
        this.anzahlArray.add(Integer.valueOf(22222));
        this.typArray.add(Integer.valueOf(2));
        this.anzahlArray.add(Integer.valueOf(25000));
        this.typArray.add(Integer.valueOf(2));
        this.anzahlArray.add(Integer.valueOf(33333));
        this.typArray.add(Integer.valueOf(2));
        this.anzahlArray.add(Integer.valueOf(44444));
        this.typArray.add(Integer.valueOf(2));
        this.anzahlArray.add(Integer.valueOf(50000));
        this.typArray.add(Integer.valueOf(2));
        this.anzahlArray.add(Integer.valueOf(75000));
        this.typArray.add(Integer.valueOf(2));
        this.anzahlArray.add(Integer.valueOf(99999));
        this.typArray.add(Integer.valueOf(2));
        this.anzahlArray.add(Integer.valueOf(100000));
        this.typArray.add(Integer.valueOf(2));
        this.anzahlArray.add(Integer.valueOf(121212));
        this.typArray.add(Integer.valueOf(2));
        this.anzahlArray.add(Integer.valueOf(123456));
        this.typArray.add(Integer.valueOf(2));
        this.anzahlArray.add(Integer.valueOf(250000));
        this.typArray.add(Integer.valueOf(2));
        this.anzahlArray.add(Integer.valueOf(100));
        this.typArray.add(Integer.valueOf(3));
        this.anzahlArray.add(Integer.valueOf(111));
        this.typArray.add(Integer.valueOf(3));
        this.anzahlArray.add(Integer.valueOf(222));
        this.typArray.add(Integer.valueOf(3));
        this.anzahlArray.add(Integer.valueOf(250));
        this.typArray.add(Integer.valueOf(3));
        this.anzahlArray.add(Integer.valueOf(333));
        this.typArray.add(Integer.valueOf(3));
        this.anzahlArray.add(Integer.valueOf(444));
        this.typArray.add(Integer.valueOf(3));
        this.anzahlArray.add(Integer.valueOf(500));
        this.typArray.add(Integer.valueOf(3));
        this.anzahlArray.add(Integer.valueOf(666));
        this.typArray.add(Integer.valueOf(3));
        this.anzahlArray.add(Integer.valueOf(750));
        this.typArray.add(Integer.valueOf(3));
        this.anzahlArray.add(Integer.valueOf(1000));
        this.typArray.add(Integer.valueOf(3));
        this.anzahlArray.add(Integer.valueOf(1234));
        this.typArray.add(Integer.valueOf(3));
        this.anzahlArray.add(Integer.valueOf(2500));
        this.typArray.add(Integer.valueOf(3));
        this.anzahlArray.add(Integer.valueOf(5000));
        this.typArray.add(Integer.valueOf(3));
        this.anzahlArray.add(Integer.valueOf(7500));
        this.typArray.add(Integer.valueOf(3));
        this.anzahlArray.add(Integer.valueOf(10000));
        this.typArray.add(Integer.valueOf(3));
        this.anzahlArray.add(Integer.valueOf(12345));
        this.typArray.add(Integer.valueOf(3));
        this.anzahlArray.add(Integer.valueOf(99));
        this.typArray.add(Integer.valueOf(4));
        this.anzahlArray.add(Integer.valueOf(100));
        this.typArray.add(Integer.valueOf(4));
        this.anzahlArray.add(Integer.valueOf(111));
        this.typArray.add(Integer.valueOf(4));
        this.anzahlArray.add(Integer.valueOf(123));
        this.typArray.add(Integer.valueOf(4));
        this.anzahlArray.add(Integer.valueOf(222));
        this.typArray.add(Integer.valueOf(4));
        this.anzahlArray.add(Integer.valueOf(333));
        this.typArray.add(Integer.valueOf(4));
        this.anzahlArray.add(Integer.valueOf(444));
        this.typArray.add(Integer.valueOf(4));
        this.anzahlArray.add(Integer.valueOf(500));
        this.typArray.add(Integer.valueOf(4));
        this.anzahlArray.add(Integer.valueOf(777));
        this.typArray.add(Integer.valueOf(4));
        this.anzahlArray.add(Integer.valueOf(999));
        this.typArray.add(Integer.valueOf(4));
        this.anzahlArray.add(Integer.valueOf(1000));
        this.typArray.add(Integer.valueOf(4));
        this.anzahlArray.add(Integer.valueOf(1234));
        this.typArray.add(Integer.valueOf(4));
        this.anzahlArray.add(Integer.valueOf(99));
        this.typArray.add(Integer.valueOf(5));
        this.anzahlArray.add(Integer.valueOf(100));
        this.typArray.add(Integer.valueOf(5));
        this.anzahlArray.add(Integer.valueOf(111));
        this.typArray.add(Integer.valueOf(5));
        this.anzahlArray.add(Integer.valueOf(123));
        this.typArray.add(Integer.valueOf(5));
        this.anzahlArray.add(Integer.valueOf(222));
        this.typArray.add(Integer.valueOf(5));
        this.anzahlArray.add(Integer.valueOf(333));
        this.typArray.add(Integer.valueOf(5));
        this.anzahlArray.add(Integer.valueOf(444));
        this.typArray.add(Integer.valueOf(5));
        this.anzahlArray.add(Integer.valueOf(500));
        this.typArray.add(Integer.valueOf(5));
        this.anzahlArray.add(Integer.valueOf(25));
        this.typArray.add(Integer.valueOf(6));
        this.anzahlArray.add(Integer.valueOf(33));
        this.typArray.add(Integer.valueOf(6));
        this.anzahlArray.add(Integer.valueOf(44));
        this.typArray.add(Integer.valueOf(6));
        this.anzahlArray.add(Integer.valueOf(50));
        this.typArray.add(Integer.valueOf(6));
        this.anzahlArray.add(Integer.valueOf(66));
        this.typArray.add(Integer.valueOf(6));
        this.anzahlArray.add(Integer.valueOf(77));
        this.typArray.add(Integer.valueOf(6));
        this.anzahlArray.add(Integer.valueOf(88));
        this.typArray.add(Integer.valueOf(6));
        this.anzahlArray.add(Integer.valueOf(99));
        this.typArray.add(Integer.valueOf(6));
        this.anzahlArray.add(Integer.valueOf(100));
        this.typArray.add(Integer.valueOf(6));
        int randomNumber = randomize();
        Log.v("", String.valueOf(this.anzahlArray.get(randomNumber)));
        this.seekBar.setProgress(((Integer) this.typArray.get(randomNumber)).intValue());
        this.anzahl.setText(String.valueOf(this.anzahlArray.get(randomNumber)));
        calc(((Integer) this.anzahlArray.get(randomNumber)).intValue());
    }

    /* Debug info: failed to restart local var, previous not found, register: 3 */
    public int randomize() {
        if (this.stack.size() == 0) {
            for (int i = 0; i < this.anzahlArray.size(); i++) {
                this.stack.add(Integer.valueOf(i));
            }
        }
        Collections.shuffle(this.stack);
        return Integer.parseInt(((Integer) this.stack.peek()).toString());
    }

    public void alertMessage(String value, boolean isHTML) {
        Builder builder = new Builder(this);
        if (isHTML) {
            builder.setMessage(Html.fromHtml(value));
        } else {
            builder.setMessage(value);
        }
        builder.setCancelable(true).setNegativeButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        builder.create();
        builder.show();
    }
}
