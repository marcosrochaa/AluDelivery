import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.aludelivery.ui.theme.AluDeliveryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AluDeliveryTheme {
                Surface {
                    MyFirstComposable()
                }
            }
        }
    }

    @Preview(showBackground = true)
@Composable
fun ColumnPreview () {
    Column {
        Text(text = "Texto Da colum")
        Text(text = "Texto Da colum")
        Text(text = "Texto Da colum")
    }
}

@Preview(showBackground = true)
@Composable
fun RowPreview(){
    Row{
        Text(text = "Text 1")
        Text(text = "Text 2")
        Text(text = "Text 3")
    }
}

@Preview(showBackground = true)
@Composable
fun BoxPreview () {
    Box {
        Text(text = "Box 1")
        Text(text = "Box 2")
        Text(text = "Box 3")
        Text(text = "Box 4")
    }

}
@Composable
fun MyFirstComposable() {
    Text(text = "oi")
    Text(text = "aaaaaaaaaaaaaaaaaaaaa")
}

@Preview(
    name = "NewSystem",
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)

@Preview(
    name = "NEXTNewSystem",
    showSystemUi = true,
)


@Preview(
    name = "TextPreview",
    heightDp = 200,
    widthDp = 200,
    showBackground = true,
    backgroundColor = 0xFFFF1144
)

@Composable
fun MyfirstComposablePreview() {
    AluDeliveryTheme {
        Surface () {
            MyFirstComposable()
        }
    }

}
}