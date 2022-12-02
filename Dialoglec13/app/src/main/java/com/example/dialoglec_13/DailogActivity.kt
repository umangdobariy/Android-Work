package com.example.dialoglec_13

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.inflate
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.dialoglec_13.databinding.ActivityCustomBinding
import com.example.dialoglec_13.databinding.ActivityCustomBinding.bind
import com.example.dialoglec_13.databinding.ActivityCustomBinding.inflate
import com.example.dialoglec_13.databinding.ActivityDialogBinding
import com.example.dialoglec_13.databinding.ActivityDialogBinding.inflate

class DailogActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialog)
    }

    fun onButtonClicked(view: View) {

        when(view.id){
            R.id.ud_simple_dialog ->{
                showSimpleDialog()
            }
            R.id.ud_single_Choice_dialog ->{
                showSingleChoiceDialog()
            }
            R.id.ud_single_Choice_dialog2 ->{
                showSingleChoicewithRadio()
            }
            R.id.ud_multi_Choice_dialog ->{
                showmultichoicedialog()
            }R.id.ud_show_custom_dialog ->{
                showCustomdialog()
            }
        }
    }

    private fun showCustomdialog() {

        var builder = AlertDialog.Builder(this)
        var bind =  ActivityCustomBinding.inflate(layoutInflater)
        builder.setView(bind.root)

        var dialog = builder.create()
        dialog.show()

        bind.udSubmit.setOnClickListener {
                Toast.makeText(this,"Successfully submitted",Toast.LENGTH_SHORT).show()
                dialog.dismiss()
        }

        bind.udCancal.setOnClickListener {
            dialog.dismiss()
        }
    }

    private fun showmultichoicedialog() {
        var colors = arrayOf("Red","Green","Blue","White","Pink")
        var list = mutableListOf<String>()

        var builder = AlertDialog.Builder(this)
        builder.setTitle("Select color")
            .setMultiChoiceItems(colors,null, DialogInterface.OnMultiChoiceClickListener { dialogInterface, i, status ->

                var color = colors[i]

                if (status){
                    list.add(color)
                }else{
                    list.remove(color)
                }
                Toast.makeText(this,"$list",Toast.LENGTH_SHORT).show()
            }).show()


    }

    private fun showSingleChoicewithRadio() {
        var colors = arrayOf("Red","Green","Blue","Pick","Yellow")
        var color = ""

        var builder = AlertDialog.Builder(this)
        builder.setTitle("Pick a color")
            .setSingleChoiceItems(colors,-1, DialogInterface.OnClickListener { dialogInterface, i ->
                color = colors[i]
            }).setPositiveButton("Done", DialogInterface.OnClickListener { dailog, i ->
                Toast.makeText(this,"$color",Toast.LENGTH_SHORT).show()
            }).setNegativeButton("Cancel", DialogInterface.OnClickListener { dialogInterface, i ->
                Toast.makeText(this,"Negative button clicked",Toast.LENGTH_SHORT).show()
            }).show()
    }

    private fun showSingleChoiceDialog() {
        var colors = arrayOf("Red","Green","Blue","pick","yellow")

        var builder = AlertDialog.Builder(this)
        builder.setTitle("Pick a Color")
            .setItems(colors, DialogInterface.OnClickListener { dialogInterface, i ->
                var color = colors[i]
                Toast.makeText(this,"$color",Toast.LENGTH_SHORT).show()
            }).show()
    }

    private fun showSimpleDialog() {

        var builder = AlertDialog.Builder(this)
        builder.setTitle("Alert")
            .setMessage("are you sure you want to logout from this application?")
            .setPositiveButton("Logout", DialogInterface.OnClickListener { dialog, i ->
                Toast.makeText(this,"Positive button clicked",Toast.LENGTH_SHORT).show()
            })
            .setNegativeButton("Cancel", DialogInterface.OnClickListener { dialogInterface, i ->
                Toast.makeText(this,"Negative button clicked",Toast.LENGTH_SHORT).show()
            })
            .show()
    }
}