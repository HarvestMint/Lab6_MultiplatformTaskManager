package dev.mintyverse.lab6

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        Column(modifier = Modifier.padding(16.dp)) {

            Text(
                text = "Task Manager",
                style = TextStyle(
                    fontSize = 30.sp,
                    color = Color(67,36,28)
                ),

            )
            TaskInputField()
            TaskList()
        }
    }
}

var tasks = mutableStateListOf<String>()

@Composable
fun TaskInputField(){
    var text by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            value = text,
            onValueChange = {text = it},
            modifier = Modifier
                .padding(end = 8.dp)
                .weight(0.75f),
            label = {Text("Enter Task")},
            supportingText = { Text(errorMessage)}


        )
        FilledTonalButton( //Button code to kick off the action to transfer the user-entered value to functions.
            onClick = { //Code for handling when button clicked while textfield is empty.
                if(text != "") tasks.add(text) else errorMessage = "Please enter a task."
            },
            colors = ButtonDefaults.filledTonalButtonColors(
                containerColor = Color(84, 34, 42) //Dr Pepper colored button
            ),
            modifier = Modifier //Adds padding and helps text location on the button face.
                .padding(start = 8.dp)
                .weight(0.25f),
            contentPadding = PaddingValues(horizontal = 20.dp)
        ) {
            Text( //Adds text to the button and controls what the text looks like.
                text = "Add Task",
                style = TextStyle(fontSize = 18.sp)
            )
        }
    }
}

@Composable
fun TaskList(modifier: Modifier = Modifier){
//Lazy column to automatically adjust to list size no matter how many tasks are added by user.
    LazyColumn(modifier = modifier) {
        items(tasks.size){ index ->
            TaskItem(
                task = tasks[index],
                onTaskDeleted = { deletedTask -> tasks.remove(deletedTask) },

                )
            Spacer(Modifier.height(8.dp))

        }
    }


}
@Composable //Takes in user task input and controls how the task is displayed
fun TaskItem(task: String, onTaskDeleted: (String) -> Unit){
    var checked by remember { mutableStateOf(false) }
//Creates a checkbox that will strikeout the text of the task when checked
    Row {
        Checkbox(
            checked = checked,
            onCheckedChange = { checked = it }
        )
        Text(
            text = task,
            style = TextStyle(
                textDecoration = if (checked) TextDecoration.LineThrough  else TextDecoration.None,
                color = if(checked) Color(136,127,133) else Color(0,0,0) //Text greys out as well when strikethrough is applied
            )

        )
        IconButton( //Button allows for the deletion of task if so desired.
            onClick = {onTaskDeleted(task)}) {
            Icon(
                Icons.Filled.Delete,
                contentDescription = "Delete Task"
            )
        }

    }
}




