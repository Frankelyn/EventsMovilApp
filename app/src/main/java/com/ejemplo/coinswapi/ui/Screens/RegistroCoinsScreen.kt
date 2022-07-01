package com.ejemplo.coinswapi.ui.Screens

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun Toolbar2() {
    TopAppBar(title = { Text(text = "Registro de Coins") })
}

@Preview(showBackground = true)
@Composable
fun RegistroCoinScreen(){

   /* var descripcionError by remember { mutableStateOf(false) }
    var valorError by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val scaffoldState = rememberScaffoldState()

    var Texto: String = " "
    Scaffold(
        topBar = { Toolbar2() }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(8.dp)
        ) {
            OutlinedTextField(
               // value = ,
               // onValueChange = { viewModel.txDeudor = it
                   // deudorError = false},
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = null
                    )
                },
                isError = descripcionError
            )

           // OutlinedTextField(value = Texto, onValueChange = Texto )

        }
    }*/
}


@Composable
fun validar(error: Boolean){
    val assistiveElementText = if(error) "Error: Obligatorio" else "*obligatorio"
    val assistiveElementColor = if(error) {
        MaterialTheme.colors.error
    }else{
        MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.medium)
    }

    Text(
        text = assistiveElementText,
        color = assistiveElementColor,
        style = MaterialTheme.typography.caption,
        modifier = Modifier.padding(start = 16.dp)
    )
}