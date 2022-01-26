package com.example.composebasicslab

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composebasicslab.ui.theme.ComposeBasicsLabTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeBasicsLabTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    MyApp()
                }
            }
        }
    }
}

@Composable
fun MyApp() {
    var showOnboarding by rememberSaveable {
        mutableStateOf(true)
    }

    if (showOnboarding) {
        OnboardingScreen(onContinueClicked = {
            showOnboarding = false
        })
    } else {
        Greetings()
    }
}


@Composable
private fun Greetings(names: List<String> = List(100) { "$it" }) {
    LazyColumn(modifier = Modifier.padding(vertical = 4.dp)) {
        items(items = names) { name ->
            Greeting(name = name)
        }
    }
}

@Composable
fun Greeting(name: String) {
    Card(
        backgroundColor = MaterialTheme.colors.primary,
        modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
    ) {
        CardContent(name = name)
    }
}

@Composable
fun CardContent(name: String) {
    val expanded = remember {
        mutableStateOf(false)
    }

//    val expandedPadding by animateDpAsState(
//        if (expanded.value) 36.dp else 0.dp,
//        animationSpec = spring(
//            dampingRatio = Spring.DampingRatioMediumBouncy,
//            stiffness = Spring.StiffnessLow
//        )
//    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            ),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
                .weight(1.0f)
                .padding(8.dp)
        )
        {
            Text(text = "Hello, ")
            Text(text = name)
            if (expanded.value) {
                Text(
                    text = "It's really gonna be a fun journey with Jetpack Compose. " +
                            "So far, so good."
                )
            }
        }

//        OutlinedButton(onClick = { expanded.value = !expanded.value }) {
//            Text(if (expanded.value) stringResource(R.string.show_less) else stringResource(R.string.show_more))
//        }
        IconButton(onClick = { expanded.value = !expanded.value }) {
            Icon(
                imageVector = if (expanded.value) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                contentDescription = if (expanded.value) stringResource(R.string.show_less) else stringResource(
                    R.string.show_more
                )
            )
        }
    }

}


@Preview(
    showBackground = true,
    widthDp = 320,
    uiMode = UI_MODE_NIGHT_YES,
    name = "DefaultPreviewDark"
)
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeBasicsLabTheme {
        MyApp()
    }
}

@Composable
fun OnboardingScreen(onContinueClicked: () -> Unit) {

    Surface {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Welcome to the App",
                color = Color.White)
            Button(
                onClick = onContinueClicked,
                modifier = Modifier.padding(vertical = 24.dp)
            ) {
                Text(text = "Continue")
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 300, heightDp = 300)
@Composable
fun OnboardingPreview() {
    ComposeBasicsLabTheme {
        OnboardingScreen(onContinueClicked = {})
    }
}