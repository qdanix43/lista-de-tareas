package adapters // Declara el paquete al que pertenece la clase TaskAdapter

import android.view.LayoutInflater // Importa la clase LayoutInflater para inflar diseños XML en vistas
import android.view.View // Importa la clase View para trabajar con elementos de interfaz de usuario
import android.view.ViewGroup // Importa la clase ViewGroup para contener vistas
import android.widget.CheckBox // Importa la clase CheckBox para manejar casillas de verificación
import android.widget.TextView // Importa la clase TextView para mostrar texto
import androidx.recyclerview.widget.RecyclerView // Importa la clase RecyclerView para trabajar con listas o rejillas desplazables
import com.example.listadetareas.R // Importa el archivo de recursos R, que contiene referencias a los recursos de la aplicación

class TaskAdapter(private val tasks: MutableList<com.example.listadetareas.models.Task>) : // Define la clase TaskAdapter que extiende RecyclerView.Adapter y toma una lista mutable de tareas como parámetro
    RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() { // Define la clase interna TaskViewHolder que extiende RecyclerView.ViewHolder

    lateinit var onCompleteClickListener: Any // Declara una variable lateinit para manejar el clic en la opción de completar tarea
    lateinit var onDeleteClickListener: Any // Declara una variable lateinit para manejar el clic en la opción de eliminar tarea

    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) { // Define la clase interna TaskViewHolder que extiende RecyclerView.ViewHolder y toma una vista como parámetro
        val taskName: TextView = itemView.findViewById(R.id.task_name) // Inicializa un TextView para mostrar el nombre de la tarea
        val taskCheckBox: CheckBox = itemView.findViewById(R.id.task_checkbox) // Inicializa un CheckBox para marcar el estado de completado de la tarea
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder { // Sobrescribe el método onCreateViewHolder para crear y devolver un nuevo ViewHolder cuando sea necesario
        val itemView = LayoutInflater.from(parent.context) // Obtiene el LayoutInflater del contexto del padre
            .inflate(R.layout.task_item, parent, false) // Infla el diseño del elemento de tarea desde XML
        return TaskViewHolder(itemView) // Devuelve un nuevo ViewHolder con la vista inflada
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) { // Sobrescribe el método onBindViewHolder para enlazar datos a elementos de vista en un ViewHolder
        val currentTask = tasks[position] // Obtiene la tarea actual en la posición dada
        holder.taskName.text = currentTask.name // Establece el nombre de la tarea en el TextView dentro del ViewHolder
        holder.taskCheckBox.isChecked = currentTask.isCompleted // Establece el estado de verificación del CheckBox según el estado de completado de la tarea
    }

    override fun getItemCount(): Int { // Sobrescribe el método getItemCount para devolver el número total de elementos en el conjunto de datos
        return tasks.size // Devuelve el tamaño de la lista de tareas
    }
}
