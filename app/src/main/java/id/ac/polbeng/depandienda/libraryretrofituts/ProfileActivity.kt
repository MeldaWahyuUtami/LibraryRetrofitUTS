package id.ac.polbeng.depandienda.libraryretrofituts

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import id.ac.polbeng.depandienda.libraryretrofituts.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Profil Developer"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Set developer profile data (Requirement H)
        binding.apply {
            tvName.text = "Depan Dienda" // Ganti dengan nama kamu
            tvEmail.text = "depandienda@polbeng.ac.id"
            tvPhone.text = "081234567890"
            tvProdi.text = "Teknik Rekayasa Perangkat Lunak"
            tvJurusan.text = "Teknik Informatika"
            tvUniversity.text = "Politeknik Negeri Bengkalis"
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
