import androidx.compose.runtime.Immutable
import com.alexmls.minichallenges.circular_indicator.domain.Product

@Immutable
data class ProductState(
    val isLoading: Boolean = true,
    val product: Product? = null,
    val stockLeft: Int = 0,
    val progress: Float = 0f,
){
    val isOutOfStock: Boolean
        get() = stockLeft <= 0
}