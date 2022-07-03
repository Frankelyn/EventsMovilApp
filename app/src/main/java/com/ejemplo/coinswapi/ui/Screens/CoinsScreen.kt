package com.ejemplo.coinswapi.ui.Screens

import android.graphics.Paint
import android.widget.Toolbar
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontSynthesis.Companion.Style
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.ejemplo.coinswapi.ViewModels.CoinsViewModel
import com.ejemplo.coinswapi.data.remote.Dto.CoinDto
import com.ejemplo.coinswapi.ui.Navigation.ScreenRoutes
import org.intellij.lang.annotations.JdkConstants



@Composable
fun CoinsScreen(
    navHostController: NavHostController,
    viewModel: CoinsViewModel = hiltViewModel()
) {

    @Composable
    fun Toolbar() {
        TopAppBar() {

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            ) {
                Text(
                    text = "Consulta de Coins",
                    style = MaterialTheme.typography.h5,
                    fontStyle = FontStyle.Italic
                )
                IconButton(onClick = { navHostController.navigate(ScreenRoutes.PostCoinScreen.ruta) }) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = null)
                }
            }

        }
    }

    val state = viewModel.state.value

    Scaffold(
        topBar = { Toolbar() }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(8.dp)
        ) {

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.coins) { Coin ->
                    CoinItem(coin = Coin, {})
                }
            }

            if (state.isLoading)
                CircularProgressIndicator()

        }
    }
}


@Composable
fun CoinItem(
    coin: CoinDto,
    onClick: (CoinDto) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .clickable { onClick(coin) },
        elevation = 8.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            AsyncImage(
                model = coin.imageUrl,
                contentDescription = "Image",

                modifier = Modifier.size(90.dp)
            )

            Column(
                modifier = Modifier.padding(8.dp),
                horizontalAlignment = Alignment.End
            ) {
                Text(text = coin.descripcion)
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "USD " + coin.valor.toString())
            }


        }
    }
}