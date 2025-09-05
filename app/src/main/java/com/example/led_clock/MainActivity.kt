package com.example.led_clock

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import com.example.led_clock.ui.theme.LedclockTheme
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LedclockTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Black
                ) {
                    ResponsiveLedClock()
                }
            }
        }
    }
}

/**
 * 하나의 문자(숫자 또는 콜론)를 애니메이션과 함께 표시하는 Composable
 */
@Composable
fun AnimatedDigit(
    digit: Char,
    fontSize: TextUnit,
    textColor: Color
) {
    AnimatedContent(
        targetState = digit,
        transitionSpec = {
            // 새 숫자가 아래에서 위로 슬라이드하며 나타나고,
            slideInVertically { height -> height } togetherWith
                    // 기존 숫자는 아래로 슬라이드하며 사라집니다. (카드가 넘어가는 효과)
                    slideOutVertically { height -> -height }
        },
        label = "anim_digit"
    ) { targetChar ->
        Text(
            text = targetChar.toString(),
            color = textColor,
            fontSize = fontSize,
            textAlign = TextAlign.Center,
            maxLines = 1
        )
    }
}


@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun ResponsiveLedClock() {
    // 화면이 꺼지지 않도록 설정한다.
    val view = LocalView.current
    LaunchedEffect(Unit) {
        view.keepScreenOn = true
    }

    // 시간 데이터를 저장하고, 이 데이터가 바뀌면 화면이 자동으로 업데이트되도록 설정
    var currentTime by remember { mutableStateOf("00:00") }

    // 1초마다 현재 시간을 업데이트한다. (HH:mm 포맷)
    LaunchedEffect(Unit) {
        // SimpleDateFormat은 반복 생성 시 리소스 낭비가 있으므로 한번만 생성
        val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())
        while (true) {
            currentTime = sdf.format(Date())
            // 정확한 시간 동기화를 위해 현재 시간의 초(second)를 계산하여 다음 분까지 남은 시간을 계산
            val nextMinuteDelay = 60_000L - (System.currentTimeMillis() % 60_000L)
            delay(nextMinuteDelay)
        }
    }

    // 화면 UI를 그리는 부분
    BoxWithConstraints(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        val density = LocalDensity.current
        val fontSize = with(density) { (maxWidth / 2.8f).toSp() }
        val textColor = Color(0xFFFFFFFF) // LED 색상

        // Text 하나 대신, Row를 사용하여 각 문자를 개별적으로 배치
        Row {
            // currentTime 문자열("HH:mm")의 각 문자에 대해 AnimatedDigit를 호출
            currentTime.forEach { char ->
                AnimatedDigit(
                    digit = char,
                    fontSize = fontSize,
                    textColor = textColor
                )
            }
        }
    }
}

/// 개발 중 미리보기를 위한 코드
@Preview(showBackground = true)
@Composable
fun LedClockPreview() {
    LedclockTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = Color.Black) {
            ResponsiveLedClock()
        }
    }
}