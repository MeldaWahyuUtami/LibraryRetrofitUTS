package id.ac.polbeng.depandienda.libraryretrofituts

data class Product(
    val id: Int,
    val title: String,
    val description: String,
    val category: String,
    val price: Double,
    val discountPercentage: Double,
    val rating: Double,
    val stock: Int,
    val tags: List<String>?,
    val brand: String?,
    val sku: String?,
    val weight: Double,
    val dimensions: Dimensions?,
    val warrantyInformation: String?,
    val shippingInformation: String?,
    val availabilityStatus: String?,
    val reviews: List<Review>?,
    val returnPolicy: String?,
    val minimumOrderQuantity: Int?,
    val images: List<String>?,
    val thumbnail: String?
)
