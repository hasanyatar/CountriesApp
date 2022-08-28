package com.example.countriesapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.countriesapp.R
import com.example.countriesapp.adapter.CountryAdapter
import com.example.countriesapp.viewmodel.FeedViewModel
import kotlinx.android.synthetic.main.fragment_feed.view.*


class FeedFragment : Fragment() {

    private lateinit var viewModel: FeedViewModel
    private val countryAdapter = CountryAdapter(arrayListOf())
    private lateinit var countryList: RecyclerView
    private lateinit var countryError: View
    private lateinit var countryLoading: View
    private lateinit var refreshLayout: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        refreshLayout = view.swipeRefreshLayout
        countryLoading = view.countryLoading
        countryError = view.countryError
        countryList = view.countryList
        viewModel = ViewModelProvider(this).get(FeedViewModel::class.java)
        viewModel.refreshData()

        view.countryList.layoutManager = LinearLayoutManager(context)
        countryList.adapter = countryAdapter
        view.swipeRefreshLayout.setOnRefreshListener {
            countryList.visibility = View.GONE
            countryError.visibility = View.GONE
            countryLoading.visibility = View.VISIBLE
            viewModel.refreshFromAPI()
            refreshLayout.isRefreshing = false
        }

        observeLiveData()

    }

    fun observeLiveData(){
        viewModel.countries.observe(viewLifecycleOwner,Observer{ countries ->
            countries?.let {
                countryList.visibility = View.VISIBLE
                countryAdapter.updateCountryList(countries)
            }
        })
        viewModel.countryError.observe(viewLifecycleOwner, Observer { error -> error?.let {
                if(it){
                countryError.visibility = View.VISIBLE
                }
                else{
                countryError.visibility = View.GONE
            }
        } }
        )

        viewModel.countryLoading.observe(viewLifecycleOwner, Observer { loading -> loading?.let{
                if(it){
                    countryLoading.visibility =View.VISIBLE
                    countryList.visibility = View.GONE
                    countryError.visibility = View.GONE
                }
                else{
                    countryLoading.visibility =View.GONE
                }
            } }
        )
    }


}