package com.umeshsingh.bookhub.activity

import android.os.Bundle
import android.view.MenuItem
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.umeshsingh.bookhub.*
import com.umeshsingh.bookhub.fragment.AboutFragment
import com.umeshsingh.bookhub.fragment.DashboardFragment
import com.umeshsingh.bookhub.fragment.FavouritesFragment
import com.umeshsingh.bookhub.fragment.ProfileFragment

class MainActivity : AppCompatActivity() {

    lateinit var drawerLayout: DrawerLayout
    lateinit var coordinatorLayout: CoordinatorLayout
    lateinit var toolbar: androidx.appcompat.widget.Toolbar
    lateinit var frameLayout: FrameLayout
    lateinit var navigationView: NavigationView

    var previousMenuItem: MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //FavouritesFragment().setBackgroundColor(Color.RED)

        drawerLayout = findViewById(R.id.DrawerLayout)
        coordinatorLayout = findViewById(R.id.CoordinatorLayout)
        toolbar = findViewById(R.id.Toolbar)
        frameLayout = findViewById(R.id.FrameLayout)
        navigationView = findViewById(R.id.NavigationView)

        setUpToolbar()

        openDashboard()

        val actionBarDrawerToggle = ActionBarDrawerToggle(this@MainActivity, drawerLayout,
            R.string.open_drawer,
            R.string.close_drawer
        )

        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        navigationView.setNavigationItemSelectedListener {

            if(previousMenuItem != null){
                previousMenuItem?.isChecked = false
            }

            it.isCheckable = true
            it.isChecked = true
            previousMenuItem = it

            when(it.itemId){
                R.id.Dashboard -> {
                    openDashboard()

                    drawerLayout.closeDrawers()

                    Toast.makeText(this@MainActivity, "Clicked on Dashboard", Toast.LENGTH_SHORT).show()
                }
                R.id.Profile -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.FrameLayout, ProfileFragment())
                        .commit()

                    supportActionBar?.title = "Profile"
                    drawerLayout.closeDrawers()

                    Toast.makeText(this@MainActivity, "Clicked on Profile", Toast.LENGTH_SHORT).show()
                }
                R.id.Favourites -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.FrameLayout, FavouritesFragment())
                        .commit()

                    supportActionBar?.title = "Favourites"
                    drawerLayout.closeDrawers()

                    //RecyclerFavorite.adapter = ContactAdapter(this, )

                    Toast.makeText(this@MainActivity, "Clicked on Favourites", Toast.LENGTH_SHORT).show()
                }
                R.id.About -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.FrameLayout, AboutFragment())
                        .commit()

                    supportActionBar?.title = "About"
                    drawerLayout.closeDrawers()

                    Toast.makeText(this@MainActivity, "Clicked on About", Toast.LENGTH_SHORT).show()
                }
            }

            return@setNavigationItemSelectedListener true
        }

    }

    fun setUpToolbar(){
        setSupportActionBar(toolbar)
        supportActionBar?.title= "Book Mart"
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id = item.itemId

        if(id==android.R.id.home){
            drawerLayout.openDrawer(GravityCompat.START)
        }
        return super.onOptionsItemSelected(item)
    }

    fun openDashboard(){
        val fragement = DashboardFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.FrameLayout, fragement)
        transaction.commit()
        supportActionBar?.title = "Dashboard"
        navigationView.setCheckedItem(R.id.Dashboard)
    }

    override fun onBackPressed() {
        val frag = supportFragmentManager.findFragmentById(R.id.FrameLayout)

        when(frag){
           !is DashboardFragment -> openDashboard()

           else -> super.onBackPressed()
        }
    }
}