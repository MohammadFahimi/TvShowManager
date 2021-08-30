package com.mfahimi.tvshowmanager.ui.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.android.material.datepicker.MaterialDatePicker
import com.mfahimi.domain.model.input.NewTvShowInput
import com.mfahimi.tvshowmanager.R
import com.mfahimi.tvshowmanager.databinding.FragmentAddNewTvShowBinding
import com.mfahimi.tvshowmanager.ext.*
import com.mfahimi.tvshowmanager.viewmodel.TvShowViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*

class AddNewTvShowFragment : BaseFragment<FragmentAddNewTvShowBinding>() {
    override val viewBinding: FragmentAddNewTvShowBinding by lazy { FragmentAddNewTvShowBinding.inflate(layoutInflater) }
    private val viewModel: TvShowViewModel by viewModel()
    private var releaseDate: Date? = null

    override fun initObjects(savedInstanceState: Bundle?) {
        super.initObjects(savedInstanceState)
        viewModel.createNewShowObservable.handleApiResponse(this,
            onLoad = {
                binding.btnSaveTvShow.disable()
                binding.progressBar.visible()
            },
            onSuccess = {
                Toast.makeText(requireContext(), getString(R.string.tmpl_tvshow_added_success, it.title), Toast.LENGTH_LONG).show()
                findNavController().popBackStack()
            },
            onError = {
                binding.btnSaveTvShow.enable()
                binding.progressBar.gone()
                Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
            }
        )

    }

    override fun initViews(view: View, savedInstanceState: Bundle?) = with(binding) {
        tvDate.setOnClickListener { selectDate() }
        btnSaveTvShow.setOnClickListener { if (isValidInput()) createNewTvShow() }
    }

    private fun selectDate() {
        val calendar = Calendar.getInstance()
        val datePicker =
            MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select date")
                .build()
        datePicker.addOnPositiveButtonClickListener {
            calendar.time.time = it
            releaseDate = calendar.time
            binding.tvDate.text = calendar.time.toString()
        }
        datePicker.show(childFragmentManager, "")

    }

    private fun isValidInput(): Boolean {
        var isValid = true
        with(binding) {
            if (tilTvSeasons.editText!!.text.isNullOrEmpty()) {
                tilTvSeasons.error = getString(R.string.err_enter_seaon)
                isValid = false
            } else {
                tilTvSeasons.isErrorEnabled = false
            }
            if (tilTvShow.editText!!.text.isNullOrEmpty()) {
                tilTvShow.error = getString(R.string.err_enter_title)
                isValid = false
            } else {
                tilTvShow.isErrorEnabled = false
            }
            if (releaseDate == null)
                isValid = false
        }
        return isValid
    }

    private fun createNewTvShow() {
        val tvShow = NewTvShowInput(
            title = binding.tilTvShow.editText!!.text.toString(),
            releaseDate = releaseDate!!.toString(),
            seasons = binding.tilTvSeasons.editText!!.text.toString().toDouble()
        )
        viewModel.startSession(tvShow)
    }

}