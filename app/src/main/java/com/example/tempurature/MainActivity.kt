package com.example.tempurature

import android.annotation.SuppressLint
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import android.app.AlertDialog.Builder
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
    //Declaring view
    private lateinit var selectedUnitLayout: LinearLayout
    private lateinit var selectedUnitText:TextView
    private lateinit var editInput:EditText
    private lateinit var textView8:TextView
    private lateinit var textview9:TextView
    private lateinit var selectedUnit:String

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //initialixing views
        selectedUnitLayout=findViewById(R.id.selectType)
        selectedUnitText =findViewById(R.id.textSelect) //
        editInput =findViewById(R.id.editInput)
        textView8=findViewById(R.id.textView8)
        textview9 =findViewById(R.id.textView9)

        selectedUnit ="Fahrenheit"

        //setting alert dialogue to appear for selecting input unit


        selectedUnitLayout.setOnClickListener(){
                showalertDialog()
        }
        editInput.addTextChangedListener() {
            var resultText:String =""
            var inputVal =editInput.text.toString()
            val df =DecimalFormat("#.##")
            if(inputVal.isNotEmpty()){
                val doubleInput =inputVal.toDouble()

                if (selectedUnit=="Fahrenheit") {
                    resultText=df.format((doubleInput-32)*5/9)
                    selectedUnitText.text="Celsius"
                }
                else if (selectedUnit=="Celsius"){
                    resultText=df.format((doubleInput*9/5+32))
                    selectedUnitText.text="Fahrenheit"
                }

                textView8.text=resultText
            }
        }
    }

    private fun showalertDialog() {

        val alertDialog: AlertDialog.Builder =AlertDialog.Builder( this@MainActivity)
        alertDialog.setTitle("Select Input Unit")  //selecting title of alert dialog
        val items =arrayOf("Fahrenheit","Celsius")  //opt in alert Dialog
        val checkedItem =-1

        alertDialog.setSingleChoiceItems(items,checkedItem,
            DialogInterface.OnClickListener(){ dialog,which ->
            selectedUnit=items[which]
            selectedUnitText.setText(selectedUnit)
        })

        alertDialog.setPositiveButton(android.R.string.ok,
            DialogInterface.OnClickListener(){dialog,which->
                dialog.dismiss()
            })
        val alert:AlertDialog=alertDialog.create()
        alertDialog.show()
    }
}