import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.alexmls.circularstocktracker.R
import com.alexmls.circularstocktracker.domain.Product
import com.alexmls.circularstocktracker.presentation.ProductEvent
import com.alexmls.circularstocktracker.presentation.components.CircularStockIndicator
import com.alexmls.circularstocktracker.presentation.components.ProductSizeSection
import com.alexmls.circularstocktracker.presentation.util.mapStockToProgress
import com.alexmls.circularstocktracker.ui.theme.AppColors
import com.alexmls.circularstocktracker.ui.theme.CircularStockTrackerTheme
import com.alexmls.circularstocktracker.ui.theme.titleLargeBold
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProductRoot(
    viewModel: ProductViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.event.collect { event ->
            when (event) {
                is ProductEvent.ShowToast ->
                    Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
    ProductScreen(
        state = state,
        onAction = viewModel::onAction
    )
}

@Composable
fun ProductScreen(
    state: ProductState,
    onAction: (ProductAction) -> Unit,
) {
    val product = state.product

    if (state.isLoading || product == null) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
        return
    }

    val isOutOfStock = state.isOutOfStock

    val productBgBrush = if (isOutOfStock) {
        Brush.linearGradient(
            listOf(
                MaterialTheme.colorScheme.outline,
                MaterialTheme.colorScheme.outline
            )
        )
    } else {
        AppColors.DiscountGradient
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val cardShape = RoundedCornerShape(16.dp)
        val shadowColor = Color(0xFF211304).copy(alpha = 0.06f)

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(
                    elevation = 20.dp,
                    shape = cardShape,
                    ambientColor = shadowColor,
                    spotColor = shadowColor
                ),
            shape = cardShape,
        ) {
            Column(
                modifier = Modifier
                    .background(
                        productBgBrush,
                        shape = cardShape
                    )
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(R.drawable.nike_pegasus),
                        contentDescription = null,
                        modifier = Modifier.size(400.dp),
                        contentScale = ContentScale.Fit
                    )
                }

                Surface(
                    color = MaterialTheme.colorScheme.surface,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.Top
                        ) {
                            Column(
                                modifier = Modifier.weight(1f)
                            ) {
                                Text(
                                    text = product.name,
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = MaterialTheme.colorScheme.onBackground
                                )

                                Text(
                                    text = product.description,
                                    style = MaterialTheme.typography.bodySmall.copy(
                                        color = MaterialTheme.colorScheme.secondaryContainer
                                    ),
                                    modifier = Modifier.padding(top = 4.dp)
                                )
                            }

                            Spacer(modifier = Modifier.width(20.dp))

                            Column(
                                verticalArrangement = Arrangement.Top,
                                horizontalAlignment = Alignment.End
                            ) {
                                Text(
                                    text = "$${product.discountedPrice.toInt()}",
                                    color = MaterialTheme.colorScheme.primary,
                                    style = MaterialTheme.typography.titleLargeBold
                                )
                                Text(
                                    text = "$${product.originalPrice.toInt()}",
                                    style = MaterialTheme.typography.bodyMedium.copy(
                                        textDecoration = TextDecoration.LineThrough,
                                        color = MaterialTheme.colorScheme.secondaryContainer
                                    )
                                )
                            }
                        }

                        ProductSizeSection(
                            modifier = Modifier.fillMaxWidth()
                        )

                        HorizontalDivider()

                        CircularStockIndicator(
                            progress = state.progress,
                            stock = state.stockLeft
                        )

                        val buttonEnabled = !isOutOfStock

                        Button(
                            onClick = { onAction(ProductAction.OnBuyClicked) },
                            enabled = buttonEnabled,
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (buttonEnabled)
                                    MaterialTheme.colorScheme.onBackground
                                else
                                    MaterialTheme.colorScheme.outline
                            ),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = if (isOutOfStock) "Out of stock" else "Buy",
                                color = MaterialTheme.colorScheme.surface,
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProductScreenPreview() {
    CircularStockTrackerTheme {
        val demoProduct = Product(
            name = "Nike Air Zoom Pegasus 41",
            description = "Legendary running shoes with Air Zoom technology and ReactX cushioning for daily training and marathons.",
            discountedPrice = 100.0,
            originalPrice = 160.0,
            stock = 12,
            imageUrl = ""
        )

        val demoStockLeft = 8
        val demoProgress = mapStockToProgress(
            stock = demoStockLeft,
            maxStock = demoProduct.stock // 12
        )

        val demoState = ProductState(
            isLoading = false,
            product = demoProduct,
            stockLeft = demoStockLeft,
            progress = demoProgress
        )

        ProductScreen(
            state = demoState,
            onAction = {}
        )
    }
}