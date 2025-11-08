import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexmls.circularstocktracker.domain.ProductRepository
import com.alexmls.circularstocktracker.presentation.ProductEvent
import com.alexmls.circularstocktracker.presentation.util.mapStockToProgress
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProductViewModel(
    private val productRepo: ProductRepository,
) : ViewModel() {

    private var hasLoadedInitialData = false

    private val _state = MutableStateFlow(ProductState())
    val state: StateFlow<ProductState> = _state
        .onStart {
            if (!hasLoadedInitialData) {
                onAction(ProductAction.LoadInitialData)
                hasLoadedInitialData = true
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = ProductState()
        )
    private val _event = MutableSharedFlow<ProductEvent>()
    val event = _event.asSharedFlow()

    fun onAction(action: ProductAction) {
        when (action) {
            ProductAction.LoadInitialData -> loadInitialData()
            ProductAction.OnBuyClicked -> onBuyClicked()
        }
    }
    private fun loadInitialData() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            val product = productRepo.getProduct()
            val stockLeft = productRepo.getCurrentStock()
            val progress = mapStockToProgress(
                stock = stockLeft,
                maxStock = product.stock
            )

            _state.value = ProductState(
                isLoading = false,
                product = product,
                stockLeft = stockLeft,
                progress = progress
            )
        }
    }
    private fun onBuyClicked() {
        viewModelScope.launch {
            val current = _state.value
            val product = current.product ?: return@launch

            if (current.stockLeft <= 0) {
                _state.update { it.copy(stockLeft = 0) }
                _event.emit(ProductEvent.ShowToast("Already out of stock"))
                return@launch
            }

            val newStockFromRepo = productRepo.decreaseStock()

            val clampedStock = newStockFromRepo.coerceAtLeast(0)
            val newProgress = mapStockToProgress(
                stock = clampedStock,
                maxStock = product.stock
            )

            _state.update {
                it.copy(
                    stockLeft = clampedStock,
                    progress = newProgress
                )
            }

            if (clampedStock == 0) {
                _event.emit(ProductEvent.ShowToast("Out of stock"))
            }
        }
    }
}