package com.filkom.mycv2.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DetailScreen(
    nim: String,
    nama: String,
    email: String,
    alamat: String,
    onDaftar: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Text(text = "Detail", fontSize = 22.sp, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
        Spacer(Modifier.height(16.dp))
        Text(text = "NIM: $nim", fontSize = 16.sp, modifier = Modifier.padding(vertical = 6.dp))
        Text(text = "Nama: $nama", fontSize = 16.sp, modifier = Modifier.padding(vertical = 6.dp))
        Text(text = "Email: $email", fontSize = 16.sp, modifier = Modifier.padding(vertical = 6.dp))
        Text(text = "Alamat: $alamat", fontSize = 16.sp, modifier = Modifier.padding(vertical = 6.dp))

        Spacer(Modifier.height(20.dp))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally),
            onClick = onDaftar
        ) { Text("DAFTAR → Form Daftar") }
    }
}
