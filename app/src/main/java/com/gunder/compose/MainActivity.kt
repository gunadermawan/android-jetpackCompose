package com.gunder.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.gunder.compose.data.User
import com.gunder.compose.ui.theme.ComposeUiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeUiTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    PreviewMessage()
                }
            }
        }
    }
}

@Composable
fun MessageCard(user: User) {
    Row(modifier = Modifier.padding(12.dp)) {
        Image(
            painter = painterResource(R.drawable.ic_android),
            contentDescription = "android logos"
        )
    }
    Column(modifier = Modifier.padding(12.dp)) {
        Text(text = user.name)
        Text(text = user.profession)
    }
}

@Composable
fun PreviewMessage() {
    MessageCard(user = User("Guna Dermawan", "Programmer"))
}

