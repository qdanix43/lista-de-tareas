package com.example.listadetareas.models.utils // Define el paquete donde reside la clase TaskDBHelper

import android.content.ContentValues // Importa la clase ContentValues para gestionar los valores de las columnas en SQLite
import android.content.Context // Importa la clase Context para obtener información sobre el entorno de la aplicación
import android.database.sqlite.SQLiteDatabase // Importa la clase SQLiteDatabase para interactuar con la base de datos SQLite
import android.database.sqlite.SQLiteOpenHelper // Importa la clase SQLiteOpenHelper para ayudar a crear y actualizar la base de datos
import android.provider.BaseColumns // Importa la clase BaseColumns para agregar columnas especiales comunes en SQLite
import android.util.Log


// Define una clase para gestionar la base de datos
class TaskDBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        // Crea la tabla de tareas
        db.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Maneja las actualizaciones de la base de datos
        // Puedes implementar lógica para migrar datos si es necesario
    }

    companion object {

        // Define las constantes para el nombre de la base de datos y la versión
        const val DATABASE_NAME = "Tasks.db"
        const val DATABASE_VERSION = 1

        // Define la consulta SQL para crear la tabla de tareas
        val SQL_CREATE_ENTRIES =
            "CREATE TABLE ${TaskContract.TaskEntry.TABLE_NAME} (" +
                    "${TaskContract.TaskEntry._ID} INTEGER PRIMARY KEY," +
                    "${TaskContract.TaskEntry.COLUMN_NAME} TEXT," +
                    "${TaskContract.TaskEntry.COLUMN_COMPLETED} INTEGER)"
    }

    object TaskContract {
        // Define las constantes para el nombre de la tabla y las columnas
        object TaskEntry : BaseColumns {
            const val TABLE_NAME = "tasks"
            const val _ID = "_id" // Debe definir esta constante _ID aquí
            const val COLUMN_NAME = "name"
            const val COLUMN_COMPLETED = "completed"
        }
    }


// Define una clase para representar una tarea
//data class Task(val id: Long, val name: String, val isCompleted: Boolean)

// Ejemplo de cómo insertar una tarea en la base de datos
// Asegúrate de que este código esté dentro de una función o método
// ya que no puede estar directamente en el nivel de la clase
val dbHelper = TaskDBHelper(context)
val db = dbHelper.writableDatabase
val values = ContentValues().apply {
    put(TaskContract.TaskEntry.COLUMN_NAME, "Completar tarea 1")
    put(TaskContract.TaskEntry.COLUMN_COMPLETED, 0)
}
val newRowId = db.insert(TaskContract.TaskEntry.TABLE_NAME, null, values)

}

fun update(task: Task) {
    val db = TaskDBHelper.writableDatabase

    var values = ContentValues()
    values.put(Task.COLUMN_NAME, task.task)
    values.put(Task.COLUMN_NAME, task.done)

    var updatedRows = db.update(Task.TABLE_NAME, values, "${DatabaseManager.COLUMN_NAME_ID} = ${task.id}", null)
    Log.i("DATABASE", "Updated records: $updatedRows")

    db.close()
}