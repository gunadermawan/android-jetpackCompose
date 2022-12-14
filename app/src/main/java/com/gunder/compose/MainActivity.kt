package com.gunder.compose

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gunder.compose.data.SampleData
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
                    PreviewConversation()
                }
            }
        }
    }
}

@Composable
fun MessageCard(user: User) {
//    to track variable using remember (memory) and mutableStateOf(observable)
    var isExpanded by remember {
        mutableStateOf(false)
    }
//    change color when expanded is active
    val surfaceColor by animateColorAsState(
        if (isExpanded) MaterialTheme.colors.primary else MaterialTheme.colors.surface
    )
    Row(modifier = Modifier.padding(12.dp)) {
        Image(
            painter = painterResource(R.drawable.ic_android),
            contentDescription = "android logos",
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .border(1.5.dp, MaterialTheme.colors.error, CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) {
            Text(
                text = user.name,
                color = MaterialTheme.colors.secondaryVariant,
                style = MaterialTheme.typography.subtitle2
            )
            Spacer(modifier = Modifier.width(4.dp))
            Surface(
                shape = MaterialTheme.shapes.medium,
                color = surfaceColor,
                modifier = Modifier
                    .animateContentSize()
                    .padding(1.dp),
                elevation = 1.dp
            ) {
                Text(
                    text = user.message,
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier.padding(all = 4.dp),
//                 if expanded, showing all its content
//                otherwise only display the first line
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1
                )
            }
        }
    }
}

@Preview(name = "light mode")
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true, name = "dark mode")
@Composable
fun PreviewMessage() {
    Surface() {
        MessageCard(user = User("Guna Dermawan", "Programmer"))
    }
}

@Composable
fun Conversation(messages: List<User>) {
    LazyColumn() {
        messages.map { item { MessageCard(it) } }
    }
}

@Composable
@Preview
fun PreviewConversation() {
    ComposeUiTheme() {
        Conversation(SampleData.conversationSample)
    }
}

