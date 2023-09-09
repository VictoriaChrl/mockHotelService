package com.example.mockhotelbookingservice.feature.hotel.room_booking.adapters

import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.mockhotelbookingservice.feature.hotel.room_booking.databinding.ItemGuestBinding
import com.example.mockhotelbookingservice.feature.hotel.room_booking.util.OnEditBackgroundChangedListener
import com.example.mockhotelbookingservice.shared.hotel.core.R
import com.example.mockhotelbookingservice.shared.hotel.core.R.drawable


class NewGuestHolder(
    private val binding: ItemGuestBinding
) : RecyclerView.ViewHolder(binding.root){

    fun bind(guest: Guest) {
        binding.apply {
            infoCustomerTitle.text = guest.title
            expandableIcon.setOnClickListener {
                if (hiddenView.visibility == View.VISIBLE) {
                    TransitionManager.beginDelayedTransition(infoCustomerCardView, AutoTransition())
                    hiddenView.visibility = View.GONE
                    expandableIcon.setImageResource(drawable.ic_arrow_down)
                } else {
                    TransitionManager.beginDelayedTransition(infoCustomerCardView, AutoTransition())
                    hiddenView.visibility = View.VISIBLE
                    expandableIcon.setImageResource(drawable.ic_arrow_up)
                }
            }
        }
    }
   fun onItemClick(): Boolean {
        binding.apply {
            val editTexts = arrayOf(editName, editSurname, editBirthday, editPassportLimit, editPassportNumber, editCitizenship)
            val inputBoxes = arrayOf(inputName, inputSurname, inputBirthday, inputPassportLimit, inputPassportNumber, inputCitizenship)

            for (i in editTexts.indices) {
                if (editTexts[i].text.isNullOrEmpty()) {
                    inputBoxes[i].setBoxBackgroundColorResource(R.color.edit_error_color)
                } else {
                    inputBoxes[i].setBoxBackgroundColorResource(R.color.edit_text_background_color)
                }
            }

            val isNameValid = !editName.text.isNullOrEmpty()
            val isSurnameValid = !editSurname.text.isNullOrEmpty()
            val isBirthdayValid = !editBirthday.text.isNullOrEmpty()
            val isPassportLimitValid = !editPassportLimit.text.isNullOrEmpty()
            val isPassportNumberValid = !editPassportNumber.text.isNullOrEmpty()
            val isCitizenshipValid = !editCitizenship.text.isNullOrEmpty()

            return isNameValid && isSurnameValid && isBirthdayValid && isPassportLimitValid && isPassportNumberValid && isCitizenshipValid
//            if (editName.text.isNullOrEmpty()) {
//                inputName.setBoxBackgroundColorResource(R.color.edit_error_color)
//            } else {
//                inputName.setBoxBackgroundColorResource(R.color.edit_text_background_color)
//            }
//            if (editSurname.text.isNullOrEmpty()) {
//                inputSurname.setBoxBackgroundColorResource(R.color.edit_error_color)
//            } else {
//                inputSurname.setBoxBackgroundColorResource(R.color.edit_text_background_color)
//            }
//            if (editBirthday.text.isNullOrEmpty()) {
//                inputBirthday.setBoxBackgroundColorResource(R.color.edit_error_color)
//            } else {
//                inputBirthday.setBoxBackgroundColorResource(R.color.edit_text_background_color)
//            }
//            if (editPassportLimit.text.isNullOrEmpty()) {
//                inputPassportLimit.setBoxBackgroundColorResource(R.color.edit_error_color)
//            } else {
//                inputPassportLimit.setBoxBackgroundColorResource(R.color.edit_text_background_color)
//            }
//            if (editPassportNumber.text.isNullOrEmpty()) {
//                inputPassportNumber.setBoxBackgroundColorResource(R.color.edit_error_color)
//            } else {
//                inputPassportNumber.setBoxBackgroundColorResource(R.color.edit_text_background_color)
//            }
//            if (editCitizenship.text.isNullOrEmpty()) {
//                inputCitizenship.setBoxBackgroundColorResource(R.color.edit_error_color)
//            } else {
//                inputCitizenship.setBoxBackgroundColorResource(R.color.edit_text_background_color)
            }

        }
    }


data class Guest(
    val id: Long, val title: String
)