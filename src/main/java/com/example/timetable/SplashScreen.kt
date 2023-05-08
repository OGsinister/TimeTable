package com.example.timetable

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.rememberImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import coil.size.Size
import com.example.timetable.ui.theme.LoadingScreenBackground
import com.example.timetable.ui.theme.TimeTableTheme
import kotlinx.coroutines.*

@SuppressLint("CustomSplashScreen")
class SplashScreen : ComponentActivity() {
    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TimeTableTheme {
                val context = LocalContext.current
                AnimatedSplashScreen(context)
            }
        }
    }
}

@Composable
fun AnimatedSplashScreen(context: Context){
    var startAnimation by remember { mutableStateOf(false) }
    val activity = (LocalContext.current as? Activity)

    val intent = Intent(context, MainActivity::class.java)

    val alphaAnim = animateFloatAsState(
        targetValue = if(startAnimation) 1f else 0f,
        animationSpec = tween(
            durationMillis = 3000
        )
    )
    LaunchedEffect(key1 = true){
        launch(Dispatchers.Main){
            startAnimation = true
            delay(3000L)
            context.startActivity(intent)
            activity?.finish() // Завершаем Splash screen activity, чтобы пользователь не смог его открыть
        }
    }
    Splash(alpha = alphaAnim.value)
}

@Composable
fun Splash(alpha: Float){
    Box(
        modifier = Modifier
            .background(LoadingScreenBackground)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Image(
            painter = painterResource(id = R.drawable.start_image),
            contentDescription = null,
            modifier = Modifier
                .size(150.dp)
                .alpha(alpha = alpha)
        )
        //GifImage(modifier = Modifier)
        /*Icon(
            modifier = Modifier
                .size(120.dp)
                .alpha(alpha = alpha),
            imageVector = Icons.Default.Check,
            contentDescription = "Logo Icon",
            tint = Color.White
        )*/
    }
}

/*@Composable
fun GifImage(modifier: Modifier){
    val context = LocalContext.current

    val imageLoader = ImageLoader.Builder(context)
        .components {
            add(ImageDecoderDecoder.Factory())
        }
        .build()
    Image(
        painter = rememberImagePainter(
            ImageRequest.Builder(context).data(data = R.drawable.learn_animation).apply(block = {
                size(Size.ORIGINAL)
            }).build(),
            imageLoader = imageLoader
        ),
        contentDescription = null,
        modifier = modifier.fillMaxWidth(),
    )
}*/
