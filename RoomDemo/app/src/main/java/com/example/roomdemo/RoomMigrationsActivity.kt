package com.example.roomdemo

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.example.roomdemo.StudentDb.Student
import com.example.roomdemo.StudentDb.StudentDatabase
import com.example.roomdemo.databinding.ActivityRoomMigrationsBinding
import kotlinx.coroutines.launch

class RoomMigrationsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRoomMigrationsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_room_migrations)
        val dao = StudentDatabase.getInstance(application).studentDao

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val nameEditText = binding.etName
        val mailEditText = binding.etMail
        val buttonSubmit = binding.btnSubmit

        buttonSubmit.setOnClickListener{
            lifecycleScope.launch {
                nameEditText.text.let {
                    dao.insertStudent(Student(0, it.toString(), mailEditText.text.toString()))
                    nameEditText.setText("")
                    mailEditText.setText("")
                }
            }
        }
        binding.root
    }
}