package com.umeshsingh.bookhub.fragment

import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.umeshsingh.bookhub.R
import com.umeshsingh.bookhub.adapter.FavouriteRecyclerAdapter
import com.umeshsingh.bookhub.database.BookDatabase
import com.umeshsingh.bookhub.database.BookEntity
import com.umeshsingh.bookhub.model.Book


class FavouritesFragment : Fragment() {

    lateinit var recyclerFavourite: RecyclerView
    lateinit var progressLayout: RelativeLayout
    lateinit var progressBar: ProgressBar
    lateinit var layoutManager: RecyclerView.LayoutManager
    lateinit var recyclerAdapter: FavouriteRecyclerAdapter
//    var dbBookList = listOf<BookEntity>()
    companion object{
        var dbBookList = ArrayList<Book>()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_favourites, container, false)

        recyclerFavourite = view.findViewById(R.id.RecyclerFavourite)

        progressLayout = view.findViewById(R.id.ProgressLayout)

        progressBar = view.findViewById(R.id.ProgressBar)

        progressLayout.visibility = View.VISIBLE

        layoutManager = GridLayoutManager(requireContext(), 2)

//        dbBookList = RetrieveFavourites(requireContext()).execute().get()

        recyclerAdapter = FavouriteRecyclerAdapter(requireContext(), dbBookList)

        recyclerFavourite.adapter = recyclerAdapter

        recyclerFavourite.layoutManager = layoutManager
        

        if (activity != null) {

            progressLayout.visibility = View.GONE

        }

        return view
    }

    class RetrieveFavourites(val context: Context) : AsyncTask<Void, Void, List<BookEntity>>() {
        override fun doInBackground(vararg p0: Void?): List<BookEntity> {

            val db = Room.databaseBuilder(context, BookDatabase::class.java, "book-db").build()

            return db.bookDao().getAllBooks()
        }

    }

}