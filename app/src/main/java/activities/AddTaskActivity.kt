package com.example.listadetareas.activities
// AddTaskActivity.kt
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.listadetareas.R
import com.example.listadetareas.models.Task

class AddTaskActivity : AppCompatActivity() {

    private lateinit var taskEditText: EditText
    private lateinit var addTaskButton: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

        taskEditText = findViewById(R.id.task_edit_text)
        addTaskButton = findViewById(R.id.add_task_button)

        addTaskButton.setOnClickListener {
            val taskName = taskEditText.text.toString()
            if (taskName.isNotBlank()) {
                val newTask = Task(0, taskName, false)
                // Aquí puedes agregar lógica para guardar la nueva tarea
                // Puedes usar un intent para enviar la tarea de regreso a la actividad principal si lo deseas
                finish() // Cerrar la actividad después de agregar la tarea
            }
        }
    }
}
