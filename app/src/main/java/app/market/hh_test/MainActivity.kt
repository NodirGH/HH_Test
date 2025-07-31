package app.market.hh_test

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import app.market.data.local.AppPreferences
import app.market.hh_test.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    @Inject
    lateinit var appPreferences: AppPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        binding.bnvMain.setupWithNavController(navHostFragment.navController)
        navController = navHostFragment.navController

        navHostFragment.navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.homeFragment,
                R.id.favoriteFragment,
                R.id.profileFragment -> {
                }
            }
        }

        if (appPreferences.isUserRegister) {
            setStartDestination(R.id.homeFragment)
        } else {
            setStartDestination(R.id.loginFragment)
        }
    }

    private fun setStartDestination(destinationId: Int) {
        val navGraph = navController.navInflater.inflate(R.navigation.main_navigation)
        navGraph.setStartDestination(destinationId)
        navController.graph = navGraph
    }
}