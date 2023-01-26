package com.example.todolist

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
//main file

class MainActivity : AppCompatActivity() {


    lateinit var item:EditText
    lateinit var add: Button
    lateinit var list:ListView

    var itemlist = ArrayList<String>()
            var fileHelper= FileHelper()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        item = findViewById(R.id.item)
        add = findViewById(R.id.add)
        list = findViewById(R.id.list)

        itemlist = fileHelper.readData(this@MainActivity)

        var arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, android.R.id.text1,itemlist)

        list.adapter= arrayAdapter

        add.setOnClickListener {
            var itemname : String = item.text.toString()
            itemlist.add(itemname)
            item.setText("")
            fileHelper.writeData(itemlist,applicationContext)
            arrayAdapter.notifyDataSetChanged()

        }
//        list.setOnItemClickListener {arrayAdapter,view, position
//            var alert = AlertDialog.Builder(this)
//            alert.setTitle("changes")
//            alert.setMessage("Do you want to edit or delete to do !!")
//            alert.setCancelable(false) //do not change data while clicking on screen
//            alert.setNegativeButton("no", DialogInterface.OnClickListener { dialogInterface, id ->
//                dialogInterface.cancel()
//            })
//
//            alert.setPositiveButton("DELETE", DialogInterface.OnClickListener { dialogInterface, id ->
//
//                itemlist.removeAt(id)
//                arrayAdapter.notifyDataSetChanged()
//                fileHelper.writeData(itemlist,applicationContext)
//            })
//            alert.create().show()
//        }
        list.setOnItemClickListener { parent, view, position, id ->

            var alert = AlertDialog.Builder(this)
            alert.setTitle("changes")
            alert.setMessage("Do you want to delete to do !!")
            alert.setCancelable(false) //do not change data while clicking on screen
            alert.setNegativeButton("no", DialogInterface.OnClickListener { dialogInterface, id ->
                dialogInterface.cancel()
            })

            alert.setPositiveButton("DELETE", DialogInterface.OnClickListener { dialogInterface, id ->

                itemlist.removeAt(position)
                arrayAdapter.notifyDataSetChanged()
                fileHelper.writeData(itemlist,applicationContext)
            })
            alert.create().show()
        }





    }
}