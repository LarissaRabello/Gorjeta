package com.example.gorjeta

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.gorjeta.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

            var percentage: Int = 0

            binding.rbOptionOne.setOnCheckedChangeListener { _, isChecked ->
                if(isChecked){
                    percentage = 10
                }
            }
            
            binding.rbOptionTwo.setOnCheckedChangeListener { _, isChecked ->
                if(isChecked){
                    percentage = 15
                }
            }
        
            binding.rbOptionThree.setOnCheckedChangeListener { _, isChecked ->
                if(isChecked){
                    percentage = 20
                }
            }
        
            binding.btnClean.setOnClickListener {
                clean()
            }

            binding.btnDone.setOnClickListener {
                val totalTableTemp = binding.tieTotal.text
                val nPeopleTemp = binding.tieNumberOfPeople.text

                if(totalTableTemp?.isEmpty() == true ||
                    nPeopleTemp?.isEmpty() == true){
                    Snackbar
                        .make(binding.tieTotal, "Preencha todos os campos", Snackbar.LENGTH_LONG)
                        .show()
                }else{
                    val totalTable: Float = binding.tieTotal.text.toString().toFloat()
                    val nPeople: Int = binding.tieNumberOfPeople.text.toString().toInt()

                    val totalTemp = totalTable / nPeople
                    val tips = totalTemp * percentage / 100
                    val totalWithTips = totalTemp + tips

                    val intent = Intent(this, SummaryActivity::class.java)
                    intent.apply {
                        putExtra("totalTable", totalTable)
                        putExtra("numPeople", nPeople)
                        putExtra("percentage", percentage)
                        putExtra("totalAmount", totalWithTips)
                    }
                    clean()
                    startActivity(intent)
                }
            }
        }

        private fun clean(){
            binding.tieTotal.setText("")
            binding.tieNumberOfPeople.setText("")
            binding.rbOptionOne.isChecked = false
            binding.rbOptionTwo.isChecked = false
            binding.rbOptionThree.isChecked = false
        }
    }