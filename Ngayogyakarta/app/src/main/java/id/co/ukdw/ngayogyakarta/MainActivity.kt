package id.co.ukdw.ngayogyakarta

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import id.co.ukdw.ngayogyakarta.engine.SearchEngine

class MainActivity : AppCompatActivity() {
    private lateinit var engine : SearchEngine

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initEngine()
    }

    private fun initEngine(){
        engine = SearchEngine(this)
    }

    fun getEngine() = engine
}