package com.cartelnetwork.feproblem1.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.cartelnetwork.feproblem1.R
import com.cartelnetwork.feproblem1.databinding.FindingFalconeResultFragmentBinding
import com.cartelnetwork.feproblem1.utils.replaceFragment

class FindingFalconResultFragment(val result : String?, val planet_name :String?,val total_taken : Int) : Fragment() {


    lateinit var binder : FindingFalconeResultFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binder =
            DataBindingUtil.inflate(
                inflater,
                R.layout.finding_falcone_result_fragment,
                container,
                false
            )

        return binder.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(result.equals("success")){
            binder.txtPlanetFound.visibility = View.VISIBLE
            binder.txtTotalTakenValue.visibility = View.VISIBLE
            binder.txtSelectPlanetTitle.text = resources.getString(R.string.success)
            binder.txtPlanetFound.append( planet_name)
            binder.txtTotalTakenValue.append(total_taken.toString())
            binder.btnTryAgain.text = resources.getString(R.string.finding_falcon)
        }else{
            binder.txtPlanetFound.visibility = View.GONE
            binder.txtTotalTakenValue.visibility = View.GONE
            binder.txtSelectPlanetTitle.text = resources.getString(R.string.failed)
            binder.btnTryAgain.text = resources.getString(R.string.try_again)
        }


        binder.btnTryAgain.setOnClickListener {
            (activity as MainActivity).replaceFragment(R.id.container,SelectPlanetsFragment(),false)
        }
        binder.txtHome.setOnClickListener {
            (activity as MainActivity).replaceFragment(R.id.container,SelectPlanetsFragment(),false)
        }
        binder.txtReset.setOnClickListener {
            (activity as MainActivity).replaceFragment(R.id.container,SelectPlanetsFragment(),false)
        }
    }
}