package com.example.led_clock

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.BoxWithConstraints
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

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun ResponsiveLedClock() {
    // 화면이 꺼지지 않도록 설정한다.
    val view = LocalView.current
    LaunchedEffect(Unit) {
        view.keepScreenOn = true
    }
    
    // 시간 데이터를 저장하고, 이 데이터가 바뀌면 화면이 자동으로 업데이트되도록 설정
    var currentTime by remember { mutableStateOf("--:--:--") }

    // 1초마다 현재 시간을 업데이트한다.
    LaunchedEffect(Unit) {
        while (true) {
            val sdf = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
            currentTime = sdf.format(Date())
            delay(1000) // 1초 대기
        }
    }

    // 화면 UI를 그리는 부분
    BoxWithConstraints(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        // LocalDensity.current를 사용하여 dp 단위를 sp 단위로 변환
        val density = LocalDensity.current

        // 사용 가능한 가로 길이(maxWidth)를 기반으로 폰트 크기를 계산
        // '4.5f'는 경험적인 값입니다. 숫자를 줄이면 글자가 더 커지고, 늘리면 작아집니다.
        // 시계 문자("HH:mm:ss")의 비율에 맞춰 적절히 조절한 값입니다.
        val fontSize = with(density) { (maxWidth / 4.1f).toSp() }

        Text(
            text = currentTime,
            color = Color(0xFF00FF00), // LED 느낌의 붉은색
            fontSize = fontSize, // 위에서 계산된 동적 폰트 크기 적용
            textAlign = TextAlign.Center,
            maxLines = 1 // 시계가 두 줄로 표시되지 않도록 강제
        )
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