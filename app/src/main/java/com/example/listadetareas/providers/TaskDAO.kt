import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.util.Log
import com.example.listadetareas.models.utils.TaskDBHelper

class TaskDAO (context: Context) {

    private var databaseManager: TaskDBHelper = TaskDBHelper(context)

    fun insert(task: Task): Task {
        val db = databaseManager.writableDatabase

        val values = ContentValues()
        values.put(Task.COLUMN_NAME, task.name)
        values.put(Task.COLUMN_COMPLETED, task.isCompleted)

        val newRowId = db.insert(Task.TABLE_NAME, null, values)
        Log.i("DATABASE", "New record id: $newRowId")

        db.close()

        task.id = newRowId.toInt()
        return task
    }

    fun delete(task: Task) {
        val db = databaseManager.writableDatabase

        val deletedRows = db.delete(Task.TABLE_NAME, "${Task._ID} = ${task.id}", null)
        Log.i("DATABASE", "Deleted rows: $deletedRows")

        db.close()
    }

    @SuppressLint("Range")
    fun find(id: Int): Task? {
        val db = databaseManager.writableDatabase

        val cursor = db.query(
            Task.TABLE_NAME,                         // The table to query
            null,       // The array of columns to return (pass null to get all)
            "${Task._ID} = $id",                        // The columns for the WHERE clause
            null,                    // The values for the WHERE clause
            null,                        // don't group the rows
            null,                         // don't filter by row groups
            null                         // The sort order
        )
        var task: Task? = null

        if (cursor.moveToNext()) {
            val id = cursor.getInt(cursor.getColumnIndex(Task._ID))
            val taskName = cursor.getString(cursor.getColumnIndex(Task.COLUMN_NAME))
            val done = cursor.getInt(cursor.getColumnIndex(Task.COLUMN_COMPLETED)) == 1

            task = Task(id, taskName, done)
        }

        cursor.close()
        db.close()

        return task
    }

    @SuppressLint("Range")
    fun findAll(): List<Task> {
        val db = databaseManager.writableDatabase

        val cursor = db.query(
            Task.TABLE_NAME,                 // The table to query
            null,     // The array of columns to return (pass null to get all)
            null,                // The columns for the WHERE clause
            null,          // The values for the WHERE clause
            null,                   // don't group the rows
            null,                   // don't filter by row groups
            null               // The sort order
        )

        val list: MutableList<Task> = mutableListOf()

        while (cursor.moveToNext()) {
            val id = cursor.getInt(cursor.getColumnIndex(Task._ID))
            val taskName = cursor.getString(cursor.getColumnIndex(Task.COLUMN_NAME.toString()))
            val done = cursor.getInt(cursor.getColumnIndex(Task.COLUMN_COMPLETED)) == 1

            val task = Task(id, taskName, done)
            list.add(task)
        }

        cursor.close()
        db.close()

        return list
    }
}
