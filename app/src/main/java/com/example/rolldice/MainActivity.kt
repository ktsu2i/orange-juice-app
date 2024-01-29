package com.example.rolldice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.rolldice.ui.theme.RollDiceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RollDiceTheme {
                RollDiceWithButtonAndImage()
            }
        }
    }
}

@Composable
fun RollDiceWithButtonAndImage(
    modifier: Modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(align = Alignment.Center)
)
{
    var gameCount: Int = 0

    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally)
    {
        var status by remember { mutableStateOf(1) }
        var diceImageID = when (status) {
            1 -> R.drawable.orange_tree
            2 -> R.drawable.orange_fruit
            3 -> R.drawable.orange_drink_full
            else -> R.drawable.orange_drink_empty
        }

        var textID = when (status) {
            1 -> R.string.tapOrangeTreeText
            2 -> R.string.tapOrangeText
            3 -> R.string.tapOrangeJuiceText
            else -> R.string.tapEmptyGlassText
        }

        var requiredNumOfTaps: Int = (5..10).random()
        var tapCount = 0

        Button(onClick = {
            if (status == 1 && status == 3)
            {
                status++
            }
            else if (status == 2)
            {
                tapCount++

                if (tapCount == requiredNumOfTaps)
                {
                    status++
                }
            }
            else
            {
                status = 0
                tapCount = 0
                requiredNumOfTaps = (5..10).random()
                gameCount++
            }
        }) {
            Image(
                painter = painterResource(id = diceImageID),
                contentDescription = "dice"
            )
        }
        
        Spacer(modifier = Modifier.height(20.dp))
        
        Text(text = stringResource(id = textID))

        Spacer(modifier = Modifier.height(50.dp))

        Text("Game count: $gameCount")
    }
}

@Preview
@Composable
fun RollDiceWithButtonAndImagePreview()
{
    RollDiceWithButtonAndImage()
}