package com.cartelnetwork.feproblem1.utils

import com.cartelnetwork.feproblem1.R

object Utils {
    fun getImageId(name : String) : Int {
        if (name.equals("Donlon")) {
            return R.drawable.donlon
        }else if (name.equals("Enchai")) {
            return R.drawable.enchai
        }
        else if (name.equals("Jebing")) {
            return R.drawable.jebing
        }
        else if (name.equals("Sapir")) {
            return R.drawable.sapirm
        }
        else if (name.equals("Lerbin")) {
            return R.drawable.lerbin
        }else  if(name.equals("Pingasor")) {
            return R.drawable.piong
        }else{
            return R.drawable.plan
        }


    }
}