package com.fatihb.countries.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.fatihb.countries.R
import com.fatihb.countries.databinding.FragmentInfoOfCountryBinding
import com.fatihb.countries.util.downFromUrl
import com.fatihb.countries.util.placeHolderProgressBar
import com.fatihb.countries.viewModel.InfoViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_info_of_country.*


class InfoOfCountry : Fragment() {

    private lateinit var viewModel: InfoViewModel
    private var counUId = 0
    private lateinit var dataBinding : FragmentInfoOfCountryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_info_of_country,container,false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        arguments?.let {
            counUId = InfoOfCountryArgs.fromBundle(
                it
            ).countryuid
        }

        viewModel = ViewModelProviders.of(this).get(InfoViewModel::class.java)
        viewModel.getDataFromRoom(counUId)



        observeLiveData()
    }


    private fun observeLiveData(){
        viewModel.countryLiveData.observe(viewLifecycleOwner, Observer { country ->
            country?.let {

                dataBinding.selectedCountry = country


                /*
                countryName.text = country.countryName
                countryCapital.text = country.countryCapital
                countryCurrency.text = country.countryCurrency
                countryLanguage.text = country.countryLanguage
                countryRegion.text = country.countryRegion
                context?.let {
                    counImage.downFromUrl(country.countryUrl, placeHolderProgressBar(it))
                }
                 */
            }
        })
    }
}