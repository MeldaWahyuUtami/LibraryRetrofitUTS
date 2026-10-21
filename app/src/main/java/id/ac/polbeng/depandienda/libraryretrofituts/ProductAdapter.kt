package id.ac.polbeng.depandienda.libraryretrofituts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.ac.polbeng.depandienda.libraryretrofituts.databinding.ItemProductBinding

class ProductAdapter(
    private val productList: List<Product>,
    private val onItemClick: (Product) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]
        holder.binding.apply {
            tvTitle.text = product.title
            tvPrice.text = "$${product.price}"
            tvStock.text = "Stock: ${product.stock}"
            tvRating.text = "★ ${product.rating}"

            // Load thumbnail using Glide
            Glide.with(ivThumbnail.context)
                .load(product.thumbnail)
                .placeholder(R.mipmap.ic_launcher)
                .into(ivThumbnail)

            root.setOnClickListener {
                onItemClick(product)
            }
        }
    }

    override fun getItemCount(): Int = productList.size
}
