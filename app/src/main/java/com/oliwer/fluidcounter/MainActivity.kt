package com.oliwer.fluidcounter

import android.icu.util.Calendar
import android.os.Bundle
import android.text.format.DateUtils
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.oliwer.fluidcounter.ui.theme.FluidCounterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FluidCounterTheme {
                MyApp()
            }
        }
    }
}

@Preview
@Composable
fun MyAppPreview() {
    FluidCounterTheme {
        MyApp(Modifier.fillMaxSize())
    }
}

@Composable
fun MyApp (modifier: Modifier = Modifier) {
    /*
    var timeOfActivation by rememberSaveable {
        mutableStateOf(Calendar.getInstance().getTime())
    }
    */
    var fluidCount by rememberSaveable {
        mutableStateOf(0)
    }

    /*
    if(!DateUtils.isToday(timeOfActivation.time)){
        timeOfActivation = Calendar.getInstance().getTime()
        fluidCount = 0
    }
    */

    Surface(modifier) {
        Column {
            Spacer(modifier = Modifier.height(30.dp))
            Row {
                FluidButtons(
                    onAddedFluidClick = { fluidAmount -> fluidCount += fluidAmount },
                    onResetClick = { fluidCount = 0 }
                )
                FluidBar(fluidCount)
                FluidMeasures()
            }
        }
    }

}

@Composable
fun FluidButtons(onAddedFluidClick: (Int) -> Unit, onResetClick: () -> Unit, modifier: Modifier = Modifier) {
    val fluidAmounts = listOf(10, 100, 200, 330)

    Column {
        LazyColumn (
            modifier = modifier.padding(10.dp)
        ) {
            items(items = fluidAmounts) {fluidAmount ->
                FluidButton(
                    onAddedFluidClick = onAddedFluidClick,
                    fluidAmount = fluidAmount
                )
            }
        }
        Spacer(modifier = Modifier.height(250.dp))
        Button(
            modifier = Modifier
                .padding(10.dp)
                .width(200.dp)
                .height(100.dp),
            onClick = onResetClick
        ) {
            Text(text = "Reset")
        }
    }

}

@Composable
fun FluidButton(onAddedFluidClick: (Int) -> Unit, fluidAmount: Int, modifier: Modifier = Modifier) {
    Button(
        modifier = Modifier
            .padding(vertical = 4.dp)
            .width(200.dp)
            .height(100.dp),
        onClick = {
            onAddedFluidClick(fluidAmount)
        }
    ) {
        Text(text = "$fluidAmount ml")
    }
}

@Composable
fun FluidBar(fluidCount: Int, modifier: Modifier = Modifier) {
    val normalized = (2000f - fluidCount) / 2000f

    Surface(
        color = Color.Black,
        modifier = Modifier
            .width(100.dp)
            .padding(top = 10.dp, bottom = 5.dp)
    ) {
        Column (
            modifier = modifier
        ) {
            //830 is the bar height
            Surface (
                color = Color.White,
                modifier = Modifier
                    .fillMaxWidth()
                    .height((normalized * 800).dp)
                    .padding(top = 5.dp, start = 5.dp, end = 5.dp)
            ) {

            }
            Surface (
                color = Color.Blue,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(((1 - normalized) * 800).dp)
                    .padding(bottom = 5.dp, start = 5.dp, end = 5.dp)
            ) {

            }

        }
    }
}

@Composable
fun FluidMeasures(modifier: Modifier = Modifier) {
    Column (
        modifier = modifier
            .fillMaxSize()
            .padding(start = 5.dp)
    ) {
        Text(
            fontSize = 25.sp,
            text = "- 2L"
        )
        Spacer(modifier = Modifier
            .height(167.5.dp)
        )
        Text(
            fontSize = 25.sp,
            text = "- 1.5L"
        )
        Spacer(modifier = Modifier
            .height(167.5.dp)
        )
        Text(
            fontSize = 25.sp,
            text = "- 1L"
        )
        Spacer(modifier = Modifier
            .height(167.5.dp)
        )
        Text(
            fontSize = 25.sp,
            text = "- .5L"
        )
        Spacer(modifier = Modifier
            .height(167.5.dp)
        )
        Text(
            fontSize = 25.sp,
            text = "- .0L"
        )
    }
}