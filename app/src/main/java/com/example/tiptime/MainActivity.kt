package com.example.tiptime

import android.icu.text.AlphabeticIndex.Bucket.LabelType
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tiptime.ui.theme.TipTimeTheme
import java.text.NumberFormat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TipTimeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    TipTimeScreen()
                }
            }
        }
    }
}

// Hello this code is written by Mir Niyazul Haque
@Composable

fun TipTimeScreen(){
    var amountInput by remember { mutableStateOf("") }
    val amount = amountInput.toDoubleOrNull()?:0.0
    val tip = calculateTip(amount)
Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp))
{
    Text(text = stringResource(id = R.string.calculate_tip), fontSize = 24.sp,
    modifier = Modifier.align(Alignment.CenterHorizontally))
    Spacer(modifier = Modifier.height(20.dp))
    EditNumberField(value=amountInput, onValueChange = {amountInput=it})
    Spacer(modifier = Modifier.height(20.dp))
    Text(text= stringResource(id = R.string.tip_amount,tip),
        modifier = Modifier.align(Alignment.CenterHorizontally),
    fontSize = (20.sp ),
    fontWeight = FontWeight.Bold
    )
}

    // Another Function
}
@Composable

fun EditNumberField(value:String,onValueChange:(String)->Unit){

    TextField(value = value,
        onValueChange = onValueChange,
        label ={ Text(text = stringResource(id = R.string.bill_amount),
            modifier = Modifier.fillMaxWidth())},
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        singleLine = true
    )
}
private fun calculateTip(amount:Double,tipPercent:Double=15.0):String{
    val tip=tipPercent/100*amount
    return NumberFormat.getCurrencyInstance().format(tip)
}


// PREVIEW
@Preview(showBackground = true)
@Composable

fun DefaultPreview() {
    TipTimeTheme {
        TipTimeScreen()
    }
}