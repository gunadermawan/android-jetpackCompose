package com.gunder.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
            contentDescription = "android logos",
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = user.name)
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = user.profession)
        }
    }
}

@Composable
fun PreviewMessage() {
    MessageCard(user = User("Guna Dermawan", "Programmer"))
}

