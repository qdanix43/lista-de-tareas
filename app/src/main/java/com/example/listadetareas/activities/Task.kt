import android.os.Parcel
import android.os.Parcelable

data class Task(var id: Int, val name: String, var isCompleted: Boolean) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readByte() != 0.toByte())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeByte(if (isCompleted) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Task> {
        val TABLE_NAME: String = "Task"
        val _ID: String = "id"
        val COLUMN_COMPLETED: String = "completed"
        val COLUMN_NAME: String = "name"

        override fun createFromParcel(parcel: Parcel): Task {
            return Task(parcel)
        }

        override fun newArray(size: Int): Array<Task?> {
            return arrayOfNulls(size)
        }
    }
}

const val ADD_TASK_REQUEST = 1

