package com.ejemplo.coinswapi.ui.Screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.ejemplo.coinswapi.ViewModels.CoinsViewModel
import com.ejemplo.coinswapi.data.remote.Dto.CoinDto



@Composable
fun HolaMundo(){
    Text(text = "Hola Mundo")
}

@Composable
fun CoinsScreen(viewModel: CoinsViewModel = hiltViewModel())
{
    val state = viewModel.state.value

    Column(modifier = Modifier.fillMaxSize()) {

        Text(text = "Coins")
        
        LazyColumn(modifier = Modifier.fillMaxSize()){
            items( state.coins){ coin ->
                CoinItem(coin = coin, {})
            }
        }

        if (state.isLoading)
            CircularProgressIndicator()

    }
}


@Composable
fun CoinItem(
    coin:CoinDto,
    onClick : (CoinDto) -> Unit
)
{
    Card(
        modifier = Modifier.clickable { onClick(coin) },
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
                    model = coin.ImageUrl,
                    contentDescription = null
                )

                Column() {
                    Text(text = coin.Descripcion.toString())
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = "USD " + coin.Valor.toString())
                }
            
            
        }
    }
}