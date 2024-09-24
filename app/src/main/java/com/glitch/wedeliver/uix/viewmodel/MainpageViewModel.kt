package com.glitch.wedeliver.uix.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.glitch.wedeliver.data.entity.FoodItem
import com.glitch.wedeliver.data.repo.FoodsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainpageViewModel @Inject constructor(var fRepo: FoodsRepository) : ViewModel() {
	var foodList = MutableLiveData<List<FoodItem>>()

	init {
		foodScan()
	}

	fun deleteCart(sepet_yemek_id: Int) {
		CoroutineScope(Dispatchers.Main).launch {
			fRepo.deleteCart(sepet_yemek_id = sepet_yemek_id, kullanici_adi = "irem_cingi")
		}
	}

	fun foodScan() {
		CoroutineScope(Dispatchers.Main).launch {
			foodList.value = fRepo.foodScan()
		}
	}

	fun saveCart(
		yemek_adi: String,
		yemek_resim_adi: String,
		yemek_fiyat: Int,
		yemek_siparis_adet: Int,
	) {
		CoroutineScope(Dispatchers.Main).launch {
			fRepo.saveCart(
				yemek_adi = yemek_adi,
				yemek_resim_adi = yemek_resim_adi,
				yemek_fiyat = yemek_fiyat,
				yemek_siparis_adet = yemek_siparis_adet,
				kullanici_adi = "irem_cingi"
			)
		}
	}
}