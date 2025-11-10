package com.alexmls.minichallenges.home.presentation.components


import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.draw.innerShadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.alexmls.minichallenges.R

@Composable
fun MiniChallengeCard(
    title: String,
    @DrawableRes imageRes: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val cardShape = RoundedCornerShape(20.dp)
    val imagePainter = painterResource(id = imageRes)

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .width(100.dp)
                .height(130.dp)
                // внешние тени
                .dropShadow(
                    shape = cardShape,
                    shadow = Shadow(
                        radius = 28.dp,
                        spread = 2.dp,
                        color = Color(0x33000000),
                        offset = DpOffset(x = 0.dp, y = 12.dp)
                    )
                )
                .dropShadow(
                    shape = cardShape,
                    shadow = Shadow(
                        radius = 12.dp,
                        spread = 0.dp,
                        color = Color(0x33000000),
                        offset = DpOffset(x = 0.dp, y = 5.dp)
                    )
                )
                // тело карточки
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFFFFFFFF),
                            Color(0xFFE7EAF4)
                        )
                    ),
                    shape = cardShape
                )
                .border(
                    width = 1.dp,
                    color = Color.White.copy(alpha = 0.9f),
                    shape = cardShape
                )
                .innerShadow(
                    shape = cardShape,
                    shadow = Shadow(
                        radius = 10.dp,
                        spread = 3.dp,
                        color = Color(0x22000000),
                        offset = DpOffset(x = 3.dp, y = 4.dp)
                    )
                )
                .innerShadow(
                    shape = cardShape,
                    shadow = Shadow(
                        radius = 8.dp,
                        spread = 5.dp,
                        color = Color(0x26FFFFFF),
                        offset = DpOffset(x = (-3).dp, y = (-3).dp)
                    )
                )
                .clip(cardShape)
                .clickable(onClick = onClick)
        ) {
            // 1) Фон: та же картинка, увеличенная и размытая
            Image(
                painter = imagePainter,
                contentDescription = null,
                modifier = Modifier
                    .matchParentSize()
                    .graphicsLayer(
                        scaleX = 1.25f,
                        scaleY = 1.25f,
                        translationX = -6f,
                        translationY = -4f
                    )
                    .blur(8.dp)                     // размытие
                    .alpha(0.9f),                    // чуть приглушить
                contentScale = ContentScale.Crop
            )

            // 2) «стеклянное» окно с нормальной картинкой
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
                    .clip(cardShape)
                    .background(Color.White.copy(alpha = 0.1f))  // лёгкий матовый слой
            ) {
                Image(
                    painter = imagePainter,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                        .border(
                            width = 1.dp,
                            color = Color.White.copy(alpha = 0.6f),
                            shape = cardShape),
                    contentScale = ContentScale.Crop
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium,
            color = Color(0xFF202124),
            textAlign = TextAlign.Center,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.width(100.dp)
        )
    }
}



@Preview(
    showBackground = true,
    backgroundColor = 0xFFF5F5F5,
    name = "Mini Challenge Card – Image as Card"
)
@Composable
fun MiniChallengeCardPreview() {
    MaterialTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF5F5F5)),
            contentAlignment = Alignment.Center
        ) {
            MiniChallengeCard(
                title = "Circular Counter Indicator",
                imageRes = R.drawable.circular_indicator, // твой скрин
                onClick = {}
            )
        }
    }
}
