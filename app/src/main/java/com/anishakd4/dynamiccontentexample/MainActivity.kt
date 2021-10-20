package com.anishakd4.dynamiccontentexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import com.anishakd4.dynamiccontentexample.ui.theme.DynamicContentExampleTheme

//val namesList: List<String> = listOf(
//    "shubham agarwal",
//    "Himanshu gupta",
//    "Surender yadav",
//    "Tathagat",
//    "Anmol jha",
//    "Sudeep",
//    "Bullu",
//    "Bharadwaj",
//    "Piyush",
//    "Yashpal sir",
//    "Sachin"
//)

val namesList: ArrayList<String> = arrayListOf(
    "shubham agarwal",
    "Himanshu gupta",
    "Surender yadav",
    "Tathagat",
    "Anmol jha",
    "Sudeep",
    "Bullu",
    "Bharadwaj",
    "Piyush",
    "Yashpal sir",
    "Sachin"
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            DynamicContentExampleTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(color = MaterialTheme.colors.background) {
//                    Greeting("Android")
//                }
//            }
            MainScreen4()
        }
    }
}

@Composable
fun GreetingList(names: List<String>) {
    for (name in names) {
        Greeting(name = name)
    }
}

@Composable
fun GreetingList2(names: List<String>) {
    Column {
        for (name in names) {
            Greeting(name = name)
        }
    }
}

@Composable
fun GreetingList3(names: List<String>) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        for (name in names) {
            Greeting(name = name)
        }

        Button(onClick = { namesList.add("New Name") }) {
            Text(text = "Add New Name")
        }
    }
}

@Composable
fun MainScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        GreetingLis()
    }
}

@Composable
fun GreetingLis() {
    for (name in namesList) {
        Greeting(name = name)
    }

    Button(onClick = { namesList.add("New Name") }) {
        Text(text = "Add New Name")
    }
}

@Composable
fun MainScreen2() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        GreetingLis2()
    }
}

@Composable
fun GreetingLis2() {
    val greetingListState = remember {
        mutableStateListOf<String>("shubham", "Himanshu", "surender", "tathagat")
    }
    for (name in greetingListState) {
        Greeting(name = name)
    }

    Button(onClick = { greetingListState.add("New Name") }) {
        Text(text = "Add New Name")
    }
}

@Composable
fun MainScreen3() {
    val greetingListState = remember {
        mutableStateListOf<String>("shubham", "Himanshu", "surender", "tathagat")
    }
    val newNameStateContent = remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        GreetingLis3(
            greetingListState,
            { greetingListState.add(newNameStateContent.value) },
            newNameStateContent.value,
            { newName -> newNameStateContent.value = newName }
        )
    }
}

@Composable
fun GreetingLis3(
    namesList: List<String>,
    buttonClick: () -> Unit,
    textFieldValue: String,
    textFieldUpdate: (newName: String) -> Unit
) {
    for (name in namesList) {
        Greeting(name = name)
    }

    TextField(value = textFieldValue, onValueChange = textFieldUpdate)

    Button(onClick = buttonClick) {
        Text(text = "Add New Name")
    }
}

@Composable
fun MainScreen4(viewModel: MainViewModel = MainViewModel()) {
    val newNameStateContent = viewModel.textFieldState.observeAsState("")
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        GreetingMessage(
            newNameStateContent.value,
            { newName -> viewModel.onTextChanged(newName) }
        )
    }
}

@Composable
fun GreetingMessage(
    textFieldValue: String,
    textFieldUpdate: (newName: String) -> Unit
) {

    TextField(value = textFieldValue, onValueChange = textFieldUpdate)

    Button(onClick = {}) {
        Text(text = "Add New Name")
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!", style = MaterialTheme.typography.h5)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
//    DynamicContentExampleTheme {
//        Greeting("Android")
//    }
    MainScreen4()
}