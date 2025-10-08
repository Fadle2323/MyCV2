package com.filkom.mycv2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxSize
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.filkom.mycv2.screen.DaftarScreen
import com.filkom.mycv2.screen.DetailScreen
import com.filkom.mycv2.screen.LoginScreen
import com.filkom.mycv2.ui.theme.MyCV2Theme
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyCV2Theme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    AppNav()
                }
            }
        }
    }
}

object Routes {
    const val LOGIN = "login"
    const val DAFTAR = "daftar"
    const val DETAIL = "detail?nim={nim}&nama={nama}&email={email}&alamat={alamat}"
}

private fun enc(s: String): String =
    URLEncoder.encode(s, StandardCharsets.UTF_8.toString())

@Composable
fun AppNav() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.LOGIN) {
        composable(Routes.LOGIN) {
            LoginScreen(
                onLogin = { nim, nama ->
                    navController.navigate("detail?nim=${enc(nim)}&nama=${enc(nama)}&email=&alamat=")
                },
                onDaftar = { navController.navigate(Routes.DAFTAR) }
            )
        }
        composable(Routes.DAFTAR) {
            DaftarScreen(
                onSimpan = { nim, nama, email, alamat ->
                    navController.navigate(
                        "detail?nim=${enc(nim)}&nama=${enc(nama)}&email=${enc(email)}&alamat=${enc(alamat)}"
                    )
                }
            )
        }
        composable(
            route = Routes.DETAIL,
            arguments = listOf(
                navArgument("nim") { type = NavType.StringType; defaultValue = "" },
                navArgument("nama") { type = NavType.StringType; defaultValue = "" },
                navArgument("email") { type = NavType.StringType; defaultValue = "" },
                navArgument("alamat") { type = NavType.StringType; defaultValue = "" },
            )
        ) { backStack ->
            val nim = backStack.arguments?.getString("nim") ?: ""
            val nama = backStack.arguments?.getString("nama") ?: ""
            val email = backStack.arguments?.getString("email") ?: ""
            val alamat = backStack.arguments?.getString("alamat") ?: ""

            DetailScreen(
                nim = nim,
                nama = nama,
                email = email,
                alamat = alamat,
                onDaftar = { navController.navigate(Routes.DAFTAR) }
            )
        }
    }
}
