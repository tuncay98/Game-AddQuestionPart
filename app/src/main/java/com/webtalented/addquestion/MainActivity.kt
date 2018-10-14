package com.webtalented.addquestion

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = FirebaseFirestore.getInstance()

        db.collection("Suallar")
                .get()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        var i: Int = 1;
                        for (document in task.result!!) {

                           val view = TextView(this);

                             view.text = "\n"+ i.toString() + ". " + document.data["Sual"].toString() + " (ID: ${document.id}) :  \n A)" + document.data["Cavab"] + " B)" + document.data["Sehv1"] +" C)" + document.data["Sehv2"] +" D)" + document.data["Sehv3"]
                            i+=1
                            view.textSize = 20f

                            list.addView(view)

                            Log.d(this@MainActivity.toString(), document.id + " => " + document.data)
                        }
                    } else {
                        val view = TextView(this);
                        view.text = "Yuklene Bilmedi, Yeniden Yoxla"
                        view.textSize = 20f

                        list.addView(view)

                        Log.w(this@MainActivity.toString(), "Error getting documents.", task.exception)
                    }
                }

        btn.setOnClickListener {
            Add()
        }

        btn2.setOnClickListener {
            Delete()
        }



    }

    fun Add(){

        val intent = Intent(this , Add::class.java)

        startActivity(intent)
    }

    fun Delete(){

        val intent = Intent(this, Delete::class.java)

        startActivity(intent)

    }
}
