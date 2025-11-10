sealed interface ProductAction {
    data object OnBuyClicked : ProductAction
    data object LoadInitialData : ProductAction
}