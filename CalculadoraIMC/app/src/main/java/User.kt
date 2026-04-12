import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    var nome: String,
    var peso: Double,
    var altura: Double,
    var imc: Double
) : Parcelable
