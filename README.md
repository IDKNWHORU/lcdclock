# Android LED Desk Clock

A simple, full-screen LED-style digital desk clock application for Android, built with Kotlin and Jetpack Compose. This app turns your Android device into a retro-style alarm clock, perfect for your desk or nightstand.

The text size is dynamically adjusted to fill the screen, ensuring it looks great on any device size, from small phones to large tablets.

## Features

-   **Full-Screen Display**: The clock takes over the entire screen for maximum visibility without distractions from the status or navigation bars.
-   **Dynamic Text Sizing**: The clock digits automatically resize to fit the width of any screen, ensuring a "꽉 찬 화면" (full-screen) experience.
-   **Always-On Screen**: The screen is kept on by default, so the clock is always visible.
-   **Customizable LED Font**: Easily change the font to any 7-segment or digital-style font you prefer.
-   **Landscape Mode**: Optimized for horizontal (landscape) orientation, typical for desk clocks.
-   **Modern UI Toolkit**: Built with 100% Kotlin and Jetpack Compose, Google's modern toolkit for building native Android UI.

## Screenshot

*(Add a screenshot of your running application here for a better visual representation.)*

![lcd clock](https://github.com/user-attachments/assets/6dee5a35-ede3-4035-a52f-6e47cff63550)

## Setup and Installation

You can build and run this project using Android Studio.

### Prerequisites

-   Android Studio (latest version recommended)
-   An Android device or emulator

### Steps

1.  **Clone the repository:**
    ```bash
    git clone https://github.com/IDKNWHORU/lcdclock.git
    ```

2.  **Open in Android Studio:**
    -   Open Android Studio.
    -   Click on `File` -> `Open`.
    -   Navigate to the cloned project directory and select it.

3.  **Sync Gradle:**
    -   Let Android Studio sync the project and download the required dependencies.

4.  **Run the App:**
    -   Select your target device (emulator or a physical device).
    -   Click the `Run 'app'` button (the green play icon).

## Customization: How to Change the Font

You can easily customize the look of the clock by using your own digital-style font.

1.  **Find a Font:**
    -   Download a digital, 7-segment, or LED-style font. A good source for free fonts is [Google Fonts](https://fonts.google.com/) or [dafont.com](https://www.dafont.com/theme.php?cat=302).
    -   The font file should be in `.ttf` or `.otf` format.

2.  **Add Font to Project:**
    -   In Android Studio's project view, navigate to the `app/src/main/res` directory.
    -   Right-click the `res` folder and select `New` -> `Android Resource Directory`.
    -   Set the `Resource type` to **font** and click `OK`.
    -   Copy your downloaded font file (e.g., `my_cool_font.ttf`) into the newly created `res/font` directory. **Note:** The filename must be lowercase and use only letters, numbers, or underscores.

3.  **Update the Code:**
    -   Open the `MainActivity.kt` file.
    -   Find the `ResponsiveLedClock` composable function.
    -   Locate the `Text` component and change the `fontFamily` attribute:

    ```kotlin
    // ... inside ResponsiveLedClock()

    Text(
        text = currentTime,
        // ... other properties
        fontFamily = FontFamily(Font(R.font.your_new_font_file_name)) // Change this line
    )
    ```
    -   Replace `your_new_font_file_name` with the name of your font file (without the `.ttf` extension).

## Technology Stack

-   **Language**: [Kotlin](https://kotlinlang.org/)
-   **UI Toolkit**: [Jetpack Compose](https://developer.android.com/jetpack/compose)
-   **Asynchronicity**: [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html)

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details.