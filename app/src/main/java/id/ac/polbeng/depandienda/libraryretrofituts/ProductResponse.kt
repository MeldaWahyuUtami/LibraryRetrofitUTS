package id.ac.polbeng.depandienda.libraryretrofituts

data class ProductResponse(
    val products: List<Product>,
    val total: Int,
    val skip: Int,
    val limit: Int
)
