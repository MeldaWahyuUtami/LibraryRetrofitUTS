package id.ac.polbeng.depandienda.libraryretrofituts

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import id.ac.polbeng.depandienda.libraryretrofituts.databinding.ActivityDetailBinding
import kotlinx.coroutines.launch

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private var productId: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Detail Product"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        productId = intent.getIntExtra("EXTRA_PRODUCT_ID", 1)

        fetchProductDetail(productId)
    }

    private fun fetchProductDetail(id: Int) {
        lifecycleScope.launch {
            try {
                val product = RetrofitClient.instance.getProductById(id)
                bindProductData(product)
            } catch (e: Exception) {
                Toast.makeText(this@DetailActivity, "Error: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun bindProductData(product: Product) {
        binding.apply {
            tvDetailTitle.text = product.title
            tvDetailPrice.text = "$${product.price}"
            tvDetailRating.text = "★ ${product.rating}"
            tvDetailCategory.text = "Category: ${product.category}"
            tvDetailStatus.text = "Status: ${product.availabilityStatus}"
            tvDetailStock.text = "Stock: ${product.stock}"
            tvDetailDescription.text = product.description

            val dim = product.dimensions
            tvDetailDimensions.text = "Dimensions: Width ${dim?.width}, Height ${dim?.height}, Depth ${dim?.depth}"
            tvDetailTags.text = "Tags: ${product.tags?.joinToString(", ") ?: "-"}"

            // Load product image (from images array or thumbnail)
            val imageUrl = product.images?.firstOrNull() ?: product.thumbnail
            Glide.with(this@DetailActivity)
                .load(imageUrl)
                .placeholder(R.mipmap.ic_launcher)
                .into(ivDetailImage)

            // Top Review (default 1 review)
            val topReview = product.reviews?.firstOrNull()
            if (topReview != null) {
                tvReviewerName.text = "${topReview.reviewerName} (${topReview.rating}★)"
                tvReviewComment.text = topReview.comment
            } else {
                tvReviewerName.text = "No Reviews"
                tvReviewComment.text = "-"
            }

            // Button to show all reviews in Dialog
            btnAllReviews.setOnClickListener {
                showAllReviewsDialog(product.reviews)
            }
        }
    }

    private fun showAllReviewsDialog(reviews: List<Review>?) {
        if (reviews.isNullOrEmpty()) {
            Toast.makeText(this, "Tidak ada review", Toast.LENGTH_SHORT).show()
            return
        }

        val reviewTexts = reviews.map { review ->
            "Name: ${review.reviewerName}\nEmail: ${review.reviewerEmail}\nRating: ${review.rating}★\nComment: ${review.comment}\nDate: ${review.date}"
        }.toTypedArray()

        AlertDialog.Builder(this)
            .setTitle("Semua Review (${reviews.size})")
            .setItems(reviewTexts) { dialog, _ ->
                dialog.dismiss()
            }
            .setPositiveButton("Tutup") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
