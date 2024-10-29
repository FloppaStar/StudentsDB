package ru.floppastar.studentsdb

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomsheet.BottomSheetDialog

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bnv = findViewById<BottomNavigationView>(R.id.bottomMenu)
        bnv.setOnItemSelectedListener {
            when (it.itemId){
                R.id.menuGroup ->{
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerViewMain, GroupsFragment())
                        .commit()
                    true
                }
                R.id.menuStudent ->{
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerViewMain, StudentsFragment())
                        .commit()
                    true
                }
                else -> false
            }
        }
    }
}