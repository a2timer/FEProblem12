package com.cartelnetwork.feproblem1.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.RadioGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.cartelnetwork.feproblem1.BaseViewModel.ViewModelFactory
import com.cartelnetwork.feproblem1.R
import com.cartelnetwork.feproblem1.adapter.SpinnerAdapter
import com.cartelnetwork.feproblem1.adapter.SpinnerItem
import com.cartelnetwork.feproblem1.api.ApiHelper
import com.cartelnetwork.feproblem1.api.RetrofitBuilder
import com.cartelnetwork.feproblem1.databinding.SelectPlanetsToSearchFragmentBinding
import com.cartelnetwork.feproblem1.model.Planets
import com.cartelnetwork.feproblem1.model.Vehicles
import com.cartelnetwork.feproblem1.utils.Status
import android.widget.RadioButton
import com.cartelnetwork.feproblem1.model.FindRequestBody


class SelectPlanetsFragment : Fragment() {
    private lateinit var viewModel: MainViewModel
    private lateinit var binder: SelectPlanetsToSearchFragmentBinding
    lateinit var adapter: SpinnerAdapter
    var spinnerList: ArrayList<SpinnerItem> = ArrayList()
    var spinnerListTwo: ArrayList<SpinnerItem> = ArrayList()
    var spinnerListThree: ArrayList<SpinnerItem> = ArrayList()
    var spinnerListFour: ArrayList<SpinnerItem> = ArrayList()
    lateinit var destinationOnePlanetDetails : Planets
    lateinit var destinationOneVehicleDetails : Vehicles
    lateinit var destinationTwoPlanetDetails : Planets
    lateinit var destinationTwoVehicleDetails : Vehicles
    lateinit var destinationThreePlanetDetails : Planets
    lateinit var destinationThreeVehicleDetails : Vehicles
    lateinit var destinationFourPlanetDetails : Planets
    lateinit var destinationFourVehicleDetails : Vehicles

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binder =
            DataBindingUtil.inflate(
                inflater,
                R.layout.select_planets_to_search_fragment,
                container,
                false
            )

        viewModel = ViewModelProviders.of(
            requireActivity(),
            ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        ).get(MainViewModel::class.java)


        binder.viewModel = viewModel

        initialDetails()

        viewModel.planetsDetailsList.observe(viewLifecycleOwner, Observer {
            if (it.size > 0) {

                for (value in it) {
                    Log.d("TAG", "" + value.name)
                    if (spinnerList.size == 0) {
                        spinnerList.add(SpinnerItem("Select"))
                    }
                    spinnerList.add(SpinnerItem(value.name))

                }

                updateAdpter()
            }
        })

        binder.txtTotalTakenTitle.append("0")

        spinnerItemSelect()
        radioGroupCheck()
        findFalconeCheck()
        return binder.root
    }

    private fun findFalconeCheck() {
        binder.btnFindFalcone.setOnClickListener {
            if (::destinationOnePlanetDetails.isInitialized &&
                ::destinationTwoPlanetDetails.isInitialized &&
                ::destinationThreePlanetDetails.isInitialized &&
                ::destinationFourPlanetDetails.isInitialized &&
                ::destinationOneVehicleDetails.isInitialized &&
                ::destinationTwoVehicleDetails.isInitialized &&
                ::destinationThreeVehicleDetails.isInitialized &&
                ::destinationFourVehicleDetails.isInitialized
            ) {

                val planets : ArrayList<String> = ArrayList()
                planets.add(destinationOnePlanetDetails.name)
                planets.add(destinationTwoPlanetDetails.name)
                planets.add(destinationThreePlanetDetails.name)
                planets.add(destinationFourPlanetDetails.name)

                val vehicles : ArrayList<String> = ArrayList()
                vehicles.add(destinationOneVehicleDetails.name)
                vehicles.add(destinationTwoVehicleDetails.name)
                vehicles.add(destinationThreeVehicleDetails.name)
                vehicles.add(destinationFourVehicleDetails.name)
                val request = FindRequestBody(viewModel.token,planets,vehicles)

                viewModel.find(request).observe(viewLifecycleOwner, Observer {
                    Log.d("TAG"," "+it)
                    it?.let { resource ->
                        if (resource.status == Status.SUCCESS)
                           Log.d("TAG"," "+it)
                    }
                })
            }
        }
   }

    private fun radioGroupCheck() {
        binder.radioGroupDestinationOne.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { group, checkedId ->

            if (checkedId == -1) {
            } else {

                val adapter = SpinnerAdapter(requireContext(), 2, spinnerListTwo)
                binder.spinerPlanetTwo.adapter = adapter

                when (checkedId) {
                    R.id.radio_vehicle_space_pod -> {
                        viewModel.totalTakenDestinationOne =
                            viewModel.planetsDetailsList.value!!.get(viewModel.destinationOnePlanet.value!! - 1).distance / 2

                        destinationOnePlanetDetails =
                            viewModel.planetsDetailsList.value!!.get(viewModel.destinationOnePlanet.value!! - 1)

                        destinationOneVehicleDetails =
                            viewModel.vehiclesDetailsList.value!!.get(0)


                        addTotalTakenTime()
                    }
                    R.id.radio_vehicle_space_rocket -> {
                        Log.d("TAG", " Space rocket ")
                        viewModel.totalTakenDestinationOne =
                            viewModel.planetsDetailsList.value!!.get(viewModel.destinationOnePlanet.value!! - 1).distance / 4
                        destinationOnePlanetDetails =
                            viewModel.planetsDetailsList.value!!.get(viewModel.destinationOnePlanet.value!! - 1)
                        destinationOneVehicleDetails =
                            viewModel.vehiclesDetailsList.value!!.get(1)
                        addTotalTakenTime()
                    }
                    R.id.radio_vehicle_space_shuttle -> {
                        Log.d("TAG", " Space shuttle ")
                        viewModel.totalTakenDestinationOne =
                            viewModel.planetsDetailsList.value!!.get(viewModel.destinationOnePlanet.value!! - 1).distance / 5
                        destinationOnePlanetDetails =
                            viewModel.planetsDetailsList.value!!.get(viewModel.destinationOnePlanet.value!! - 1)
                        destinationOneVehicleDetails =
                            viewModel.vehiclesDetailsList.value!!.get(2)
                        addTotalTakenTime()
                    }
                    R.id.radio_vehicle_space_ship -> {
                        Log.d("TAG", " Space ship ")
                        viewModel.totalTakenDestinationOne =
                            viewModel.planetsDetailsList.value!!.get(viewModel.destinationOnePlanet.value!! - 1).distance / 10
                        destinationOnePlanetDetails =
                            viewModel.planetsDetailsList.value!!.get(viewModel.destinationOnePlanet.value!! - 1)
                        destinationOneVehicleDetails =
                            viewModel.vehiclesDetailsList.value!!.get(3)
                        addTotalTakenTime()
                    }
                }
            }
        })
        binder.radioGroupDestinationTwo.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { group, checkedId ->

            if (checkedId == -1) {
            } else {

                val adapter = SpinnerAdapter(requireContext(), 3, spinnerListThree)
                binder.spinerPlanetThree.adapter = adapter

                when (checkedId) {
                    R.id.radio_vehicle_space_pod_two -> {
                        viewModel.totalTakenDestinationTwo =
                                viewModel.planetsDetailsListTwo.value!!.get(viewModel.destinationTwoPlanet.value!! - 1).distance / 2
                        destinationTwoPlanetDetails =
                            viewModel.planetsDetailsListTwo.value!!.get(viewModel.destinationTwoPlanet.value!! - 1)
                        destinationTwoVehicleDetails =
                            viewModel.vehiclesDetailsList.value!!.get(0)
                        addTotalTakenTime()
                    }
                    R.id.radio_vehicle_space_rocket_two -> {
                        Log.d("TAG", " Space rocket ")
                        viewModel.totalTakenDestinationTwo =
                            viewModel.planetsDetailsListTwo.value!!.get(viewModel.destinationTwoPlanet.value!! - 1).distance / 4
                        destinationTwoPlanetDetails =
                            viewModel.planetsDetailsListTwo.value!!.get(viewModel.destinationTwoPlanet.value!! - 1)
                        destinationTwoVehicleDetails =
                            viewModel.vehiclesDetailsList.value!!.get(1)
                        addTotalTakenTime()
                    }
                    R.id.radio_vehicle_space_shuttle_two -> {
                        Log.d("TAG", " Space shuttle ")
                        viewModel.totalTakenDestinationTwo =
                            viewModel.planetsDetailsListTwo.value!!.get(viewModel.destinationTwoPlanet.value!! - 1).distance / 5
                        destinationTwoPlanetDetails =
                            viewModel.planetsDetailsListTwo.value!!.get(viewModel.destinationTwoPlanet.value!! - 1)
                        destinationTwoVehicleDetails =
                            viewModel.vehiclesDetailsList.value!!.get(2)
                        viewModel.totalTakenTime.value=  viewModel.totalTakenDestinationOne + viewModel.totalTakenDestinationTwo+viewModel.totalTakenDestinationThree+viewModel.totalTakenDestinationFour
                        binder.txtTotalTakenTitle.text = "Total taken :"+viewModel.totalTakenTime.value
                    }
                    R.id.radio_vehicle_space_ship_two -> {
                        Log.d("TAG", " Space ship ")
                        viewModel.totalTakenDestinationTwo =
                            viewModel.planetsDetailsListTwo.value!!.get(viewModel.destinationTwoPlanet.value!! - 1).distance / 10
                        destinationTwoPlanetDetails =
                            viewModel.planetsDetailsListTwo.value!!.get(viewModel.destinationTwoPlanet.value!! - 1)
                        destinationTwoVehicleDetails =
                            viewModel.vehiclesDetailsList.value!!.get(3)
                        addTotalTakenTime()
                    }
                }
            }
        })
        binder.radioGroupDestinationThree.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { group, checkedId ->

            if (checkedId == -1) {
            } else {

                val adapter = SpinnerAdapter(requireContext(), 3, spinnerListFour)
                binder.spinerPlanetFour.adapter = adapter

                when (checkedId) {
                    R.id.radio_vehicle_space_pod_three -> {
                        viewModel.totalTakenDestinationThree =
                                viewModel.planetsDetailsListThree.value!!.get(viewModel.destinationThreePlanet.value!! - 1).distance / 2
                        destinationThreePlanetDetails =
                            viewModel.planetsDetailsListThree.value!!.get(viewModel.destinationThreePlanet.value!! - 1)
                        destinationThreeVehicleDetails =
                            viewModel.vehiclesDetailsList.value!!.get(0)
                        addTotalTakenTime()
                    }
                    R.id.radio_vehicle_space_rocket_three -> {
                        Log.d("TAG", " Space rocket ")
                        viewModel.totalTakenDestinationThree =
                            viewModel.planetsDetailsListThree.value!!.get(viewModel.destinationThreePlanet.value!! - 1).distance / 4
                        destinationThreePlanetDetails =
                            viewModel.planetsDetailsListThree.value!!.get(viewModel.destinationThreePlanet.value!! - 1)
                        destinationThreeVehicleDetails =
                            viewModel.vehiclesDetailsList.value!!.get(1)
                        addTotalTakenTime()
                    }
                    R.id.radio_vehicle_space_shuttle_three -> {
                        Log.d("TAG", " Space shuttle ")
                        viewModel.totalTakenDestinationThree =
                            viewModel.planetsDetailsListThree.value!!.get(viewModel.destinationThreePlanet.value!! - 1).distance / 5
                        destinationThreePlanetDetails =
                            viewModel.planetsDetailsListThree.value!!.get(viewModel.destinationThreePlanet.value!! - 1)
                        destinationThreeVehicleDetails =
                            viewModel.vehiclesDetailsList.value!!.get(2)
                        addTotalTakenTime()
                    }
                    R.id.radio_vehicle_space_ship_three -> {
                        Log.d("TAG", " Space ship "+ viewModel.planetsDetailsListThree.value+" "+viewModel.destinationTwoPlanet.value)
                        viewModel.totalTakenDestinationThree=
                            viewModel.planetsDetailsListThree.value!!.get(viewModel.destinationThreePlanet.value!! - 1).distance / 10
                        destinationThreePlanetDetails =
                            viewModel.planetsDetailsListThree.value!!.get(viewModel.destinationThreePlanet.value!! - 1)
                        destinationThreeVehicleDetails =
                            viewModel.vehiclesDetailsList.value!!.get(3)
                        addTotalTakenTime()
                    }
                }

            }
        })
        binder.radioGroupDestinationFour.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { group, checkedId ->

            if (checkedId == -1) {
            } else {



                when (checkedId) {
                    R.id.radio_vehicle_space_pod_four -> {
                        viewModel.totalTakenDestinationFour =
                                viewModel.planetsDetailsListFour.value!!.get(viewModel.destinationFourPlanet.value!! - 1).distance / 2
                        destinationFourPlanetDetails =
                            viewModel.planetsDetailsListFour.value!!.get(viewModel.destinationFourPlanet.value!! - 1)
                        destinationFourVehicleDetails =
                            viewModel.vehiclesDetailsList.value!!.get(0)
                        addTotalTakenTime()
                    }
                    R.id.radio_vehicle_space_rocket_four -> {
                        Log.d("TAG", " Space rocket ")
                        viewModel.totalTakenDestinationFour=
                                viewModel.planetsDetailsListFour.value!!.get(viewModel.destinationFourPlanet.value!! - 1).distance / 4
                        destinationFourPlanetDetails =
                            viewModel.planetsDetailsListFour.value!!.get(viewModel.destinationFourPlanet.value!! - 1)
                        destinationFourVehicleDetails =
                            viewModel.vehiclesDetailsList.value!!.get(1)
                        viewModel.totalTakenTime.value=  viewModel.totalTakenDestinationOne + viewModel.totalTakenDestinationTwo+viewModel.totalTakenDestinationThree+viewModel.totalTakenDestinationFour
                        binder.txtTotalTakenTitle.text = "Total taken :"+viewModel.totalTakenTime.value
                    }
                    R.id.radio_vehicle_space_shuttle_four -> {
                        Log.d("TAG", " Space shuttle ")
                        viewModel.totalTakenDestinationFour =
                                viewModel.planetsDetailsListFour.value!!.get(viewModel.destinationFourPlanet.value!! - 1).distance / 5
                        destinationFourPlanetDetails =
                            viewModel.planetsDetailsListFour.value!!.get(viewModel.destinationFourPlanet.value!! - 1)
                        destinationFourVehicleDetails =
                            viewModel.vehiclesDetailsList.value!!.get(2)
                        addTotalTakenTime()
                    }
                    R.id.radio_vehicle_space_ship_four -> {
                        Log.d("TAG", " Space ship ")
                        viewModel.totalTakenDestinationFour =
                                viewModel.planetsDetailsListFour.value!!.get(viewModel.destinationFourPlanet.value!! - 1).distance / 10
                        destinationFourPlanetDetails =
                            viewModel.planetsDetailsListFour.value!!.get(viewModel.destinationFourPlanet.value!! - 1)
                        destinationFourVehicleDetails =
                            viewModel.vehiclesDetailsList.value!!.get(3)
                        addTotalTakenTime()
                    }
                }
            }
        })

    }

    private fun spinnerItemSelect() {

        binder.spinerPlanetOne.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {

                }

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    binder.radioGroupDestinationOne.clearCheck()
                    viewModel.destinationOnePlanet.value = position

                    if (position > 0) {
                        spinnerListTwo = ArrayList()
                        val planetDetailsTwo: ArrayList<Planets> = ArrayList()
                        for (size in viewModel.planetsDetailsList.value!!) {
                            if (size == viewModel.planetsDetailsList.value!!.get(viewModel.destinationOnePlanet.value!! - 1)) {

                            } else
                                planetDetailsTwo.add(size)
                        }

                        viewModel.planetsDetailsListTwo.value = planetDetailsTwo
                        spinnerListTwo.add(0, SpinnerItem("Select"))
                        for (planet in viewModel.planetsDetailsListTwo.value!!) {
                            spinnerListTwo.add(SpinnerItem(planet.name))
                        }
                    }else{
                        viewModel.totalTakenDestinationOne = 0
                        addTotalTakenTime()
                        spinnerListTwo = ArrayList()
                        spinnerListTwo.add(SpinnerItem("Select"))
                        val adapter = SpinnerAdapter(requireContext(), 2, spinnerListTwo)
                        binder.spinerPlanetTwo.adapter = adapter
                    }
                }

            }
        binder.spinerPlanetTwo.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {

                }

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    Log.d("TAG", " p2 " + position + " p3 " + id)
                    binder.radioGroupDestinationTwo.clearCheck()
                    viewModel.destinationTwoPlanet.value = position

                    if (position > 0) {
                        spinnerListThree = ArrayList()
                        val planetDetailsThree: ArrayList<Planets> = ArrayList()
                        for (size in viewModel.planetsDetailsListTwo.value!!) {
                            if (size == viewModel.planetsDetailsListTwo.value!!.get(viewModel.destinationTwoPlanet.value!! - 1)) {

                            } else
                                planetDetailsThree.add(size)
                        }

                        viewModel.planetsDetailsListThree.value = planetDetailsThree
                        spinnerListThree.add(0, SpinnerItem("Select"))
                        for (planet in viewModel.planetsDetailsListThree.value!!) {
                            spinnerListThree.add(SpinnerItem(planet.name))
                        }
                    }else{
                        viewModel.totalTakenDestinationTwo = 0
                        addTotalTakenTime()
                        spinnerListThree = ArrayList()
                        spinnerListThree.add(SpinnerItem("Select"))
                        val adapter = SpinnerAdapter(requireContext(), 3, spinnerListThree)
                        binder.spinerPlanetThree.adapter = adapter
                    }
                }


            }
        binder.spinerPlanetThree.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {

                }

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    Log.d("TAG", " p2 " + position + " p3 " + id)
                    binder.radioGroupDestinationThree.clearCheck()
                    viewModel.destinationThreePlanet.value = position

                    if (position > 0) {
                        spinnerListFour = ArrayList()
                        val planetDetailsFour: ArrayList<Planets> = ArrayList()
                        for (size in viewModel.planetsDetailsListThree.value!!) {
                            if (size == viewModel.planetsDetailsListThree.value!!.get(viewModel.destinationTwoPlanet.value!! - 1)) {

                            } else
                                planetDetailsFour.add(size)
                        }

                        viewModel.planetsDetailsListFour.value = planetDetailsFour
                        spinnerListFour.add(0, SpinnerItem("Select"))
                        for (planet in viewModel.planetsDetailsListFour.value!!) {
                            spinnerListFour.add(SpinnerItem(planet.name))
                        }
                    }else{
                        viewModel.totalTakenDestinationThree = 0
                        addTotalTakenTime()
                        spinnerListFour = ArrayList()
                        spinnerListFour.add(SpinnerItem("Select"))
                        val adapter = SpinnerAdapter(requireContext(), 4, spinnerListFour)
                        binder.spinerPlanetFour.adapter = adapter
                    }
                }

            }
        binder.spinerPlanetFour.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {

                }

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    Log.d("TAG", " p2 " + position + " p3 " + id)
                    binder.radioGroupDestinationFour.clearCheck()
                    viewModel.destinationFourPlanet.value = position
                }

            }
    }

    private fun updateAdpter() {
        adapter = SpinnerAdapter(requireContext(), 1, spinnerList)
        binder.spinerPlanetOne.adapter = adapter

    }


    private fun initialDetails() {
        viewModel.getUsers().observe(viewLifecycleOwner, Observer {
            it?.let { resource ->
                if (resource.status == Status.SUCCESS)
                    viewModel.token = it.data!!.token
            }
        })

        viewModel.getPlanets().observe(viewLifecycleOwner, Observer {
            it?.let { resource ->
                Log.d("TAG", " " + it)
                if (resource.status == Status.SUCCESS) {
                    val planetDetails: ArrayList<Planets> = ArrayList()
                    for (size in resource.data!!) {
                        planetDetails.add(size)
                    }
                    viewModel.planetsDetailsList.value = planetDetails
                }
            }
        })
        viewModel.getVehicle().observe(viewLifecycleOwner, Observer {
            it?.let { resource ->
                Log.d("TAG", " " + it)
                if (resource.status == Status.SUCCESS) {
                    val vehicleDetails: ArrayList<Vehicles> = ArrayList()
                    for (size in resource.data!!) {
                        vehicleDetails.add(size)
                    }
                    viewModel.vehiclesDetailsList.value = vehicleDetails
                }
            }
        })

        viewModel.destinationOnePlanet.observe(viewLifecycleOwner, Observer {
            Log.d("TAG", "dest 1 " + it)
            if (it > 0) {
                binder.radioGroupDestinationOne.visibility = View.VISIBLE
                hideRadioOption(
                    viewModel.planetsDetailsList.value!!.get(viewModel.destinationOnePlanet.value!! - 1).name,
                    viewModel.planetsDetailsList.value!!.get(viewModel.destinationOnePlanet.value!! - 1).distance,
                    binder.radioVehicleSpacePod,
                    binder.radioVehicleSpaceRocket,
                    binder.radioVehicleSpaceShuttle
                )
            } else {
                binder.radioGroupDestinationOne.visibility = View.GONE
                binder.radioGroupDestinationOne.clearCheck()
            }
        })

        viewModel.destinationTwoPlanet.observe(viewLifecycleOwner, Observer {
            Log.d("TAG", "dest 1 " + it)
            if (it > 0) {
                binder.radioGroupDestinationTwo.visibility = View.VISIBLE
                hideRadioOption(
                    viewModel.planetsDetailsListTwo.value!!.get(viewModel.destinationTwoPlanet.value!! - 1).name,
                    viewModel.planetsDetailsListTwo.value!!.get(viewModel.destinationTwoPlanet.value!! - 1).distance,
                    binder.radioVehicleSpacePodTwo,
                    binder.radioVehicleSpaceRocketTwo,
                    binder.radioVehicleSpaceShuttleTwo
                )

            } else {
                binder.radioGroupDestinationTwo.visibility = View.GONE
                binder.radioGroupDestinationTwo.clearCheck()
            }
        })
        viewModel.destinationThreePlanet.observe(viewLifecycleOwner, Observer {
            Log.d("TAG", "dest 1 " + it)
            if (it > 0) {
                binder.radioGroupDestinationThree.visibility = View.VISIBLE
                hideRadioOption(
                    viewModel.planetsDetailsListThree.value!!.get(viewModel.destinationThreePlanet.value!! - 1).name,
                    viewModel.planetsDetailsListThree.value!!.get(viewModel.destinationThreePlanet.value!! - 1).distance,
                    binder.radioVehicleSpacePodThree,
                    binder.radioVehicleSpaceRocketThree,
                    binder.radioVehicleSpaceShuttleThree
                )


            } else {
                binder.radioGroupDestinationThree.visibility = View.GONE
                binder.radioGroupDestinationThree.clearCheck()
            }
        })
        viewModel.destinationFourPlanet.observe(viewLifecycleOwner, Observer {
            Log.d("TAG", "dest 1 " + it)
            if (it > 0) {
                binder.radioGroupDestinationFour.visibility = View.VISIBLE
                hideRadioOption(
                    viewModel.planetsDetailsListFour.value!!.get(viewModel.destinationFourPlanet.value!! - 1).name,
                    viewModel.planetsDetailsListFour.value!!.get(viewModel.destinationFourPlanet.value!! - 1).distance,
                    binder.radioVehicleSpacePodFour,
                    binder.radioVehicleSpaceRocketFour,
                    binder.radioVehicleSpaceShuttleFour
                )


            } else {
                binder.radioGroupDestinationFour.visibility = View.GONE
                binder.radioGroupDestinationFour.clearCheck()
            }
        })
    }

    fun hideRadioOption(
        planetName: String,
        distence: Int,
        radioOne: RadioButton,
        radioTwo: RadioButton,
        radioThree: RadioButton
    ) {
        when (distence) {
            100, 200 -> {
                radioOne.visibility = View.VISIBLE
                radioTwo.visibility = View.VISIBLE
                radioThree.visibility = View.VISIBLE

            }
            300 -> {
                radioOne.visibility = View.GONE
            }
            400 -> {
                radioOne.visibility = View.GONE
                radioTwo.visibility = View.GONE
            }
            500, 600 -> {
                radioOne.visibility = View.GONE
                radioTwo.visibility = View.GONE
                radioThree.visibility = View.GONE
            }
        }
    }


    fun addTotalTakenTime(){
        viewModel.totalTakenTime.value=  viewModel.totalTakenDestinationOne + viewModel.totalTakenDestinationTwo+viewModel.totalTakenDestinationThree+viewModel.totalTakenDestinationFour
        binder.txtTotalTakenTitle.text = "Total taken :"+viewModel.totalTakenTime.value
    }



}