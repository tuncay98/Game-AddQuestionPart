package com.webtalented.addquestion

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_add.*



class Add : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        val db = FirebaseFirestore.getInstance()

        button.setOnClickListener {

            if(sual.text.isNotEmpty() && cavab.text.isNotEmpty() && sehv1.text.isNotEmpty() && sehv2.text.isNotEmpty() && sehv3.text.isNotEmpty()){

                val data : HashMap<String, Any> = HashMap()
                data["Sual"] = sual.text.toString()
                data["Cavab"] = cavab.text.toString()
                data["Sehv1"] = sehv1.text.toString()
                data["Sehv2"] = sehv2.text.toString()
                data["Sehv3"] = sehv3.text.toString()

                db.collection("Suallar")
                        .add(data)
                        .addOnSuccessListener { documentReference ->

                            val intent = Intent(this, MainActivity::class.java);
                            startActivity(intent)

                            Log.d(this@Add.toString(), "DocumentSnapshot written with ID: " + documentReference.id) }

                        .addOnFailureListener { e ->

                            Toast.makeText(this@Add , "Save edilmedi", Toast.LENGTH_LONG).show()

                            Log.w(this@Add.toString(), "Error adding document", e) }


            }else{
                Toast.makeText(this@Add , "Bosluq Qoymayin", Toast.LENGTH_LONG).show()
            }
        }

    }

}
