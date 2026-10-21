package id.ac.polbeng.depandienda.libraryretrofituts

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import id.ac.polbeng.depandienda.libraryretrofituts.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Daftar Produk (25 Items)"

        binding.rvProducts.layoutManager = LinearLayoutManager(this)

        fetchProducts()
    }

    private fun fetchProducts() {
        binding.progressBar.visibility = View.VISIBLE
        lifecycleScope.launch {
            try {
                // Fetch 25 products as requested in requirement b
                val response = RetrofitClient.instance.getProducts(25)
                binding.progressBar.visibility = View.GONE

                val products = response.products
                val adapter = ProductAdapter(products) { product ->
                    // On item click -> open DetailActivity
                    val intent = Intent(this@MainActivity, DetailActivity::class.java)
                    intent.putExtra("EXTRA_PRODUCT_ID", product.id)
                    startActivity(intent)
                }
                binding.rvProducts.adapter = adapter

            } catch (e: Exception) {
                binding.progressBar.visibility = View.GONE
                Toast.makeText(this@MainActivity, "Error: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_profile -> {
                // Open ProfileActivity (Requirement d & e)
                val intent = Intent(this, ProfileActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
