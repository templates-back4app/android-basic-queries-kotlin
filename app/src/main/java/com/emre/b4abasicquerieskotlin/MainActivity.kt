package com.emre.b4abasicquerieskotlin

import android.app.ProgressDialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.parse.ParseException
import com.parse.ParseObject
import com.parse.ParseQuery

class MainActivity : AppCompatActivity() {

    private var resultList: RecyclerView? = null
    private var queryByName: Button? = null
    private var queryByFriendCount: Button? = null
    private var queryByOrdering: Button? = null
    private var queryByAll: Button? = null
    private var clearResults: Button? = null

    private var adapter: ResultAdapter? = null
    private var progressDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        resultList = findViewById(R.id.resultList)
        queryByName = findViewById(R.id.queryByName)
        queryByFriendCount = findViewById(R.id.queryByFriendCount)
        queryByOrdering = findViewById(R.id.queryByOrdering)
        queryByAll = findViewById(R.id.queryByAll)
        clearResults = findViewById(R.id.clearResults)
        progressDialog = ProgressDialog(this)

        queryByName?.setOnClickListener(View.OnClickListener { view: View? -> doQueryByName() })
        queryByFriendCount?.setOnClickListener(View.OnClickListener { view: View? -> doQueryByFriendCount() })
        queryByOrdering?.setOnClickListener(View.OnClickListener { view: View? -> doQueryByOrdering() })
        queryByAll?.setOnClickListener(View.OnClickListener { view: View? -> doQueryByAll() })
        clearResults?.setOnClickListener(View.OnClickListener { view: View? -> adapter?.clearList() })

    }

    private fun doQueryByName() {
        val query = ParseQuery<ParseObject>("Profile")
        query.whereContains("name", "Adam")
        progressDialog!!.show()
        query.findInBackground { objects: List<ParseObject>?, e: ParseException? ->
            progressDialog!!.hide()
            if (e == null) {
                adapter = ResultAdapter(this, objects)
                resultList!!.layoutManager = LinearLayoutManager(this)
                resultList!!.adapter = adapter
            } else {
                Toast.makeText(this, e.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun doQueryByFriendCount() {
        val query = ParseQuery<ParseObject>("Profile")
        query.whereGreaterThan("friendCount", 20)
        progressDialog!!.show()
        query.findInBackground { objects: List<ParseObject>?, e: ParseException? ->
            progressDialog!!.hide()
            if (e == null) {
                adapter = ResultAdapter(this, objects)
                resultList!!.layoutManager = LinearLayoutManager(this)
                resultList!!.adapter = adapter
            } else {
                Toast.makeText(this, e.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun doQueryByOrdering() {
        val query = ParseQuery<ParseObject>("Profile")
        query.orderByDescending("birthDay")
        progressDialog!!.show()
        query.findInBackground { objects: List<ParseObject>?, e: ParseException? ->
            progressDialog!!.hide()
            if (e == null) {
                adapter = ResultAdapter(this, objects)
                resultList!!.layoutManager = LinearLayoutManager(this)
                resultList!!.adapter = adapter
            } else {
                Toast.makeText(this, e.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun doQueryByAll() {
        val query = ParseQuery<ParseObject>("Profile")
        query.whereContains("name", "Adam")
        query.whereGreaterThan("friendCount", 20)
        query.orderByDescending("birthDay")
        progressDialog!!.show()
        query.findInBackground { objects: List<ParseObject>?, e: ParseException? ->
            progressDialog!!.hide()
            if (e == null) {
                adapter = ResultAdapter(this, objects)
                resultList!!.layoutManager = LinearLayoutManager(this)
                resultList!!.adapter = adapter
                resultList!!.isNestedScrollingEnabled = false
            } else {
                Toast.makeText(this, e.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }
}