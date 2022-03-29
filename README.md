# crypto_info

CryptoInfo is a cryptocurrency app that shows informations about currency
Application built with [Jetpack Compose](https://developer.android.com/jetpack/compose).
The goal of this application is to showcase Material components, Android Views
inside Compose, and UI state handling, [MVVM clean arcitecture](https://www.toptal.com/android/android-apps-mvvm-with-clean-architecture).

## Screenshots

<img src="screenshots/cryptoinfo.gif" width="200" />

## Features

This application contains 3 screens:
- __Splash__ [screen][splash] that fades out after 4 seconds then slides the main content in from
the bottom of the screen.
- __CoinsList__ [screen][coins_list] where you can explore coins list, the rank of the currency, symbol, if the currency is active or not.
 - Clicking on item will lead you to the coin detail screen.
- __CoinDetail__ [screen][coin_detail] cointain the details of a specific coin with a description, some tags and the creators 

Crypto Info is a multi-activity app that showcases how navigating between activities can be done in
Jetpack Compose.

## Hilt

Crypto Indo uses Hilt to manage its dependencies. Hilt's ViewModel (with the
`@HiltViewModel` annotation) works perfectly with Compose's ViewModel integration (`viewModel()`
composable function) as you can see in the following snippet of code. `viewModel()` will
automatically use the factory that Hilt creates for the ViewModel:

```
@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase
) : ViewModel() {}

@Composable
fun CoinListScreen(
    navController: NavController,
    viewModel: CoinListViewModel = hiltViewModel()
) {
}
```

### Status: 🚧 In progress

Crypto Info is still in under development, and some features are not yet implemented.


[splash]: app/src/main/java/com/ldcoding/cryptocurrencyapp/presentation/SplashScreen.kt
[coins_list]: app/src/main/java/com/ldcoding/cryptocurrencyapp/presentation/coin_list/CoinListScreen.kt
[coin_detail]: app/src/main/java/com/ldcoding/cryptocurrencyapp/presentation/coin_detail/CoinDetailScreen.kt
