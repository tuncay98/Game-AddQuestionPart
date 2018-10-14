package com.webtalented.addquestion

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_delete.*
import android.util.Log
import android.widget.Toast


import com.google.firebase.firestore.FirebaseFirestore


class Delete : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete)

        val db = FirebaseFirestore.getInstance()

        sil.setOnClickListener { it ->
            if(id.text.isNotEmpty()){

                db.collection("Suallar").document(id.text.toString())
                        .delete()
                        .addOnSuccessListener {
                            val intent = Intent(this , MainActivity::class.java)
                            startActivity(intent)
                            Log.d(this@Delete.toString(), "DocumentSnapshot successfully deleted!") }
                        .addOnFailureListener { e ->
                            Toast.makeText(this@Delete, "Silinmedi" , Toast.LENGTH_LONG).show()
                            Log.w(this@Delete.toString(), "Error deleting document", e) }

            }else{
                Toast.makeText(this@Delete, "Id daxil edilmedi", Toast.LENGTH_LONG).show()
            }
        }
    }
}
