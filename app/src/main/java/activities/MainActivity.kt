package com.example.listadetareas.activities // Declara el paquete al que pertenece la clase AddTaskActivity

import android.annotation.SuppressLint // Importa la anotación SuppressLint para controlar advertencias específicas del código
import android.content.Intent // Importa la clase Intent para iniciar nuevas actividades o servicios
import androidx.appcompat.app.AppCompatActivity // Importa la clase base AppCompatActivity para actividades de la biblioteca de compatibilidad
import android.os.Bundle // Importa la clase Bundle para pasar datos entre actividades
import android.widget.Button // Importa la clase Button para manejar botones en la interfaz de usuario
import android.widget.EditText // Importa la clase EditText para manejar campos de entrada de texto en la interfaz de usuario
import com.example.listadetareas.R // Importa el archivo de recursos R, que contiene referencias a los recursos de la aplicación

class MainActivity : AppCompatActivity() { // Define la clase AddTaskActivity que extiende AppCompatActivity

    private lateinit var addTaskButton: Button // Declara una variable lateinit para el botón de agregar tarea

    @SuppressLint("MissingInflatedId") // Anota la función onCreate para suprimir advertencias de ID faltantes
    override fun onCreate(savedInstanceState: Bundle?) { // Sobrescribe el método onCreate para inicializar la actividad
        super.onCreate(savedInstanceState) // Llama al método onCreate de la clase base
        setContentView(R.layout.activity_main) // Establece el diseño de la actividad desde XML

        addTaskButton = findViewById(R.id.add_task_button) // Inicializa el botón de agregar tarea

        addTaskButton.setOnClickListener { // Establece un OnClickListener para el botón de agregar tarea
            val intent = Intent(this, AddTaskActivity::class.java)
            startActivity(intent)
        }
    }
}
